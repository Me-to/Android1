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

public class a  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        duoxian();
    }

    private void duoxian() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                http();
            }
        }).start();
    }

    private void http() {
        try {
            URL url=new URL("ASDLFJAS;DJFSD");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byte[] bytes=new byte[1024];
            int i=0;
            while ((i=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,1);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
