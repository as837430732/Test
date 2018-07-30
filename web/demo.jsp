<%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/3
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%--通过serverlet获取客户端发送至服务器的数据--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--取项目根路径--%>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>主界面</title>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/carousel.css">
    <script src="js/jquery-1.11.3.js"></script>
    <script src="js/bootstrap.js"></script>

</head>
<%--css样式 横版 占大小 --%>
<style>
    .form-group {
        width: 25%;
        float: left;
        padding: 20px;
    }
</style>


<body style="background :url(img/2.jpg);">

<%--action属性访问http://127.0.0.1:8080/--%>
<div style="margin: 20px;">

    <!--图片轮播-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div id="carousel-example-generic" class="carousel slide"
                     data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0"
                            class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="img/adver/1.jpg"
                                 alt="...">
                            <div class="carousel-caption">...</div>
                        </div>
                        <div class="item">
                            <img src="img/adver/2.jpg"
                                 alt="...">
                            <div class="carousel-caption">...</div>
                        </div>
                        <div class="item">
                            <img src="img/adver/3.jpg"
                                 alt="...">
                            <div class="carousel-caption">...</div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic"
                       role="button" data-slide="prev"> <span
                            class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a> <a class="right carousel-control" href="#carousel-example-generic"
                            role="button" data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                </div>
            </div>
        </div>
    </div>

    <form role="form" action="<%=path%>/TemptServlet?flag=demo" method="post">
        <%--class是样式--%>
        <div class="form-group">
            <select name="year" id="year" class="form-control">
                <option value="1901">1901</option>
                <option value="1902">1902</option>
            </select>
        </div>

        <div class="form-group">
            <select name="mouth" id="mouth" class="form-control">
                <%for (int i = 1; i <= 12; i++) {%>
                <option value="<%=i%>"><%=i%>
                </option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <select name="day" id="day" class="form-control">
                <%for (int i = 1; i <= 30; i++) {%>
                <option value="<%=i%>"><%=i%>
                </option>
                <%}%>
            </select>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <%--submit属性 把表单内容发送到服务器--%>
                <button type="submit" class="btn btn-success" style="width: 100px;">提交</button>

            </div>
        </div>

        <div class="form-group">
            <select name="year1" id="year1" class="form-control">
                <option value="1901">1901</option>
                <option value="1902">1902</option>
            </select>
        </div>


        <div class="form-group">
            <select name="mouth1" id="mouth1" class="form-control">
                <%for (int i = 1; i <= 12; i++) {%>
                <option value="<%=i%>"><%=i%>
                </option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <select name="day1" id="day1" class="form-control">
                <%for (int i = 1; i <= 30; i++) {%>
                <option value="<%=i%>"><%=i%>
                </option>
                <%}%>
            </select>
        </div>


    </form>

</div>
</body>
</html>
