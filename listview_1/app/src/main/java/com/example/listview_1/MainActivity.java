package com.example.listview_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] Name = {"modbusIP地址", "modbus端口", "一体机IP地址", "一体机端口", "报警灯端子", "推杆开端子", "推杆关端子"};
    String[] Message = new String[7];
    ListView listView;
    List<Bean> list;
    BaseA base;
    SharedPreferences sharedPreferences;
    Button buttonq, buttonfinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onlick();


    }

    private void tan(final int i) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplicationContext(), R.layout.tan, null);
        Button button = view.findViewById(R.id.tanbutton);
        final EditText editText = view.findViewById(R.id.taneditText);
        alertDialog.setView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message[i] = editText.getText().toString().trim();
                oplist();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void onlick() {
        buttonfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                for (int i = 0; i < Name.length; i++) {
                    editor.putString("" + i, Message[i]);
                    editor.commit();
                }
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
    }

    private void oplist() {

        listView.setAdapter(base);
       list = new ArrayList<Bean>();
        for (int i = 0; i < Name.length; i++) {
            list.add(new Bean(Name[i], Message[i]));
        }
        base.notifyDataSetChanged();
    }

    private void init() {

        list = new ArrayList<Bean>();
        base = new BaseA();
        listView = findViewById(R.id.listview);
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        buttonq = findViewById(R.id.button1);
        buttonfinish = findViewById(R.id.button2);
        for (int i = 0; i < Name.length; i++) {
            Message[i] = sharedPreferences.getString("" + i, "null");
        }
        oplist();

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
                view = LinearLayout.inflate(getApplicationContext(),R.layout.item,null);
                viewHoder.tv1 = view.findViewById(R.id.textView1);
                viewHoder.tv_2 = view.findViewById(R.id.textView2);
                view.setTag(viewHoder);
            } else {
               viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv1.setText(list.get(i).a);
            viewHoder.tv_2.setText(list.get(i).b);

            return view;
        }
    }

    class ViewHoder {
        TextView tv1;
        TextView tv_2;
    }

    class Bean {
        String a;
        String b;

        public Bean(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

