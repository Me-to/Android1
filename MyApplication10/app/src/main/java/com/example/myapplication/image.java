package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class image extends AppCompatActivity {
    static String TAG="--------------------";
    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL="https://upload-images.jianshu.io/upload_images/944365-207a738cb165a2da.png?imageMogr2/auto-orient/strip|imageView2/2/w/840/format/webp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView=findViewById(R.id.image_v);
        progressBar=findViewById(R.id.pb);
        new MyAsycTask1().execute(URL);
    }
    class MyAsycTask1 extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.e(TAG, "doInBackground:----- ");
            String  url= strings[0];
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;
            try {
                connection=new URL(url).openConnection();
                is=connection.getInputStream();
                BufferedInputStream bis=new BufferedInputStream(is);
                //等待3秒
                Thread.sleep(3000);
                bitmap= BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            Log.e(TAG, "onPreExecute: -----");
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.e(TAG, "onPostExecute:------ ");
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.e(TAG, "onProgressUpdate:------ ");
            super.onProgressUpdate(values);
        }
    }

}
