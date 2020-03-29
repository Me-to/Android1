package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_bao,btn_qu;
Base base;
    SharedPreferences sharedPreferences;
    List<Bean> list;
    String[] stringName = {"modbusIP地址", "modbus端口", "一体机IP地址", "一体机端口", "报警灯端子", "推杆开端子", "推杆关端子"};
    String[] stringMessage = new String[7];
    ListView listView;
    String TAG = "------------------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
    }
    private void openlist(){

    }

    private void onclick() {
        btn_qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                for (int i=0;i<stringName.length;i++){
                    editor.putString(""+i,stringMessage[i]);
                    editor.commit();
                }
                Toast.makeText(MainActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    private void init() {
        btn_bao=findViewById(R.id.buttonbao);
        btn_qu=findViewById(R.id.buttonqu);
        listView=findViewById(R.id.list_view);
        list = new ArrayList<Bean>();
        base=new Base();
        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
        for (int i=0;i<stringMessage.length;i++){
            stringMessage[i]=sharedPreferences.getString(""+i,"Null");
        }
    }

    class Base extends BaseAdapter {
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
                viewHoder.tV_name = view.findViewById(R.id.tv_sName);
                viewHoder.tv_message = view.findViewById(R.id.tv_sMessage);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tV_name.setText(list.get(i).sName);
            viewHoder.tv_message.setText(list.get(i).sMessageg);
            return view;
        }
    }

    class ViewHoder {
        TextView tV_name;
        TextView tv_message;

    }

    class Bean {
        String sName;
        String sMessageg;

        public Bean(String sName, String sMessageg) {
            this.sName = sName;
            this.sMessageg = sMessageg;
        }
    }
}
