package com.recycler.zx.zxrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycler.zx.zxrecyclerview.R;
import com.recycler.zx.zxrecyclerview.bean.Photo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zx on 2015/12/7.
 */
public class MainAdatper extends RecyclerView.Adapter<MainAdatper.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<Photo> mMainList;
    public void setList(List<Photo> mainList){
        this.mMainList = mainList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvItem.setText(mMainList.get(position).getName());
        holder.ivItem.setImageResource(mMainList.get(position).getdId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMainList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_item)
        TextView tvItem;
        @Bind(R.id.iv_item)
        ImageView ivItem;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void refreshAllData(List<Photo> newsBeans) {
        mMainList.clear();
        if (mMainList.isEmpty()) {
            mMainList.addAll(newsBeans) ;
        }
        // 更新RecyclerView视图
        notifyDataSetChanged();
    }
}
