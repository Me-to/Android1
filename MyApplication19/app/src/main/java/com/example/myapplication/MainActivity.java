package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MySqliteHelper mySqliteHelper;
    SQLiteDatabase sqLiteDatabase;
    List<Bean> list;
    Base base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        listView=findViewById(R.id.list_view);
        base=new Base();
        mySqliteHelper=new MySqliteHelper(this,"zhang.db",null,1);
        sqLiteDatabase=mySqliteHelper.getWritableDatabase();
        for (int i=0;i<20;i++){
            Insert(i);
        }
        listView.setAdapter(base);


    }


    private void Insert(int i ){
        ContentValues values=new ContentValues();
        values.put("_id",i);
        values.put("name","姓名"+i);
        values.put("age","年龄"+i);

        sqLiteDatabase.insert("person",null,values);
    }

    private void  Query (){
        Cursor cursor=sqLiteDatabase.query("person",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name= cursor.getString(1);
            String  age= cursor.getString(2);

            Bean bean=new Bean(id,name,age);
            list.add(bean);


        }
    }

    class  Base extends BaseAdapter{

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
            if (view==null){
                viewHoder=new ViewHoder();
                view= LinearLayout.inflate(getApplicationContext(),R.layout.item,null);
                viewHoder.tv_id=view.findViewById(R.id.tv_1);
                viewHoder.tv_name=view.findViewById(R.id.tv_2);
                viewHoder.tv_age=view.findViewById(R.id.tv_3);
                view.setTag(viewHoder);
            }else {
                viewHoder= (ViewHoder) view.getTag();
            }
            viewHoder.tv_id.setText(list.get(i).id);
            viewHoder.tv_age.setText(list.get(i).age);
            viewHoder.tv_name.setText(list.get(i).name);
           return view;
        }

    }

    class  ViewHoder{
        TextView tv_id;
        TextView tv_name;
        TextView tv_age;
    }
    class Bean{
        String id,name,age;

        public Bean(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }


}
