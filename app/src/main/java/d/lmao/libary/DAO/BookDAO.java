package d.lmao.libary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.database.DatabaseHelper;
import d.lmao.libary.model.Book;

public class BookDAO {
    private SQLiteDatabase db;
    public static final String TABLE_BOOK = "BOOK";
    public static final String BOOK_COLUMN_MASACH = "MaSach";
    public static final String BOOK_COLUMN_TENSACH = "TenSach";
    public static final String BOOK_COLUMN_GIA = "Gia";
    public static final String BOOK_COLUMN_SOLUONG = "SoLuong";
    public static final String BOOK_COLUMN_ANH="Image";
    public static final String CREATE_TABLE_BOOK = "Create table " + TABLE_BOOK + "("+
            BOOK_COLUMN_MASACH + " VARCHAR(50), "
            + BOOK_COLUMN_TENSACH + " VARCHAR(50), "
            + BOOK_COLUMN_GIA + " int, "
            + BOOK_COLUMN_SOLUONG + " INT, "+BOOK_COLUMN_ANH  +" BLOB )";
    public static final String TAG = "Book Dao";


    public BookDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertBook(Book book) {
        ContentValues values = new ContentValues();

        values.put(BOOK_COLUMN_MASACH, book.getMasach());
        values.put(BOOK_COLUMN_TENSACH, book.getTensach());
        values.put(BOOK_COLUMN_GIA,book.getGia());
        values.put(BOOK_COLUMN_SOLUONG, book.getSoluong());
        values.put(BOOK_COLUMN_ANH,book.getAnh());
        try {
            if (db.insert(TABLE_BOOK, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateBook(String masach, String tensach,String gia, String soluong, byte[] anh) {
        ContentValues values = new ContentValues();
        values.put(BOOK_COLUMN_MASACH,masach);
        values.put(BOOK_COLUMN_TENSACH, tensach);
        values.put(BOOK_COLUMN_GIA,gia);
        values.put(BOOK_COLUMN_SOLUONG,soluong);
        values.put(BOOK_COLUMN_ANH,anh);
        int result = db.update(TABLE_BOOK, values, "MaSach=?", new String[]{masach});
        if (result == 0) {
            return -1;
        }
        return 1;


    }

    public List<Book> getBook() {
        List<Book> listBook = new ArrayList<>();
        Cursor c = db.query(TABLE_BOOK,null, null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Book book=new Book();

            book.setMasach(c.getString(0));
            book.setTensach(c.getString(1));
            book.setGia(c.getString(2));
            book.setGia(c.getString(3));
            book.setAnh(c.getBlob(4));
            listBook.add(book);
            Log.d("//=====", book.toString());
            c.moveToNext();
        }
        c.close();
        return listBook;
    }
    public int deleteBook(String name) {
        int result = db.delete(TABLE_BOOK, "MaSach=?", new String[]{name});
        if (result == 0)
            return -1;
        return 1;
    }



}
