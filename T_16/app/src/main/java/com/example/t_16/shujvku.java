package com.example.t_16;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class shujvku extends AppCompatActivity {
    List<Bean> list;
    MainActivity m;
    ListView listView;
    BaseA base;
    String TAG="-----------";
    MySqliteHelper mySqliteHelper;
    SQLiteDatabase sqLiteOpenHelper;
public Handler handler=new Handler();
    //private int []a={"#FFF"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shujvku);
        init();


    }

    private void init() {
        list=new ArrayList<>();
        listView=findViewById(R.id.listviewww);
        base=new BaseA();
        listView.setAdapter(base);
        m = new MainActivity();
        mySqliteHelper = new MySqliteHelper(this);
        sqLiteOpenHelper = mySqliteHelper.getWritableDatabase();
        openlist();

    }

    void openlist() {
        listView.setAdapter(base);
        list = new ArrayList<Bean>();
        for (int i=0;i<list.size();i++){

        }
        base.notifyDataSetChanged();

    }
    public void a(){
        handler.post(runnable1);
    }

Runnable  runnable1 =new Runnable() {
        @Override
        public void run() {
            Insert();
            Qurey();
            Log.e(TAG, "run:" );
            Log.e(TAG, m.guang1);
            Log.e(TAG, m.wen1);
            Log.e(TAG, m.ren1);  Log.e(TAG, m.kong1);
            Log.e(TAG, m.shi1);

            handler.postDelayed(runnable1,1000);
        }
    };

    void Insert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.WEN, "温度：" + m.wen1 + "C");
        contentValues.put(Constant.SHI, "湿度：" + m.shi1 + "%");
//        if (m.ren1.equals("1")) {
//            contentValues.put(Constant.REN, "人体：有人");
//        } else {
//            contentValues.put(Constant.REN, "人体：无人");
//        }

        contentValues.put(Constant.GUANG, "光照：" + m.guang1);
        contentValues.put(Constant.KONG, "空气质量：" + m.kong1);
        openlist();

    }

    void Qurey() {
        Cursor cursor = sqLiteOpenHelper.query("qian", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String wen = cursor.getString(0);
            String shi = cursor.getString(1);
            String ren = cursor.getString(2);
            String guang = cursor.getString(3);
            String kong = cursor.getString(4);
            Bean bean = new Bean(wen, shi, ren, kong, guang);
            list.add(bean);
            openlist();
        }
    }

    class BaseA extends BaseAdapter {
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
        public View getView(int i, View view, ViewGroup parent) {
            viewHoder = new ViewHoder();
            if (view == null) {
                view = LinearLayout.inflate(getApplicationContext(), R.layout.item, null);
                viewHoder.wen = view.findViewById(R.id.wen2);
                viewHoder.shi = view.findViewById(R.id.shi2);
                viewHoder.guang = view.findViewById(R.id.guang2);
                viewHoder.kong = view.findViewById(R.id.kong2);
                viewHoder.ren = view.findViewById(R.id.ren2);
                view.setTag(viewHoder);
            } else {
                viewHoder = (ViewHoder) view.getTag();
            }
            viewHoder.wen.setText(list.get(i).wen);
            viewHoder.shi.setText(list.get(i).shi);
            viewHoder.ren.setText(list.get(i).ren);
            viewHoder.kong.setText(list.get(i).kong);
            viewHoder.guang.setText(list.get(i).guang);
            return view;
        }
    }

    class ViewHoder {
        TextView wen, shi, guang, ren, kong;
    }

    class Bean {
        String wen, shi, ren, kong, guang;

        public Bean(String wen, String shi, String ren, String kong, String guang) {
            this.wen = wen;
            this.shi = shi;
            this.ren = ren;
            this.kong = kong;
            this.guang = guang;
        }
    }

}
