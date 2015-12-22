package com.recycler.zx.zxrecyclerview.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessageService extends Service {

    public static final int say = 0x1;
    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return  messenger.getBinder();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String info = (String) msg.obj;
            switch (msg.what) {
                case  say :
                    Toast.makeText(getApplicationContext(),"info="+info,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private Messenger messenger = new Messenger(handler);
}
