package com.example.a2019_t2_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shezhi extends AppCompatActivity {
    EditText editText;
    Button btn_queding, btn_quxiao, btn_tan_queding;
    String[] strname = {"modbus IP地址", "modbus 端口", "一体机 IP地址", "一体机地址", "报警灯端子", "推杆开端子", "推杆关端子"};
    String[] strmessage = new String[7];
    Myadapter myadapter;
    ListView listView;
    SharedPreferences sharedPreferences;
    List<Bean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
        tanchaung();
    }

    private void init() {
        btn_queding = findViewById(R.id.btn_baocun);
        listView = findViewById(R.id.list_view);
        btn_quxiao = findViewById(R.id.btn_quxiao);
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        for (int i = 0; i < strmessage.length; i++) {
            strmessage[i] = sharedPreferences.getString("" + i, "NULL");
        }
        openlist();
    }
/********************       这个我忘了打   界面确定取消按钮      ****************************/
    private void dianji(){
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                for (int i=0;i<strmessage.length;i++){
                    editor.putString(""+i,strmessage[i]);
                    editor.commit();
                }
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<strmessage.length;i++){
                    strmessage[i]=sharedPreferences.getString(""+i,"NULL");
                }
                openlist();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tanchaung(i);
            }
        });
    }

    /************************   忘了在里面传入 int i  ***************************/
    private void tanchaung(int i) {
        View view = View.inflate(getApplicationContext(), R.layout.tanchuang, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        editText = view.findViewById(R.id.editText);
        btn_tan_queding = findViewById(R.id.button);

        /***********************    这个是来分辨每个listview是那个值   ****************************/
        btn_tan_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strmessage[i]=editText.getText().toString().trim();
                openlist();
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String a = editText.getText().toString();
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("" + i, a);
//                editor.commit();
//                alertDialog.dismiss();
//            }
//        });
        alertDialog.show();
        openlist();
    }

    private void openlist() {
        listView.setAdapter(myadapter);
        list = new ArrayList<>();
        for (int i = 0; i < strmessage.length; i++) {
            list.add(new Bean(strname[i], strmessage[i]));

        }
        /************************  没打，无法刷新  ***************************/
        myadapter.notifyDataSetChanged();
    }

    class Myadapter extends BaseAdapter {
        ViewHoder viewHoder = new ViewHoder();

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
                view = LinearLayout.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.tv_message = view.findViewById(R.id.tv_message);
                viewHoder.tv_name = view.findViewById(R.id.tv_name);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_name.setText(list.get(i).itemname);
            viewHoder.tv_message.setText(list.get(i).itemmessage);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_name;
        TextView tv_message;
    }

    class Bean {
        String itemname;
        String itemmessage;

        public Bean(String itemname, String itemmessage) {
            this.itemname = itemname;
            this.itemmessage = itemmessage;
        }
    }
}
