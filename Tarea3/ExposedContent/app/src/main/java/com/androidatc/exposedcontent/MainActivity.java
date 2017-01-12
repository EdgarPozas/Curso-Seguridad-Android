package com.androidatc.exposedcontent;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status=(TextView) findViewById(R.id.tv_status);
        ContentValues tuple=new ContentValues();
        tuple.put(Constants.TEXT,Constants.TEXT_DATA);
        getContentResolver().insert(Constants.URL,tuple);

        String cols[]=new String[]{Constants.ID,Constants.TEXT};

        Uri u=Constants.URL;
        Cursor c=getContentResolver().query(u,cols,null,null,null);

        if(c.moveToFirst())
            status.setText("Data from: "+c.getString(c.getColumnIndex(Constants.TEXT)));
        else
            status.setText("Access denied");

    }
}
