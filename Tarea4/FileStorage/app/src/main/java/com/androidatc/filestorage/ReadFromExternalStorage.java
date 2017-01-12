package com.androidatc.filestorage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFromExternalStorage extends Activity {

    private TextView externalFileContent;
    private String fileName="testFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_external_storage);
        externalFileContent=(TextView) findViewById(R.id.file_content);
        String fileContent=readFileExternalStorage(fileName);
        externalFileContent.setText(fileContent);
    }

    public String readFileExternalStorage(String fileName) {
        StringBuilder text=new StringBuilder();
        try{
            if(CommonUtil.isSdReadable()){
                File sdcard= Environment.getExternalStorageDirectory();
                BufferedReader br=new BufferedReader(new FileReader(fileName));
                String line;
                while((line=br.readLine())!=null){
                    text.append(line);
                    System.out.println("text"+text);
                    text.append("n");
                }
            }
        }catch (Exception ex){
                ex.printStackTrace();
            System.out.println("Error");
        }
        return text.toString();
    }
}
