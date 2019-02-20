package d.lmao.libary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.database.DatabaseHelper;
import d.lmao.libary.model.User;

public class UserDAO {
    private SQLiteDatabase db;
    public static final String TABLE_USER = "USER";

    public static final String USER_COLUMN_USERNAME = "Username";
    public static final String USER_COLUMN_PASSWORD = "Password";
    public static final String USER_COLUMN_NAME = "Name";
    public static final String USER_COLUMN_PHONE = "Phone";
    public static final String USER_COLUMN_ANH="Anh";

    public static final String CREATE_TABLE_USER = "Create table " + TABLE_USER + "(" +
            USER_COLUMN_USERNAME + " VARCHAR(50)  PRIMARY KEY NOT NULL, "


            + USER_COLUMN_PASSWORD + " VARCHAR(50), " +USER_COLUMN_NAME + " VARCHAR(50), " + USER_COLUMN_PHONE +" int, "
            +USER_COLUMN_ANH +" blob )";

    public static final String TAG = "USER DAO";

    public DatabaseHelper helper;
    public UserDAO(Context context) {
        helper= new DatabaseHelper(context);

    }

    public int insertUser(User user) {
        ContentValues values = new ContentValues();
        db = helper.getWritableDatabase();
        values.put(USER_COLUMN_USERNAME, user.getUsername());
        values.put(USER_COLUMN_PASSWORD, user.getPass());
        values.put(USER_COLUMN_NAME, user.getName());
        values.put(USER_COLUMN_PHONE, user.getPhone());
        values.put(USER_COLUMN_ANH,user.getAnh());
        try {
            if (db.insert(TABLE_USER, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateUser(String cfpass) {
        ContentValues values = new ContentValues();
        db = helper.getWritableDatabase();

        values.put(USER_COLUMN_PASSWORD,cfpass);


        int result = db.update(TABLE_USER, values, "Username=?", new String[]{cfpass});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<User> getAllUser() {
        List<User> listUser = new ArrayList<>();
        db = helper.getWritableDatabase();
        Cursor c = db.query(TABLE_USER, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User user = new User();
            user.setUsername(c.getString(0));
            user.setPass(c.getString(1));
            user.setName(c.getString(2));
            user.setPhone(c.getString(3));
            user.setAnh(c.getBlob(4));
            listUser.add(user);
            Log.d("//=====", user.toString());
            c.moveToNext();
        }
        c.close();
        return listUser;
    }

    public User getUser(String username) {
        List<User> dsUser = new ArrayList<>();
        db = helper.getWritableDatabase();
        User ee = new User();
        Cursor c = db.query(TABLE_USER, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {


            ee.setUsername(c.getString(0));
            ee.setPass(c.getString(1));
            ee.setName(c.getString(2));
            ee.setPhone(c.getString(3));
            ee.setAnh(c.getBlob(4));
            dsUser.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return ee;
    }

    public int deleteUser(String user) {
        db = helper.getWritableDatabase();
        int result = db.delete(TABLE_USER, "Username=?", new String[]{user});
        if (result == 0)
            return -1;
        return 1;

    }
}
