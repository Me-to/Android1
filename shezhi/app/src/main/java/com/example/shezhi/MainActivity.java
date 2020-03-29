package com.example.shezhi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_queding, btn_quxiao, btn_tan;
    ListView listView;
    List<Bean> list = new ArrayList<>();
    EditText editText;
    MyAdapter myAdapter = new MyAdapter();
    SharedPreferences sharedPreferences;
    String[] str_name = {"modbus IP地址", "modbus 端口", "一体机 IP地址", "报警灯端子", "推杆开端子", "推杆关端子"};
    String[] str_message = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onclick();
    }



    /**************************右上角一个ActionBar***************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_zhuce:
                Intent a=new Intent(this,zhuce.class);
                startActivity(a);
                break;
                default:
                    break;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

    private void onclick() {
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < str_message.length; i++) {
                    str_message[i] = sharedPreferences.getString("" + i, "null");

                }
            }
        });
        /****************保存获得的信息********************/
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for (int i = 0; i < str_name.length; i++) {
                    editor.putString("" + i, str_message[i]);
                    editor.commit();
                }
            }
        });
        /****************************************   语句记忆不全。  ******************************************************/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tanchuang(i);
            }
        });
    }

    private void tanchuang(int i) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(getApplication(), R.layout.tanchuang, null);
        editText = view.findViewById(R.id.et_xinxi);
        btn_tan = view.findViewById(R.id.btn_tanchuang);
        alertDialog.setView(view);
        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = editText.getText().toString().trim();
                str_message[i] = a;
                openlist();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }


    private void init() {
        btn_queding = findViewById(R.id.btn_queding);
        btn_quxiao = findViewById(R.id.quxiao);
        listView = findViewById(R.id.list_view);
        /****************************************   语句记忆不全。getSharedPreferences  ******************************************************/
        sharedPreferences = getSharedPreferences("zhang", Context.MODE_PRIVATE);
        for (int i = 0; i < str_message.length; i++) {
            str_message[i] = sharedPreferences.getString("" + i, "NULL");
        }
        openlist();
    }

    private void openlist() {


        listView.setAdapter(myAdapter);
        /*********************一定要新建一个list=new ArrayList<>();否则会一直新建ListView**************************/
        list = new ArrayList<>();
        /*****************没有添加数据*******************/
        for (int i = 0; i < str_name.length; i++) {
            list.add(new Bean(str_name[i], str_message[i]));
        }
        /******************不知道怎么更新******************/
        myAdapter.notifyDataSetChanged();

    }

    class MyAdapter extends BaseAdapter {
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
                view = LinearLayout.inflate(getApplication(), R.layout.item, null);
                viewHoder.tv_mesage = view.findViewById(R.id.tv_message);
                viewHoder.tv_name = view.findViewById(R.id.tv_name);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_name.setText(list.get(i).item_name);
            viewHoder.tv_mesage.setText(list.get(i).item_message);
            return view;
        }
    }


    class ViewHoder {
        TextView tv_name;
        TextView tv_mesage;
    }

    class Bean {
        String item_name;
        String item_message;

        public Bean(String item_name, String item_message) {
            this.item_name = item_name;
            this.item_message = item_message;
        }
    }
}
