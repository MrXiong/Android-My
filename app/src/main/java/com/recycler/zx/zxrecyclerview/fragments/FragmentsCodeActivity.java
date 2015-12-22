package com.recycler.zx.zxrecyclerview.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recycler.zx.zxrecyclerview.R;

public class FragmentsCodeActivity extends AppCompatActivity {

    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_code);

        addContentLayout();
    }

    //t通过代码添加fragment
    private void addContentLayout() {
        FragmentManager fm =  getFragmentManager();
        //开启一个事务
        FragmentTransaction ft = fm.beginTransaction();
        contentFragment = new ContentFragment();

        //添加fragment
        ft.add(R.id.frameLayout_content,contentFragment);
        //ft.remove();
       // ft.replace();替换，先删除，再添加
        ft.commit();//提交事务
    }
}
