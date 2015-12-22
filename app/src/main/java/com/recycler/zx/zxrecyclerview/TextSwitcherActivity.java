package com.recycler.zx.zxrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.recycler.zx.zxrecyclerview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextSwitcherActivity extends AppCompatActivity implements ViewFactory, View.OnTouchListener {
    private int index;
    private float downX;
    private float lastX;
    @Bind(R.id.ts_text)
    TextSwitcher mTsText;
    private String texts[] = {"111", "222", "333"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        ButterKnife.bind(this);
        mTsText.setOnTouchListener(this);
        mTsText.setFactory(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                //手指按下的X坐标
                downX = event.getX();
                break;
            case MotionEvent.ACTION_UP :
                lastX = event.getX();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    if(index > 0){
                        index --;
                    }else{
                        Toast.makeText(getApplication(), "已经是第一张", Toast.LENGTH_SHORT).show();
                        index = texts.length -1;
                    }
                }
                if(lastX < downX){
                    if(index < texts.length - 1){
                        index ++ ;
                    }else{
                        Toast.makeText(getApplication(), "到了最后一张", Toast.LENGTH_SHORT).show();
                        index = 0;
                    }
                }
                mTsText.setText(texts[index]);
                break;
        }
        return true;
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(this);
        tv.setText(texts[0]);
        return tv;
    }
}
