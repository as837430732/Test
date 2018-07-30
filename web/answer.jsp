<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.tempt" %><%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/3
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--取项目根路径--%>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>功能界面</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
</head>

<%--css样式 横版 占大小 --%>
<style>

    table {
        text-align: center;
    }
    th {
        text-align: center;
    }

</style>

<body style="background :url(img/2.jpg)" >

<%--<embed style="display:none" height="100" width="100" src="miusic/space.mp3" autostart="true"></embed>--%>

<% List<tempt> tempts = (ArrayList<tempt>) request.getAttribute("tempts"); %>

<%--<%for(tempt t:tempts) { %>
  &lt;%&ndash;  <%=%> 为输出&ndash;%&gt;
  <%= t.toString() %><br />
<%}%>--%>
<%@include file="nav.jsp"%>

<div>
    <form role="form" action="<%=path%>/TemptServlet?flag=answer" method="post">
        <div style="width: 71%;margin: 5px;margin-left: 100px;text-align: center;">
            <table class="table table-striped">
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


</body>
</html>
