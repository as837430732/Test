<%--
  Created by IntelliJ IDEA.
  User: 高浩然
  Date: 2017/3/13
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1200px;height:600px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    // 显示标题，图例和空的坐标轴
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/TemptServlet?flag=test",
        dataType:"json",
        async: false,
        error:function () {
            alert(1111);
        },
        success: function (data) {
            alert(data);
            var date = [];
            var tempture = [];
            //alert(data);
            for (var v in data) {
                date.push(data[v].dat)
                tempture.push(data[v].temp);

            }
            //alert(date);
            myChart.setOption({
                title: {
                    text: '时间——温度曲线图'
                },
                tooltip: {},
                legend: {
                    data:['温度']
                },
                xAxis: {
                    data: []
                },
                yAxis: {},
                series: [{
                    name: '温度',
                    type: 'line',
                    data: []
                }]
            });
        }
    })

    //动态bar 图
    //app.title = '柱状图框选';

//    var xAxisData = [];
//    var data1 = [];
//    var data2 = [];
//    var data3 = [];
//
//    var itemStyle = {
//        normal: {
//        },
//        emphasis: {
//            barBorderWidth: 1,
//            shadowBlur: 10,
//            shadowOffsetX: 0,
//            shadowOffsetY: 0,
//            shadowColor: 'rgba(0,0,0,0.5)'
//        }
//    };
//
//    option = {
//        backgroundColor: '#eee',
//        legend: {
//            data: ['bar', 'bar2', 'bar3', 'bar4'],
//            align: 'left',
//            left: 10
//        },
//        brush: {
//            toolbox: ['rect', 'polygon', 'lineX', 'lineY', 'keep', 'clear'],
//            xAxisIndex: 0
//        },
//        toolbox: {
//            feature: {
//                magicType: {
//                    type: ['stack', 'tiled']
//                },
//                dataView: {}
//            }
//        },
//        tooltip: {},
//        xAxis: {
//            data: xAxisData,
//            name: 'X Axis',
//            silent: false,
//            axisLine: {onZero: true},
//            splitLine: {show: false},
//            splitArea: {show: false}
//        },
//        yAxis: {
//            inverse: true,
//            splitArea: {show: false}
//        },
//        grid: {
//            left: 100
//        },
//        visualMap: {
//            type: 'continuous',
//            dimension: 1,
//            text: ['High', 'Low'],
//            inverse: true,
//            itemHeight: 200,
//            calculable: true,
//            min: -2,
//            max: 6,
//            top: 60,
//            left: 10,
//            inRange: {
//                colorLightness: [0.4, 0.8]
//            },
//            outOfRange: {
//                color: '#bbb'
//            },
//            controller: {
//                inRange: {
//                    color: '#2f4554'
//                }
//            }
//        }
//
//    };
//
//    myChart.on('brushSelected', renderBrushed);
//
//    function renderBrushed(params) {
//        var brushed = [];
//        var brushComponent = params.batch[0];
//
//        for (var sIdx = 0; sIdx < brushComponent.selected.length; sIdx++) {
//            var rawIndices = brushComponent.selected[sIdx].dataIndex;
//            brushed.push('[Series ' + sIdx + '] ' + rawIndices.join(', '));
//        }
//
//        myChart.setOption({
//            title: {
//                backgroundColor: '#333',
//                text: 'SELECTED DATA INDICES: \n' + brushed.join('\n'),
//                bottom: 0,
//                right: 0,
//                width: 100,
//                textStyle: {
//                    fontSize: 12,
//                    color: '#fff'
//                }
//            }
//        });
//    }
    //定时器
   // setInterval(function () {

   // }, 500);


</script>

</body>
</html>
