package com.example.zhiwei_zhu.drivinglicense.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.zhiwei_zhu.drivinglicense.Enter.InfoEnter;
import com.example.zhiwei_zhu.drivinglicense.R;
import com.example.zhiwei_zhu.drivinglicense.Thread.ImageRunnable;
import com.example.zhiwei_zhu.drivinglicense.Thread.TimeRunnable;
import com.example.zhiwei_zhu.drivinglicense.biz.ImageGetSuuces;
import com.example.zhiwei_zhu.drivinglicense.biz.Tools;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;


/**
 * Created by zhiwei_zhu on 17/2/17.
 */

public class LianXiActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "LianXiActivity";

    private TextView backtv, stoptv, timetv;

    private int time = 2700;

    private boolean falg = false;

    private Thread timeThread = null;

    private String threadLock = new String();

    private TimeRunnable mRunnable = null;

    private LinearLayout mLinearLayoutXZ;

    private LinearLayout mLinearLayoutPD;

    private TextView mTextViewTimu;

    private ImageView mImageViewTuPian;

    private RadioGroup mRadioGroupXZ;

    private RadioGroup mRadioGroupPD;

    private TextView mTextViewDaAnJieShi;

    private Button mButtonNext;

    private JSONObject mJSONObject = null;

    private JSONArray mJSONArray = null;

    private ArrayList mArrayList = null;

    private int index = 0;

    private RadioButton mRadioButton1,mRadioButton2,mRadioButton3,mRadioButton4;
    private RadioButton mRadioButtonP1,mRadioButtonP2;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mImageViewTuPian.setImageDrawable((Drawable) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxi);
        initView();

        initListener();

        getData();

    }

    private void initListener() {
        stoptv.setOnClickListener(this);
        backtv.setOnClickListener(this);


    }

    private void initView() {

        backtv = (TextView) findViewById(R.id.back);
        stoptv = (TextView) findViewById(R.id.stop);
        timetv = (TextView) findViewById(R.id.time_tv);
        timetv.setText(getResources().getText(R.string.tilteltext));
        mRunnable = new TimeRunnable(mHandler, timetv);

        mLinearLayoutXZ = (LinearLayout) findViewById(R.id.layout_xuanzhe);
        mLinearLayoutPD = (LinearLayout) findViewById(R.id.layout_panduan);

        mTextViewTimu = (TextView) findViewById(R.id.tv_timu);
        mImageViewTuPian = (ImageView) findViewById(R.id.img_tupian);

        mRadioGroupXZ = (RadioGroup) findViewById(R.id.rg_group_xz);
        mRadioGroupPD = (RadioGroup) findViewById(R.id.rg_group_pd);

        mRadioGroupXZ.setOnCheckedChangeListener(this);
        mRadioGroupPD.setOnCheckedChangeListener(this);

        mTextViewDaAnJieShi = (TextView) findViewById(R.id.tv_jishidaan);
        mButtonNext = (Button) findViewById(R.id.btn_next);
        mButtonNext.setOnClickListener(this);



        mRadioButton1 = (RadioButton) findViewById(R.id.rg_group_xz_a);
        mRadioButton2 = (RadioButton) findViewById(R.id.rg_group_xz_b);
        mRadioButton3 = (RadioButton) findViewById(R.id.rg_group_xz_c);
        mRadioButton4 = (RadioButton) findViewById(R.id.rg_group_xz_d);

        mRadioButtonP1 = (RadioButton) findViewById(R.id.rg_group_pd_yes);
        mRadioButtonP2 = (RadioButton) findViewById(R.id.rg_group_pd_no);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mHandler != null && mRunnable != null) {
            mHandler.postDelayed(mRunnable, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop:
                startAndStop();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_next:
                index++;
                ShowTopic(mArrayList,index);
                break;
        }
    }

    private void startAndStop() {
        if (!falg) {
            falg = true;
            stoptv.setText("暂停");
            mRunnable.setStopFlag(falg, true);
        } else {
            falg = false;
            mRunnable.setStopFlag(falg, true);
            stoptv.setText("开始");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRunnable.setStopFlag(false, false);
        mHandler = null;
        mRunnable = null;
    }

    private void getData(){

        RequestParams mRequestParams = new RequestParams("http://api.avatardata.cn/Jztk/Query");
        mRequestParams.setConnectTimeout(60000);
        mRequestParams.addParameter("key","9dbbf6f93c294f8a9c24475064d7ffa5");
        mRequestParams.addParameter("subject","1");
        mRequestParams.addParameter("model","c1");
        mRequestParams.addParameter("testType","rand");

        x.http().request(HttpMethod.GET, mRequestParams, new Callback.CacheCallback<JSONObject>() {
            @Override
            public boolean onCache(JSONObject result) {
                return false;
            }

            @Override
            public void onSuccess(JSONObject result) {
                mArrayList = Tools.pareJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG,ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.i(TAG,"结束");
                ShowTopic(mArrayList,0);
            }
        });
    }


    private void ShowTopic(ArrayList<InfoEnter> arrayList, int indexs){

       InfoEnter info =  arrayList.get(indexs);
        String item3 = info.getItem3();
        mTextViewTimu.setText(info.getWenTi());
        String url = info.getUrl();
        mImageViewTuPian.setImageDrawable(null);
        if(!url.equals("") || !url.equals(null)){
            getDrawable(url);
        }
        if(item3.equals("")||item3.equals(null)){
            mLinearLayoutXZ.setVisibility(View.GONE);
            mLinearLayoutPD.setVisibility(View.VISIBLE);
            mRadioButtonP1.setText(info.getItem1());
            mRadioButtonP2.setText(info.getItem2());
            mRadioButtonP1.setChecked(false);
            mRadioButtonP2.setChecked(false);

        }else{
            mLinearLayoutXZ.setVisibility(View.VISIBLE);
            mLinearLayoutPD.setVisibility(View.GONE);
            mRadioButton1.setText(info.getItem1());
            mRadioButton2.setText(info.getItem2());
            mRadioButton3.setText(info.getItem3());
            mRadioButton4.setText(info.getItem4());
            mRadioButton1.setChecked(false);
            mRadioButton2.setChecked(false);
            mRadioButton3.setChecked(false);
            mRadioButton4.setChecked(false);
        }
        mTextViewDaAnJieShi.setText("");
        mTextViewDaAnJieShi.setBackgroundColor(Color.WHITE);

    }



    /*
        通过URL来获取图片,显示在ImageView
     */
    private void getDrawable(String url){
        ImageRunnable mImageRunnable = null;
        mImageRunnable = new ImageRunnable(url);
        mImageRunnable.setLicntion(new ImageGetSuuces() {
            @Override
            public void getImages(Drawable drawable) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = drawable;
                mHandler.sendMessage(msg);
            }
        });
        Thread thread = null;
        thread = new Thread(mImageRunnable);
        thread.start();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rg_group_xz_a:
                selectTopic("1");
                break;
            case R.id.rg_group_xz_b:
                selectTopic("2");
                break;
            case R.id.rg_group_xz_c:
                selectTopic("3");
                break;
            case R.id.rg_group_xz_d:
                selectTopic("4");
                break;
            case R.id.rg_group_pd_yes:
                selectTopic("1");
                break;
            case R.id.rg_group_pd_no:
                selectTopic("2");
                break;
        }
    }


    private boolean selectTopic(String string){
        InfoEnter infoEnter = (InfoEnter) mArrayList.get(index);
        String anser = infoEnter.getDaAn();
        mTextViewDaAnJieShi.setText(infoEnter.getDaAnJieShi());
        if(string.equals(anser)){
            mTextViewDaAnJieShi.setBackgroundColor(Color.GREEN);
            return true;
        }
        mTextViewDaAnJieShi.setBackgroundColor(Color.RED);

        return false;
    }

    private void ShowDalog(){

    }
}
