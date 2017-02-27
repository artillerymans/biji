package com.example.zhiwei_zhu.drivinglicense;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

import com.example.zhiwei_zhu.drivinglicense.ChangLiang.ChangLiang;

import org.xutils.x;

/**
 * Created by zhiwei_zhu on 17/2/16.
 */

public class MyApplitions extends Application {
    private static final String TAG = "MyApplitions";
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        getWindonsWidth();
    }

    public void getWindonsWidth() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        ChangLiang.WIDTH= wm.getDefaultDisplay().getWidth();
        ChangLiang.HEIGHT = wm.getDefaultDisplay().getHeight();
        Log.i(TAG,"W:H"+ChangLiang.WIDTH+":"+ChangLiang.HEIGHT);
    }
}
