package com.example.debug.small.guideview;

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

public class SecondImgFragment extends Fragment{
    public SecondImgFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_second, container, false);
        return view;
    }
}
