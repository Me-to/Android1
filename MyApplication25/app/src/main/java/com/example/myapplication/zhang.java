package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class zhang extends AppCompatActivity  {
    RotateAnimation rotateAnimation;
    Button button;
    ImageView imageView;
    boolean b=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rotateAnimation= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a);
        rotateAnimation.setDuration(300);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        imageView.setAnimation(rotateAnimation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b){
                    imageView.startAnimation(rotateAnimation);
                    b=false;
                }else {
                    imageView.clearAnimation();
                    b=true;
                }
            }
        });
    }
}
