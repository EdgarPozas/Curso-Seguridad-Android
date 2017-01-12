package com.androidatc.filestorage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFromInternalStorage extends Activity {

    private TextView internalFileContent;
    private String fileName="testFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_external_storage);
        internalFileContent=(TextView) findViewById(R.id.file_content);
        String fileContent=readFileInternalStorage(fileName,this);
        internalFileContent.setText(fileContent);
    }

    public String readFileInternalStorage(String fileName, Context context) {
        String stringtoReturn="";
        try{
            String sfilename=fileName;
            InputStream inputStream=context.openFileInput(sfilename);
            if(inputStream!=null){
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String receiveString="";
                StringBuilder stringBuilder=new StringBuilder();
                while((receiveString=bufferedReader.readLine())!=null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                stringtoReturn=stringBuilder.toString();

            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error");
        }
        return stringtoReturn;
    }
}
