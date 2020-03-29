package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class a extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       setImage(R.drawable.p0);
    }

    private void setImage(int id) {
        File file =new File(getFilesDir().getAbsolutePath()+"image");
        if (!file.exists()){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),id);
            try {
                FileOutputStream fos=openFileOutput("image1.jpg", Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
