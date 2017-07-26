<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生端请假管理</title>
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
	var jsonStr = '{"stuLeave.leaveId":"_leave_id", "stuLeave.stu.name":"_stu_name", "stuLeave.startTime":"_start_time", "stuLeave.endTime":"_end_time", "stuLeave.leaveDay":"_leave_day", "stuLeave.status":"_status", "stuLeave.des":"_des", "stuLeave.firstLeave":"_first_leave", "stuLeave.secondLeave":"_second_leave", "stuLeave.pass":"_pass"}';


	$(function() {
		setPagination("list");
	});
	
	function getNowFormatDate() { // 获取当前系统时间
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
	}
	
	function stuName(value, row, index) {
		return value.name;
	}

	function status(value, row, index) {
		if (value == 1) {
			return "可用";
		}
		return "不可用";
		
	}
	
	function level(value, row, index) {
		if (value == 1) {
			return "通过";
		}
		return "不通过";
		
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
	
	function showAddLeave() {
		$("#addForm").form("clear"); // 清除表单的内容
		$("#addWin").window("open"); // 打开窗口
		$("#addStuName").textbox("setValue", '${sessionScope.stu.name }');
		$("#addLeaveDay").datetimebox('setValue', formatterDate1(new Date()));
	}
	
	function addLeave() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			$.post("<%=path%>/sl/add",
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
	
	function showEditLeave() {
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			var jsonStr1 = jsonStr.replace("_leave_id", row.leaveId).replace("_stu_name", row.stu.name).replace("_start_time", row.startTime).replace("_end_time", row.endTime).replace("_leave_day", row.leaveDay).replace("_status", row.status).replace("_des", row.des).replace("_first_leave", row.firstLeave).replace("_second_leave", row.secondLeave).replace("_pass", row.pass);
			$("#editForm").form("load", JSON.parse(jsonStr1)); // 把JSON对象row的值自动赋值给form表单
			$("#editWin").window("open"); // 打开编辑的窗口
		} else {
			$.messager.alert("提示", "请选择你要编辑的假条", "error");
		}
	}
	
	function editLeave() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/sl/edit",
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
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.leaveId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.leaveId + "')>激活</a>";
	}
	
	function frostStatus(id) { // 冻结
		$.get("<%=path %>/sl/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) { // 激活
		$.get("<%=path %>/sl/activation?id=" + id,
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
			$.get("<%=path %>/sl/pagerByDay1?startDay=" + startDay + "&endDay=" + endDay,
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
			collapsible:true,
			url:'<%=path %>/sl/pagerByStuId',
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
				<th data-options="field:'status',width:80" formatter="status">状态</th>
				<th data-options="field:'firstLevel',width:80" formatter="level">任课老师审核</th>
				<th data-options="field:'secondLevel',width:80" formatter="level">班主任审核</th>
				<th data-options="field:'pass',width:80" formatter="level">最终状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddLeave();" class="easyui-linkbutton" iconCls="icon-add" plain="true">申请假条</a>
			<a href="javascript:;" onclick="showEditLeave();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改假条</a>
		</div>
		<div>
			时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
	<!-- 添加请假的窗口 -->
	<div id="addWin" class="easyui-window" title="申请请假" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">学生姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="stuLeave.stu.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>开始时间:</td>
		    			<td><input class="easyui-datebox" name="stuLeave.startTime" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>结束时间:</td>
		    			<td><input class="easyui-datebox" name="stuLeave.endTime" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>提交时间:</td>
		    			<td><input class="easyui-datetimebox" id="addLeaveDay" name="stuLeave.leaveDay" data-options="editable:false"/></td>
		    		</tr>
		    		<tr>
		    			<td>请假原因:</td>
		    			<td><input class="easyui-validatebox easyui-textbox" name="stuLeave.des" data-options="required: true, multiline:true" style="height:60px"></input></td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addLeave();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeAddWin()">取消</a>
		    </div>
		</div>
	</div>
	
	<!-- 修改请假的窗口 -->
	<div id="editWin" class="easyui-window" title="修改请假" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="stuLeave.leaveId" />
		    	<input type="hidden" name="stuLeave.firstLeave" />
		    	<input type="hidden" name="stuLeave.secondLeave" />
		    	<input type="hidden" name="stuLeave.pass" />
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">学生姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="stuLeave.stu.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>开始时间:</td>
		    			<td><input class="easyui-datebox" name="stuLeave.startTime" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>结束时间:</td>
		    			<td><input class="easyui-datebox" name="stuLeave.endTime" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>提交时间:</td>
		    			<td><input class="easyui-datebox" name="stuLeave.leaveDay" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>请假原因:</td>
		    			<td><input class="easyui-validatebox easyui-textbox" name="stuLeave.des" data-options="required: true, multiline:true" style="height:60px"></input></td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editLeave();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeEditWin()">取消</a>
		    </div>
		</div>
	</div>
</body>
</html>