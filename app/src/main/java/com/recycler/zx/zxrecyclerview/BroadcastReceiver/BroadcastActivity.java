package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import com.recycler.zx.zxrecyclerview.BroadcastReceiver.SystemBroadcast.AlarmReceiver;
import com.recycler.zx.zxrecyclerview.MenuActivity;
import com.recycler.zx.zxrecyclerview.R;

import java.util.List;

public class BroadcastActivity extends AppCompatActivity {


    //（优先级平等的情况下，比如OrderedBroadcast有序广播）动态广播先收到，静态广播后收到
    private MyReceiver2 receiver2 = new MyReceiver2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    public void sendNomal(View v){
        Intent in = new Intent();
        in.setAction("com.broad1");
        in.putExtra("key1","value1");
        sendBroadcast(in);
    }

    //动态注册**********************************************************************************
    public void send2(View v){
        Intent in = new Intent();
        in.setAction("com.broad2");
        in.putExtra("key2","value2");
        sendBroadcast(in);
    }

    //在该方法中进行注册
    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.broad2");
        registerReceiver(receiver2,filter);
    }

    //在该方法中解除广播注册
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver2);
    }
//有序广播****************************************************************************************
    public void sendOrder(View v){
        Intent intent = new Intent();
        intent.setAction("com.order");
        sendOrderedBroadcast(intent,null);//null代表接收器不需要权限

    }
//黏性广播****************************************************************************************
//先发送后接收
    public void sendSticky(View v){
        Intent in = new Intent();
        in.setAction("com.mystick");
        sendStickyBroadcast(in);
    }
    public void openSendSticky(View v){
        startActivity(new Intent(this, MenuActivity.class));
    }


    //发送短信
    public void sms(View v){
        //获取短信管理器
        SmsManager sms = SmsManager.getDefault();
        //拆分长短信
       List<String > list =  sms.divideMessage("000000000000000");
        sms.sendTextMessage("13888888",null,"你好，",null,null);
    }
    //启动闹钟
    public void alarm(View v){
        //获取到了系统的闹钟服务
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long trigger = System.currentTimeMillis()+3000;
        Intent in = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        //只会执行一次的闹钟
        am.set(AlarmManager.RTC,trigger,pendingIntent);
    }
    public void alarming(View v){

    }
}
