package com.example.debug.small;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.example.debug.small.adapter.NoScrollViewPager;
import com.example.debug.small.R;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private NoScrollViewPager mVp;
    private JPTabBar mTabBar;
    private com.example.debug.small.fragments.HomeFragment homeFragment;
    private com.example.debug.small.fragments.TwoFragment twoFragment;
    private com.example.debug.small.fragments.ThreeFragment threeFragment;
    private com.example.debug.small.fragments.FourFragment fourFragment;
    private ArrayList<Fragment> fragments;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=this.getSharedPreferences("checked",MODE_PRIVATE);
        boolean firstload=sharedPreferences.getBoolean("firstload",true);
        if(firstload){
            Intent intent =new Intent();
            intent.setClass(this,GuideActivity.class);
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_main);
            initView();
            homeFragment = new com.example.debug.small.fragments.HomeFragment();
            twoFragment = new com.example.debug.small.fragments.TwoFragment();
            threeFragment = new com.example.debug.small.fragments.ThreeFragment();
            fourFragment = new com.example.debug.small.fragments.FourFragment();
            fragments = new ArrayList<Fragment>();
            fragments.add(homeFragment);
            fragments.add(twoFragment);
            fragments.add(threeFragment);
            fragments.add(fourFragment);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
            mVp.setAdapter(adapter);
            mTabBar.setContainer(mVp);
        }
    }
    private void initView(){

        mVp = findViewById(R.id.vp);
        mTabBar = findViewById(R.id.tabbar);
    }
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
            return super.onKeyDown(keyCode, event);
    }
        //设置标题
    @Titles
    private static final String[] mTitles={"首页","通讯录","发现","我"};
    //设置选中图标
    @SeleIcons
    private static final int[] mSelectIcons={
            R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected
    };

    //设置未选中图标
    @NorIcons
    private static final int[] mNormalIcon={R.mipmap.tab1_normal,R.mipmap.tab2_normal,R.mipmap.tab3_normal,R.mipmap.tab4_normal};
    private class ViewPagerAdapter extends FragmentStatePagerAdapter{
        private ArrayList<Fragment> fragments;
        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments)
        {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
               return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
