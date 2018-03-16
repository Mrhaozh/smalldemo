package com.example.debug.small.myutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.debug.small.myutils.DbHelper;

/**
 * Created by lenovo on 2018/3/5.
 */

public class DbUtils {
    private DbHelper dbHelper;
    public DbUtils(Context context){
        dbHelper=new DbHelper(context);
    }
    //插入数据
    public long addData(String name,String phone){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone", phone);
        long rowid = sqLiteDatabase.insert("hello", null, contentValues);
        sqLiteDatabase.close();
        return rowid;
    }
    //删除数据
    public int deleteData(String name){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete("hello", "name=?", new String[]{name});
        sqLiteDatabase.close();
        return deleteResult;
    }
    //修改数据
    public int updateData(String name,String newphone){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", newphone);
        int updateResult = sqLiteDatabase.update("hello",contentValues,
                "name=?", new String[]{name});
        sqLiteDatabase.close();
        return updateResult;
    }
    //查找数据
    public String alterData(String name){
        String phone=null;
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("hello", new String[]{"phone"},
                "name=?", new String[]{name}, null, null, null);
        if(cursor.moveToNext()){
            phone = cursor.getString(0);
        }
        cursor.close();
        sqLiteDatabase.close();
        return phone;
    }
}
