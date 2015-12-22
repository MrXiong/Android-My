package com.recycler.zx.zxrecyclerview.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.MenuActivity;
import com.recycler.zx.zxrecyclerview.R;
import com.recycler.zx.zxrecyclerview.TimeActivity;
import com.recycler.zx.zxrecyclerview.fragments.ContentFragment;
import com.recycler.zx.zxrecyclerview.fragments.TitleFragment;

public class MyActionBarActivity extends AppCompatActivity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_action_bar);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();//隐藏actionbar
        actionBar.setHomeButtonEnabled(true);
        startActivity(new Intent(this, MenuActivity.class));

        //导航条
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);//标题栏不显示
        //添加选项卡
        ActionBar.Tab tab = actionBar.newTab()
                .setText("美女")
                .setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("新闻")
                .setTabListener(this);
        actionBar.addTab(tab);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getCallState();
        telephonyManager.listen(new PhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        //搜索
        MenuItem mi = menu.findItem(R.id.yellow);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mi);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MyActionBarActivity.this,"---"+query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });
        //分享
        MenuItem mi2 =  menu.findItem(R.id.share);
        ShareActionProvider provier = (ShareActionProvider)MenuItemCompat.getActionProvider(mi2);
        provier.setShareIntent(getDefaultIntent2());
        return true;

    }
    private Intent getDefaultIntent(){
        Intent in = new Intent(Intent.ACTION_SEND);
        in.setType("image/*");
        return in;
    }
    private Intent getDefaultIntent2(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"这是。。");
        startActivity(intent);
        return intent;
    }

    //导航条
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    Toast.makeText(this,tab.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    //电话服务
    class PhoneStateListener extends android.telephony.PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING :
                    Toast.makeText(MyActionBarActivity.this,"正在响铃",Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_IDLE :
                    Toast.makeText(MyActionBarActivity.this,"正在响铃",Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    Toast.makeText(MyActionBarActivity.this,"正在响铃",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
