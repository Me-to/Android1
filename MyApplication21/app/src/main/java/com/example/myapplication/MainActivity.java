package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        in();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.p1:
                                popupMenu.dismiss();
                                break;
                            case R.id.p2:
                                Intent intent = new Intent(MainActivity.this, a.class);
                                startActivity(intent);
                                popupMenu.dismiss();
                                break;
                            case R.id.p3:
                                Intent intent1 = new Intent(MainActivity.this, b.class);
                                startActivity(intent1);
                                popupMenu.dismiss();
                                break;

                        }
                        return false;
                    }
                });
            }
        });

    }


    private void in() {
        try {
            throw new Exception("严重错误：---------------------------------------------------------------------------张前是大傻逼--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
