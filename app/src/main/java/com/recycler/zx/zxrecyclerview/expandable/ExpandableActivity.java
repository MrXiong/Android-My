package com.recycler.zx.zxrecyclerview.expandable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.recycler.zx.zxrecyclerview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpandableActivity extends AppCompatActivity {

    List<String> parent = null;
    Map<String, List<String>> map = null;
    @Bind(R.id.ex_qq_group)
    ExpandableListView mExQqGroup;
    private ExpandableAdapter mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        ButterKnife.bind(this);
        initData();
        mAdatper = new ExpandableAdapter();
        mAdatper.setData(parent, map);
        mExQqGroup.setAdapter(mAdatper);
    }
    // 初始化数据
    public void initData() {
        parent = new ArrayList<String>();
        for(int i = 1; i < 4; i++) {
            parent.add("parent"+i);
        }
        map = new HashMap<String, List<String>>();
            for(int i = 0; i < 3; i++) {
                List<String> list = new ArrayList<String>();
                for(int j = 1; j < 4; j++) {
                    list.add("child"+(i+1)+"-"+j);
                }
                map.put(parent.get(i), list);
            }
    }
}
