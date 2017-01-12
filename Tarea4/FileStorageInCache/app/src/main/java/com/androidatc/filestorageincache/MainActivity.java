package com.androidatc.filestorageincache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enterData;
    private TextView showData;
    private Button save,show;
    private final String TEMP_FILE_NAME="cacheFile.txt";
    private File tempFile;

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

        File cDir=getBaseContext().getCacheDir();
        tempFile=new File(cDir.getPath()+"/"+TEMP_FILE_NAME);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                SaveData();
                show.setEnabled(true);
                break;
            case R.id.show:
                ShowData();
                break;
        }
    }

    private void SaveData(){
        FileWriter writer=null;
        try{
            writer=new FileWriter(tempFile);
            writer.write(enterData.getText().toString());
            writer.close();
            Toast.makeText(getBaseContext(),"File save in cache",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ShowData(){
        String strLine="";
        StringBuilder text=new StringBuilder();
        try{
            FileReader fileReader=new FileReader(tempFile);
            BufferedReader br=new BufferedReader(fileReader);
            while((strLine=br.readLine())!=null){
                text.append(strLine+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData.setText(text);
    }

}
