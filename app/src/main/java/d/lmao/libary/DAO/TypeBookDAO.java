package d.lmao.libary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.database.DatabaseHelper;
import d.lmao.libary.model.Book;
import d.lmao.libary.model.TypeBook;

public class TypeBookDAO {
    private SQLiteDatabase db;
    public static final String TABLE_TYPE_BOOK = "TYPEBOOK";

    public static final String BOOK_COLUMN_MA_LOAI_SACH = "MaLoaiSach";
    public static final String BOOK_COLUMN_TEN_LOAI_SACH = "TenLoaiSach";
    public static final String BOOK_COLUMN_TAC_GIA = "TacGia";
    public static final String BOOK_COLUMN_VI_TRI = "ViTri";

    public static final String CREATE_TABLE_TYPE_BOOK = "Create table " + TABLE_TYPE_BOOK + "("+
            BOOK_COLUMN_MA_LOAI_SACH + " VARCHAR(7), "
            + BOOK_COLUMN_TEN_LOAI_SACH + " VARCHAR(50), "
            + BOOK_COLUMN_TAC_GIA + " VARCHAR(50), "
            + BOOK_COLUMN_VI_TRI + " VARCHAR(50))";
    public static final String TAG = "TYPE BOOK DAO";


    public TypeBookDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertTypeBook(TypeBook typeBook) {
        ContentValues values = new ContentValues();

        values.put(BOOK_COLUMN_MA_LOAI_SACH, typeBook.getMaloai());
        values.put(BOOK_COLUMN_TEN_LOAI_SACH, typeBook.getName());
        values.put(BOOK_COLUMN_TAC_GIA,typeBook.getTacgia());
        values.put(BOOK_COLUMN_VI_TRI, typeBook.getVitri());

        try {
            if (db.insert(TABLE_TYPE_BOOK, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateTypeBook(String id, String tenLS, String tacgia, String vitri) {
        ContentValues values = new ContentValues();
        values.put(BOOK_COLUMN_MA_LOAI_SACH, id);
        values.put(BOOK_COLUMN_TEN_LOAI_SACH,tenLS);
        values.put(BOOK_COLUMN_TAC_GIA,tacgia);
        values.put(BOOK_COLUMN_VI_TRI, vitri);

        int result = db.update(TABLE_TYPE_BOOK, values, "MaLoaiSach=?", new String[]{id});
        if (result == 0) {
            return -1;
        }
        return 1;


    }

    public List<TypeBook> getTypeBook() {
        List<TypeBook> listTypeBook = new ArrayList<>();
        Cursor c = db.query(TABLE_TYPE_BOOK,null, null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TypeBook typeBook=new TypeBook();

            typeBook.setMaloai(c.getString(0));
            typeBook.setName(c.getString(1));
            typeBook.setTacgia(c.getString(2));
            typeBook.setVitri(c.getString(3));

            listTypeBook.add(typeBook);
            Log.d("//=====", typeBook.toString());
            c.moveToNext();
        }
        c.close();
        return listTypeBook;
    }
    public int deleteLoaiSach(String name) {
        int result = db.delete(TABLE_TYPE_BOOK, "MaLoaiSach=?", new String[]{name});
        if (result == 0)
            return -1;
        return 1;
    }


}
