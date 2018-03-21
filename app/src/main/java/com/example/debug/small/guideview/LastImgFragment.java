package com.example.debug.small.guideview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.debug.small.MainActivity;
import com.example.debug.small.R;

/**
 * Created by lenovo on 2018/3/21.
 */

public class LastImgFragment extends Fragment{
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public LastImgFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.guide_third,container,false);
        button=view.findViewById(R.id.gotoMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getActivity().getSharedPreferences("checked", Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putBoolean("firstload",false);
                editor.commit();
                Intent intent=new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
