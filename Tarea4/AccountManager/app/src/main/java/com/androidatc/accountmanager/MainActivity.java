package com.androidatc.accountmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageProfile;
    private TextView name,email,gender;
    String textName,textEmail,txtGender,textBirthday,userImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageProfile = (ImageView) findViewById(R.id.imageView1);
        name = (TextView) findViewById(R.id.textViewNameValue);
        email = (TextView) findViewById(R.id.textViewEmailValue);
        gender = (TextView) findViewById(R.id.textViewGenderValue);

        Intent intent = getIntent();

        textEmail = intent.getStringExtra("email_id");
        System.out.println(textEmail);
        email.setText(textEmail);

        try {
            System.out.println("home google");

            JSONObject profileData = new JSONObject(AbstractGetNameTask.GOOGLE_USER_DATA);
            if (profileData.has("picture")){
                userImageUrl=profileData.getString("picture");
                new GetImageFromUrl().execute(userImageUrl);
            }
            if (profileData.has("name")){
                textName=profileData.getString("name");
                name.setText(textName);
            }
            if (profileData.has("gender")){
                txtGender=profileData.getString("gender");
                gender.setText(txtGender);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public class GetImageFromUrl extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap map=null;
            for (String url:params){
                map=downloadImage(url);
            }
            return map;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageProfile.setImageBitmap(bitmap);
        }
        private Bitmap downloadImage(String url){
            Bitmap bitmap=null;
            InputStream stream=null;
            BitmapFactory.Options bmOptions=new BitmapFactory.Options();
            bmOptions.inSampleSize=1;
            try{
                stream=getHttpConnection(url);
                bitmap=BitmapFactory.decodeStream(stream,null,bmOptions);
                stream.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return bitmap;
        }
        private InputStream getHttpConnection(String urlString) throws IOException{
            InputStream stream=null;
            URL url=new URL(urlString);
            URLConnection connection=url.openConnection();
            try{
                HttpURLConnection httpURLConnection=(HttpsURLConnection) connection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    stream=httpURLConnection.getInputStream();
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return stream;
        }
    }
}
