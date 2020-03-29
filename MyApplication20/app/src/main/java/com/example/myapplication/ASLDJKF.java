package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ASLDJKF extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImage();
    }

    private void setImage(int id) {
        File file = new File(getFilesDir().getAbsolutePath() + "/zhangqian");
        if (!file.exists()) {

            file.mkdirs();

        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("image.jpg", MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
