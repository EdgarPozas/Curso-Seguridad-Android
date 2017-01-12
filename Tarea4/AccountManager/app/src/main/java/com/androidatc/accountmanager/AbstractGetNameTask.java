package com.androidatc.accountmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by LAB-INV-07 on 11/01/2017.
 */

public abstract class AbstractGetNameTask extends AsyncTask<Void,Void,Void>{
    private static final String TAG="TokenInfoTask";
    protected SplashActivity mActivity;
    public static String GOOGLE_USER_DATA="No_data";
    protected String mScope,mEmail;
    protected int mRequestCode;

    AbstractGetNameTask(SplashActivity activity,String email,String scope){
        this.mActivity=activity;
        this.mEmail=email;
        this.mScope=scope;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            fetchNameFromProfileServer();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    protected  void onError(String msg,Exception e){
        if(e!=null){
            Log.e(TAG,"Exception ",e);
        }
    }
    private void fetchNameFromProfileServer() throws IOException{
        String token=fetchToken();
        URL url=new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+token);
        HttpURLConnection con=(HttpsURLConnection) url.openConnection();
        int sc=con.getResponseCode();
        if(sc==200){
            InputStream is =con.getInputStream();
            GOOGLE_USER_DATA=readResponse(is);
            is.close();

            Intent intent=new Intent(mActivity,MainActivity.class);
            intent.putExtra("email_id",mEmail);
            mActivity.startActivity(intent);
            mActivity.finish();
            return;
        }else if(sc==401){
            GoogleAuthUtil.invalidateToken(mActivity,token);
            onError("Server auth error",null);
            return;
        }else{
            onError("server retuned the following error code"+sc,null);
            return;
        }
    }

    protected abstract String fetchToken() throws IOException;

    private static String readResponse(InputStream is) throws IOException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] data=new byte[2048];
        int len=0;
        while((len=is.read(data,0,data.length))>=0){
            bos.write(data,0,len);
        }
        return new String(bos.toByteArray(),"UTF-8");
    }

}
