package com.example.a2019_t2_1;

import android.app.AlertDialog;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    BaseA base;
    ImageView imageView;
    ListView listView;
    List<Bean> list;
    String[] name = {"modbus IP地址", "modbus 端口", "一体机 IP地址", "一体机 端口", "zigbee IP地址", "zigbee 端口", "推杆开端子", "推杆关端子", "双联继电器系列号", "照明灯联数", "风扇联数", "设置界限值"};
    String[] message = {"192.168.0.200", "2003", "192.168.0.200", "2002", "192.168.200", "950", "1", "0", "1", "1", "2", "30"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        list = new ArrayList<Bean>();
        base = new BaseA();
        imageView=findViewById(R.id.shezhi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView = findViewById(R.id.listviewaa);
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        openlist();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
    }

    void tan(final int i) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplicationContext(), R.layout.tan, null);
        final EditText editText = view.findViewById(R.id.tanbuttoneditText);
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
                viewHoder.textView1 = view.findViewById(R.id.itemtextView4);
                viewHoder.textView2 = view.findViewById(R.id.itemtextView5);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.textView1.setText(list.get(i).bean_1);
            viewHoder.textView2.setText(list.get(i).bean_2);
            return view;
        }
    }

    class ViewHoder {
        TextView textView1;
        TextView textView2;
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
