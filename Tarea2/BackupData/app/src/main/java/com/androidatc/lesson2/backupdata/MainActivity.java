package com.androidatc.lesson2.backupdata;

import android.app.backup.BackupManager;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BackupManager backupManager;
    SharedPreferences.Editor edit;
    TextView showData;
    EditText enterData;
    Button save,show;

    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backupManager=new BackupManager(this);
         showData = (TextView) findViewById(R.id.load_data);
        enterData=(EditText)findViewById(R.id.enter_data);
        save=(Button) findViewById(R.id.save);
        show=(Button) findViewById(R.id.show);
        show.setOnClickListener(this);
        save.setOnClickListener(this);
        show.setEnabled(false);
        prefs=getSharedPreferences(BackupClass.PREFS_TEST, Context.MODE_PRIVATE);

        edit=prefs.edit();

        if(ShowData().length()>0){
            show.setEnabled(true);
        }
    }
    private void SaveData(String key,String value){
        edit.putString(key,value);
        edit.commit();
        Log.d("Test","Calling backup");
        backupManager.dataChanged();
    }
    private String ShowData(){
        String savedData=prefs.getString("save","");
        return savedData;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                break;
            case R.id.show:
                break;
        }
    }
}
