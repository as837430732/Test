<%@ page import="javax.swing.text.Document" %>
<%@ page import="com.model.tempt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/12
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%--阈值界面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <title>阈值界面</title>
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="js/jquery-1.11.3.js"></script>
    <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div style="padding: 100px 300px 10px; float: left;width: 1400px">
    <div class="input-group" style="width: 270px;float: left">
        <input id="max" type="text" class="form-control">
        <span class="input-group-addon">最大值</span>
    </div>

    <div class="input-group" style="width: 270px;float: right">
        <input id="min" type="text" class="form-control">
        <span class="input-group-addon">最小值</span>
    </div>

    <div>
        <button class="btn btn-success" id="btn" type="button" style="width: 100px;" onclick="click()">选择</button>
    </div>



    <% List<tempt> tempts = (ArrayList<tempt>) request.getAttribute("tempts"); %>
    <div>
        <form  role="form" action="<%=path%>/TemptServlet?flag=yuzhi" method="post">

            <div style="width: 71%;margin: 5px;margin-left: 100px;text-align: center;">
                <table class="table table-striped" id="yu">
                    <%--<caption>条纹表格布局</caption>--%>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>时间</th>
                        <th>温度</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (tempt tempt : tempts) { %>
                    <tr>
                        <td><%=tempt.getId()%>
                        </td>
                        <td><%=tempt.getDat()%>
                        </td>
                        <td><%=tempt.getTemp()%>
                        </td>
                    </tr>
                    <%}%>

                    </tbody>
                </table>
            </div>



        </form>

    </div>
</div>

<script type="text/javascript">

    var btn = document.getElementById('btn');
    btn.onclick=function () {

        var a = document.getElementById('min').value;
        var min = parseInt(a);

        var b = document.getElementById('max').value;
        var max = parseInt(b);
        var tb = document.getElementById("yu");    //获取table对像
        var rows = tb.rows;
        rows[1].style.background = "red";
        for (var i = 1; i < rows.length; i++) {    //--循环所有的行
            var cells = rows[i].cells;
            for (var j = 0; j < 3; j++) {   //--循环所有的列
               // alert(1);
                var a = cells[2].innerHTML;
                console.log(a);
                var m = parseInt(a);
                if (m >= min && m <= max) {

                    rows[i].style.background = "red";//背景颜色设置
                }

            }

        }
    }
    //显示
</script>
</body>
</html>
