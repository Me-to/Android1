package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

   String TAG="------------";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();
    }

    private void start() {
        String path=Environment.getExternalStorageDirectory().getPath()+"/sasa.jpg";
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri=Uri.fromFile(new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,1);
    }


}


