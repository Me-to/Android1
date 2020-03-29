package com.example.zuopin4;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class aaaa extends AppCompatActivity {
    List<String> list;
    List<String> list1;

    RecyclerView recyclerView;
    HomeAdapter madapter;
    LinearLayoutManager linearLayoutManager;
    String[] a = {"w", "s", "s", "s", "s"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h);

        init();
        recyclerView = findViewById(R.id.recylerView11111111111111111111);
        //设置布局管理器
       recyclerView.setLayoutManager(linearLayoutManager);
        //设置adapter
        recyclerView.setAdapter(madapter);
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL));

    }



    private void init() {
        linearLayoutManager = new LinearLayoutManager(this);
        madapter = new HomeAdapter();

        list = new ArrayList<>();
        list1=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
            list1.add("--"+i);

        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(aaaa.this).inflate(R.layout.item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {
            holder.textView1.setText(list1.get(position));
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView, textView1;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView1 = itemView.findViewById(R.id.number1);
                textView = itemView.findViewById(R.id.number);
            }

        }

    }


}
