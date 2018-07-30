package com.model;

import com.model.Point;

import java.util.List;

/**
 * Created by lenovo on 2017/4/26.
 */
public class means {
    private List<Point> onePoint;
    private  List<Point> twoPoint;
    private Point one;
    private Point two;

    public List<Point> getOnePoint() {
        return onePoint;
    }

    public void setOnePoint(List<Point> onePoint) {
        this.onePoint = onePoint;
    }

    public List<Point> getTwoPoint() {
        return twoPoint;
    }

    public void setTwoPoint(List<Point> twoPoint) {
        this.twoPoint = twoPoint;
    }

    public Point getOne() {
        return one;
    }

    public void setOne(Point one) {
        this.one = one;
    }

    public Point getTwo() {
        return two;
    }

    public void setTwo(Point two) {
        this.two = two;
    }
}
