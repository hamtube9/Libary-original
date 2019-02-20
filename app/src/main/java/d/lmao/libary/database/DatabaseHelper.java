package d.lmao.libary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import d.lmao.libary.DAO.BookDAO;
import d.lmao.libary.DAO.HoaDonDAO;
import d.lmao.libary.DAO.TypeBookDAO;
import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_Name="Libary";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookDAO.CREATE_TABLE_BOOK);
        db.execSQL(HoaDonDAO.CREATE_TABLE_BILL);
        db.execSQL(UserDAO.CREATE_TABLE_USER);
        db.execSQL(TypeBookDAO.CREATE_TABLE_TYPE_BOOK);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + BookDAO.TABLE_BOOK);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+HoaDonDAO.TABLE_HOADON);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+UserDAO.TABLE_USER);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TypeBookDAO.TABLE_TYPE_BOOK);


        onCreate(db);
    }
}
