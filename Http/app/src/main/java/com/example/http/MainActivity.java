package com.example.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            httpa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void httpa() throws IOException {
        String urls="www.ansdfl.com";
        //URL     新建一个url对象
        URL url=new URL(urls);
        //HttpURLConnection  =        url.openConnection();
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        //InputStream =                      getInputStream();
        InputStream inputStream=httpURLConnection.getInputStream();
        //ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        //byte[]
        byte[] bytes= new byte[1024];
        //int i=0;
        int i=0;
        //进行一个inputStream。read（byte）的循环

        while ((i=inputStream.read(bytes))!=-1){
            //byteArrayOutStream.write(byte,0,1)
            byteArrayOutputStream.write(bytes,0,1);
        }

    }
}
