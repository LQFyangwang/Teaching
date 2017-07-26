<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看自己的成绩</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	
	function showName(value) {
		return value.name;
	}
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/score/pagerByDay1?startDay=" + startDay + "&endDay=" + endDay,
					function(data) {
						if (data.result.result == "success") {
							$("#list").datagrid("reload"); // 更新表格
							// $("#list").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择时间段", "error");
		}
	}
	
	
</script>
</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			url:'<%=path %>/score/pagerByStuId',
			method:'get',
			collapsible:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'scoreId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:100" formatter="showName">学生姓名</th>
				<th data-options="field:'course',width:100" formatter="showName">课程</th>
				<th data-options="field:'score',width:80">成绩</th>
				<th data-options="field:'testDay',width:120" formatter="formatterDate">考试时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
			考试时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>

</body>
</html>