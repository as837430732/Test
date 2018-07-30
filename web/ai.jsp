<%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/5/1
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人工智能界面</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1000px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/TemptServlet?flag=AI",
        dataType:"json",
        async: false,

        success: function (data) {
            /*alert(data.oneX);
             alert(data.oneY);*/

            var onePoint = [];

            var one=[];
            var two=[];
            for(var v in data.Point) {
                var point2 = [data.Point[v].x , data.Point[v].y];
                onePoint.push(point2);
            }

            var point3=[data.oneX,data.oneY];
            var point1=[data.twoX,data.twoY];

            alert(point3);
            alert(point1);
            // 填入数据
            var date = [];
            var maxtempture = [];
            var mintempture = [];

            for(var v in data) {
                // var d = data[v].year + "-" + data[v].mouth + "-" + data[v].day;
                date.push(data[v].data);
                maxtempture.push(data[v].maxtem);
                mintempture.push(data[v].mintem);
            }


            myChart.setOption({
                        title : {
                            text: '天气的分布情况',
                            subtext: '抽样调查来自: 1901'
                        },

                        tooltip : {
                            // trigger: 'axis',
                            showDelay : 0,

                        },

                        brush: {
                        },
                        legend: {
                            data: ['第一类'],
                            left: 'center'
                        },
                        xAxis : [
                            {
                                type : 'value',
                                scale:true,
                                axisLabel : {
                                    formatter: '{value}℉'
                                },
                                splitLine: {
                                    show: false
                                }
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                scale:true,
                                axisLabel : {
                                    formatter: '{value}℉ '
                                },
                                splitLine: {
                                    show: false
                                }
                            }
                        ],
                        series : [

                            {
                                name:'第一类',
                                type:'scatter',
                                data: onePoint,
                                markLine : {
                                    animation: false,
                                    label: {
                                        normal: {
                                            formatter: 'y = 0.5 * x + 3',
                                            textStyle: {
                                                align: 'right'
                                            }
                                        }
                                    },
                                    lineStyle: {
                                        normal: {
                                            type: 'solid'
                                        }
                                    },
                                    tooltip: {
                                        formatter: 'y = 0.5 * x + 3'
                                    },
                                    data: [[{
                                        coord: point3,
                                        symbol: 'none'
                                    }, {
                                        coord:  point1,
                                        symbol: 'none'
                                    }]]
                                }

                            },


                        ]

                    }
            )
        }
    })
    </script>
</body>
</html>




<%--option = {--%>
