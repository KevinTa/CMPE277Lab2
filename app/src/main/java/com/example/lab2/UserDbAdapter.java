package com.example.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class UserDbAdapter {
    DbHelper Userhelper;
    public UserDbAdapter(Context context)
    {
        Userhelper = new DbHelper(context);
    }

    public long insertUserData(String name, String pass)
    {
        SQLiteDatabase dbb = Userhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME, name);
        contentValues.put(DbHelper.MyPASSWORD, pass);
        long id = dbb.insert(DbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getCurrentUserData(String username, String userpassword)
    {
        SQLiteDatabase db = Userhelper.getWritableDatabase();
        String[] columns = {DbHelper.UID,DbHelper.NAME,DbHelper.MyPASSWORD};
        String whereClause = DbHelper.NAME + " = ? AND " + DbHelper.MyPASSWORD + " = ?";
        String[] whereArgs = new String[] {
                username,
                userpassword
        };

        Cursor cursor =db.query(DbHelper.TABLE_NAME,columns,whereClause,whereArgs,null,null,null);
        String matches = "";
        while (cursor.moveToNext())
        {
            String name =cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
            matches = name;
        }
        return matches;
    }


    static class DbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "userTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String NAME = "Name";
        private static final String MyPASSWORD= "Password";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "OnUpgrade", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }
    }
}
