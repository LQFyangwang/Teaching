<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>总结管理</title>
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon-hamburg.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/themes/jeasyui.icons.hamburg.js"></script>

<script>

	$(function() {
		setPagination("list");
	});
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/summary/pagerByDay1?startDay=" + startDay + "&endDay=" + endDay,
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
	
	function term(value) {
		if (value == 1) {
			return "第一个学期";
		} else if (value == 2) {
			return "第二个学期";
		} else if (value == 3) {
			return "第三个学期";
		} else if (value == 4) {
			return "第四个学期";
		}
		return "暂无";
	}
	
	function name(value) {
		return value.name;
	}
	
</script>
</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/summary/pagerByStuId',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'summaryId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:80" formatter="name">老师姓名</th>
				<th data-options="field:'stu',width:80" formatter="name">学生姓名</th>
				<th data-options="field:'summaryDay',width:140" formatter="formatterDate">总结时间</th>
				<th data-options="field:'des',width:200">总结详情</th>
				<th data-options="field:'term',width:80" formatter="term">学期</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div>
			总结时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
</body>
</html>