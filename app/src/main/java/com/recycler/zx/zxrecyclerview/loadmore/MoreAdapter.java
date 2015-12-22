package com.recycler.zx.zxrecyclerview.loadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.recycler.zx.zxrecyclerview.R;
import com.recycler.zx.zxrecyclerview.utils.AdapterUtils;

import java.util.List;

/**
 * Created by zx on 2015/12/14.
 */
public class MoreAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    public MoreAdapter(Context context){
        this.mContext = context;
    }

    public void setList(List<String> list) {
        this.mList = AdapterUtils.getList(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item, parent, false);
            holder.tvFirst = (TextView) convertView.findViewById(R.id.tv_first);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvFirst.setText(getItem(position));

        return convertView;
    }
    static class ViewHolder{
        TextView tvFirst;
    }
}
