package com.recycler.zx.zxrecyclerview.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
//和普通广播是一样的（优先级平等的情况下，比如OrderedBroadcast有序广播）动态广播先收到，静态广播后收到
public class OrderReceiver extends BroadcastReceiver {
    public OrderReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"有序1",Toast.LENGTH_SHORT).show();
       // abortBroadcast();//中断有序广播，只能中断有序广播,或者使用setResult方法向下传递，高优先级往低优先级传递
        Bundle bundle = new Bundle();
        bundle.putString("key22","vlaue111");
        setResultExtras(bundle);
    }
}
