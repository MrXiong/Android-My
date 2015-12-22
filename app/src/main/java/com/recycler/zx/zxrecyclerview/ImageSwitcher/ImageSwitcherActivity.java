
package com.recycler.zx.zxrecyclerview.ImageSwitcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.recycler.zx.zxrecyclerview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageSwitcherActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory,View.OnTouchListener {

    private int index;
    private float downX;
    private float lastX;
    private int IMGS[] = {R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d};
    @Bind(R.id.is_img)
    ImageSwitcher isImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        ButterKnife.bind(this);
        //设置Factory
        isImg.setFactory(this);
        //设置OnTouchListener，我们通过Touch事件来切换图片
        isImg.setOnTouchListener(this);
    }

    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(IMGS[0]);
        return iv;
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
                        index = IMGS.length -1;
                    }
                }
                if(lastX < downX){
                    if(index < IMGS.length - 1){
                        index ++ ;
                    }else{
                        Toast.makeText(getApplication(), "到了最后一张", Toast.LENGTH_SHORT).show();
                        index = 0;
                    }
                }
                isImg.setImageResource(IMGS[index]);
                break;
        }
        return true;
    }
}

