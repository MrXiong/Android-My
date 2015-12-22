package com.recycler.zx.zxrecyclerview.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recycler.zx.zxrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopBacksFragment extends Fragment {

private static final String TITLE = "title";
    private String title ;
    public PopBacksFragment(){}
//    public PopBacksFragment(String title) {
//        this.title =title;
//    }
    //fragment的标准传参数方法,这样传参数的时候屏幕旋转数据不会消失，（并且Fragment是不允许使用有参的构造方法的）
    public static PopBacksFragment getInstance(String title){
        PopBacksFragment pf = new PopBacksFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        pf.setArguments(bundle);
        return pf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pop_backs, container, false);
       TextView tvPop = (TextView) v.findViewById(R.id.tv_pop);
        tvPop.setText(getArguments().getString(TITLE));
        return v;
    }

}
