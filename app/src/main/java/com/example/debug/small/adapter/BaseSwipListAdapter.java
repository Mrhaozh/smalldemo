package com.example.debug.small.adapter;

import android.widget.BaseAdapter;

/**
 * Created by lenovo on 2018/3/8.
 */

public abstract class BaseSwipListAdapter extends BaseAdapter{
    public boolean getSwipEnableByPosition(int position){
        return true;
    }
}
