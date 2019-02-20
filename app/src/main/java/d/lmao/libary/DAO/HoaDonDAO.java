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
import d.lmao.libary.model.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;
    public static final String TABLE_HOADON = "HOADON";
    public static final String HOADON_COLUMN_ID = "ID";

    public static final String HOADON_COLUMN_DATETIME = "Datetime";

    public static final String   CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_HOADON + "(" +
            "" + HOADON_COLUMN_ID + " NCHAR(7) PRIMARY KEY NOT NULL," +
            "" + HOADON_COLUMN_DATETIME + " LONG NOT NULL" +
            ")";
    public static final String TAG = "Hoa Don DAO";


    public HoaDonDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertHoaDon(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put(HOADON_COLUMN_ID,hoaDon.getId());
        values.put(HOADON_COLUMN_DATETIME,hoaDon.getDatetime());

        try {
            if (db.insert(TABLE_HOADON, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateHoaDon(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put(HOADON_COLUMN_ID,hoaDon.getId());
        values.put(HOADON_COLUMN_DATETIME,hoaDon.getDatetime());
        try {
            if (db.insert(TABLE_HOADON, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }




    public List<HoaDon> getHoaDon() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_HOADON,null, null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            HoaDon hoaDon=new HoaDon();
            hoaDon.setId(c.getString(0));
            hoaDon.setDatetime(c.getLong(1));

            listHoaDon.add(hoaDon);
            Log.d("//=====", hoaDon.toString());
            c.moveToNext();
        }
        c.close();
        return listHoaDon;
    }
    public int deleteHoaDOn(String name) {
        int result = db.delete(TABLE_HOADON, "id=?", new String[]{name});
        if (result == 0)
            return -1;
        return 1;
    }
}
