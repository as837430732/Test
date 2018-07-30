package com.model;

/**
 * Created by 高浩然 on 2017/3/3.
 */
/**
 * Created by 高浩然 on 2017/3/3.
 */
public class tempt {
    private int id;
    private String dat;
    private int temp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "tempt{" +
                "id=" + id +
                ", dat='" + dat + '\'' +
                ", temp='" + temp + '\'' +
                '}'+'*';
    }

    public tempt(int id, String dat, int temp) {
        this.id = id;
        this.dat = dat;
        this.temp = temp;
    }
}
