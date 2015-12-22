package com.recycler.zx.zxrecyclerview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.BroadcastReceiver.StickyReceiver;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @Bind(R.id.tv_bg)
    TextView mTvBg;
    @Bind(R.id.tv_popmenu)
    TextView mTvPopmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();//隐藏actionbar
        actionBar.setDisplayHomeAsUpEnabled(true);
        registerForContextMenu(mTvBg);//注册上下文菜单到TextView
        mTvPopmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建弹出式菜单
                PopupMenu  popupMenu = new PopupMenu(MenuActivity.this,v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_context,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.red :
                                Toast.makeText(MenuActivity.this, "red", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.blue :
                                Toast.makeText(MenuActivity.this, "blue", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.yellow :
                                Toast.makeText(MenuActivity.this, "yellow", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*menu.add(0,100,1,"设置游戏");
        menu.add(0,200,2,"开始游戏");
        menu.add(0,300,3,"退出游戏");*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case 100:
                Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
                break;
            case 200:
                Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
                break;
            case 300:
                Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //上下文菜单

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                mTvBg.setBackgroundColor(Color.RED);
                break;
            case R.id.blue:
                mTvBg.setBackgroundColor(Color.BLUE);
                break;
            case R.id.yellow:
                mTvBg.setBackgroundColor(Color.YELLOW);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
    }



    //广播
    private StickyReceiver receiver = new StickyReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter= new IntentFilter();
        filter.addAction("com.mystick");
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}

