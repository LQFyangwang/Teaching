<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生请假管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	$(function() {
		setPagination("list");
	});
	
	function stuName(value, row, index) {
		return value.name;
	}
	
	function leave(value, row, index) { // 其他身份看到的状态
		if (value == 1) {
			return "通过";
		}
		return "不通过";
		
	}
	
	function pass(value, row, index) { // 最终审核
		if (value == 1) {
			return "通过";
		}
		return "不通过";
		
	}
	
	function firstLevel(value, row, index) { // 任课老师审核
		if (value == 0) {
			return "不通过&nbsp;&nbsp;<a href='javascript:;' onclick=allow('" + row.leaveId + "','" + row.firstLevel + "','" + row.secondLevel + "')>允许</a>";
		}
		return "通过";
	}
	
	function secondLevel(value, row, index) { // 班主任审核
		if (value == 0) {
			return "不通过&nbsp;&nbsp;<a href='javascript:;' onclick=allow('" + row.leaveId + "','" + row.firstLevel + "','" + row.secondLevel + "')>允许</a>";
		}
		return "通过";
	}
	
	function allow(leaveId, firstLevel, secondLevel) { // 允许通过
		$.get("<%=path %>/sl/allow?stuLeaveId=" + leaveId + "&firstLevel=" + firstLevel + "&secondLevel=" + secondLevel,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function searchLeave(value,name){ // 根据学生姓名查询数据
	    if (value != "") {
	    	$.get("<%=path %>/sl/pagerByStuName?stuName=" + value,
					function(data) {
						if (data.result.result == "success") {
							// $("#list").datagrid("reload"); // 更新表格
							$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
	    } else {
	    	$.messager.alert("提示", "请输入学生姓名", "error");
	    }
	}
	
	
	function searchLeaveDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/sl/pagerByLeaveDay?startDay=" + startDay + "&endDay=" + endDay,
					function(data) {
						if (data.result.result == "success") {
							// $("#list").datagrid("reload"); // 更新表格
							$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
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
			collapsible:true,
			url:'<%=path %>/sl/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'leaveId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:80" formatter="stuName">学生姓名</th>
				<th data-options="field:'startTime',width:140" formatter="formatterDate">开始时间</th>
				<th data-options="field:'endTime',width:140" formatter="formatterDate">结束时间</th>
				<th data-options="field:'leaveDay',width:140" formatter="formatterDate">提交时间</th>
				<th data-options="field:'des',width:150">请假详情</th>
				<s:if test="#session.emp.role.name == '任课老师'">
					<th data-options="field:'firstLevel',width:100" formatter="firstLevel">任课老师审核</th>
				</s:if>
				<s:else>
					<th data-options="field:'firstLevel',width:100" formatter="leave">任课老师审核</th>
				</s:else>
				<s:if test="#session.emp.role.name == '班主任'">
					<th data-options="field:'secondLevel',width:100" formatter="secondLevel">班主任审核</th>
				</s:if>
				<s:else>
					<th data-options="field:'secondLevel',width:100" formatter="leave">班主任审核</th>
				</s:else>
				
				<th data-options="field:'pass',width:80" formatter="pass">最终状态</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div>
			时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="searchLeaveDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:searchLeave,prompt:'输入学生姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'stuName',iconCls:'icon-man'">学生姓名</div>
			</div>
		</div>
	</div>
</body>
</html>