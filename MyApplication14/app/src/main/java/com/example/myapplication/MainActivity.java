package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String TAG="--------------";
    String FileName = "/data/data";
    BitmapFactory.Options options;
    String path = "/data/data";
    Bitmap bitmap=BitmapFactory.decodeFile(FileName);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = getBitmapFactory(FileName, 400, 400);
        saveBitmap(bitmap,path);
    }

    private BitmapFactory.Options getBitmapFactory(String fileName, int pixelW, int pixelH) {


        // Bitmap bitmap=BitmapFactory.decodeResource(R.drawable.ic_launcher_background,null);

        BitmapFactory.Options options = new BitmapFactory.Options();
        File file = new File(FileName);
        if (file.exists()) {
            //inJustDecodeBounds 为True时候不返回Bitmp ，只返回这个bitmp的尺寸
            options.inJustDecodeBounds = true;
            //设置图片色彩
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            //预加载图片
            BitmapFactory.decodeFile(fileName, options);
            //获取原始图片宽高
            int originalW = options.outWidth;
            int originalH = options.outHeight;
            //上面设置为true获取bitmap尺寸大小，在这里一定要重新设置为false，否则位图加载不出来
            options.inJustDecodeBounds = false;
            options.inSampleSize = getSampleSize(originalW, originalH, pixelW, pixelH);
            return options;
        }
        return null;
    }

    private int getSampleSize(int originalW, int originalH, int piexlW, int piexlH) {
        int simpleSize = 1;
        if (originalW > originalH && originalW > piexlW) {
            simpleSize = originalW / piexlW;
        } else if (originalH > originalW && originalH > piexlH) {
            simpleSize = originalH / piexlH;
        }
        if (simpleSize <= 0) {
            simpleSize = 1;
        }
        return simpleSize;
    }
  private void saveBitmap(Bitmap bitmap,String path){
        String savePath;
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            savePath = path;
        }else {
            Log.e(TAG, "saveBitmap: " );
            return;
        }
        try {
            file=new File(savePath+".jpg");
            if (!file.exists()){
                file.mkdirs();
            }
            FileOutputStream fos=new FileOutputStream(file);
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
