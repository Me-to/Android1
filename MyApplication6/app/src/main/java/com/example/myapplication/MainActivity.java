package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<Bean> list=new ArrayList<>();
ListView listView;
MyAdapter myAdapter=new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            for (int i=0;i<7;i++){
                list.add(new Bean("姓名"+i,"张前"+i));
            }
            listView=findViewById(R.id.list_view);
            listView.setAdapter(myAdapter);

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
                viewHoder.tv_age=view.findViewById(R.id.tv_2);
                viewHoder.tv_name=view.findViewById(R.id.tv_1);
                view.setTag(viewHoder);
            }else {
                viewHoder= (ViewHoder) view.getTag();
            }
            viewHoder.tv_name.setText(list.get(i).name);
            viewHoder.tv_age.setText(list.get(i).age);
            return view;
        }
    }

    class ViewHoder{
        TextView tv_name;
        TextView tv_age;
    }
    class Bean{
        String name;
        String age;

        public Bean(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

}
