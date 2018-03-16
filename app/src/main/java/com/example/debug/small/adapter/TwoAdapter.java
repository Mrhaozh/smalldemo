package com.example.debug.small.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debug.small.R;

import java.util.List;

/**
 * Created by lenovo on 2018/3/8.
 */

public class TwoAdapter extends BaseSwipListAdapter{
    private List<ApplicationInfo> mAppList;
    private Context mContext;
    public TwoAdapter(List<ApplicationInfo> mAppList,Context mContext){
        this.mContext=mContext;
        this.mAppList=mAppList;
    }
    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_app, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.iv_icon);
            viewHolder.textView=convertView.findViewById(R.id.iv_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        ApplicationInfo item=getItem(position);
        viewHolder.imageView.setImageDrawable(item.loadIcon(mContext.getPackageManager()));
        viewHolder.textView.setText(item.loadLabel(mContext.getPackageManager()));
        return convertView;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    @Override
    public boolean getSwipEnableByPosition(int position) {
        if(position % 2 == 0){
            return false;
        }
        return true;
    }
}
