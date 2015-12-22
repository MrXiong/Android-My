package com.recycler.zx.zxrecyclerview.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/*1内部有一个工作线程来执行耗时操作，只需要实现onHandleIntent方法即可
2完成工作后会自动停止服务
3.同时执行多个任务时会以工作队列的方式依次执行
* */
public class MyIntentService extends IntentService {

    private static final String TAG =MyIntentService.class.getSimpleName() ;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        for (int i =0;i<50;i++) {
            Log.v(TAG,"onHandleIntent"+Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
