package com.example.a2019_t3_1;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuPresenter;

import java.util.ArrayList;
import java.util.List;

public class SHEZHI extends AppCompatActivity {
    ListView listView;
    SharedPreferences sharedPreferences;
    BaseA base;
    List<Bean> list;
    Button button1, button2;
    TextView tv1;

    String[] name = {"modbus IP地址", "modbus 端口", "zigbee IP地址", "zigbee 端口", "微动开关端子", "双联继电器系列号", "zigbee照明灯联数", "上课时间", "下课时间", "光照最大值", "光照最小值"};
    String[] neirong = {"192.168.0.200", "952", "192.168.0.200", "2001", "0", "1", "1", "08:42", "10:32", "33", "3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sehzhi);
        init();
        oplist();
    }


    private void init() {
        base = new BaseA();
        list = new ArrayList<Bean>();
        listView = findViewById(R.id.listview_);
        button1 = findViewById(R.id.button4);
        button2 = findViewById(R.id.button5);
        tv1 = findViewById(R.id.textView3);
        sharedPreferences = getSharedPreferences("zhagnqian", MODE_PRIVATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                oplist();
                for (int i = 0; i < name.length; i++) {
                    editor.putString("" + i, neirong[i]);
                    editor.commit();
                }
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        Button button = findViewById(R.id.tanbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //         SharedPreferences.Editor editor=sharedPreferences.edit();
                neirong[i] = editText.toString().trim();
                //*************
                oplist();
                //***************
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void oplist() {
        listView.setAdapter(base);
        list = new ArrayList<Bean>();
        for (int i = 0; i < name.length; i++) {
            list.add(new Bean(name[i], neirong[i]));
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
                viewHoder.tv_1 = view.findViewById(R.id.tv__1);
                viewHoder.tv_2 = view.findViewById(R.id.tv__2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).bean_name);
            viewHoder.tv_2.setText(list.get(i).bean_message);

            return view;
        }
    }

    class ViewHoder {
        TextView tv_1;
        TextView tv_2;

    }

    class Bean {
        String bean_name;
        String bean_message;

        public Bean(String bean_name, String bean_message) {
            this.bean_name = bean_name;
            this.bean_message = bean_message;
        }
    }
}
