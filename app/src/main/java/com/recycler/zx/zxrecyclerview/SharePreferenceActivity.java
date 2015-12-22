package com.recycler.zx.zxrecyclerview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SharePreferenceActivity extends AppCompatActivity {

    private SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        //获取当前程序SharedPreferences
        sf = getSharedPreferences("share_all", Context.MODE_PRIVATE);
    }

    //进行还原数据
    @Override
    protected void onResume() {
        super.onResume();

       String result =  sf.getString("key1","");
    }

    //在该时间方法里来存储数据
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor =  sf.edit();
        editor.putString("key1","values1");
        editor.commit();

        editor.remove("key1");//根据key删除
        editor.clear();//清楚所有的SharedPreferences
    }
    //分享文本
    public void shareText(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"这是。。");
        startActivity(intent);

        startActivity(Intent.createChooser(intent,getResources().getText(R.string.app_name)));
    }
    //分享二进制
    public void shareimg(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri url = Uri.parse("/sdcard/1.jpg");
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM,url);
        startActivity(intent);

        startActivity(Intent.createChooser(intent,getResources().getText(R.string.app_name)));
    }
}
