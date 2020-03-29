package com.example.a2019_t5_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    Button button1;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    List<Bean> list;
    ListView listView;
    String[] name = {"zigbee IP地址", "zigbee 端口", "温度通道", "湿度通道"};
    String[] message = {"192.168.0.200", "2001", "4", "2"};
    BaseA base;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }


    void tan(int i) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(this, R.layout.tan, null);
        Button button = view.findViewById(R.id.tanbutton);
        EditText editText = view.findViewById(R.id.taneditText);
        alertDialog.setView(view);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message[i] = editText.getText().toString().trim();
                alertDialog.dismiss();
                openlist();
            }
        });
    }

    private void init() {
        list = new ArrayList<Bean>();
        base = new BaseA();
        listView = findViewById(R.id.listviewa);
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (int i = 0; i < name.length; i++) {
                    editor.putString("" + i, message[i]);
                    editor.commit();

                }
                Toast.makeText(shezhi.this, "保存完成", Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        openlist();
    }


    private void openlist() {
        listView.setAdapter(base);
        list = new ArrayList<Bean>();
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
                view = LinearLayout.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.tv_1 = view.findViewById(R.id.item_1);
                viewHoder.tv_2 = view.findViewById(R.id.item_2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).bean_1);
            viewHoder.tv_2.setText(list.get(i).bean_2);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_1;
        TextView tv_2;
    }

    class Bean {
        String bean_1;
        String bean_2;

        public Bean(String bean_1, String bean_2) {
            this.bean_1 = bean_1;
            this.bean_2 = bean_2;
        }
    }

}
