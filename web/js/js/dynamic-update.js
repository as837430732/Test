$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        var chart;
        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
                        i=19;
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {

                            var x = date, // current time
                                y = 1;
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: ' 统计表 '
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Temp'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: '详细信息',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
                    j=0;
                    for (i =-19; i <= 0; i++) {
                        data.push({
                            x: date,
                            y: 1
                        });
                    }
                    return data;
                })()
            }]
        });
    });

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

     var s = "1901-03-09 13:00:00.0";
     s = s.replace(/-/g,"/");
     var date = new Date(s );
});