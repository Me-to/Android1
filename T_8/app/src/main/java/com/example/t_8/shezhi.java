package com.example.t_8;

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
    String[] name = {"zigbee IP地址", "zigbee 端口", "双联继电器系列号", "风扇联数", "照明灯联数", "温度通道号", "湿度通道号", "光照最高值", "光照最低值"};
    String[] message = {"192.168.0.200", "2001", "1", "2", "1", "4", "22", "11", "6", "2"};
    List<Bean> list;
    BaseA base;
    ListView listView;
    SharedPreferences sharedPreferences;
    RadioButton zi, shou;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
        list = new ArrayList<>();
        button = findViewById(R.id.button2_queding);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zi = findViewById(R.id.radioButtonzi);
        shou.findViewById(R.id.radioButtonshou);
        listView = findViewById(R.id.listviewa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tan(position);
            }
        });
        if (sharedPreferences.getString("o", null).equals("1")) {
            zi.setChecked(true);
        } else if (sharedPreferences.getString("o", null).equals("2")) {
            shou.setChecked(true);
        }
        zi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("o", "1");
                editor.apply();

            }
        });
        shou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("o", "2");
                editor.apply();
            }
        });
open();
    }

    void open() {
        list = new ArrayList<>();
        listView.setAdapter(base);
        for (int i = 0; i < name.length; i++) {
            list.add(new Bean(name[i], message[i]));
        }
        base.notifyDataSetChanged();
    }

    void tan(int i) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplicationContext(), R.layout.tan, null);
        Button button = view.findViewById(R.id.tan_bt);
        EditText editText = view.findViewById(R.id.tan_editText);
        alertDialog.setView(view);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message[i] = editText.getText().toString().trim();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("" + i, message[i]);
                editor.apply();
                open();
                alertDialog.dismiss();
            }
        });
    }

    class BaseA extends BaseAdapter {
        ViewHoder viewHoder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            if (view == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.name = view.findViewById(R.id.item_name);
                viewHoder.message = view.findViewById(R.id.item_message);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();

            }
            viewHoder.name.setText(list.get(i).item1);
            viewHoder.message.setText(list.get(i).item2);
            return view;
        }
    }

    class ViewHoder {
        TextView name, message;
    }

    class Bean {
        String item1, item2;

        public Bean(String item1, String item2) {
            this.item1 = item1;
            this.item2 = item2;
        }
    }
}
