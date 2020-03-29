package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URLConnection;

public class i extends AppCompatActivity {
    Myaaaa mya;
    ImageView iv_tupain;
    ProgressBar pb_quan;
    String URL = "http://www.sdwfvc.com/info/1083/22249.htm";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i);
        init();
    }

    private void init() {
        iv_tupain = findViewById(R.id.iv_tupain);
        pb_quan = findViewById(R.id.pb_quan);
        mya = new Myaaaa();
        mya.execute();

    }

    class Myaaaa extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
