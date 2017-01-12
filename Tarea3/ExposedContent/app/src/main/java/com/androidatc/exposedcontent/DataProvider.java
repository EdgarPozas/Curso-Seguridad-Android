package com.androidatc.exposedcontent;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by LAB-INV-07 on 10/01/2017.
 */

public class DataProvider extends ContentProvider{

    public static final String AUTHORITY="com.androidatc.exposedcontent.provider";
    private static final String DATABASE_NAME="table.db";
    private static final int DATABASE_VERSION=1;
    private static final String DATUM_TABLE_NAME="t1";
    private static final UriMatcher sUriMatcher;
    private static final int DATUM=1;
    private static final int DATUM_ID=2;
    private static HashMap<String,String> projMap;
    static {
        sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,DATUM_TABLE_NAME,DATUM);
        sUriMatcher.addURI(AUTHORITY,DATUM_TABLE_NAME+"/#",DATUM_ID);
        projMap=new HashMap<String,String>();
        projMap.put(Constants.ID,Constants.ID);
        projMap.put(Constants.TEXT,Constants.TEXT);
    }

    private static  class DBHelper extends SQLiteOpenHelper{
        DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+DATUM_TABLE_NAME+"("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+Constants.TEXT+" VARCHAR(20)"+")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DATUM_TABLE_NAME);
            onCreate(db);
        }
    }
    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper=new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(DATUM_TABLE_NAME);
        qb.setProjectionMap(projMap);
        if(sUriMatcher.match(uri)!=DATUM) {
            if (sUriMatcher.match(uri) == DATUM_ID)
                selection = selection + "_id" + uri.getLastPathSegment();
            else
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c=qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if(sUriMatcher.match(uri)==DATUM)
            return Constants.CONTENT_TYPE;
        else
            throw new IllegalArgumentException("Unknown URI"+uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(sUriMatcher.match(uri)!=DATUM)
            throw new IllegalArgumentException("Unknown URI"+uri);
        ContentValues v;
        if(values!=null)
            values=new ContentValues(values);
        else
            values=new ContentValues();
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long rId=db.insert(DATUM_TABLE_NAME,Constants.TEXT,values);
        if(rId>0){
            Uri u= ContentUris.withAppendedId(Constants.URL,rId);
            getContext().getContentResolver().notifyChange(u,null);
            return u;
        }
        throw new SQLException("Failed to insert");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 1;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
