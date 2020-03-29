package com.example.zuopin1;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LoggingMXBean;

public class shezhi extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String[] name = {"modbus IP地址", "modbus 端口", "LED IP地址", "LED 端口", "modbus 人体端子", "modbus 烟雾端子", "报警器端子"};
    String[] message = {"192.168.0.200", "2003", "192.168.0.200", "2004", "2", "3", "2"};
    Button buttonbaocun, buttonquxiao;
    List<Bean> list;
    ListView listView;
    BaseA base;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        list = new ArrayList<>();
        base = new BaseA();
        listView = findViewById(R.id.listviewaaa);
        buttonbaocun = findViewById(R.id.bt_baocun);
        buttonquxiao = findViewById(R.id.bt_quxiao);
        openlist();
        onclick();

    }

    void tan(int i) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplicationContext(), R.layout.tan, null);
        EditText editText = view.findViewById(R.id.taneditText);
        Button button = view.findViewById(R.id.tanbutton);
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


    void openlist() {
        base = new BaseA();
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
                viewHoder = new ViewHoder();
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


    private void onclick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
        buttonbaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (int i = 0; i < name.length; i++) {
                    editor.putString("" + i, message[i]);
                    editor.commit();
                    Toast.makeText(shezhi.this, "保存完成", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
