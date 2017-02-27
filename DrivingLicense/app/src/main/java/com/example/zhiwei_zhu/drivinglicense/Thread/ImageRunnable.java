package com.example.zhiwei_zhu.drivinglicense.Thread;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.zhiwei_zhu.drivinglicense.biz.ImageGetSuuces;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by zhiwei_zhu on 17/2/21.
 */

public class ImageRunnable implements Runnable {

    private static final String TAG = "ImageRunnable";
    private String url;
    private ImageGetSuuces mImageGetSuuces;

    public ImageRunnable(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            mImageGetSuuces.getImages(d);
        }catch (Exception e) {
            Log.i(TAG,"图片解析错误="+e.toString());
            e.printStackTrace();
        }
    }

    public void setLicntion(ImageGetSuuces licntion){
        this.mImageGetSuuces = licntion;
    }

}
