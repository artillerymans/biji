package com.example.zhiwei_zhu.drivinglicense.Enter;

/**
 * Created by zhiwei_zhu on 17/2/16.
 */

public class InfoEnter {

    private int id;
    private String wenTi;
    private String daAn;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String daAnJieShi;
    private String url;

    public InfoEnter() {

    }

    public InfoEnter(int id, String wenTi, String daAn, String item1, String item2, String item3, String item4, String daAnJieShi, String url) {
        this.id = id;
        this.wenTi = wenTi;
        this.daAn = daAn;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.daAnJieShi = daAnJieShi;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getWenTi() {
        return wenTi;
    }

    public String getDaAn() {
        return daAn;
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public String getItem3() {
        return item3;
    }

    public String getItem4() {
        return item4;
    }

    public String getDaAnJieShi() {
        return daAnJieShi;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWenTi(String wenTi) {
        this.wenTi = wenTi;
    }

    public void setDaAn(String daAn) {
        this.daAn = daAn;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public void setDaAnJieShi(String daAnJieShi) {
        this.daAnJieShi = daAnJieShi;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "InfoEnter{" +
                "id=" + id +
                ", wenTi='" + wenTi + '\'' +
                ", daAn='" + daAn + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", daAnJieShi='" + daAnJieShi + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
