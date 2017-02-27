package com.example.zhiwei_zhu.drivinglicense.Thread;


import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by zhiwei_zhu on 17/2/17.
 */

public class TimeRunnable implements Runnable {
    private static final String TAG = "TimeRunnable";
    private Handler mHandler;

    private int TIME = 2700;

    private int s = 60;

    private int tAll = 44;

    private boolean stopFlag = false;
    private boolean allStop = true;

    private StringBuilder mStringBuilder = null;

    private TextView mTextView;

    public TimeRunnable(Handler handler, TextView textView) {
        mHandler = handler;
        mStringBuilder = new StringBuilder();
        this.mTextView = textView;
    }

    @Override
    public void run() {

        try {
            if (!allStop) {
                return;
            }
            if (stopFlag) {
                StringBuilder time = getTime(TIME);
                mTextView.setText(time);
            }
            mHandler.postDelayed(this, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private StringBuilder getTime(int time) {
        TIME = time - 1;
        s = s - 1;
        if (s == 0) {
            tAll = tAll - 1;
            s = 59;
        }
        mStringBuilder.delete(0, mStringBuilder.length());
        mStringBuilder.append(tAll);
        mStringBuilder.append(":");
        mStringBuilder.append(s);
        Log.i(TAG, mStringBuilder.toString() + "< time");
        return mStringBuilder;
    }

    public void setStopFlag(boolean flag, boolean allStop) {
        this.stopFlag = flag;
    }

}
