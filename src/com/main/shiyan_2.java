package com.main;

import com.model.StringtoData;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 高浩然 on 2017/3/5.
 */

public class shiyan_2 {
    public static void main(String[] args) throws IOException {
        String c = "\\d{15}(\\d{12}).*N9([+-]\\d{5})\\+.*";
        String datatime = "";
        String year, mounth, day,time;
        int temp ;
        String line = "";
       // java.sql.Date d = null;
        int i = 0;

        Test_2 t = new Test_2("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/trdb", "root", "root");

        File f = new File("E://1901//1901");
        FileWriter writefile=new FileWriter("D:\\1901.txt",true);

        if (f.exists()) {
            System.out.println("存在");
        }
        BufferedReader b1 = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        while ((line = b1.readLine()) != null) {
            //b=b+line+"\n\r";
            Pattern p = Pattern.compile(c);
            Matcher m = p.matcher(line);
            if (m.find()) {
                // System.out.println(m.group(0));
                //  System.out.println(m.group(1));
                year = expect_zero(m.group(1).substring(0, 4));
                mounth = expect_zero(m.group(1).substring(4, 6));
                day = expect_zero(m.group(1).substring(6, 8));
                time =expect_zero(m.group(1).substring(8, 10));

                datatime=year+"-"+mounth+"-"+day+" "+time+":00:00";

                temp = dei_temp(m.group(2));
                //显示年月日及温度
                System.out.println("日期 " + year + "年" + mounth + "月" + day + "日" + time+"时 "+"  温度" + temp);
                writefile.write("("+datatime+"   "+temp+")"+"\r\n");//写入文件中

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null; //初始化date
                try {
                    date = sdf.parse(datatime);//将string转换为date类型
                    Timestamp timestamp = new Timestamp(date.getTime());//时间戳

                    System.out.println(timestamp);

                  //  t.t_insert(timestamp, temp);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }


                //  System.out.println(m.group(2));
                i++;
                //System.out.println(m.group(2));
            }
        }
        writefile.close();
        System.out.println("总共" + i + "条数据");
        t.t_select();
    }
//            Pattern p =Pattern.compile(c);
//            Matcher m = p.matcher(a);
//            if(m.find()){
//               // System.out.println(m.group(0));
//                System.out.println(m.group(1));
//                data[i]=m.group(1);
//               temp[i]= dei_temp(m.group(2));
//                System.out.println(m.group(2));
//                i++;
//                //System.out.println(m.group(2));
//            }

    //System.out.println(b);


//            for(int ii=0;ii<data.length;i++){
//                System.out.println("日期"+data[ii]+"  温度"+temp[ii]);
//            }
//            System.out.println("jieshu");


    //去除日期中的0
    public static String expect_zero(String s){
        return Integer.parseInt(s)+"";
    }

    //处理温度信息的方法
    public static int dei_temp(String s){
        char fu = s.charAt(0);//获取符号
        //  System.out.println("符号"+fu);
        String temp = s.substring(1,5);//获取温度
        // System.out.println("温度"+temp);
        int t=Integer.parseInt(temp);
        // System.out.println(t);
        String a =String.valueOf(fu)+t+"";
        System.out.println("整型"+Integer.parseInt(a));
        return Integer.parseInt(a);
    }
}
