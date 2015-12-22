package com.recycler.zx.zxrecyclerview.Handler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;

//Handler的内存泄露问题
//1.定义一个内部类时，会默认拥有外部类对象的引用,所以建议使用内部类时，最好定义一个静态（static）内部类（因为静态内部类就相当于一个外部类，不拥有外部类的引用）
//2.引用的强弱：强引用(new 出来的对象，就是强引用，就算内存不足也不会被自动回收,想要回收必须把对象置空或销毁当前对象，回收器才会回收)
// ->软引用（A引用B，当B内存不足的时候，系统就会回收它）
// ->弱引用（引用的对象如果不存在就得不到）
//3.
public class MemoryOverflowActivity extends AppCompatActivity {

    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.progressBar2)
    ProgressBar progressBar2;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_overflow);
        ButterKnife.bind(this);
        //这样写会出现内存泄露
        /*1.当Handler作为内部类使用的时候，那么handler持有外部类的引用；
        如果外部类退出的时候，handler还在运行的话，外部类（Activity）就退出不了，就算退出了，还还在占用着内存。那么就造成了内存溢出（泄露）
        （Handler依赖于外部类）*/
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 60 * 10 * 1000);//延迟10分钟执行run方法
        Toast.makeText(this, "来过", Toast.LENGTH_SHORT).show();
        finish();*/
    }

    private MyHandler handler = new MyHandler(this);


    //这个是标准的Handler写法，1.static内部类（相当于外部类，不依赖外部类）2.软引用（防止内存泄露）3.通过mWeakReference.get()返回一个Activity
    private static class MyHandler extends Handler {
        //软引用Activity
        WeakReference<MemoryOverflowActivity> mWeakReference;

        public MyHandler(MemoryOverflowActivity activity) {
            mWeakReference = new WeakReference<MemoryOverflowActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //如果MemoryOverflowActivity被销毁了，那么mWeakReference.get()返回的就是null
            //如果返回null你就不能 name 变量（因为name属于MemoryOverflowActivity的变量，MemoryOverflowActivity为null，它的变量自然就不能使用）
            //使用软引用，就可以避免内存泄露（溢出）
            MemoryOverflowActivity activity = mWeakReference.get();
            if (activity != null) {

            }
        }
    }


    //通过AsyncTask实现一个异步任务
    private static class DownLoadAsynTask extends AsyncTask<String, Integer, String> {

        private MemoryOverflowActivity mActivity;

        public DownLoadAsynTask(MemoryOverflowActivity activity) {
            WeakReference<MemoryOverflowActivity> mWeakReference = new WeakReference<MemoryOverflowActivity>(activity);
            mActivity = mWeakReference.get();
        }
        //执行任务之前触发，可以做一些初始化工作,主线程调用的

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mActivity.textView2.setText("开始执行下载任务");
            mActivity.progressBar2.setProgress(0);
        }

        //执行后台任务的方法，类似于线程，所以不能在该方法中访问Ui组件
        @Override
        protected String doInBackground(String... params) {
            String imgurl = params[0];
            try {
                URL url = new URL(imgurl);
                //打开连接
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int size = con.getContentLength();
                //0:表示需要更新最大进度值1：表示更新当前进度值
                publishProgress(0,size);
                InputStream in = con.getInputStream();

                byte[] bytes = new byte[20];
                int len = -1;
                while((len = in.read(bytes)) != -1) {
                    //out.write(bytes,0,size);
                    publishProgress(1,len);//更新当前进度
                    //out.flush();
                }
                //out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
            return "success";

        }

        //更新进度值
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]){
                case 0:
                mActivity.progressBar2.setMax(values[1]);
                    break;
                case 1:
                    mActivity.progressBar2.incrementProgressBy(values[1]);
                    break;
            }
        }

        //当doInBackground被调用返回的时候调用
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("success")) {
                mActivity.textView2.setText("下载完成");
            }else {
                Toast.makeText(mActivity,"失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void download(View v) {
        DownLoadAsynTask task = new DownLoadAsynTask(this);
        task.execute("http://p2.so.qhimg.com/t01e767afd2dbff19ac.jpg");
    }

}
