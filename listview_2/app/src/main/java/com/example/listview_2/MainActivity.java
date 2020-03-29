package com.example.listview_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String[] Name={"modbusIP地址","modbus端口","一体机IP地址","一体机端口","报警灯端子","推杆开端子","推杆关端子"};
    String[] Message=new String[7];
    SharedPreferences sharedPreferences;
    ListView listView;
    List<Bean> list;
    Button buttonbaocun,buttonqueding;
    BaseA base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    init();
    onlick();
    }

    private void onlick() {
        buttonqueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonbaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                for (int i=0;i<Name.length;i++){
                    editor.putString(""+i,Message[i]);
                    editor.commit();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tan(i);
            }
        });
    }

    private void tan(final int i) {
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View view=View.inflate(getApplicationContext(),R.layout.tan,null);
        Button button=view.findViewById(R.id.tanbutton3);
        final EditText editText=view.findViewById(R.id.taneditText);
        alertDialog.setView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message[i]=editText.getText().toString().trim();
                openlist();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void init() {

        base=new BaseA();
        list=new ArrayList<Bean>();
        listView=findViewById(R.id.listview);
        buttonbaocun=findViewById(R.id.button);
        buttonqueding=findViewById(R.id.button2);
        sharedPreferences=getSharedPreferences("aaa",MODE_PRIVATE);
        for (int i=0;i<Name.length;i++){
            Message[i]=sharedPreferences.getString(""+i,"null");
        }
        openlist();
    }


    private void openlist(){
        listView.setAdapter(base);
        list=new ArrayList<Bean>();
        for (int i=0;i<Name.length;i++){
            list.add(new Bean(Name[i],Message[i]));
        }
        base.notifyDataSetChanged();
    }
    class BaseA extends BaseAdapter{
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
            viewHoder=new ViewHoder();
            if (view==null){
                view= LinearLayout.inflate(getApplicationContext(),R.layout.item,null);

                viewHoder.tv_1=view.findViewById(R.id.textView1);
                viewHoder.tv_2=view.findViewById(R.id.textView2);
                view.setTag(viewHoder);
            }else {
                viewHoder= (ViewHoder) view.getTag();
            }
            viewHoder.tv_1.setText(list.get(i).name);
            viewHoder.tv_2.setText(list.get(i).message);
            return view;
        }
    }
    class ViewHoder{
        TextView tv_1;
        TextView tv_2;
    }
    class Bean{
        String name;
        String message;

        public Bean(String name, String message) {
            this.name = name;
            this.message = message;
        }
    }

}
