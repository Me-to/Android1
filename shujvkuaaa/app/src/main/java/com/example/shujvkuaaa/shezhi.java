package com.example.shujvkuaaa;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    ListView listView=findViewById(R.id.list_view);
    List<Bean> list = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void tanchaunga(){
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        View view=View.inflate(getApplication(),R.layout.tanchuang,null);
        Button button=view.findViewById(R.id.btn_tan);
        EditText editText=view.findViewById(R.id.et_tanchaung);
        ad.setView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad.dismiss();
            }
        });
        ad.show();
    }
    public void open(){
        listView.setAdapter(myAdapter);
        list=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            list.add(new Bean());
        }
     myAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter {
        ViewHoder viewHoder = new ViewHoder();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LinearLayout.inflate(getApplication(), R.layout.item, null);
                viewHoder.textView1 = view.findViewById(R.id.text1);
                viewHoder.textView2 = view.findViewById(R.id.text2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.textView1.setText(list.get(i).name);
            viewHoder.textView2.setText(list.get(i).age);
            return view;
        }
    }

    class ViewHoder {
        TextView textView1;
        TextView textView2;

    }

    class Bean {
        String name;
        String age;

        public Bean(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
