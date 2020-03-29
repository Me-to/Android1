package com.example.MyApplication;

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
    Button buttonqueding,buttonquxiao;
    Base base;
    TextView textView;
    SharedPreferences sharedPreferences;
    ListView listView;
    List<Bean> list;
    String[] name={"modbus IP地址","modbus IP端口","LED IP地址","LED 端口","红外对射DI口","车位数量"};
    String [] message={"192.125","4554","45","5","2","5"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        buttonqueding=findViewById(R.id.btn2_que);
        buttonquxiao=findViewById(R.id.btn2_quxiao);
        textView=findViewById(R.id.tv_2);
        list = new ArrayList<Bean>();
        base=new Base();
        listView=findViewById(R.id.listvieww);
        sharedPreferences=getSharedPreferences("A",MODE_PRIVATE);
        onclick();
        openlist();
    }

    private void onclick() {
        buttonquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                for (int i=0;i<name.length;i++){
                   editor.putString(""+i,message[i]);
                   editor.apply();
                }
                Toast.makeText(shezhi.this, "保存完成", Toast.LENGTH_SHORT).show();
            }
        });
        buttonqueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tan(position);
            }
        });
    }
    private void tan(int i){
        AlertDialog alertDialog=new AlertDialog.Builder(getApplicationContext()).create();
        View view=View.inflate(getApplicationContext(),R.layout.tan,null);
        EditText editText=view.findViewById(R.id.ettan_xinxi);
        Button button=view.findViewById(R.id.bt_tan);
        alertDialog.setView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message[i]=editText.getText().toString().trim();
                openlist();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void openlist(){
        listView.setAdapter(base);
        list=new ArrayList<>();
        for (int i=0;i<name.length;i++){
            list.add(new Bean(name[i],message[i]));
        }
        base.notifyDataSetChanged();
    }

    class Base extends BaseAdapter {
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
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                viewHoder = new ViewHoder();
                convertView = View.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.tv_1 = convertView.findViewById(R.id.tv_item1);
                viewHoder.tv_2 = convertView.findViewById(R.id.tv_item2);
                convertView.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) convertView.getTag();
            }
            viewHoder.tv_1.setText(list.get(position).item1);
            viewHoder.tv_2.setText(list.get(position).item2);
            return convertView;
        }
    }

    class Bean {
        String item1;
        String item2;

        public Bean(String item1, String item2) {
            this.item1 = item1;
            this.item2 = item2;
        }
    }

    class ViewHoder {
        TextView tv_1, tv_2;
    }

}
