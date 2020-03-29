package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
 BitmapFactory.Options options;
    String TAG="--------------";
    Bitmap bitmap1;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView;

       imageView=findViewById(R.id.imageView);
         bitmap1=getBitmapFactory(7,7);
        imageView.setImageBitmap(bitmap1);
        Log.e(TAG, "修改后的大小"+bitmap1.getByteCount() );
    }
    private Bitmap getBitmapFactory(int pixelH,int pixelW){
        Log.e(TAG, "getBitmapFactory:0 " );
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        options.inPreferredConfig=Bitmap.Config.RGB_565;


        int originalW=options.outWidth;
        int originalH=options.outHeight;

        options.inJustDecodeBounds=false;
        options.inSampleSize=getSampleSize(originalW,originalH,pixelH,pixelW);
        Log.e(TAG, "getBitmapFactory: 1" );
        return   BitmapFactory.decodeResource(getResources(),R.drawable.p2);
    }
    private int getSampleSize(int originalW,int orifinalH,int piexlH,int piexlW){
        Log.e(TAG, "getSampleSize: 3");
        int simpleSize=1;
        if (orifinalH>originalW&&orifinalH>piexlH){
            simpleSize=orifinalH/piexlH;
        }else if (originalW>orifinalH&&originalW>piexlW){
            simpleSize=originalW/piexlW;
        }
        if (simpleSize<=0){
            simpleSize=1;
        }
        Log.e(TAG, "getSampleSize: 4" );
        return simpleSize;
    }
}

