package com.recycler.zx.zxrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimeActivity extends AppCompatActivity {


    @Bind(R.id.timePicker)
    TimePicker timePicker;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.datePicker)
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);

    }
    //屏幕旋转时会触发（也就是说异常销毁activity）
   /* protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key1","valuess");
    }*/
    //可以在onCreate中恢复数据,也可以在onRestoreInstanceState中恢复数据

    /*protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }*/

    public void SaveClick(View v) {
        /*int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        Toast.makeText(this, "hout" + hour + "" + minute, Toast.LENGTH_SHORT).show();*/
    }
    public void OKClick(View v){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        Toast.makeText(this, "年" + year + "月" + (month+1)+"日"+day, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }
}
