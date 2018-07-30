<%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/13
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎光临综合系统</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/carousel.css">
    <script type="text/javaScript" src="js/js/jquery-3.1.1.min.js"></script>
    <script type="text/javaScript" src="js/bootstrap.js"></script>
    <style>
        .thumbnail:hover {
            border-color: red;
        }
    </style>
</head>
<body>


<!--导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">综合系统</a>
        </div>
        <div>
            <!--向左对齐-->
            <ul class="nav navbar-nav navbar-left">
                <li><a href="">商城首页</a></li>
                <li><a href="">新品优惠</a></li>
                <li><a href="">入门影视</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        商品分类
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">jmeter</a></li>
                        <li><a href="#">EJB</a></li>
                        <li><a href="#">Jasper Report</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>

            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">

            </ul>
            <p class="navbar-text navbar-right">
                游客您好，欢迎来到大众点评！
                <a href="" data-toggle="modal" data-target="#login">[登陆]</a>
                <a href="" data-toggle="modal" data-target="#register">[注册]</a>
            </p>
        </div>
    </div>
</nav>


<!-- 登陆框 -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">用户登陆</h4>
            </div>
            <form class="form-horizontal" role="form">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="firstname" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="lastname" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">请记住我
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">登陆</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--注册框-->
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">用户登陆</h4>
            </div>
            <form class="form-horizontal" role="form">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="firstname" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="lastname" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">密码确认</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="lastname" placeholder="请输入确认密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline">
                                <input type="radio" name="sex" id="optionsRadios3" value="男" checked>男
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="sex" id="optionsRadios4"  value="女">女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="lastname1" placeholder="请输入年龄" type="number" required="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="lastname2" placeholder="请输入邮箱">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">注册</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

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

<div class="container-fluid">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">大众分类 <a href="" class="btn">查看更多>></a></h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6 col-md-3" >
                    <div class="thumbnail">
                        <a target="_blank" href="">
                            <a href="">
                                <img src="img/cate/1.png" alt="">
                            </a>
                            <div class="caption">
                                <h3 style="text-align: center">美食</h3>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bottom">
    <nav id="bottomNav">
        <div class="col-md-12">
            <ul class="breadcrumb text-center">
                <li><a href="#">店铺信息</a> <span class="divider"></span></li>
                <li><a href="#">商品目录</a> <span class="divider"></span></li>
                <li class="active"><a href="#">联系我们</a></li>
                <div class="text-primary">&copy;&nbsp;neusoft.2016</div>
            </ul>

        </div>
    </nav>
</div>

</body>
</html>
