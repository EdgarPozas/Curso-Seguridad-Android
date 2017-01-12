package com.androidatc.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class IntentReceiverActivity extends AppCompatActivity {
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_receiver);
        Bundle bundl= getIntent().getExtras();
        String r1=bundl.getString("Username");
        String r2=bundl.getString("Password");
        t1=(TextView) findViewById(R.id.textView1);
        t2=(TextView) findViewById(R.id.textView2);
        t1.setText("Username: "+ r1);
        t2.setText("Password: "+r2);
        Log.d("Username" , r1);
        Log.d("Password" , r2);
    }
}
