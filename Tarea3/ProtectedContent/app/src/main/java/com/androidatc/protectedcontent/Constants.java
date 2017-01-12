package com.androidatc.protectedcontent;

import android.net.Uri;

/**
 * Created by LAB-INV-07 on 10/01/2017.
 */

public class Constants {
    private Constants(){
    }
    public static final Uri URL=Uri.parse("content://"+DataProvider.AUTHORITY+"/t1");
    public static final String CONTENT_TYPE="vnd.android.cursor.dir/vnd.android.contenproviderlab.t1";
    public static final String ID="_ID";
    public static final String TEXT="MESSAGE";
    public static final String TEXT_DATA="HOLA";
}
