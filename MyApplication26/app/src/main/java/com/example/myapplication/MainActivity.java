package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RotateAnimation animation;
    Button button;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        button=findViewById(R.id.button);
      animation= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.drawable.qw);
      animation.setDuration(300);
      animation.setRepeatCount(-1);
      imageView.startAnimation(animation);
    }
}
