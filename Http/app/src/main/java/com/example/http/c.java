package com.example.http;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class c extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        duoxiancheng();

    }

    private void duoxiancheng() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                http();
            }
        }).start();
    }

    private void http() {
        URL url= null;
        try {
            url = new URL("FJDSKAFKL");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            int i=0;
            byte[] bytes=new byte[1024];
            while ((i=inputStream.read())!=-1){
                byteArrayOutputStream.write(bytes,0,1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
