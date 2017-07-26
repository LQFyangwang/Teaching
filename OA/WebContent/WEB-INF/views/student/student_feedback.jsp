<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生端查询自己的反馈信息</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>

	//用于回显
	var jsonStr = '{"stuFeedback.feedbackId":"_feedback_id", "stuFeedback.stu.name":"_stu_name", "stuFeedback.feedbackDay":"_feedback_day", "stuFeedback.des":"_des", "stuFeedback.status":"_status"}';

	$(function() {
		setPagination("list");
	});
	
	function stuName(value, row, index) {
		return value.name;
	}

	function status(value, row, index) {
		if (value == 1) {
			return "可用";
		}
		return "不可用";
		
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.feedbackId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.feedbackId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/sf/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/sf/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/sf/pagerByDay1?startDay=" + startDay + "&endDay=" + endDay,
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
	
	formatterDate1 = function (date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		var hor = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
	};
	
	function showAddFeedback() {
		$("#addForm").form("clear"); // 清除表单的内容
		$("#addWin").window("open"); // 打开窗口
		$("#addStuName").textbox("setValue", '${sessionScope.stu.name }');
		$("#addFeedbackDay").datetimebox('setValue', formatterDate1(new Date()));
	}
	
	function addFeedback() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			$.post("<%=path%>/sf/add",
					$("#addForm").serialize(), // Form表单序列化
					function(data) {
						if (data.result.result == "success") { //表示添加成功
							$("#addWin").window("close"); // 关闭指定窗口
							$("#list").datagrid("reload"); // 重新加载网格数据
							$.messager.alert("提示", data.result.message, "info");
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
					}, "json");
			
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
	}
	
	function showEditFeedback() {
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			var jsonStr1 = jsonStr.replace("_feedback_id", row.feedbackId).replace("_stu_name", row.stu.name).replace("_feedback_day", row.feedbackDay).replace("_des", row.des).replace("_status", row.status);
			$("#editForm").form("load", JSON.parse(jsonStr1)); // 把JSON对象row的值自动赋值给form表单
			$("#editWin").window("open"); // 打开编辑的窗口
		} else {
			$.messager.alert("提示", "请选择你要编辑的反馈信息", "error");
		}
	}
	
	function editFeedback() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/sf/edit",
				$("#editForm").serialize(),
				function(data) {
					if (data.result.result == "success") {
						$("#editWin").window("close"); // 关闭指定的窗口
						$("#list").datagrid("reload"); // 更新表格
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
		
	}
	
	function closeAddWin() {
		$("#addWin").window("close"); // 打开窗口
	}
	
	function closeEditWin() {
		$("#editWin").window("close"); // 打开窗口
	}
	
	
	
</script>
</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/sf/pagerByStuId',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'feedbackId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:80" formatter="stuName">学生姓名</th>
				<th data-options="field:'feedbackDay',width:140" formatter="formatterDate">反馈时间</th>
				<th data-options="field:'des',width:150">反馈详情</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddFeedback();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditFeedback();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			反馈时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
	<!-- 添加反馈的窗口 -->
	<div id="addWin" class="easyui-window" title="申请请假" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">学生姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="stuFeedback.stu.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>反馈时间:</td>
		    			<td><input class="easyui-datetimebox" id="addFeedbackDay" name="stuFeedback.feedbackDay" data-options="editable:false"/></td>
		    		</tr>
		    		<tr>
		    			<td>反馈描述:</td>
		    			<td><input class="easyui-validatebox easyui-textbox" name="stuFeedback.des" data-options="required: true, multiline:true" style="height:60px"></input></td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addFeedback();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeAddWin()">取消</a>
		    </div>
		</div>
	</div>
	
	<!-- 修改反馈的窗口 -->
	<div id="editWin" class="easyui-window" title="修改请假" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="stuFeedback.feedbackId" />
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">学生姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="stuFeedback.stu.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>反馈时间:</td>
		    			<td><input class="easyui-datebox" name="stuFeedback.feedbackDay" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>反馈描述:</td>
		    			<td><input class="easyui-validatebox easyui-textbox" name="stuFeedback.des" data-options="required: true, multiline:true" style="height:60px"></input></td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editFeedback();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeEditWin()">取消</a>
		    </div>
		</div>
	</div>
</body>
</html>