package com.recycler.zx.zxrecyclerview.Buy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.recycler.zx.zxrecyclerview.Buy.adapter.StoreAdapter;
import com.recycler.zx.zxrecyclerview.Buy.base.GlobalConfig;
import com.recycler.zx.zxrecyclerview.Buy.base.MyApplication;
import com.recycler.zx.zxrecyclerview.Buy.base.VolleyTool;
import com.recycler.zx.zxrecyclerview.Buy.entity.CommonResponse;
import com.recycler.zx.zxrecyclerview.Buy.entity.Store;
import com.recycler.zx.zxrecyclerview.Buy.request.GsonRequest;
import com.recycler.zx.zxrecyclerview.Buy.utils.ToastUtils;
import com.recycler.zx.zxrecyclerview.MainActivity;
import com.recycler.zx.zxrecyclerview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    protected static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRvStore;
    private RequestQueue mRequestQueue;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Store> mStoreList;
    private StoreAdapter mStoreAdapter;
    private GsonRequest<CommonResponse> mGsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mContext = MyApplication.getContext();
        mRequestQueue = VolleyTool.getRequestQueue(mContext);

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRvStore = (RecyclerView) findViewById(R.id.rv_store);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvStore.setLayoutManager(mLinearLayoutManager);
        mRvStore.setItemAnimator(new DefaultItemAnimator());
        mStoreAdapter = new StoreAdapter();
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void initListener() {
        mStoreAdapter.setOnItemClickListener(new StoreAdapter.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                ToastUtils.show(mContext, mStoreList.get(position).getStoreName());
            }
        });
        mStoreAdapter.setOnLongItemClickListener(new StoreAdapter.OnLongItemClickListener() {
            public void onLongItemClick(View view, int position) {
                mStoreAdapter.deleteItem(position);
            }
        });
    }

    private void initData() {
        mStoreList = new ArrayList<Store>();
        mGsonRequest = new GsonRequest<CommonResponse>(GlobalConfig.URL_TEST, CommonResponse.class, new Response.Listener<CommonResponse>() {

            public void onResponse(CommonResponse response) {
                Store storeList[] = response.getResult().getStoreList();
                mStoreList.addAll(Arrays.asList(storeList));//使用Arrays.asList后返回的list是AbstractList 不支持增删改
                mStoreAdapter.setStoreList(mStoreList);
                mRvStore.setAdapter(mStoreAdapter);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(mGsonRequest);


    }

    @Override
    public void onRefresh() {

    }

}
