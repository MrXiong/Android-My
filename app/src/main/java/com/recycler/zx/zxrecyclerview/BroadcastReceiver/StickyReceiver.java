package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StickyReceiver extends BroadcastReceiver {
    public StickyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"黏性",Toast.LENGTH_SHORT).show();
    }
}
