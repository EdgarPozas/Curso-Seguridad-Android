package com.androidatc.filestorage;

import android.os.Environment;
import android.util.Log;

/**
 * Created by LAB-INV-07 on 11/01/2017.
 */

public class CommonUtil {
    public static boolean isSdReadable(){
        boolean mExternal=false;
        try{
            String state= Environment.getExternalStorageState();
            if(Environment.MEDIA_MOUNTED.equals(state)){
                mExternal=true;
                Log.i("isReadable","is readable");
            }
            else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
                mExternal=true;
                Log.i("isReadable","is readable");
            }else{
                mExternal=false;
            }
        }catch (Exception ex){

        }
        return  mExternal;
    }
}
