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
    <title>折线图</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
    <div id="line"></div>
</body>
<script src="<%=path %>/public/plugins/jquery.min.js"></script>
<script src="<%=path %>/public/plugins/highcharts/highcharts.js"></script>
<script src="<%=path %>/public/plugins/highcharts/highcharts-zh_CN.js"></script>
<script>

    $(function () {
        $.get("<%=path %>/statics/emp",
            function (data) {
                empStatics("line", data.series);
            }, "json"
        );
    });

    function empStatics(id, data) {
        new Highcharts.Chart(id, {
            // 图表的标题
            title: {
                text: '不同城市的月平均气温',
                x: -20
            },
            // 子标题
            subtitle: {
                text: '数据来源: WorldClimate.com',
                x: -20
            },
            xAxis: {
                categories: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
            },
            yAxis: {
                title: {
                    text: '月薪 (元)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '元'
            },
            // 图例
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            // 数据
            series: data
        });
    }

</script>
</html>
