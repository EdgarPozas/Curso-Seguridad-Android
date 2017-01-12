package com.androidatc.sqlitestorage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText enterData;
    private TextView showData;
    private Button save,show;
    EventDataSQLHelper userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showData=(TextView) findViewById(R.id.load_data);
        enterData=(EditText) findViewById(R.id.enter_data);
        save=(Button) findViewById(R.id.save);
        show=(Button) findViewById(R.id.show);

        save.setOnClickListener(this);
        show.setOnClickListener(this);
        show.setEnabled(false);

        Cursor cursor=getEvents();
        if(showData(cursor).length()>0)
            show.setEnabled(true);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                enterData(enterData.getText().toString());
                show.setEnabled(true);
                break;
            case R.id.show:
                Cursor cursor=getEvents();
                showData.setText(showData(cursor));
                break;
        }
    }

    private void enterData(String username){
        SQLiteDatabase db=userData.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(EventDataSQLHelper.USERNAME,username);
        db.insert(EventDataSQLHelper.TABLE,null,values);
    }
    private Cursor getEvents(){
        SQLiteDatabase db=userData.getWritableDatabase();
        Cursor cursor=db.query(EventDataSQLHelper.TABLE,null,null,null,null,null,null);
        startManagingCursor(cursor);
        return cursor;
    }
    private String showData(Cursor cursor){
        StringBuilder ret=new StringBuilder("");
        while(cursor.moveToNext()){
            long id=cursor.getLong(0);
            String name=cursor.getString(1);
            ret.append(id+":"+name+"\n");
        }
        return ret.toString();
    }

}
