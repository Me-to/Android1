package com.example.zuopin2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    BaseA base;
    ListView listView;
    List<Bean> list;
    String[] name = {"温度最高值", "温度最低值", "湿度最高值", "湿度最低值"};
    String[] message = new String[4];
    Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        base = new BaseA();
        list = new ArrayList<Bean>();
        button1 = findViewById(R.id.shebutton1);
        button2 = findViewById(R.id.shebutton2);
        listView = findViewById(R.id.listviewaaaa);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        for (int i = 0; i < name.length; i++) {
            message[i] = sharedPreferences.getString("" + i, "null");
        }

        openlist();
        onclick();
    }

    void tan(int i) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplicationContext(), R.layout.tan, null);
        Button button = view.findViewById(R.id.tanbutton);
        EditText editText = view.findViewById(R.id.taneditText);
        alertDialog.setView(view);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message[i] = editText.getText().toString().trim();
                openlist();
                alertDialog.dismiss();
            }
        });
    }

    private void onclick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (int i = 0; i < name.length; i++) {
                    editor.putString("" + i, message[i]);
                    editor.commit();
                }
                Toast.makeText(shezhi.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void openlist() {
        list = new ArrayList<>();
        listView.setAdapter(base);
        for (int i = 0; i < name.length; i++) {
            list.add(new Bean(name[i], message[i]));
        }
        base.notifyDataSetChanged();
    }

    class BaseA extends BaseAdapter {
        ViewHoder viewHoder;

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
                viewHoder=new ViewHoder();
                view = View.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.tv_1 = view.findViewById(R.id.item1);
                viewHoder.tv_2 = view.findViewById(R.id.item2);
                view.setTag(viewHoder);

            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).name);
            viewHoder.tv_2.setText(list.get(i).message);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_1, tv_2;

    }

    class Bean {
        String name, message;

        public Bean(String name, String message) {
            this.name = name;
            this.message = message;
        }
    }
}
