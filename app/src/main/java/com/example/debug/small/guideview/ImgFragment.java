package com.example.debug.small.guideview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debug.small.R;

/**
 * Created by lenovo on 2018/3/21.
 */

@SuppressLint("ValidFragment")
public class ImgFragment extends Fragment{
    private int id;
    public ImgFragment(int id){
        this.id=id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(id, container, false);
        return view;
    }
}
