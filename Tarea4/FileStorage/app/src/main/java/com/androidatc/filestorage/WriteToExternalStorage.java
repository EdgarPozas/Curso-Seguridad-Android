package com.androidatc.filestorage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class WriteToExternalStorage extends Activity {

    private EditText edFileName;
    private EditText edContent;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_to_external_storage);
        edFileName=(EditText) findViewById(R.id.enter_filename);
        edContent=(EditText) findViewById(R.id.enter_file_content);
        btnSave=(Button)findViewById(R.id.save);
        btnSave.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String fileName=edFileName.getText().toString();
                String content=edContent.getText().toString();
                writeFileExternalStorage(content,v.getContext(),fileName);
            }
        });
    }

    public void writeFileExternalStorage(String srtWrite,Context context,String fileName) {
        try{
            if(CommonUtil.isSdReadable()){
                String fullPath= Environment.getExternalStorageDirectory().getAbsolutePath();
                File myFile=new File(fullPath+File.pathSeparator+"/"+fileName);
                FileOutputStream fOut=new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter=new OutputStreamWriter(fOut);
                myOutWriter.append(srtWrite);
                myOutWriter.close();
                fOut.close();
                Toast.makeText(context,"File in memory external",Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(context,"Error to save",Toast.LENGTH_LONG).show();
        }
    }
}
