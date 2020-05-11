package com.barnamechi.ayandehsazan.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class UserDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabaseModel";
    private static final String TABLE_NAME = "UserDatabase";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DISPLAYNAME = "displayname";
    private static final String KEY_EMAIL = "useremail";
    private static final String KEY_AVATAR = "avatarurl";
    private static final String[] COLUMNS = {KEY_ID, KEY_USERNAME, KEY_DISPLAYNAME,
            KEY_EMAIL, KEY_AVATAR};

    public UserDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE UserDatabase ( "
                + "id INTEGER, " + "username TEXT, " + "displayname TEXT, " + "useremail TEXT , " + " avatarurl TEXT)";

        db.execSQL(CREATION_TABLE);

    }

    public UserDatabaseModel getUserFromDatabase(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null) {
            cursor.moveToFirst();
        }

        UserDatabaseModel userDatabaseModel = new UserDatabaseModel();
        userDatabaseModel.setId(Integer.parseInt(cursor.getString(0)));
        userDatabaseModel.setUsername(cursor.getString(1));
        userDatabaseModel.setDisplayname(cursor.getString(2));
        userDatabaseModel.setUseremail(cursor.getString(3));
        userDatabaseModel.setUserAvatarURL(cursor.getString(4));

        return userDatabaseModel;
    }

    public List<UserDatabaseModel> allUserDatabase() {

        List<UserDatabaseModel> userDatabaseModels = new LinkedList<UserDatabaseModel>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        UserDatabaseModel userDatabaseModel = null;

        if (cursor.moveToFirst()) {
            do {
                userDatabaseModel = new UserDatabaseModel();
                userDatabaseModel.setId(Integer.parseInt(cursor.getString(0)));
                userDatabaseModel.setUsername(cursor.getString(1));
                userDatabaseModel.setDisplayname(cursor.getString(2));
                userDatabaseModel.setUseremail(cursor.getString(3));
                userDatabaseModel.setUserAvatarURL(cursor.getString(4));
                userDatabaseModels.add(userDatabaseModel);
            } while (cursor.moveToNext());
        }

        return userDatabaseModels;
    }

    public void addUserDatabase(UserDatabaseModel userDatabaseModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, userDatabaseModel.getId());
        values.put(KEY_USERNAME, userDatabaseModel.getUsername());
        values.put(KEY_DISPLAYNAME, userDatabaseModel.getDisplayname());
        values.put(KEY_USERNAME, userDatabaseModel.getUseremail());
        values.put(KEY_AVATAR, userDatabaseModel.getUserAvatarURL());

        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteAll() {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


}
