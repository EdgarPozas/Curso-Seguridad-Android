package com.androidatc.hackapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String message="Username: "
                +this.getIntent().getStringExtra("Username")
                +"\nPassword: "+
                this.getIntent().getStringExtra("Password");
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("data was accessed")
                .setMessage(message).setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create().show();
    }
}
