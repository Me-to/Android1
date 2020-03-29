package com.example.t_16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(@Nullable Context context) {
        super(context, Constant.DATA_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql="create table if not exists " +
//                Constant.TABLE_NAME+"(" +
//                Constant._ID+" Integer primary key autoincrement ," +
//                Constant.NAME+ " varchar(20)," +
//                Constant.AGE+" varchar(20)" +
//                ")";
        String sql="create TABLE if not exists qian(_id Integer primary key autoincrement,wen varchar(20),shi varchar(20),ren varchar(20),kong varchar(20),guan varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
