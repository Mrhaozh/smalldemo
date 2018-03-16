package com.example.debug.small.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.debug.small.R;
import com.example.debug.small.adapter.TwoAdapter;

import java.util.List;

/**
 * Created by lenovo on 2018/3/1.
 */

public class TwoFragment extends Fragment{
    private SwipeMenuListView listView;
    private List<ApplicationInfo> mAppList;
    public TwoFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        listView = view.findViewById(R.id.twoList);
        mAppList = getActivity().getPackageManager().getInstalledApplications(0);
        final TwoAdapter twoAdapter = new TwoAdapter(mAppList,getActivity());
        listView.setAdapter(twoAdapter);
        SwipeMenuCreator creator=new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCe)));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("打开");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
                SwipeMenuItem delItem = new SwipeMenuItem(getActivity());
                delItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCe)));
                delItem.setTitle("删除");
                delItem.setTitleSize(18);
                delItem.setTitleColor(Color.WHITE);
                delItem.setWidth(dp2px(90));
                menu.addMenuItem(delItem);
            }
        };

        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                ApplicationInfo item = mAppList.get(position);
                switch (index){
                    case 0:
                        open(item);
                        break;
                    case 1:
                        mAppList.remove(position);
                        twoAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
        listView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"点击第"+"position"+"个项目",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,View view,int position, long id) {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return view;
    }
    private void delete(ApplicationInfo item) {
        // delete app
        try {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.fromParts("package", item.packageName, null));
            startActivity(intent);
        } catch (Exception e) {
        }
    }
    public void open(ApplicationInfo item){
        Toast.makeText(getActivity(),"打开",Toast.LENGTH_SHORT).show();
    }
    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_left) {
            listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            return true;
        }
        if (id == R.id.action_right) {
            listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

