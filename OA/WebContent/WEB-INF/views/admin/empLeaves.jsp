<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
var levelStr1 = "firstLevel";
var levelStr2 = "secondLevel";
var statusStr = "status";
var jsonStr = '"empLeave.status":"_status"';
function showLeaveList() {
	$("#leaveList").datagrid({url:"<%=path%>/empLeave/secondPager"});
	$("#leaveWin").window("open");
	
}

function showLeaveList2() {
	$("#leaveList").datagrid({url:"<%=path%>/empLeave/firstPager"});
	$("#leaveWin").window("open");
}

function showAddWin() {
	$("#addForm").form("clear");
	$("#addEmpId").val('${sessionScope.emp.empId}');
	$("#addEmpName").textbox("setValue", '${sessionScope.emp.name}');
	$("#addWin").window("open");
	
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

function addLeave() {
	var startTime = $("#addStartTime").datetimebox("getValue");
	var endTime = $("#addEndTime").datetimebox("getValue");
	var currTime = getNowFormatDate();
	if (startTime >= currTime && endTime > startTime) {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/empLeave/add",
				$("#addForm").serialize(),
				function (data) {
					if (data.result.result == "success") {
						$("#addWin").window("close"); // 关闭指定的窗口
						$("#MyList").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "info");
					}
			}, "json"
			);
		} else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}
	} else {
		$.messager.alert("提示", "开始时间(结束时间)不能在当前时间(开始时间)之前！", "info");
	}
}

function empName(value, row, index) {
	return value.name;
}

function status(value, row, index) {
	if (value == 1) {
		return "可用     " + "<a href='javascript:;' onclick=updateStatus2('" + row.leaveId + "')>冻结</a>";
	}
	return "不可用     " + "<a href='javascript:;' onclick=updateStatus1('" + row.leaveId + "')>激活</a>";
}

function pass(value, row, index) {
	if (value == 1) {
		return "通过";
	} else if(value == 0) {
		return "未通过";
	} else if(value == 2) {
		return "待审核";
	}
}

function firstLevel(value, row, index) {
	if (value == 1) {
		return "通过";
	} else if (value == 0) {
		return "不通过";
	} else if (value == 2) {
		return "待审核     " + "<a href='javascript:;' onclick=updateLevel2('" + row.leaveId + "')>回绝</a>  " + "<a href='javascript:;' onclick=updateLevel1('" + row.leaveId + "')>接受</a>"
	}
}

function secondLevel(value, row, index) {
	if (value == 1) {
		return "通过";
	} else if (value == 0) {
		return "不通过";
	} else if (value == 2) {
		return "待审核     " + "<a href='javascript:;' onclick=updateLevel4('" + row.leaveId + "')>回绝</a>  " + "<a href='javascript:;' onclick=updateLevel3('" + row.leaveId + "')>接受</a>";
	}
}

function updateLevel1(id) {
	var row = $("#leaveList").datagrid("getSelected");
	$.get("<%=path %>/empLeave/acceptFirst?id=" + id + "&leaveCount=" + row.leaveCount,
			function(data) {
				if (data.result.result == "success") {
					$("#leaveList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateLevel2(id) {
	var row = $("#leaveList").datagrid("getSelected");
	$.get("<%=path %>/empLeave/refuseFirst?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#leaveList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateLevel3(id) {
	$.get("<%=path %>/empLeave/acceptSecond?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#leaveList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateLevel4(id) {
	$.get("<%=path %>/empLeave/refuseSecond?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#leaveList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateStatus1(id) {
	$.get("<%=path %>/empLeave/activationStatus?value=0&id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#MyList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateStatus2(id) {
	$.get("<%=path %>/empLeave/freezeStatus?value=1&id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#MyList").datagrid("reload");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function showMyLeave() {
	$("#MyList").datagrid({url:"<%=path%>/empLeave/byIdPager?empId=" + '${sessionScope.emp.empId}'});
	$("#MyLeaveWin").window("open");
}

function showEditWin() {
	var row = $("#MyList").datagrid("getSelected"); // 获取首个选中的数据
	if (row) {
	if (row.pass == 2) {
		$("#editLeaveId").val(row.leaveId);
		$("#editEmpId").val('${sessionScope.emp.empId}');
		$("#editFirst").val(row.firstLevel);
		$("#editSecond").val(row.secondLevel);
		$("#editEmpName").textbox("setValue", '${sessionScope.emp.name}');
		$("#editStartTime").datetimebox("setValue", row.startTime);
		$("#editEndTime").datetimebox("setValue", row.endTime);
		$("#editDes").textbox("setValue", row.des);
		$("#editWin").window("open");
	} else {
		$.messager.alert("提示", "无法编辑未通过(通过)审核的申请！", "info");
	}
		} else {
			$.messager.alert("提示", "请选择一个申请编辑", "info");
		}
}

function editLeave() {
	var startTime = $("#editStartTime").datetimebox("getValue");
	var endTime = $("#editEndTime").datetimebox("getValue");
	var currTime = getNowFormatDate();
	if (startTime >= currTime && endTime > startTime) {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/empLeave/update",
				$("#editForm").serialize(),
				function(data) {
					if (data.result.result == "success") {
						$("#editWin").window("close"); // 关闭指定的窗口
						$("#MyList").datagrid("reload"); // 更新表格
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
	} else {
		$.messager.alert("提示", "开始时间(结束时间)不能在当前时间(开始时间)之前！", "info");
	}
}

function queryByDay() { // 根据时间段查询数据
	var startDay = $("#startDay").textbox('getValue');
	var endDay = $("#endDay").textbox('getValue')
	if (startDay != "" && endDay != "") {
		if (endDay > startDay) {
		$.get("<%=path %>/empLeave/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
				function(data) {
					if (data.result.result == "success") {
						$("#leaveList").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		} else {
			$.messager.alert("提示", "结束时间不能在开始时间之前！", "error");
		}
	} else {
		$.messager.alert("提示", "请选择时间段", "error");
	}
}

function queryByName(value){ // 根据学生姓名查询数据
    if (value != "") {
    	$.get("<%=path %>/empLeave/pagerByName?value=" + value,
				function(data) {
					if (data.result.result == "success") {
						$("#leaveList").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
    } else {
    	$.messager.alert("提示", "请输入姓名", "error");
    }
}

</script>
</head>
<body style="height:100%;">
	<div id="myTb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
		<c:if test="${sessionScope.emp.role.name == '总经理'}">
			<a href="javascript:;" onclick="showLeaveList();" class="easyui-linkbutton" iconCls="icon-edit">请假申请管理(老板)</a>
		</c:if>
		<c:if test="${sessionScope.emp.role.name == '校长' || sessionScope.emp.role.name == '后勤主任' || sessionScope.emp.role.name == '财务主任' || sessionScope.emp.role.name == '招生主任' || sessionScope.emp.role.name == '教务主任'}">
			<a href="javascript:;" onclick="showLeaveList2();" class="easyui-linkbutton" iconCls="icon-edit">请假申请管理(主任)</a>
		</c:if>
		<c:if test="${sessionScope.emp.role.name != '总经理' }">
			<a href="javascript:;" onclick="showMyLeave();" class="easyui-linkbutton" iconCls="icon-edit">我的申请</a>
		</c:if>
		</div>
	</div>
	<div id="addWin" class="easyui-window" title="请假申请" data-options="closed:true" style="width:500px;height:auto;">
		<form id="addForm">
		<input type="hidden" id="addEmpId" name="empLeave.emp.empId" />
			<table>
				<tr>
					<td width="100px;">员工名</td>
					<td><input class="easyui-textbox" type="text" id="addEmpName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>开始时间</td>
		    		<td><input class="easyui-datetimebox" id="addStartTime" name="empLeave.startTime" data-options="required:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
		    		<td>结束时间</td>
		    		<td><input class="easyui-datetimebox" id="addEndTime" name="empLeave.endTime" data-options="required:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
					<td>请假描述</td>
					<td><input class="easyui-textbox" type="text" name="empLeave.des" data-options="multiline:true, 'required':true," style="height:60px" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addLeave();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="leaveWin" class="easyui-window" title="请假管理" data-options="iconCls:'icon-search', closed:true" style="width:auto;height:100%;">
		<table id="leaveList" class="easyui-datagrid"
				data-options="toolbar:'#tb1',
				singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20" style="height:100%;">
			<thead>
				<tr>
					<th data-options="field:'empLeave.leaveId',checkbox:true">编号</th>
					<th data-options="field:'emp',width:80" formatter="empName">员工名</th>
					<th data-options="field:'startTime',width:120" formatter="formatterDate">开始时间</th>
					<th data-options="field:'endTime',width:120" formatter="formatterDate">结束时间</th>
					<th data-options="field:'leaveCount',width:60">请假天数</th>
					<th data-options="field:'leaveDay',width:120" formatter="formatterDate">提交时间</th>
					<th data-options="field:'des',width:120">请假描述</th>
					<c:if test="${sessionScope.emp.role.name == '校长' || sessionScope.emp.role.name == '后勤主任' || sessionScope.emp.role.name == '财务主任' || sessionScope.emp.role.name == '招生主任' || sessionScope.emp.role.name == '教务主任'}">
					<th data-options="field:'firstLevel',width:120" formatter="firstLevel">直属审核</th>
					</c:if>
					<c:if test="${sessionScope.emp.role.name == '总经理'}">
					<th data-options="field:'secondLevel',width:120" formatter="secondLevel">老板审核</th>
					</c:if>
					<th data-options="field:'pass',width:120" formatter="pass">最终审核</th>
				</tr>
			</thead>
		</table>
		<div id="tb1" style="padding:5px;height:auto">
			<div>
				时间段：
				<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
				至
				<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
				<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
				<input id="ss" class="easyui-searchbox" style="width:150px" data-options="searcher:queryByName,prompt:'请输入员工姓名'"></input>
			</div>
		</div>
	</div>
	<div id="MyLeaveWin" class="easyui-window" title="我的申请" data-options="iconCls:'icon-search', closed:true" style="width:auto;height:100%;">
		<table id="MyList" class="easyui-datagrid"
				data-options="toolbar:'#tb2',
				singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20" style="height:100%;">
			<thead>
				<tr>
					<th data-options="field:'empLeave.leaveId',checkbox:true">编号</th>
					<th data-options="field:'emp',width:80" formatter="empName">员工名</th>
					<th data-options="field:'startTime',width:120">开始时间</th>
					<th data-options="field:'endTime',width:120">结束时间</th>
					<th data-options="field:'leaveCount',width:60">请假天数</th>
					<th data-options="field:'leaveDay',width:120">提交时间</th>
					<th data-options="field:'des',width:120">请假描述</th>
					<th data-options="field:'status',width:120" formatter="status">状态</th>
					<th data-options="field:'pass',width:120" formatter="pass">最终审核</th>
				</tr>
			</thead>
		</table>
		<div id="tb2" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:;" onclick="showAddWin();" class="easyui-linkbutton" iconCls="icon-add">我要申请 </a>
				<a href="javascript:;" onclick="showEditWin();" class="easyui-linkbutton" iconCls="icon-edit">修改 </a>
			</div>
		</div>
	</div>
	<div id="editWin" class="easyui-window" title="请假申请编辑" data-options="closed:true" style="width:500px;height:auto;">
		<form id="editForm">
		<input type="hidden" id="editLeaveId" name="empLeave.leaveId" />
		<input type="hidden" id="editEmpId" name="empLeave.emp.empId" />
		<input type="hidden" id="editFirst" name="empLeave.firstLevel" />
		<input type="hidden" id="editSecond" name="empLeave.secondLevel" />
			<table>
				<tr>
					<td style="width: 100px;">员工名</td>
					<td><input class="easyui-textbox" type="text" id="editEmpName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>开始时间</td>
		    		<td><input class="easyui-datetimebox" id="editStartTime" name="empLeave.startTime" data-options="required:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
		    		<td>结束时间</td>
		    		<td><input class="easyui-datetimebox" id="editEndTime" name="empLeave.endTime" data-options="required:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
					<td>请假描述</td>
					<td><input class="easyui-textbox" id="editDes" type="text" name="empLeave.des" data-options="multiline:true, 'required':true," style="height:60px" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editLeave();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>