package com.example.zhiwei_zhu.drivinglicense.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhiwei_zhu.drivinglicense.ChangLiang.ChangLiang;
import com.example.zhiwei_zhu.drivinglicense.R;

/**
 * Created by zhiwei_zhu on 17/2/16.
 */

public class YuanButton extends View {
    private static final String TAG = "YuanButton";

    private int windonsWith = 300;
    private Paint mPaint = null;
    private Paint mPaint1 = null;
    private String text = null;

    public YuanButton(Context context) {
       this(context,null);
        Log.i(TAG,"YuanButton(Context context)");
        init();
    }

    public YuanButton(Context context, AttributeSet attrs) {
        this(context,attrs,0);
        Log.i(TAG,"YuanButton(Context context, AttributeSet attrs)");
        init();
    }

    public YuanButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.i(TAG,"YuanButton(Context context, AttributeSet attrs, int defStyleAttr)");
        TypedArray typ = context.obtainStyledAttributes(attrs, R.styleable.YuanButton_value);
        text = typ.getString(R.styleable.YuanButton_value_names);
        Log.i(TAG,"text==="+text);
        typ.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,"onMeasure(int widthMeasureSpec, int heightMeasureSpec)");
        setMeasuredDimension(windonsWith,windonsWith);
        Log.i(TAG,"windonsWith:"+windonsWith);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"onDraw(Canvas canvas)");
        drawC1orC2(canvas);
    }



    private void init(){

        mPaint = new Paint();
        mPaint1 = new Paint();

        if(ChangLiang.WIDTH!=0){
            windonsWith = ChangLiang.WIDTH/2;
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setAntiAlias(true);
        mPaint1.setDither(true);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setTextAlign(Paint.Align.CENTER);
        mPaint1.setTextSize(70);

    }

    private void drawC1orC2(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(windonsWith/2, windonsWith/2, windonsWith/2-10, mPaint);
        canvas.restore();

        canvas.save();
        canvas.drawText(text,windonsWith/2, windonsWith/2,mPaint1);
        canvas.restore();
    }

}
