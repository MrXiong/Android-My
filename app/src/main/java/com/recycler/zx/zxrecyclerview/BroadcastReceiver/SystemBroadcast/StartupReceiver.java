package com.recycler.zx.zxrecyclerview.BroadcastReceiver.SystemBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartupReceiver extends BroadcastReceiver {
    public StartupReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机",Toast.LENGTH_SHORT).show();
    }
}
