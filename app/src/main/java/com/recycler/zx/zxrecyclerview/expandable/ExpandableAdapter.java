package com.recycler.zx.zxrecyclerview.expandable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.recycler.zx.zxrecyclerview.MainActivity;
import com.recycler.zx.zxrecyclerview.R;
import com.recycler.zx.zxrecyclerview.utils.AdapterUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zx on 2015/12/14.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> mParent;
    private Map<String, List<String>> mData;
    public ExpandableAdapter(){

    }

    public void setData(List<String> parent, Map<String, List<String>> data) {
        this.mParent = AdapterUtils.getList(parent);
        this.mData = data;
    }

    @Override
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mParent.get(groupPosition);
        return mData.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = mParent.get(groupPosition);
        return mData.get(key).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_parent, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv_parent);
        tv.setText(mParent.get(groupPosition));
        return tv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String key = mParent.get(groupPosition);
        String info = mData.get(key).get(childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_child, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.tv_child);
        tv.setText(info);
        return tv;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
