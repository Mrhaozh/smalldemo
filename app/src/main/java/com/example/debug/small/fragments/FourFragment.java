package com.example.debug.small.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debug.small.R;

/**
 * Created by lenovo on 2018/3/1.
 */

public class FourFragment extends android.support.v4.app.Fragment{
    public FourFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        return view;
    }
}
