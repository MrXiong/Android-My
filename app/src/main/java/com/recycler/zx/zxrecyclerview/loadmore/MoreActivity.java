package com.recycler.zx.zxrecyclerview.loadmore;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.recycler.zx.zxrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity implements AbsListView.OnScrollListener{

    @Bind(R.id.lv_more)
    ListView mLvMore;
    private List<String> mList;
    private LayoutInflater mInflater;
    private int lastItem;
    private static final int DATA_LOAD = 0x1;
    private MoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        mList = new ArrayList<>();
        ButterKnife.bind(this);
        mInflater = LayoutInflater.from(this);
        mLvMore.setOnScrollListener(this);
        initData();
        initFooterView();

    }

    private void initFooterView() {
        View view = mInflater.inflate(R.layout.layout_load,null);
        mLvMore.addFooterView(view);
        mAdapter = new MoreAdapter(this);
        mAdapter.setList(mList);
        mLvMore.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i=0;i<10;i++) {
            mList.add("列表"+i);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(lastItem == mList.size()) {
            new Thread(new LoadThread()).start();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = mLvMore.getLastVisiblePosition();
        lastItem = mLvMore.getChildCount();
        }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MoreActivity.DATA_LOAD:
                mAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };
class LoadThread implements Runnable{
    @Override
    public void run() {
        initData();
        handler.sendEmptyMessage(MoreActivity.DATA_LOAD);
    }
}
}
