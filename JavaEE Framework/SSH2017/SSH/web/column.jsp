<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 2017/8/15
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>条形图</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
    <div id="column"></div>
</body>
<script src="<%=path %>/public/plugins/jquery.min.js"></script>
<script src="<%=path %>/public/plugins/highcharts/highcharts.js"></script>
<script src="<%=path %>/public/plugins/highcharts/highcharts-zh_CN.js"></script>
<script>

    $(function () {
        $.get("<%=path %>/statics/emp",
            function (data) {
                empStatics("column", data.series);
            }, "json"
        );
    });

    function empStatics(id, data) {
        $('#' + id).highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '员工月薪统计图'
            },
            xAxis: {
                categories: [
                    '一月',
                    '二月',
                    '三月',
                    '四月',
                    '五月',
                    '六月',
                    '七月',
                    '八月',
                    '九月',
                    '十月',
                    '十一月',
                    '十二月'
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '月薪 (元)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
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
            series: data
        });
    }

</script>
</html>
