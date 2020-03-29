package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    RotateAnimation animation;
    ImageView imageView;
Button button;
boolean b=true;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        //animation = (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.a);\
        animation= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a);
        imageView = findViewById(R.id.imageView);
        animation.setDuration(300);
        imageView.setAnimation(animation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b){
                    imageView.startAnimation(animation);
                    b=false;
                }else {
                    b=true;
                    imageView.clearAnimation();
                }
            }
        });


    }
}
