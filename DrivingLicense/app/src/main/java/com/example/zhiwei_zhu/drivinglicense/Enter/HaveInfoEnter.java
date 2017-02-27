package com.example.zhiwei_zhu.drivinglicense.Enter;

import java.util.ArrayList;

/**
 * Created by zhiwei_zhu on 17/2/16.
 */

public class HaveInfoEnter {
    private int error_code;
    private String reason;
    private ArrayList<InfoEnter> result;

    public HaveInfoEnter() {

    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<InfoEnter> getResult() {
        return result;
    }

    public void setResult(ArrayList<InfoEnter> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HaveInfoEnter{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
