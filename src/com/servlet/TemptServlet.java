package com.servlet;

import com.main.gan_ai;
import com.model.Point;
import com.model.gan_point;
import com.model.tempt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高浩然 on 2017/3/3.
 */
public class TemptServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }


    String b_d=null,e_d=null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取到前端的数据 demo中的name属性就是标识下拉框中的内容 按钮的属性为submit 即把表单内容发送给服务器
        //通过severlet的request进行获取
        //String b_data = null,e_data=null;
        //data d=null;
        switch (request.getParameter("flag")){
            case "demo"://主界面
                List<String> a=demo(request,response);
                b_d=  a.get(0);//获取到开始时间 以及 结束时间
                e_d=  a.get(1);
               // d = new data(b_data,e_data);
            break;
            case "answer"://降序 升序
                answer(request,response,b_d,e_d);
                break;
            case "date"://显示用户选择的日期
                date(request,response);
                break;
            case "change"://显示温度列表
                change(request , response);
                break;
            case "homepage"://回到主页
                homepage(request,response);
                break;
            case "quxian"://动态显示的曲线图 只局限与11月30日
                quxian(request,response,b_d,e_d);
                break;
            case "test"://用于显示选择时间段数据的曲线图
                test(request,response,b_d,e_d);
                break;
            case "yuzhi"://用于显示阈值选择界面
                yuzhi(request,response,b_d,e_d);
                break;
            case "AI"://用于人工智能功能
                AI(request,response,b_d,e_d);
        }
    }

    private void AI(HttpServletRequest request, HttpServletResponse response, String b_d, String e_d) throws ServletException, IOException {
        System.out.println("进入人工智能界面");
        System.out.println("-----------"+b_d);
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";
        Point[] point = new Point[10000];
        List<Point> po=new ArrayList<>();
        Point po1=new Point();
        Point po2=new Point();
        int j=0;
        //此部分实现将用户选择时间段的信息显示在曲线图中
        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"'  GROUP BY theti";
            //group by去重
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String id = null;
            String date=null;
            //两个类 因为有两种类别 +1  -1
//            gan_point g1 = null;
//            gan_point g2 = null;
            List<Integer> x1 = new ArrayList<>();
            List<Integer> x2 = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            int temp ;

            while(rs.next()){
                
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");
                System.out.print("2222222222"+temp);
                System.out.print("2222222222"+temp);
                point[j]=new Point(j,temp);
                po.add(point[j]);

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                //若温度大于零属于+1
                if(temp>20){
                    //g1=new gan_point(temp,temp,1);
                    x1.add(j);
                    x2.add(temp);
                    y.add(1);
                }else{
                    //若温度小于零属于-1
                   // g2=new gan_point(temp,temp,-1);
                    x1.add(j);
                    x2.add(temp);
                    y.add(-1);
                }
//                tempt1.add(new tempt(i,date,temp));
//                i++;
                j++;
            }
            //进行参数训练
            gan_ai gan = new gan_ai();
            String s = gan.training(x1,x2,y,j);
            String[] crood=s.split(" ");//坐标
            po1.setX(j);
            po1.setY(Double.parseDouble(crood[0]));
            po2.setX(Double.parseDouble(crood[1]));
            po2.setY(0);
            System.out.print(po1.toString());
            System.out.print(po2.toString());
            rs.close();
            con.close();

            //将数据转换为json格式 并且存放至test的界面中


                try {
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArrayOne = JSONArray.fromObject(po);
                    jsonObject.put("Point" , jsonArrayOne);
                    jsonObject.put("oneX" ,(int)po1.getX() );
                    jsonObject.put("oneY" ,(int) po1.getY());
                    jsonObject.put("twoX" ,(int)po2.getX());
                    jsonObject.put("twoY" ,(int)po2.getY());
                    System.out.println(jsonArrayOne);
                    response.setContentType("text/html;chartset=utf-8");
                    response.getWriter().print(jsonObject);
                }catch (Exception e) {
                    e.printStackTrace();
                }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void yuzhi(HttpServletRequest request, HttpServletResponse response, String b_d, String e_d) throws ServletException, IOException {
        System.out.println("进入阈值界面中");
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";


        //此部分实现将用户选择时间段的信息显示在曲线图中
        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"'  GROUP BY theti";

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("tempts" , tempt1);
        //通过这行代码将request response中的信息发送给answer界面

        request.getRequestDispatcher("/specific.jsp").forward(request , response);
    }

    private void test(HttpServletRequest request, HttpServletResponse response, String b_d, String e_d) {

        System.out.println("已经进入test");
        System.out.println("-----------"+b_d);
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";


        //此部分实现将用户选择时间段的信息显示在曲线图中
        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"'  GROUP BY theti";
            //group by去重
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();

            //将数据转换为json格式 并且存放至test的界面中
            if(tempt1 != null) {
                JSONArray jsonArray = JSONArray.fromObject(tempt1);
                try {
                    response.setContentType("text/html;chartset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print(jsonArray);
                    return;
//                    response.getWriter().print(jsonArray);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void quxian(HttpServletRequest request, HttpServletResponse response, String b_d, String e_d) throws ServletException, IOException {

        System.out.println("已经进入");
        System.out.println("-----------"+b_d);
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";


        //此部分实现将用户选择时间段的信息显示在曲线图中
        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"'";

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //此部分得出温度最高点的日期
        List<tempt> tempt2 = new ArrayList<>();
        List<tempt> tempt3 = new ArrayList<>();
        int i1=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where temperatur in (select min(temperatur) from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"')";

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt2.add(new tempt(i1,date,temp));


                i1++;

            }
            rs.close();
           // con.close();

            con = DriverManager.getConnection(url,user,password);
            Statement statement1 = con.createStatement();
            int i2=0;
            String sq =  "select * from temptd where temperatur in (select max(temperatur) from temptd where theti between '" +b_d+"'"+"and"+"'"+e_d+"')";
            ResultSet rs1 = statement1.executeQuery(sq);
            while(rs1.next()){
                System.out.println("===========================");
                id=rs1.getString("id");
                date=rs1.getString("theti");
                temp = rs1.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                System.out.println("--------------------------");
                tempt3.add(new tempt(i2,date,temp));


                i2++;

            }
            rs1.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //信息存储在request 和 response中
        //后台发给前台数据
        List<tempt> tempts = new ArrayList<>();

        request.setAttribute("tempt3",tempt3);
        request.setAttribute("tempt2",tempt2);
        request.setAttribute("tempts" , tempt1);

        //通过这行代码将request response中的信息发送给answer界面
        request.getRequestDispatcher("/change.jsp").forward(request , response);
    }

    private void homepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("____________啦啦啦啦啦");
        request.getRequestDispatcher("/demo.jsp").forward(request , response);
    }

    private void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";


        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
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
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");
                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("tempts" , tempt1);
        //通过这行代码将request response中的信息发送给answer界面
        request.getRequestDispatcher("/line.jsp").forward(request , response);


    }

    public String StringtoInt(String s){
        System.out.println("s_data___________________"+Integer.parseInt(s));
        if(Integer.parseInt(s)<10){
            s="0"+s;

        }
        System.out.println(s);
        return s;
    }
    private void date(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入这里");
        //选择的时间
        String s_year = request.getParameter("year2");
        String s_mouth = request.getParameter("mouth2");
        String s_day = request.getParameter("day2");


        String i_s_mouth = StringtoInt(s_mouth);
        String i_s_day = StringtoInt(s_day);

        String s_date = s_year+"-"+i_s_mouth+"-"+i_s_day;
        System.out.println("------"+i_s_mouth+i_s_day);
        System.out.println("s_date"+s_date);

        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";


        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti like '"+s_date+"%'";

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");


            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
               i++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("tempts" , tempt1);
        //通过这行代码将request response中的信息发送给answer界面
        request.getRequestDispatcher("/answer.jsp").forward(request , response);
    }

    private List<String> demo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String b_year = request.getParameter("year");
        String b_mouth = request.getParameter("mouth");
        String b_day = request.getParameter("day");

        String e_year = request.getParameter("year1");
        String e_mouth = request.getParameter("mouth1");
        String e_day = request.getParameter("day1");



        System.out.println(b_year+b_mouth+b_day+"sfsdfdfsefsdfdsf");
        System.out.println(e_year+e_mouth+e_day+"sfsdfdfsefsdfdsf");
        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";

        String b_date = b_year+"-"+b_mouth+"-"+b_day+" ";
        String e_date = e_year+"-"+e_mouth+"-"+e_day+" ";

        System.out.println(b_date);
        List<tempt> tempt1 = new ArrayList<>();
        int i=0;
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from temptd where theti between '" +b_date+"'"+"and"+"'"+e_date+"'";

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");

                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //信息存储在request 和 response中
        //后台发给前台数据
        List<tempt> tempts = new ArrayList<>();
//        tempts.add(new tempt(1,"1990123123" , "123"));
//        tempts.add(new tempt(2,"1990123123" , "123"));
//        tempts.add(new tempt(3,"1990123123" , "123"));
//        tempts.add(new tempt(4,"1990123123" , "123"));
//        tempts.add(new tempt(5,"1990123123" , "123"));

        request.setAttribute("tempts" , tempt1);

        //通过这行代码将request response中的信息发送给answer界面
        request.getRequestDispatcher("/answer.jsp").forward(request , response);
        List<String> l =new ArrayList<>();
        l.add(b_date);
        l.add(e_date);
        return  l;
    }

    private void answer(HttpServletRequest request, HttpServletResponse response,String b_data,String e_data) throws ServletException, IOException {

        System.out.println("成功到达");



        System.out.println("____________________________________");
        System.out.println(b_data+"fdfdfd"+e_data);
        System.out.println("____________________________________");
        String xu = request.getParameter("xu");
        System.out.println("序列 "+xu);

        //数据库操作
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/trdb";
        String user = "root";
        String password = "root";
        String sql = null;
        int i=0;

        List<tempt> tempt1 = new ArrayList<>();
        try{
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            if(xu.equals("top")){
                sql = "SELECT * from temptd where theti between '" +b_data+"'"+"and"+"'"+e_data+"' ORDER BY temperatur ASC";//升序
                System.out.println("121212121212");
            }

            if(xu.equals("down"))
                sql ="SELECT * from temptd where theti between '" +b_data+"'"+"and"+"'"+e_data+"' ORDER BY temperatur DESC";//降序
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("id" + "\t" + "时间"+"\t\t\t\t"+"温度");
            System.out.println("-----------------");

            String id = null;
            String date=null;
            int temp ;
            while(rs.next()){
                id=rs.getString("id");
                date=rs.getString("theti");
                temp = rs.getInt("temperatur");
                //输出结果
                System.out.println("id"+id+"   date"+date+"  temperature"+temp);
                tempt1.add(new tempt(i,date,temp));
                i++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("tempts" , tempt1);
        //通过这行代码将request response中的信息发送给answer界面
        request.getRequestDispatcher("/answer.jsp").forward(request , response);
    }


}

