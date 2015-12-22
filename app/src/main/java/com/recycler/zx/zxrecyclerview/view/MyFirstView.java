package com.recycler.zx.zxrecyclerview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.recycler.zx.zxrecyclerview.R;

/**
 * Created by zx on 2015/12/15.
 */
public class MyFirstView extends View {

    private int textColor;
    private float textSize;
    private String text;
    private Paint paint;//画笔

    public MyFirstView(Context context) {
        super(context);
    }


    public MyFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //通常使用2个参数的构造方法
   public MyFirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
       //实例化画笔
        paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.myview);
        textColor = typedArray.getColor(R.styleable.myview_textColor,0xFFFFFF);
        textSize = typedArray.getDimension(R.styleable.myview_textSize,22);
        text =  typedArray.getString(R.styleable.myview_text);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawText(text,10,10,paint);
    }
}
