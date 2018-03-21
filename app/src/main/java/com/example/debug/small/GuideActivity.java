package com.example.debug.small;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.debug.small.guideview.ImgFragment;
import com.example.debug.small.guideview.LastImgFragment;
import com.example.debug.small.guideview.ViewpageAdapter;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/3/21.
 */

public class GuideActivity extends AppCompatActivity{
    private TextView txv_one;
    private TextView txv_two;
    private TextView txv_three;
    private ViewPager viewPager;
    private ImgFragment fg1;
    private ImgFragment fg2;
    private LastImgFragment fg3;
    private ViewpageAdapter viewpageAdapter;
    private ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager=findViewById(R.id.viewpager);
        fg1=new ImgFragment(R.layout.guide_first);
        fg2=new ImgFragment(R.layout.guide_second);
        fg3=new LastImgFragment();
        fragments=new ArrayList<>();
        fragments.add(fg1);
        fragments.add(fg2);
        fragments.add(fg3);
        viewpageAdapter=new ViewpageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(viewpageAdapter);
    }
}
