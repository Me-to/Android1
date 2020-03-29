package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView imageView;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
button=findViewById(R.id.button);
imageView=findViewById(R.id.imagev);
imageView.setImageResource(R.drawable.open);
init();
    }

    private void init() {
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }
}