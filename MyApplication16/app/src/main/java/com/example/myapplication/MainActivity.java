package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String TAG="------------";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveImage();
    }
    private void saveImage() {
        // getFilesDir().getAbsolutePath()+"/image"\
        //在本地创建一个文件夹
        File file;
        file = new File(getFilesDir().getAbsolutePath() + "/image");
        Log.e(TAG,getFilesDir().getAbsolutePath() );
        // File absoluteFile = getFilesDir().getAbsoluteFile();
        //判断本地是否存在，防止每次启动App都要创建
        if (file.exists()) {
            return;
        }
        Log.i(TAG, "----------------------------------------------------------------");
        //使用BitmapFactory把res下的图片转换成Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p9);
        FileOutputStream fos = null;
        try {
            //获得一个可写的输入流
            fos = openFileOutput("imag00e.jpg", Context.MODE_PRIVATE);
            //使用图片压缩对图片进行处理  压缩的格式  可以是JPEG、PNG、WEBP
            //第二个参数是图片的压缩比例，第三个参数是写入流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(TAG, "绝对路径" + getFilesDir().getAbsolutePath() + "/image");
    }
}
