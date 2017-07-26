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
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '性别比例'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
			name: '性别比例',
			colorByPoint: true,
			data:[]
		}]
    };

$(function () {
	getPieChart("pie", "<%=path %>/temp/pie", tempData);
});
		</script>

</head>
<body>

	<div id="pie"></div>
	
</body>
</html>