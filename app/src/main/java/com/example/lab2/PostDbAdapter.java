package com.example.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class PostDbAdapter {
    DbHelper myPosthelper;
    public PostDbAdapter(Context context)
    {
        myPosthelper = new DbHelper(context);
    }

    public long insertPostData(String name, String post)
    {
        SQLiteDatabase dbb = myPosthelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME, name);
        contentValues.put(DbHelper.MyPOST, post);
        long id = dbb.insert(DbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getCurrentPostData(String username)
    {
        SQLiteDatabase db = myPosthelper.getWritableDatabase();
        String[] columns = {DbHelper.UID,DbHelper.NAME,DbHelper.MyPOST};
        String whereClause = DbHelper.NAME + " = ? " ;
        String[] whereArgs = new String[] {
                username
        };

        Cursor cursor =db.query(DbHelper.TABLE_NAME,columns,whereClause,whereArgs,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(DbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
            String  post =cursor.getString(cursor.getColumnIndex(DbHelper.MyPOST));
            buffer.append(cid+ "   " + name + "   " + post +" \n");
        }
        return buffer.toString();
    }

    public  int deletePost(String id)
    {
        SQLiteDatabase db = myPosthelper.getWritableDatabase();
        String[] whereArgs ={id};

        int count = db.delete(DbHelper.TABLE_NAME ,DbHelper.UID+" = ?",whereArgs);
        return  count;
    }

    static class DbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "postDatabase";
        private static final String TABLE_NAME = "postTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String NAME = "Name";
        private static final String MyPOST= "Message";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPOST+" VARCHAR(225));";
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
