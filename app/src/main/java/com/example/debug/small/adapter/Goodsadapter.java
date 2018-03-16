package com.example.debug.small.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.debug.small.R;
import com.example.debug.small.bean.Goodsbean;

import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/3/6.
 */

public class Goodsadapter extends BaseSwipListAdapter{
    private ArrayList<Goodsbean> goodsbeans;
    private Context mContext;
    public Goodsadapter(ArrayList<Goodsbean> goodsbeans, Context mContext){
        this.goodsbeans=goodsbeans;
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return goodsbeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.main_title = convertView.findViewById(R.id.main_title);
            viewHolder.sub_title = convertView.findViewById(R.id.sub_title);
            viewHolder.main_image = convertView.findViewById(R.id.main_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.main_title.setText(goodsbeans.get(position).getGoods_name());
        viewHolder.sub_title.setText(goodsbeans.get(position).getGoods_detail());
        String imgurl = "http://a3.att.hudong.com/08/93/01300000171625121722936591061.jpg";
        //Glide.with(mContext).load(imgurl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.main_image);
        Glide.with(mContext).load(imgurl).animate(R.anim.item_alpha_in).into(viewHolder.main_image);
        return convertView;
    }
    private class ViewHolder{
        TextView main_title;
        TextView sub_title;
        ImageView main_image;
    }

    @Override
    public boolean getSwipEnableByPosition(int position) {
        if(position % 2==0){
            return false;
        }
        return true;
    }
}
