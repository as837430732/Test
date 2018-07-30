package com.main;

import java.sql.*;

/**
 * Created by 高浩然 on 2017/3/3.
 */
public class Test2 {
    String driver,url,user,password;

    public Test2(String driver, String url, String user, String password) {
        this.driver = driver;  //驱动程序名
        this.url = url;   //URL指向要访问的数据库名trdb
        this.user = user; //MySQL配置时的用户名
        this.password = password; //MySQL配置时的密码
    }

    public static void T_insert(String year,String mounth,String day,String temp1){
        //声明Connection对象
        Connection con;
        Test2 t = new Test2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
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
            String sql = "insert into tempt (year,mounth,day,temperature) " +
                    "values("+year+","+mounth+","+day+","+temp1+");";
            //3.ResultSet类，用来存放获取的结果集！！
           statement.execute(sql);

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
    public static void  T_select(){
        //声明Connection对象
        Connection con;
        Test2 t = new Test2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
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
            String sql = "select * from tempt";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String job = null;
            String id = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("data");
                //获取stuid这列数据
                id = rs.getString("temperature");

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

//    public static void main(String[] args) {
//        //声明Connection对象
//             Connection con;
//        com.main.Test2 t = new com.main.Test2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");
////               //驱动程序名
////               String driver = "com.mysql.jdbc.Driver";
////                 //URL指向要访问的数据库名trdb
////                 String url = "jdbc:mysql://localhost:3306/trdb";
////                //MySQL配置时的用户名
////              String user = "root";
////        //MySQL配置时的密码
////               String password = "root";
//               //遍历查询结果集
//                try {
//                        //加载驱动程序
//                       Class.forName(t.driver);
//                        //1.getConnection()方法，连接MySQL数据库！！
//                    con = DriverManager.getConnection(t.url,t.user,t.password);
//                       if(!con.isClosed())
//                               System.out.println("Succeeded connecting to the Database!");
//                      //2.创建statement类对象，用来执行SQL语句！！
//                        Statement statement = con.createStatement();
//                        //要执行的SQL语句
//
//                        //3.ResultSet类，用来存放获取的结果集！！
//                       ResultSet rs = statement.executeQuery(sql);
//                        System.out.println("-----------------");
//                       System.out.println("执行结果如下所示:");
//                       System.out.println("-----------------");
//                        System.out.println("姓名" + "\t" + "职称");
//                      System.out.println("-----------------");
//
//                       String job = null;
//                      String id = null;
//                    while(rs.next()){
//                              //获取stuname这列数据
//                               job = rs.getString("data");
//                              //获取stuid这列数据
//                                id = rs.getString("temperature");
//
//                              //输出结果
//                               System.out.println(id + "\t" + job);
//                          }
//                        rs.close();
//                        con.close();
//                    } catch(ClassNotFoundException e) {
//                       //数据库驱动类异常处理
//                     System.out.println("Sorry,can`t find the Driver!");
//                        e.printStackTrace();
//                        } catch(SQLException e) {
//                        //数据库连接失败异常处理
//                         e.printStackTrace();
//                         }catch (Exception e) {
//                         // TODO: handle exception
//                         e.printStackTrace();
//                     }finally{
//                         System.out.println("数据库数据成功获取！！");
//                     }
//            }
    }

