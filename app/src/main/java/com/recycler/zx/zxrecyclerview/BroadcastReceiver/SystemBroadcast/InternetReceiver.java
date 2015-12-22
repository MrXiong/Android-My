package com.recycler.zx.zxrecyclerview.BroadcastReceiver.SystemBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
    public InternetReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info !=null) {
            String name = info.getType()+"";
            Toast.makeText(context,"---"+name,Toast.LENGTH_SHORT).show();
        }

    }
}
