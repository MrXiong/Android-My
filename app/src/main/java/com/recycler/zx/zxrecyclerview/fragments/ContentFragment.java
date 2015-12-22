package com.recycler.zx.zxrecyclerview.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recycler.zx.zxrecyclerview.R;

/**
 * Created by zx on 2015/12/17.
 */
public class ContentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.layout_content,container,false);
        return v;
    }
}
