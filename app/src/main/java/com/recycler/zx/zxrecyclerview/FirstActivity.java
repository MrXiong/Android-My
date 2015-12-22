package com.recycler.zx.zxrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.recycler.zx.zxrecyclerview.ActionBar.MyActionBarActivity;
import com.recycler.zx.zxrecyclerview.BroadcastReceiver.BroadcastActivity;
import com.recycler.zx.zxrecyclerview.Buy.BuyActivity;
import com.recycler.zx.zxrecyclerview.FileAndJsonPaser.FileActivity;
import com.recycler.zx.zxrecyclerview.Handler.MemoryOverflowActivity;
import com.recycler.zx.zxrecyclerview.ImageSwitcher.ImageSwitcherActivity;
import com.recycler.zx.zxrecyclerview.Service.ServiceActivity;
import com.recycler.zx.zxrecyclerview.adapter.FirstAdapter;
import com.recycler.zx.zxrecyclerview.bean.ActivityContent;
import com.recycler.zx.zxrecyclerview.expandable.ExpandableActivity;
import com.recycler.zx.zxrecyclerview.fragments.FragmentsActivity;
import com.recycler.zx.zxrecyclerview.fragments.FragmentsCodeActivity;
import com.recycler.zx.zxrecyclerview.fragments.PopBackTaskActivity;
import com.recycler.zx.zxrecyclerview.fragments.fragmentAndActivity.FramentAndActivity;
import com.recycler.zx.zxrecyclerview.loadmore.MoreActivity;
import com.recycler.zx.zxrecyclerview.volleyAndAsyncAndWebservice.WebServiceActivity;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends BaseActivity {

    private List<ActivityContent> mList;
    private ListView mLvFrist;
    private FirstAdapter mAdapter;
    private ActivityContent mActivityContent;
    private String[] names = { "recyclerView", "TimeActivity","more","ExpandableActivity",
            "ImageSwitcherActivity","TextSwitcherActivity","ViewFlipperActivity","MenuActivity","ViewPagerActivity",
    "NotificationActivity","ServiceActivity","BroadcastActivity","FragmentsActivity",
            "FragmentsCodeActivity","PopBackTaskActivity","FramentAndActivity,",
            "MyActionBarActivity","SharePreferenceActivity","BuyActivity","MemoryOverflowActivity","FileActivity","WebServiceActivity"};
    private Class<?>[] classs = { MainActivity.class, TimeActivity.class,MoreActivity.class,
            ExpandableActivity.class,ImageSwitcherActivity.class,
            TextSwitcherActivity.class,ViewFlipperActivity.class,MenuActivity.class,ViewPagerActivity.class,
    NotificationActivity.class,ServiceActivity.class,BroadcastActivity.class,
            FragmentsActivity.class,FragmentsCodeActivity.class,
            PopBackTaskActivity.class,FramentAndActivity.class,
            MyActionBarActivity.class,SharePreferenceActivity.class, BuyActivity.class, MemoryOverflowActivity.class,FileActivity.class,
            WebServiceActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        initData();


    }

    private void initView() {
        mLvFrist = (ListView) findViewById(R.id.lv_first);
    }

    private void initData() {
        mList = new ArrayList<ActivityContent>();
        for (int i = 0; i < names.length; i++) {
            mActivityContent = new ActivityContent();
            mActivityContent.setContent(names[i]);
            mActivityContent.setCls(classs[i]);
            mList.add(mActivityContent);
        }
        setAdapter();

    }

    private void setAdapter() {
        mAdapter = new FirstAdapter(mList);
        mLvFrist.setAdapter(mAdapter);

        mLvFrist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final ActivityContent activityContent = mAdapter.getItem(position);
                sendActivity(FirstActivity.this, activityContent.getCls());
            }
        });
    }

}
