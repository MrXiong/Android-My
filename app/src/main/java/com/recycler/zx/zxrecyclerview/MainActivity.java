package com.recycler.zx.zxrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.adapter.MainAdatper;
import com.recycler.zx.zxrecyclerview.bean.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.sl_refresh)
    SwipeRefreshLayout mSlRefresh;
    @Bind(R.id.rv_list)
    RecyclerView mRvList;
    private MainAdatper mMainAdatper;
    private List<Photo> mData;
    private int IMAGES[] = {R.mipmap.a,
            R.mipmap.a,
            R.mipmap.b,
            R.mipmap.c,
            R.mipmap.d,
            R.mipmap.a,
            R.mipmap.b,
            R.mipmap.c,
            R.mipmap.d,
            R.mipmap.a,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //listview效果
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        // manager.setOrientation(LinearLayout.VERTICAL);
        // mRvList.setLayoutManager(manager);
        //gridview效果
        //GridLayoutManager manager = new GridLayoutManager(this,3);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);
        mSlRefresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        initData();
        initListener();
    }

    private void initData() {
        mData = new ArrayList<Photo>();
        for (int i = 0; i < IMAGES.length; i++) {
            Photo photo = new Photo();
            photo.setdId(IMAGES[i]);
            photo.setName("图片" + i);
            mData.add(photo);
        }
    }

    private void initListener() {
        mMainAdatper = new MainAdatper();
        mMainAdatper.setList(mData);
        mRvList.setAdapter(mMainAdatper);
        mMainAdatper.setOnItemClickListener(new MainAdatper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        // 设置下拉刷新出发事件
        mSlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainAdatper.refreshAllData(getDatas("2"));
            }
        });
    }
    /**
     * 为列表提供数据 ， 设置不同的flag ， 区分刷新数据
     * @param flag
     * @return
     */
    public List<Photo> getDatas(String flag) {
        List<Photo> newsBeans = new ArrayList<>();
        for (int i = 0; i < IMAGES.length; i++) {
            Photo photo = new Photo();
            photo.setName("新数据"+flag);
            photo.setdId(IMAGES[i]);
            newsBeans.add(photo);
        }
        if (mSlRefresh != null) {
            // 判断是不是正在刷新
            if (mSlRefresh.isRefreshing()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 2秒之后关闭刷新
                        mSlRefresh.setRefreshing(false);
                    }
                }, 2000) ;
            }
        }
        return newsBeans;
    }
}
