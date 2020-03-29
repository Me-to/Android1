package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    RotateAnimation animation;
    Button button;
    boolean a = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        imageView = findViewById(R.id.imageView);
        animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(300);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == true) {
                    imageView.startAnimation(animation);
                    a = false;
                    Log.e("******************", "" + a);
                } else if (a == false) {
                    imageView.clearAnimation();
                    a = true;
                    Log.e("******************", "" + a);
                }
            }
        });

    }
}
