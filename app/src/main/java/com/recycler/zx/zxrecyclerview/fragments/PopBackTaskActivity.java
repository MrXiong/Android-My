package com.recycler.zx.zxrecyclerview.fragments;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.recycler.zx.zxrecyclerview.R;

public class PopBackTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_task);
    }

    public void one(View v){
        PopBacksFragment popBacksFragment = PopBacksFragment.getInstance("one");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.ft_content,popBacksFragment);
        //把当前fragment添加到Activity栈
        ft.addToBackStack(null);
        ft.commit();

    }
    public void two(View v){
        PopBacksFragment popBacksFragment = PopBacksFragment.getInstance("two");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.ft_content,popBacksFragment);
        //把当前fragment添加到Activity栈
        ft.addToBackStack(null);
        ft.commit();
    }

    //点返回键的时候，判断栈中是否存在，存在就做出栈操作
    //比activity性能要高一些
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //如果返回栈里面没有了
            if(getFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else {
                getFragmentManager().popBackStack();//出栈，不进行退出操作
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
