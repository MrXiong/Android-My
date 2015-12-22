package com.recycler.zx.zxrecyclerview.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    @Bind(R.id.bn_start)
    Button bnStart;
    @Bind(R.id.bn_stop)
    Button bnStop;
    @Bind(R.id.bn_intent_start)
    Button bnIntentStart;
    @Bind(R.id.bn_bind)
    Button bnBind;
    @Bind(R.id.bn_unbind)
    Button bnUnbind;
    @Bind(R.id.bn_ipc)
    Button bnIpc;
    @Bind(R.id.bn_message)
    Button bnMessage;

    private ICat cat;
    private boolean bound = false;//是否已经绑定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bn_start, R.id.bn_stop, R.id.bn_intent_start, R.id.bn_bind, R.id.bn_unbind, R.id.bn_ipc,R.id.bn_message})
    void onClicked(View v) {
        Intent in = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.bn_start:
                startService(in);
                break;
            case R.id.bn_stop:
                stopService(in);
                break;
            case R.id.bn_intent_start:
                startIntentService();
                break;
            case R.id.bn_bind:
                bind();
                break;
            case R.id.bn_unbind:
                unbind();
                break;
            case R.id.bn_ipc:
                ipc();
                break;
            case R.id.bn_message:
                ipcMessage();
                break;
        }
    }


    private void ipc() {
        if (cat == null) {
            return;
        }
        try {
            cat.setName("tom");

            Toast.makeText(this, "---" + cat.desc(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "---" + cat.getPerson().name.toString(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //绑定服务的连接回调接口
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定成功调用
            cat = ICat.Stub.asInterface(service);
            Toast.makeText(ServiceActivity.this, cat + "", Toast.LENGTH_SHORT).show();
            bound = true;
            Toast.makeText(ServiceActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务异常调用（正常退出不会调用的）
            bound = false;
        }
    };

    private void bind() {
        Intent in = new Intent(this, MyBoundService.class);
        //异步绑定,如果是绑定成功后会回调onServiceConnected
        bindService(in, con, Context.BIND_AUTO_CREATE);//没有创建就自动创建，创建了就直接绑定
    }

    private void unbind() {
        if (bound) {//如果已经绑定
            unbindService(con);
            Toast.makeText(ServiceActivity.this, "解绑", Toast.LENGTH_SHORT).show();
        }

    }

    private void startIntentService() {
        Intent in2 = new Intent(this, MyIntentService.class);
        startService(in2);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//使用Message实现IPC
    Messenger mMessenger;
    boolean flag;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            flag = true;
            Toast.makeText(ServiceActivity.this,"绑定成功message",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag = false;
            mMessenger = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent in = new Intent(this,MessageService.class);
        bindService(in,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    private void ipcMessage() {
        Message msg =  Message.obtain();//创建一个消息对象
        msg.what = MessageService.say;
        msg.obj = "你好哇";
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    if(flag) {
        unbindService(serviceConnection);
    }
        flag = false;
    }
}
