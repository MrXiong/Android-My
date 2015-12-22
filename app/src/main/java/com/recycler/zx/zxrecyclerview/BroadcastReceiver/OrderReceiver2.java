package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OrderReceiver2 extends BroadcastReceiver {
    public OrderReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       Bundle b =  getResultExtras(false);
        String data = b.getString("key22");
        Toast.makeText(context,"有序2"+data,Toast.LENGTH_SHORT).show();
    }
}
