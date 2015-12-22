
  
package com.recycler.zx.zxrecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.recycler.zx.zxrecyclerview.R;
import com.recycler.zx.zxrecyclerview.bean.ActivityContent;

import java.util.List;

public class FirstAdapter extends BaseAdapter{

	private List<ActivityContent> mList;
	public FirstAdapter(List<ActivityContent> list){
		this.mList = list;
	}
	public int getCount() { 
		return mList.size(); 
	}

	@Override
	public ActivityContent getItem(int position) {
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
		holder.tvFirst.setText(getItem(position).getContent());
		
		return convertView; 
	}

	static class ViewHolder{
		TextView tvFirst;
	}
}
  