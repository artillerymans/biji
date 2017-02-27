package com.example.zhiwei_zhu.drivinglicense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.zhiwei_zhu.drivinglicense.View.YuanButton;
import com.example.zhiwei_zhu.drivinglicense.activity.LianXiActivity;

import static com.example.zhiwei_zhu.drivinglicense.R.id.km1_cb;
import static com.example.zhiwei_zhu.drivinglicense.R.id.km2_cb;
import static com.example.zhiwei_zhu.drivinglicense.R.id.km3_cb;
import static com.example.zhiwei_zhu.drivinglicense.R.id.km4_cb;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private CheckBox km1, km2, km3, km4;

    private KM CHECK_FALG = KM.KM1TRUE;

    private YuanButton mYuanButtonMoni,mYuanButtonQuance;

    private Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        ininListener();
    }

    private void ininListener() {
        km1.setOnClickListener(this);
        km2.setOnClickListener(this);
        km3.setOnClickListener(this);
        km4.setOnClickListener(this);
    }

    private void initView() {
        km1 = (CheckBox) findViewById(km1_cb);
        km2 = (CheckBox) findViewById(km2_cb);
        km3 = (CheckBox) findViewById(km3_cb);
        km4 = (CheckBox) findViewById(km4_cb);
        mYuanButtonMoni = (YuanButton) findViewById(R.id.moniceshi);
        mYuanButtonQuance = (YuanButton) findViewById(R.id.tikuquce);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.km1_cb:
                if (km1.isChecked()) {
                    CHECK_FALG = KM.KM1TRUE;
                    km2.setChecked(false);
                    km3.setChecked(false);
                    km4.setChecked(false);
                }
                break;
            case R.id.km2_cb:
                if (km2.isChecked()) {
                    CHECK_FALG = KM.KM2TRUE;
                    km1.setChecked(false);
                    km3.setChecked(false);
                    km4.setChecked(false);
                }
                break;
            case R.id.km3_cb:
                if (km3.isChecked()) {
                    CHECK_FALG = KM.KM3TRUE;
                    km1.setChecked(false);
                    km2.setChecked(false);
                    km4.setChecked(false);
                }
                break;
            case R.id.km4_cb:
                if (km4.isChecked()) {
                    CHECK_FALG = KM.KM4TRUE;
                    km1.setChecked(false);
                    km2.setChecked(false);
                    km3.setChecked(false);
                }
                break;
            case R.id.moniceshi:
                    mIntent = null;
                    mIntent = new Intent(this, LianXiActivity.class);
                    mIntent.putExtra("key","mn");
                    startActivity(mIntent);
                break;
            case R.id.tikuquce:
                    mIntent = null;
                    mIntent = new Intent(this, LianXiActivity.class);
                    mIntent.putExtra("key","qc");
                    startActivity(mIntent);
                break;
        }
        Log.i(TAG,"KM IS === "+CHECK_FALG);
    }

    private enum KM {
        KM1TRUE,
        KM2TRUE,
        KM3TRUE,
        KM4TRUE,
    }
}
