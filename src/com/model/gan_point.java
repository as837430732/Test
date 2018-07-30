package com.model;

/**
 * Created by 高浩然 on 2017/5/2.
 */
public class gan_point {
    //横纵坐标以及属于+1 -1哪一类
    double x1 =0;
    double x2 =0;
    double y = 1;

    public gan_point() {
    }

    public gan_point(double x1, double x2, double y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
