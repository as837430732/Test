package com.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by 高浩然 on 2017/3/5.
 */
//负责将字符串转换为data
public class StringtoData {
    public final static java.sql.Date string2Date(String dateString)
            throws java.lang.Exception {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
        dateFormat.setLenient(false);
        java.util.Date timeDate = dateFormat.parse(dateString);//util类型
        java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
        return dateTime;
    }
}
