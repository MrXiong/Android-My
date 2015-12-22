package com.recycler.zx.zxrecyclerview.fragments.fragmentAndActivity;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.recycler.zx.zxrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    private MyMenuListener mMyMenuListener;
    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mMyMenuListener = (MyMenuListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
       Button news = (Button) v.findViewById(R.id.news);
       Button music = (Button) v.findViewById(R.id.music);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyMenuListener.changeValue("news");
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyMenuListener.changeValue("music");
            }
        });
        return v;
    }

    //定义一个回调接口，要求宿主（包含它的Activity）去实现它
    public static interface MyMenuListener{
        public void changeValue(String value);
    }
}
