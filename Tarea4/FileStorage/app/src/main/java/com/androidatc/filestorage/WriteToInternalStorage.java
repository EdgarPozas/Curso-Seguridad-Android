package com.androidatc.filestorage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteToInternalStorage extends Activity {

    private EditText edFileName;
    private EditText edContent;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_to_internal_storage);
        edFileName=(EditText) findViewById(R.id.enter_filename);
        edContent=(EditText) findViewById(R.id.enter_file_content);
        btnSave=(Button)findViewById(R.id.save);
        btnSave.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String fileName=edFileName.getText().toString();
                String content=edContent.getText().toString();
                writeFileInternalStorage(content,v.getContext(),fileName);
            }
        });
    }

    public void writeFileInternalStorage(String srtWrite,Context context,String fileName) {
        try{
            FileOutputStream fos=context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(srtWrite.getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(context,"File saved in memory internal",Toast.LENGTH_LONG).show();
        }catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(context,"Error in save memory internal",Toast.LENGTH_LONG).show();
        }
    }
}
