package com.main; /**
 * Created by 高浩然 on 2017/3/2.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class shiyan {

        public static void main(String[] args) throws IOException {
            String a =
                    "0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999";
            //String b = "^\\d{15}(.*)+\\d{5}+\\d{5}+.*-\\d{2}+.*N9(.)(.*)+";
            String c= "\\d{15}(\\d{8}).*N9([+-]\\d{5})\\+.*";
            String data ="";
            String year,mounth,day;
            String temp = "";
            String line="";
            int i=0;

            Test2 t = new Test2("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/trdb","root","root");

            File f = new File("E://1901//1901");
            if(f.exists()){
                System.out.println("存在");
            }
            BufferedReader b1 = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		while((line=b1.readLine())!=null){
			//b=b+line+"\n\r";
            Pattern p =Pattern.compile(c);
            Matcher m = p.matcher(line);
            if(m.find()){
               // System.out.println(m.group(0));
              //  System.out.println(m.group(1));
                year=expect_zero(m.group(1).substring(0,4));
                mounth=expect_zero(m.group(1).substring(4,6));
                day=expect_zero(m.group(1).substring(6,8));

               temp= dei_temp(m.group(2));
                //显示年月日及温度
                System.out.println("日期 "+year+"年"+mounth+"月"+day+"日"+"  温度"+temp);

               t.T_insert(year,mounth,day,temp);
              //  System.out.println(m.group(2));
                    i++;
                //System.out.println(m.group(2));
            }
		}
            System.out.println("总共"+i+"条数据");
            t.T_select();

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
        }

    //去除日期中的0
    public static String expect_zero(String s){
        return Integer.parseInt(s)+"";
    }

//处理温度信息的方法
    public static String dei_temp(String s){
        char fu = s.charAt(0);//获取符号
      //  System.out.println("符号"+fu);
        String temp = s.substring(1,5);//获取温度
       // System.out.println("温度"+temp);
        int t=Integer.parseInt(temp);
       // System.out.println(t);
        return String.valueOf(fu)+t+"";
    }
}

