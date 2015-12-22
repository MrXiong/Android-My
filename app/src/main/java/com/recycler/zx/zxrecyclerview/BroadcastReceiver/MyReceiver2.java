package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*自定义广播接收器
* */
public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    //接收的方法
    @Override
    public void onReceive(Context context, Intent intent) {
       String info =  intent.getStringExtra("key2");
        Toast.makeText(context,info ,Toast.LENGTH_SHORT).show();
    }
}
