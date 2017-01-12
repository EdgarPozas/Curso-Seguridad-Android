package com.androidatc.unaunthorizedaccess;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView result_app1=(TextView)findViewById(R.id.app1_result);
        TextView result_app2=(TextView)findViewById(R.id.app2_result);

        final String app1UriString="content://com.androidatc.exposedcontent.provider/t1";
        final String app2UriString="content://com.androidatc.protectedcontent.provider/t1";
        String cols[]=new String[]{"_ID","MESSAGE"};
        Uri u =Uri.parse(app1UriString);
        Cursor c=getContentResolver().query(u,cols,null,null,null);
        if(c.moveToFirst())
            result_app1.setText("Content"+c.getString(c.getColumnIndex("MESSAGE")));
        else
            result_app1.setText("Access denied");
        u =Uri.parse(app2UriString);
        try{
            c=getContentResolver().query(u,cols,null,null,null);
            if(c.moveToFirst())
                result_app1.setText("Content"+c.getString(c.getColumnIndex("MESSAGE")));
            else
                result_app1.setText("Access denied");
        }catch (SecurityException e){
            result_app2.setText("Access Denied");
        }
    }
}
