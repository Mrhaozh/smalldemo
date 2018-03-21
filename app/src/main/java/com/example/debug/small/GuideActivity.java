package com.example.debug.small;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.debug.small.guideview.ImgFragment;
import com.example.debug.small.guideview.LastImgFragment;
import com.example.debug.small.guideview.SecondImgFragment;
import com.example.debug.small.guideview.ViewpageAdapter;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/3/21.
 */

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {
    private TextView txv_one;
    private TextView txv_two;
    private TextView txv_three;
    private ImageView image;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面编号
    private int bmpWidth;//移动条图片的长度
    private int one = 0;//移动条滑动一页的距离
    private int two = 0;//移动条滑动两页的距离
    private ViewPager viewPager;
    private ImgFragment fg1;
    private SecondImgFragment fg2;
    private LastImgFragment fg3;
    private ViewpageAdapter viewpageAdapter;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = findViewById(R.id.viewpager);
        fg1 = new ImgFragment();
        fg2 = new SecondImgFragment();
        fg3 = new LastImgFragment();
        fragments = new ArrayList<>();
        fragments.add(fg1);
        fragments.add(fg2);
        fragments.add(fg3);
        viewpageAdapter = new ViewpageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewpageAdapter);
        viewPager.setCurrentItem(0);
        txv_one = findViewById(R.id.txv_one);
        txv_two = findViewById(R.id.txv_two);
        txv_three = findViewById(R.id.txv_three);
        image = findViewById(R.id.cusor_image);
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        image.setImageMatrix(matrix);//设置动画初始位置
        one = offset * 2 + bmpWidth;
        two = one * 2;
        txv_one.setOnClickListener(this);
        txv_two.setOnClickListener(this);
        txv_three.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation=null;
        switch(position){
            case 0:
                if(currIndex==1){
                    animation = new TranslateAnimation(one, 0, 0, 0);
                }else if(currIndex==2){
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if(currIndex==0){
                    animation=new TranslateAnimation(offset,one,0,0);
                }else if(currIndex==2){
                    animation=new TranslateAnimation(two,one,0,0);
                }
                break;
            case 2:
                if(currIndex==0){
                    animation=new TranslateAnimation(offset,two,0,0);
                }else if(currIndex==1){
                    animation=new TranslateAnimation(one,two,0,0);
                }
                break;
        }
        currIndex=position;
        animation.setFillAfter(true);
        animation.setDuration(300);
        image.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txv_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.txv_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.txv_three:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
