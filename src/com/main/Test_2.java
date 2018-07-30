package com.main;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

/**
 * Created by 高浩然 on 2017/3/5.
 */
public class Test_2 {
    String driver,url,user,password;

    public Test_2(String driver, String url, String user, String password) {
        this.driver = driver;  //驱动程序名
        this.url = url;   //URL指向要访问的数据库名trdb
        this.user = user; //MySQL配置时的用户名
        this.password = password; //MySQL配置时的密码
    }

    public static void t_insert(Timestamp time, int temp1){
        //声明Connection对象
        Connection con;
        Test_2 t = new Test_2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
        try {
            //加载驱动程序
            Class.forName(t.driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(t.url,t.user,t.password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            System.out.println("dfsdfdsfsd"+time);
            //要执行的SQL语句
            String sql = "insert into temptd (theti,temperatur) values ("+"'"+time+"'"+","+temp1+");";
            //3.ResultSet类，用来存放获取的结果集！！
            statement.executeUpdate(sql);

        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("语句成功执行！！");
        }
    }

    public static void t_delete(){
        //声明Connection对象
        Connection con;
        Test_2 t = new Test_2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
        try {
            //加载驱动程序
            Class.forName(t.driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(t.url,t.user,t.password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
           // System.out.println("dfsdfdsfsd"+time);
            //要执行的SQL语句
            String sql = "DELETE  FROM  ";
            //3.ResultSet类，用来存放获取的结果集！！
            statement.executeUpdate(sql);

        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("语句成功执行！！");
        }
    }

    public static void  t_select(){
        //声明Connection对象
        Connection con;
        Test_2 t = new Test_2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
//               //驱动程序名
//               String driver = "com.mysql.jdbc.Driver";
//                 //URL指向要访问的数据库名trdb
//                 String url = "jdbc:mysql://localhost:3306/trdb";
//                //MySQL配置时的用户名
//              String user = "root";
//        //MySQL配置时的密码
//               String password = "root";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(t.driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(t.url,t.user,t.password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String job = null;
            String id = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("theti");
                //获取stuid这列数据
                id = rs.getString("temperatur");

                //输出结果
                System.out.println(id + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }

    }
}
