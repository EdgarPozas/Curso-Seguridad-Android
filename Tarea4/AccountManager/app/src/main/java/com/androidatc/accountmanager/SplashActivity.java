package com.androidatc.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    Context mContetx=SplashActivity.this;
    AccountManager accountManager;
    String token;
    int serverCode;
    private static final String SCOPE="oauth2:https://www.googleapis.com/auth/userinfo.profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        syncGoogleAccount();
    }
    private String[] getAccountNames(){
        accountManager=AccountManager.get(this);
        Account[] accounts=accountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names= new String[accounts.length];
        for (int i=0;i<names.length;i++){
            names[i]=accounts[i].name;
        }
        return names;
    }
    private AbstractGetNameTask getTask(SplashActivity activity,String email,String scope){
        return new GetNameInForeground(activity,email,scope);
    }
    public void syncGoogleAccount(){
        if (isNetworkAvalible()){
            String[] accountarrs=getAccountNames();
            if(accountarrs.length>0){
                getTask(SplashActivity.this,accountarrs[0],SCOPE).execute();
            }else{
                Toast.makeText(SplashActivity.this,"No sync google",Toast.LENGTH_SHORT).show();
            }
         }else{
            Toast.makeText(SplashActivity.this,"No network service",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isNetworkAvalible(){
        ConnectivityManager cm=(ConnectivityManager) mContetx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            Log.e("Network","avalible");
            return true;
        }
        Log.e("Network","not avalible");
        return false;
    }

}
