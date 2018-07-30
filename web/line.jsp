<%@ page import="com.model.tempt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/10
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%--测试用的界面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<%List<tempt> tempts = (ArrayList<tempt>)request.getAttribute("tempts");%>
<div class="hidden" id="data">
    <%--
        <div id="data">
    --%>
    <%for(tempt tempt:tempts) {%>
    <%=tempt.getTemp()%>+<%=tempt.getDat()%>*
    <%}%>
</div>
<div id="container" style="height: 100%"></div>

<script type="text/javascript" src="js/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;

    var str = $("#data").html();
    //alert(typeof str );
    var aData = [];
    alert(1);
    aData = str.split('*');
    var tempt = [];
    var dat = [];
    for(var i=0;i<aData.length-1;i++){
        var s = aData[i].split("+");
        tempt[i] = s[0];
        s[1]=s[1].replace(/-/g,"/");
        dat[i] = new Date(s[1]);//.replace(/-/g, "/");
        alert(dat[i]);
    }
    var i = 0;
    function randomData() {
        now = dat[i];
        value = tempt[i];
        return {
            name: now.toString(),
            value: [
                [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
                Math.round(value)
            ]
        }
        i++;
    }

    var data = [];


        data.push(randomData());


    option = {
        title: {
            text: '动态数据 + 时间坐标轴'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: data
        }]
    };


    setInterval(function () {


        data.shift();
        data.push(randomData());

        myChart.setOption({
            series: [{
                data: data
            }]
        });
    }, 1000);
    ;


    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>
