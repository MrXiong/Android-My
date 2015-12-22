package com.recycler.zx.zxrecyclerview.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
//1.服务同时只会被创建一个实例，可以通过调用stopService停止服务或者调用this.stopSelf();方法终止服务
//2.当执行一个已经启动的服务，就不会再创建(onCreate)了,会直接调用onStartCommand
//3.默认情况下服务与主线程在同一个进程中的同一个线程中执行，如果服务执行一个耗时的操作，我们必须使用子线程来完成操作，避免阻塞主线程
//4.使用 started service 启动一个服务，在没有关闭之前会一直在后台运行
public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //服务只创建一次，
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"onCreate");
    }

   /* 会多次启动
    在该方法中实现服务的核心业务,
    默认情况下服务与主线程在同一个线程中执行，如果服务执行一个耗时的操作，我们必须使用子线程来完成
    操作，避免阻塞主线程
    */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程完成耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //work
                for(int i = 0;i<100;i++){
                    Log.v(TAG,"onStartCommand"+i+"---"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i == 30) {
                        MyService.this.stopSelf();
                        break;
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
