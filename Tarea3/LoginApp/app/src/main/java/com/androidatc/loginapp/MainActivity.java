package com.androidatc.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.editText1);
        e2=(EditText) findViewById(R.id.editText2);

    }
    public void submitInfo(View v){
        Intent intent=new Intent();
        intent.putExtra("Username",e1.getText().toString());
        intent.putExtra("Password",e2.getText().toString());
        intent.setClassName("com.androidatc.loginapp","com.androidatc.IntentReceiverActivity");
       //intent.setAction("testlogin");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}
