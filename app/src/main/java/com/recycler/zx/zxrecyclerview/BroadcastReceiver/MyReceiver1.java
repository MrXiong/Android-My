package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*自定义广播接收器
* */
public class MyReceiver1 extends BroadcastReceiver {
    public MyReceiver1() {
    }

    //接收的方法
    @Override
    public void onReceive(Context context, Intent intent) {
       String info =  intent.getStringExtra("key1");
        Toast.makeText(context,info ,Toast.LENGTH_SHORT).show();
    }
}
