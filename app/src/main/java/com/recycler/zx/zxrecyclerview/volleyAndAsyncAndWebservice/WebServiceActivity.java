package com.recycler.zx.zxrecyclerview.volleyAndAsyncAndWebservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.recycler.zx.zxrecyclerview.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebServiceActivity extends AppCompatActivity {

    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.wv_list)
    WebView wvList;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        ButterKnife.bind(this);
        initWebView();
    }

    private void initWebView() {
       WebSettings settings =  wvList.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);


        wvList.requestFocus();
        wvList.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //设置点击链接在webview中显示
        wvList.setWebViewClient(new WebViewClient());
        wvList.setWebChromeClient(new WebChromeClient());
        //wvList.loadUrl("http://www.baidu.com/");
        myHandler = new MyHandler(this);
        wvList.addJavascriptInterface(new myObj(),"myweb");
        wvList.loadUrl("file:///android_asset/html/index.html");
    }

    public class myObj{
        @JavascriptInterface
        public void toWeb(){
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    wvList.loadUrl("javascript:myfun()");
                }
            });
        }
    }

    public void webServiceClick(View v) {
        myHandler = new MyHandler(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getTelephoneInfo("13888888888");
            }
        }).start();

    }

    private static class MyHandler extends Handler {
        private WeakReference<WebServiceActivity> mWeakReference;
        private WebServiceActivity mActivity;

        public MyHandler(WebServiceActivity activity) {
            mWeakReference = new WeakReference<WebServiceActivity>(activity);
            mActivity = mWeakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity != null) {
                String result = (String) msg.obj;
                mActivity.button4.setText(result);
            }
        }
    }

    public void getTelephoneInfo(String phone_number) {
        //命名空间
        String nameSpace = "http://WebXml.com.cn/";
        //调用的方法名称
        String methodName = "getMobileCodeInfo";
        // webservice的网址
        String URL = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        //命名空间+方法
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("mobileCode", phone_number);
        rpc.addProperty("userId", "");
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于
       /* envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);*/
        HttpTransportSE transport = new HttpTransportSE(URL);
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        String result = object.getProperty(0).toString();
        Message msg = myHandler.obtainMessage();
        msg.obj = result;
        myHandler.sendMessage(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && wvList.canGoBack()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
