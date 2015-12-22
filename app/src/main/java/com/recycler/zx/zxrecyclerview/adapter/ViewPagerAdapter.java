package com.recycler.zx.zxrecyclerview.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zx on 2015/12/14.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mList;
    private String [] text;

    public void setList(List<View> list, String [] text) {
        this.mList = list;
        this.text = text;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    //实例化
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mList.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //获取标题
    @Override
    public CharSequence getPageTitle(int position) {

        return text[position];
    }
}
