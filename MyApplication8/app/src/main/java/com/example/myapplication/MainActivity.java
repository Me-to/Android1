package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.DrmInitData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.im_door);
        imageView.setImageResource(R.drawable.open_door);
        final AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();

        button= findViewById(R.id.btn_);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
            }
        });
    }
}
