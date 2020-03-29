package com.example.a2019_t3_1;

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
    ListView listView;
    EditText editText;
    Button btn_tan;
    List<Bean> list;
    MyAdapter myAdapter;
    SharedPreferences sharedPreferences;
    String[] strNane={"modbus IP地址","modbus 端口","一体机 IP地址","一体机 端口","zigbee IP地址","zigbee 端口","推杆开端子","推杆关端子","双联继电器系列号","照明灯联数","风扇联数"};
    String[] strMessage=new String[11];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
        onclick();
    }

    private void init() {
        list=new ArrayList<>();
        myAdapter=new MyAdapter();
        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
        listView=findViewById(R.id.list_view);
        for (int i=0;i<strMessage.length;i++){
            strMessage[i]=sharedPreferences.getString(""+i,"NULL");
        }


    }
    private void onclick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tanchaung(i);
            }
        });
    }
    private void tanchaung(int i){
        View view=View.inflate(getApplicationContext(),R.layout.tanchuang,null);
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        btn_tan=findViewById(R.id.btn_tan);
        editText=findViewById(R.id.et_tanxin);
        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strMessage[i]=editText.getText().toString();
                openlist();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(""+i,strMessage[i]);
                editor.commit();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void openlist(){
        listView.setAdapter(myAdapter);
        list=new ArrayList<>();

        for (int i=0;i<strMessage.length;i++){
            list.add(new Bean(strNane[i],strMessage[i]));


        }
        //忘了刷新的语句
        myAdapter.notifyDataSetChanged();

    }

    class MyAdapter extends BaseAdapter{
ViewHoder viewHoder=new ViewHoder();
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
            if (view==null){
                view= LinearLayout.inflate(getApplicationContext(),R.layout.item,null);
                viewHoder.tv_message=view.findViewById(R.id.tv_item2);
                viewHoder.tv_name=view.findViewById(R.id.tv_item1);
                view.setTag(viewHoder);
            }else {
                viewHoder= (ViewHoder) view.getTag();
            }
            viewHoder.tv_name.setText(list.get(i).Beanname);
            viewHoder.tv_message.setText(list.get(i).Benamessage);

            return view;
        }
    }


    class  ViewHoder{
        TextView tv_name;
        TextView tv_message;
    }

    class  Bean{
        String Beanname;
        String Benamessage;

        public Bean(String beanname, String benamessage) {
            Beanname = beanname;
            Benamessage = benamessage;
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
