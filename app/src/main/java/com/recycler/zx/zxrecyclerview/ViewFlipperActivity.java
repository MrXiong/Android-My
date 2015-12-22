package com.recycler.zx.zxrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;
import android.widget.ViewFlipper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewFlipperActivity extends AppCompatActivity {

    private float downX;
    private float lastX;
    @Bind(R.id.vf_img)
    ViewFlipper mVfImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    //手指按下的X坐标
                    downX = event.getX();
                    break;
                case MotionEvent.ACTION_UP :
                    lastX = event.getX();
                    //抬起的时候的X坐标大于按下的时候就显示上一张图片
                    if(lastX > downX){
                        mVfImg.showPrevious();
                    }
                    if(lastX < downX){
                        mVfImg.showNext();;
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }
}
