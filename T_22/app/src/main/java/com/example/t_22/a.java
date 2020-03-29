package com.example.t_22;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class a  extends AppCompatActivity {

    GridView gridView;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
    }
}
