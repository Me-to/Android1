package com.example.a2019_t12_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context) {
        super(context, "info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists  person(_id interget primary key  ,name text,icon_path text,name_number text,phone text,passworld text,job text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
