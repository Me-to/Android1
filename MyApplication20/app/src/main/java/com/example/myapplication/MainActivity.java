package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    String TAG="--------------------";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.open);
        final AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        imageView.getDrawable();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
            }
        });
    }


}
