package com.example.zhiwei_zhu.drivinglicense.biz;

import android.util.Log;

import com.example.zhiwei_zhu.drivinglicense.Enter.InfoEnter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhiwei_zhu on 17//21.
 */

public class Tools {

    private static final String TAG = "Tools";


    public static ArrayList pareJson(JSONObject result) {

        Log.i(TAG, "数据:" + result.toString());
        JSONArray mJSONArray = null;
        ArrayList<InfoEnter> mArrayList = new ArrayList();
        try {
            int code = (int) result.get("error_code");
            Log.i(TAG, "错误码:" + code);
            if (code == 0) {
                mJSONArray = result.getJSONArray("result");
                int length = mJSONArray.length() - 1;
                for (int i = 0; i < length; i++) {
                    JSONObject js = mJSONArray.getJSONObject(i);
                    InfoEnter mInfoEnter = new InfoEnter();
                    mInfoEnter.setId(js.getInt("id"));
                    mInfoEnter.setWenTi(js.getString("question"));
                    mInfoEnter.setDaAn(js.getString("answer"));
                    mInfoEnter.setDaAnJieShi(js.getString("explains"));
                    mInfoEnter.setUrl(js.getString("url"));
                    mInfoEnter.setItem1(js.getString("item1"));
                    mInfoEnter.setItem2(js.getString("item2"));
                    mInfoEnter.setItem3(js.getString("item3"));
                    mInfoEnter.setItem4(js.getString("item4"));

                    Log.i(TAG, "========================================================================");
                    Log.i(TAG, mInfoEnter.toString());
                    Log.i(TAG, "========================================================================");
                    mArrayList.add(i, mInfoEnter);
                }

            }else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(1,result.getString("error_code"));
                return  arrayList;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrayList;

    }
}
