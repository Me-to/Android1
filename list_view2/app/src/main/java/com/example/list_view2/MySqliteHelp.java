package com.example.list_view2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteHelp extends SQLiteOpenHelper {
    public MySqliteHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
         super(context,name,factory,version);
        // super(context, "zhangqian", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sa="create table if not exists person(_id INTEGER PRIMARY KEY,name varchar(20),age varchar(20))";
       //   String sql="create table if not exists person (_id Integer primary key autoincrement,name varchar(20),age varchar(20) ) ";
          sqLiteDatabase.execSQL(sa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
