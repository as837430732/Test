package com.main;

import java.util.List;

/**
 * Created by 高浩然 on 2017/5/1.
 */
public class gan_ai {

    public int Sign(float f){
        if(f > 0)
            return 1;
        else
            return -1;
    }

    public String training(List<Integer> x1 ,List<Integer> x2 , List<Integer> y,int jj){


//        float [] x1 = {1,2,3,2,3,4,6,7};//横坐标
//        float [] x2 = {3,5,8,6,1,1,2,3};//纵坐标
//        float [] y ={1,1,1,1,-1,-1,-1,-1};
        float [] weight ={0,0};
        float bias=0;
        float predict;
        for(int j=0;j<300;j++){
            for(int i=0;i<x1.size();i++){
                predict = Sign(weight[0] * x1.get(i) + weight[1] * x2.get(i) + bias);
                System.out.println("train data: x: ("+x1.get(i)+","+x2.get(i)+") y: "+y.get(i)+"  ==> predict: "+predict);
                if(y.get(i) * predict <= 0){//判断误分类点
                    weight[0] = weight[0] +  y.get(i) * x1.get(i);//更新权重
                    weight[1] = weight[1] +  y.get(i) * x2.get(i);//
                    bias = bias +   y.get(i);//更新偏置量
                    System.out.println("upd ate weight and bias: ");
                    System.out.println(weight[0]+"  "+weight[1]+"  "+bias);
                }
            }
        }
        System.out.println("stop training: ");
        System.out.println(weight[0]+"  "+weight[1]+"  "+bias);
        //计算出在x轴的截距以及在y轴的截距 y =w1*x1+ w2*x2+ bias
        //(0,-bias/w2)  (-bias/w1,0)
        //需要返还给主程序两个参数  通过字符串连接
        float a1 = (-bias-jj*weight[0])/weight[1];
        float a2 = -bias/weight[0];
        return a1+" "+a2;
    }

//    public static void main(String[] args) {
//        gan_ai s = new gan_ai();
//        s.training();
//    }
}
