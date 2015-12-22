package com.recycler.zx.zxrecyclerview;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationActivity extends Activity implements View.OnClickListener {

    private static  final int  N_ID_1 = 0x1;
    @Bind(R.id.tv_no1)
    Button mTvNo1;
    @Bind(R.id.tv_no2)
    Button mTvNo2;
    @Bind(R.id.tv_no3)
    Button mTvNo3;
    @Bind(R.id.tv_no4)
    Button mTvNo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        mTvNo1.setOnClickListener(this);
        mTvNo2.setOnClickListener(this);
        mTvNo3.setOnClickListener(this);
        mTvNo4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_no1 :
                initNotification1();
                break;
            case R.id.tv_no2 :
                initNotification2();
                break;
            case R.id.tv_no3 :
                initNotification3();
                break;
            case R.id.tv_no4 :
                initNotification4();
                break;
        }
    }

    private void initNotification1() {
        //Notification n = new Notification();//api11之前创建通知的方式
       // Notification.Builder builder = new Notification.Builder(this);//11之后
        //v4支持包，即所有版本都支持
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.a);//设置小图标
        builder.setContentTitle("您有一条新消息");
        builder.setContentText("一大波优惠正在来袭");
        builder.setOngoing(true);//常驻通知，不可以删除的通知。
        builder.setDefaults(Notification.DEFAULT_ALL);//all代表声音，震动，呼吸灯都有
        builder.setNumber(55);
        builder.setTicker("新消息来了");
        builder.setAutoCancel(true);//设置自动取消通知
        //参数：上下文，请求编码（目前没用），意图，创建方式
        PendingIntent in = PendingIntent.getActivity(this,0,new Intent(this,TimeActivity.class).putExtra("1","222"),PendingIntent.FLAG_UPDATE_CURRENT);
        //通知的事件
        builder.setContentIntent(in);

        //创建一个通知对象
        Notification nn = builder.build();
        //获取系统通知管理器，然后发送通知
        NotificationManager manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(N_ID_1,nn);

    }

    private void initNotification2() {
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.a);//设置小图标
        builder.setContentTitle("您有一条新消息");
        builder.setContentText("您有一条新消息22222");
        //设置大视图样式
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("大标题：李白");
        style.addLine("1111");
        style.addLine("1122211");
        style.addLine("1133311");
        builder.setNumber(55);
        style.setSummaryText("大消息来了");

        builder.setStyle(style);
        //创建一个通知对象
        Notification nn = builder.build();
        NotificationManager manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(N_ID_1,nn);


    }
    private void initNotification3() {

        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.a);//设置小图标
        builder.setContentTitle("您有一条新消息");
        builder.setContentText("一大波优惠正在来袭");
        builder.setProgress(100,5,false);//false是确定的进度
        NotificationManager manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(N_ID_1,builder.build());

    }
    private void initNotification4() {
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this);
        //以下3个属性的设置至少要有一个
        builder.setSmallIcon(R.mipmap.a);//设置小图标
        //builder.setContentTitle("您有一条新消息");
       // builder.setContentText("一大波优惠正在来袭");
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_1);
        builder.setContent(remoteViews);
        builder.setTicker("自定义消息来了");
        NotificationManager manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(N_ID_1,builder.build());

    }
    //最重要的2句话
    public void setNotification(NotificationCompat.Builder  builder){
        //创建一个通知对象
        Notification nn = builder.build();
        //获取系统通知管理器，然后发送通知
        NotificationManager manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(N_ID_1,nn);
    }
}
