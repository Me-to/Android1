

package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String TAG = "----------";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImage(R.drawable.p8);
    }

    private void setImage(int id) {
        //在本地创造一个文件夹
        File file = new File(getFilesDir().getAbsolutePath() + "/image");
        //判断是否存在，防止每次都要创建
        if (!file.exists()) {
            //如果不存在则创建
            file.mkdirs();
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        FileOutputStream fos = null;
        try {
            //获得一个可写的输入流
            fos = openFileOutput("image2.jpg", Context.MODE_PRIVATE);
            //使用图片压缩对图片进行处理，压缩的格式可以是JPEG,PNG,WEBP
            //第二个参数是图片的压缩比例，第三个参数是写入流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e(TAG, getFilesDir().getAbsolutePath());
    }

}