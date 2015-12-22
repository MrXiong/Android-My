package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.recycler.zx.zxrecyclerview.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class FileActivity extends AppCompatActivity {

    public static final String TAG = FileActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        try {
            //存储私有文件
           // savePrivateFile();
            //读取私有文件
           // readPrivateFile();
            //读取res/raw
           // readRawFile();
            //读取缓存文件
           // readCacheFile();
            //sd卡管理：写入
           // writeToSDcard();
            //pull解析xml
           // pullXmlFile();
            //json解析
            //jsonParser();
           // GsonParser();
            //GsonsPaser();
            GsonsPaser2();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GsonsPaser2() {
        {
            JsonParse jp = new JsonParse(this);
            MyWeather myWeather = jp.parseJsonArrayFromGsons2();
            List<Results> results = myWeather.getResults();
            Log.v(TAG,myWeather+"");
        }
    }

    private void GsonsPaser() {
        JsonParse jp = new JsonParse(this);
        List<StoreType> list = jp.parseJsonArrayFromGsons();
        for (StoreType s:list) {
            Log.v(TAG,s+"");
        }
    }

    private void GsonParser() {
        JsonParse jp = new JsonParse(this);
        List<City> list = jp.parseJsonArrayFromGson();
        String str = jp.toJsonArrayFromGson(list);
        for (City s:list) {
            Log.v(TAG,s+"");
        }
        Log.v(TAG,"创建Gson数据"+str);
    }

    private void jsonParser() {
        JsonParse jp = new JsonParse(this);
        List<City> list = jp.parseJsonFromJsonReader();
       String str = jp.createJson(list);
        Log.v(TAG,"创建json数据"+str);
        for (City s:list) {
            Log.v(TAG,s+"");
        }
    }

    private void pullXmlFile() {
        InputStream is = null;
        try {
            is = getAssets().open("cache/person.xml");
            List<Person> list = XmlPull.pullxml(is);
            for (Person p :list) {
                Toast.makeText(this,p+"",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //判断有sd卡，并且不是只读的
    private boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){//是否有sd卡
            //判断是不是只读的sd卡
            if(!state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

                return true;
            }
            return false;
        }
        return false;
    }
    private void writeToSDcard() {
        if(isExternalStorageWritable()) {
        //访问sd卡路径
        Environment.getExternalStorageDirectory().getPath();
        //可以访问和保存不同类型的 storage/sdcard/movies丶storage/sdcard/pictures
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }
    }
    //写入外部（sd卡）私有文件
    private void writePrivateToSDcard() {
        //别的程序无法访问
        //getExternalFilesDir在手机把内部存储当做外部sd卡存储使用的时候此方法返回null
        //storage/sdcard/android/data/com.recycler.zx.zxrecyclerview/1.txt
        File file = getExternalFilesDir(null);
        if(file != null) {
            try {
                FileOutputStream out = new FileOutputStream(file+"/1.txt");
                PrintStream ps = new PrintStream(out);
                ps.print("1111111111111");
                ps.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    //写入缓存到外部sd卡
    private void writeCacheFileToSDcard() {
        //别的程序无法访问
        //getExternalFilesDir在手机把内部存储当做外部sd卡存储使用的时候此方法返回null
        //storage/sdcard/android/data/com.recycler.zx.zxrecyclerview/1.txt
        try {
        File file = File.createTempFile("sss",null,getExternalCacheDir());
        if(file != null) {

                FileOutputStream out = new FileOutputStream(file+"/1.txt");
                PrintStream ps = new PrintStream(out);
                ps.print("1111111111111");
                ps.close();
                out.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCacheFile() {
        try {
            File file = File.createTempFile("temp.tmp",null,getCacheDir());
            FileOutputStream out = new FileOutputStream(file);
            PrintStream ps = new PrintStream(out);
            ps.print("星星是");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readRawFile() {
        try{
            InputStream is = getResources().openRawResource(R.raw.a);
            StringBuffer sb2 = new StringBuffer();
            byte[] bs2 = new byte[1024];
            int len2 = -1;
            while((len2 = is.read(bs2))!=-1) {
                sb2.append(new String(bs2,0,bs2.length));
            }
            is.close();
            Toast.makeText(this,sb2,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readPrivateFile() {
        try{
            InputStream in = openFileInput("a.txt");
            StringBuffer sb = new StringBuffer();
            byte[] bs = new byte[1024];
            int len = -1;
            while((len = in.read(bs))!=-1) {
                sb.append(new String(bs,0,bs.length));
            }
            in.close();
            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePrivateFile() {
        try{
        OutputStream out = openFileOutput("a.txt", Context.MODE_APPEND);//MODE_PRIVATE代表覆盖,MODE_APPEND代表追加
        String info = "1111";
        byte[] bytes = info.getBytes();
        out.write(bytes,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
