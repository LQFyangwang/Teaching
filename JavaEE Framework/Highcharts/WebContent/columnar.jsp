<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script src="<%=path %>/js/jquery-1.12.0.min.js"></script>
<script src="<%=path %>/js/highcharts.js"></script>
<script src="<%=path %>/js/my-charts.js"></script>

<script type="text/javascript">

var tempData = {
		chart: {
            type: 'column'
        },
        title: {
            text: '月平均温度'
        },
        subtitle: {
            text: '数据来源: WorldClimate.com'
        },
        xAxis: {
        	categories: ['一月', '二月', '三月', '四月', '五月', '六月',
        	                '七月', '八月', '九月', '十月', '十一月', '十二月'],
            crosshair: true
        },
        yAxis: {
            min: -20.0,
            title: {
            	text: '温度 (°C)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} °C</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: []
    };



$(function () {
	getColumnarChart("columnar", "<%=path %>/temp/temp", tempData);
});
		</script>

</head>
<body>

	<div id="columnar"></div>
	
</body>
</html>