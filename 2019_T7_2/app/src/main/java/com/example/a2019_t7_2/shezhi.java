package com.example.a2019_t7_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    List<Bean> list;
    ListView listView;
    Button button1, button2;
    BaseA base;
    TextView textView;
    RadioButton radioButton1, radioButton2;
    SharedPreferences sharedPreferences;
    String[] name = {"zigbee IP地址", "ziggbee端口", "双联继电器系列号", "风扇联数", "照明灯联数", "湿度通道号", "湿度最高值", "湿度最低值", "光照最高值", "光照最低值"};
    String[] message = {"192.168.1.110", "952", "1", "2", "1", "4", "22", "11", "6", "2"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        list = new ArrayList<Bean>();
        base = new BaseA();
        listView = findViewById(R.id.listviewaaa);
        textView = findViewById(R.id.taaaa);
        button1 = findViewById(R.id.button1);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        button2 = findViewById(R.id.button2);
        openlist();
        onclick();
    }

    private void onclick() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (int i = 0; i < name.length; i++) {
                    editor.putString("" + i, message[i]);
                    editor.commit();

                }
            }
        });
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("a", "1");
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("a", "2");
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shezhi.this, MainActivity.class);
                startActivityForResult(intent, 1);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });

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
            viewHoder = new ViewHoder();
            if (view == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.tv_1 = view.findViewById(R.id.item1);
                viewHoder.tv_2 = view.findViewById(R.id.item2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).bean1);
            viewHoder.tv_2.setText(list.get(i).bean2);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_1, tv_2;
    }

    class Bean {
        String bean1, bean2;

        public Bean(String bean1, String bean2) {
            this.bean1 = bean1;
            this.bean2 = bean2;
        }
    }
}
