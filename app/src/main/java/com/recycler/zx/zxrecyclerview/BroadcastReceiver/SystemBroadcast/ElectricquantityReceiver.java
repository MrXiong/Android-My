package com.recycler.zx.zxrecyclerview.BroadcastReceiver.SystemBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

//检测电量变化
public class ElectricquantityReceiver extends BroadcastReceiver {
    public ElectricquantityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       int current = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);//当前电量
        int all = intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);//总电量
        int precent = current * 100/all;//获得百分比
        Toast.makeText(context,"当前电量"+precent,Toast.LENGTH_SHORT).show();
    }
}
