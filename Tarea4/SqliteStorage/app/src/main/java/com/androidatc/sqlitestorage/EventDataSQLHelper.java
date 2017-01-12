package com.androidatc.sqlitestorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by LAB-INV-07 on 11/01/2017.
 */

public class EventDataSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="events.db";
    private static final int DATABASE_VERSION=1;
    public static final  String TABLE="User";

    public static final String USERNAME="username";

    public EventDataSQLHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TABLE+" ("
                + BaseColumns._ID
                +" integer primary key autoincrement, "
                +USERNAME+" text not null);";
        Log.d("EventsDATa","create");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>=newVersion)
            return;
        String sql=null;
        if(oldVersion==1)
            sql="alter table "+TABLE+" add note text;";
        if(oldVersion==2)
            sql="";
        if(sql!=null)
            db.execSQL(sql);
    }
}
