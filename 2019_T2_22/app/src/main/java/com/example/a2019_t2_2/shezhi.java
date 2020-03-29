package com.example.a2019_t2_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class shezhi extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    List<Bean> list;
    ListView listView;
    Button button;
    ImageView imageView;
    BaseA base;
    String[] name = {"modbus IP地址", "modbus 端口", "一体机IP地址", "一体机端口", "报警灯端子", "推杆开端子", "推杆关端子"};
    String[] message = {"192.168.200", "952", "192.168.0.200", "2002", "2", "2", "1"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewaa);
        init();
    }

    private void init() {
        list=new ArrayList<Bean>();
        imageView=findViewById(R.id.imaaaa);
        listView = findViewById(R.id.listaaaaaaaaaa);
        button = findViewById(R.id.listbao);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        base = new BaseA();
        openlist();
        imageView.setOnClickListener(new View.OnClickListener() {
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                for (int i=0;i<name.length;i++){
                    editor.putString(""+i,message[i]);
                    editor.commit();
                }
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


    void tan(int i){
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View view= View.inflate(getApplicationContext(),R.layout.tan,null);
        EditText editText=view.findViewById(R.id.editTexttan);
        Button button=view.findViewById(R.id.tanbutton);
        alertDialog.setView(view);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message[i]=editText.getText().toString();
                openlist();
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
                viewHoder.tv_1 = view.findViewById(R.id.item1);
                viewHoder.tv_2 = view.findViewById(R.id.item2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).iname);
            viewHoder.tv_2.setText(list.get(i).imessage);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_1;
        TextView tv_2;
    }

    class Bean {
        String iname;
        String imessage;

        public Bean(String iname, String imessage) {
            this.iname = iname;
            this.imessage = imessage;
        }
    }
}
