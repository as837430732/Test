<%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/14
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">综合系统</a>
        </div>

        <audio style="display:none" controls="controls" autoplay="autoplay">
            <source src="miusic/space.mp3" type="audio/ogg" />
        </audio>
        <%--播放音乐--%>

        <div>
            <!--向左对齐-->
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <%--<a href="<%=path%>/TemptServlet?flag=homepage">主页</a>--%>
                    <a href="<%=path%>/TemptServlet?flag=homepage" class="btn btn-info btn-lg">
                        <span class="glyphicon glyphicon-home"></span> Home
                    </a></li>
                <li><a href="<%=path%>/TemptServlet?flag=quxian" method="post">曲线</a></li>
                <li><a href="<%=path%>/TemptServlet?flag=yuzhi" method="post">阈值</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search" action="<%=path%>/TemptServlet?flag=answer" method="post">
                <div class="form-group">
                    <select name="xu" id="xu" class="form-control">
                        <option value="top">升序</option>
                        <option value="down">降序</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">选择</button>
            </form>

            <form class="navbar-form navbar-left" role="search" action="<%=path%>/TemptServlet?flag=AI" method="post">
                <div class="form-group">
                    <button type="submit" class="btn btn-default">人工智能</button>
                </div>

            </form>

            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">

            </ul>
            <p class="navbar-text navbar-right">
                <a href="" data-toggle="modal" data-target="#time">日期</a>
            </p>
        </div>
    </div>
</nav>

<!--日期-->
<div class="modal fade" id="time" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">日期</h4>
            </div>
            <form class="form-horizontal" role="form" action="<%=path%>/TemptServlet?flag=date" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="year" class="col-sm-2 control-label">年：</label>
                        <div class="col-sm-10">
                            <select name="year2" id="year" class="form-control">
                                <option value="1901">1901</option>
                                <option value="1902">1902</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mouth" class="col-sm-2 control-label">月：</label>
                        <div class="col-sm-10">
                            <select name="mouth2" id="mouth" class="form-control">
                                <%for (int i = 1; i <= 12; i++) {%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="day" class="col-sm-2 control-label">日：</label>
                        <div class="col-sm-10">
                            <select name="day2" id="day" class="form-control">
                                <%for (int i = 1; i <= 30; i++) {%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">确认</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

