package com.recycler.zx.zxrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @Bind(R.id.vp_list)
    ViewPager mVpList;
    @Bind(R.id.pts_list)
    PagerTabStrip mPtsList;

    private List<View> list = new ArrayList<>();

    private String text[] = {"111","222","333"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        initViews();
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.setList(list,text);
        //默认设置当前选项卡
        mVpList.setCurrentItem(1);
        mVpList.setAdapter(adapter);
    }

    private void initViews() {
        list.add(getLayoutInflater().inflate(R.layout.layout_1,null));
        list.add(getLayoutInflater().inflate(R.layout.layout_2,null));
        list.add(getLayoutInflater().inflate(R.layout.layout_3,null));

        //设置属性
        mPtsList.setBackgroundColor(Color.YELLOW);
        mPtsList.setTabIndicatorColor(Color.RED);
        mPtsList.setTextColor(Color.WHITE);

     /*   mVpList.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ViewPagerActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }
}
