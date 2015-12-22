package com.recycler.zx.zxrecyclerview.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*.通过绑定服务来实现功能的步骤：
1.客户端通过bindService方法来绑定一个服务对象（service只是一个中间人），如果绑定成功，会回调onServiceConnected方法
2.通过Service来暴露业务接口（我不能直接调用业务，我可以通过service，service再调用业务，业务逻辑要符合）
3.服务端通过创建a.AIDL文件来定义一个可以被客户端调用的业务接口
一个AIDL文件要满足
1.不能有修饰符，类似接口的写法，
2.支持类型有：8种基本数据类型,List<String>, Map,自定义类型
3.服务端还需要提供一个业务接口的实现类，通常我们会extends Sub
4.通过Service的onBind方法返回被绑定的业务对象
5.客户端如果绑定成功就可以向调用自己的方法一样调用远程的业务对象方法
6.把service 变成一个远程服务（跨进程服务）android:process="remote"，那么就会调用Stub.Proxy
* */
public class MyBoundService extends Service {
    public MyBoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CatImpl();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
