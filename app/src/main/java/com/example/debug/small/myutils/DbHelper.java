package com.example.debug.small.myutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/3/5.
 */

public class DbHelper extends SQLiteOpenHelper{
    public DbHelper(Context context){
        super(context, "mydb.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS hello(id integer primary key autoincrement," +
                "name varchar(20) not null unique,phone varchar(20) not null unique)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table hello add account varchar(20)");
    }
}
