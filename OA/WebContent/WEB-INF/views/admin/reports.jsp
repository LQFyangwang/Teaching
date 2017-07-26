<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
var jsonReport = '"report.reportId":"_reportId", "report.reportDay":"_reportDay", "report.des":"_des", "report.status":"_status", ';
var jsonEmp = '"report.emp.empId":"_emp_empId", "report.emp.name":"_emp_name"';
var jsonStr = '{' + jsonReport + jsonEmp + '}';
$(function() {
	setPagination("reportList");
});

function authority(methodName, jsonStr, method) {
	$.get("<%=path %>/auth/authority?methodName=" + methodName,
		function(data) {
			if (data.result.result == "success") {
				if (method == "add") {
					$("#addWin").window("open"); // 打开窗口
				} else if (method == "edit") {
					$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
					$("#editWin").window("open"); // 打开编辑的窗口
				}
			} else if (data.result.result == "fail") {
				$.messager.alert("提示", data.result.message, "error");
			}
		}, "json");
}

function empName(value, row, index) {
	if (value != null) {
	return value.name;
	} else {
		return "";
	}
}

function status(value, row, index) {
	if (value == 1) {
		if (row.emp.empId == "${sessionScope.emp.empId}") {
				return "可用       " + "<a href='javascript:;' onclick=frostStatus('" + row.reportId + "')>冻结</a>";
		} else {
			return "可用";
		}
	} 
	if (row.emp.empId == "${sessionScope.emp.empId}") {
		return "不可用       " + "<a href='javascript:;' onclick=activationStatus('" + row.reportId + "')>激活</a>";
	} else {
		return "不可用 ";
	}
}
	
function getNowFormatDate() {
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
	
function showAddReportWin(value1, value2) {
	$("#addForm").form("clear");
	$("#addEmpId").val(value1);
	$("#addEmpName").textbox("setValue", value2);
	$("#addReportDay").datetimebox('setValue', getNowFormatDate());
	authority("com.ht.action.ReportAction.add", "", "add");
}

function addReport() {
	if ($("#addForm").form("validate")) {
		$.post("<%=path %>/report/add",
			$("#addForm").serialize(),
			function (data) {
				if (data.result.result == "success") {
					$("#addWin").window("close"); // 关闭指定的窗口
					$("#reportList").datagrid("reload"); // 重新加载指定数据网格数据
					$.messager.alert("提示", data.result.message, "info");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "info");
				}
		}, "json"
		);
	} else {
		$.messager.alert("提示", "请输入正确的数据", "info");
	}
}

function showEditReportWin(value1) {
	var row = $('#reportList').datagrid('getSelected');
	if (row) {
		var empIdStr1 = value1;
		var empIdStr2 = row.emp.empId;
		if (empIdStr1 == empIdStr2) {
			var jsonStr1 = jsonStr.replace("_reportId", row.reportId).replace("_emp_empId", row.emp.empId).replace("_reportDay", row.reportDay).replace("_des", row.des).replace("_status", row.status).replace("_emp_name", row.emp.name);
			$("#editForm").form("load", JSON.parse(jsonStr1));
			$("#editWin").window("open");
		} else {
			$.messager.alert("提示", "对不起你没有权力修改其它员工的日志", "error");
		}
	} else {
		$.messager.alert("提示", "请至少选择一行编辑", "info");
	}
}

function editReport() {
	if ($("#editForm").form("validate")) {
		$.post("<%=path %>/report/update",
				$("#editForm").serialize(),
				function (data) {
					if (data.result.result == "success") {
						$("#editWin").window("close"); // 关闭指定的窗口
						$("#reportList").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "info");
					}
			}, "json"
			);
	} else {
		$.messager.alert("提示", "请输入正确的表单数据", "info");
	}
}

function queryByDay() { // 根据时间段查询数据
	var startDay = $("#startDay").textbox('getValue');
	var endDay = $("#endDay").textbox('getValue')
	if (startDay != "" && endDay != "") {
		if (endDay > startDay) {
			$.get("<%=path %>/report/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
					function(data) {
						if (data.result.result == "success") {
							$("#reportList").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "结束时间不能在开始时间之前！", "error");
		}
	} else {
		$.messager.alert("提示", "请选择时间！", "error");
	}
}

function queryByName(value){ // 根据学生姓名查询数据
    if (value != "") {
    	$.get("<%=path %>/report/pagerByName?value=" + value,
				function(data) {
					if (data.result.result == "success") {
						// $("#list").datagrid("reload"); // 更新表格
						$("#reportList").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
    } else {
    	$.messager.alert("提示", "请输入姓名", "error");
    }
}

function frostStatus(id) {
	$.get("<%=path %>/report/frost?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#reportList").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function activationStatus(id) {
	$.get("<%=path %>/report/activation?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#reportList").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}
</script>
</head>
<body style="height:100%;">
	<table id="reportList" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/report/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'reportId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:80" formatter="empName">员工名</th>
				<th data-options="field:'reportDay',width:120" formatter="formatterDate">日志时间</th>
				<th data-options="field:'des',width:80">日志描述</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddReportWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }');" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditReportWin('${sessionScope.emp.empId }');" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
		<div>
			时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:150px" data-options="searcher:queryByName,prompt:'请输入员工姓名'"></input>
		</div>
	</div>
	<div id="addWin" class="easyui-window" title="添加日志" data-options="closed:true" style="width:500px;height:auto;">
		<form id="addForm">
		<input type="hidden" id="addEmpId" name="report.emp.empId" />
			<table>
				<tr>
					<td width="100px;">员工名</td>
					<td><input class="easyui-textbox" type="text" id="addEmpName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>日志时间:</td>
		    		<td><input class="easyui-datetimebox" id="addReportDay" name="report.reportDay" data-options="multiline:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
					<td>日志描述</td>
					<td><input class="easyui-textbox" type="text" id="addReportDes" name="report.des" data-options="multiline:true, 'required':true," style="height:60px" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addReport();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="editWin" class="easyui-window" title="修改日志" data-options="closed:true" style="width:500px;height:auto;">
		<form id="editForm">
			<input type="hidden" id="editReportId" name="report.reportId" />
			<input type="hidden" id="editEmpId" name="report.emp.empId" />
			<table>
				<tr>
					<td width="100px;">员工名</td>
					<td><input class="easyui-textbox" type="text" id="editEmpName" name="report.emp.name" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>日志时间:</td>
		    		<td><input class="easyui-datetimebox" id="editReportDay" name="report.reportDay" data-options="multiline:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
					<td>日志描述</td>
					<td><input class="easyui-textbox" type="text" id="editReportDes" name="report.des" data-options="multiline:true, 'required':true," style="height:60px" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editReport();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>