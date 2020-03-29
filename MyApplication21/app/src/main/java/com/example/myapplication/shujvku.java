package com.example.myapplication;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import java.util.ArrayList;
import java.util.List;

public class shujvku extends AppCompatActivity {
    List<Bean> list;
    Base base;

    ListView listView;
    MySqliteHelp mySqliteHelp;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView = findViewById(R.id.list_view);
        list = new ArrayList<Bean>();

        base = new Base();
        mySqliteHelp = new MySqliteHelp(this);
        sqLiteDatabase = mySqliteHelp.getWritableDatabase();
        for (int i = 0; i < 20; i++) {
            insert(i);
        }
        query();
        listView.setAdapter(base);

    }


    private void insert(int i) {
        ContentValues values = new ContentValues();
        values.put("_id", "id" + i);
        values.put("name", "name" + i);
        values.put("age", "age" + i);
        sqLiteDatabase.insert("qian", null, values);
    }

    private void query() {
        Cursor cursor = sqLiteDatabase.query("qian", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            Bean bean = new Bean(id, name, age);
            list.add(bean);
        }

    }

    class Base extends BaseAdapter {
        ViewHoder viewHoder = null;

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
                viewHoder.tv_age = view.findViewById(R.id.tv_id);
                viewHoder.tv_name = view.findViewById(R.id.tv_name);
                viewHoder.tv_age = view.findViewById(R.id.tv_age);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.tv_id.setText(list.get(i).id);
            viewHoder.tv_name.setText(list.get(i).name);
            viewHoder.tv_age.setText(list.get(i).age);
            return view;
        }
    }

    class ViewHoder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_age;
    }

    class Bean {
        String id;
        String name;
        String age;

        public Bean(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

}
