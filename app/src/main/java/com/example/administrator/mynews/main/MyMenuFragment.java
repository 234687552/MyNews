package com.example.administrator.mynews.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mynews.R;
import com.mxn.soul.flowingdrawer_core.MenuFragment;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class MyMenuFragment extends MenuFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        return setupReveal(view);

    }
}
