package com.example.debug.small.myutils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/3/5.
 */

public class FileHelper {
    private Context mcontext;
    public FileHelper(Context mContext){
        super();
        this.mcontext=mcontext;
    }
    public void save(String name,String password){
        SharedPreferences sp = mcontext.getSharedPreferences("info", mcontext.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name", name);
        editor.putString("pwd", password);
        editor.commit();
    }
    public Map<String,String> read() {
        Map<String, String> map = new HashMap<>();
        SharedPreferences sp = mcontext.getSharedPreferences("info", mcontext.MODE_PRIVATE);
        map.put("username", sp.getString("name", ""));
        map.put("password", sp.getString("pwd", ""));
        return map;
    }
}
