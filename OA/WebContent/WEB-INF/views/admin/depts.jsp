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
	var jsonStr = '{"deptInfo.depId":"_depId","deptInfo.depName":"_depName","deptInfo.empId":"_empId","deptInfo.empName":"_empName","deptInfo.des":"_des","deptInfo.status":"_status"}';
	$(function() {
		setPagination("deptList");
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
	
	function showAddDeptWin() {
		authority("com.ht.action.DeptAction.addDept", "", "add");
	}
	
	function addDept() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/dept/addDept",
				$("#addForm").serialize(),
				function (data) {
					if (data.result.result == "success") {
						$("#addWin").window("close"); // 关闭指定的窗口
						$("#deptList").datagrid("reload"); // 重新加载指定数据网格数据
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
	
	function showEditDeptWin() {
		var rows = $("#deptList").datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示", "请先选择一个需要修改的商品", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) { // 有选中的行
				var jsonStr1 = jsonStr.replace("_depId", row.depId).replace("_depName", row.depName).replace("_empId", row.empId).replace("_empName", row.empName).replace("_des", row.des).replace("_status", row.status);
				$("#editDeptId").val(row.depId);
				$("#editEmpId").val(row.empId);
				$("#editEmpName").textbox("setValue", row.empName);
				$("#editDeptName").textbox("setValue", row.depName);
				$("#editDes").textbox("setValue", row.des);
				authority("com.ht.action.DeptAction.update", jsonStr1, "edit");
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示", "请先选择一个需要修改的商品", "info");
		}
	}
	
	function editDept() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/dept/update",
					$("#editForm").serialize(),
					function (data) {
						if (data.result.result == "success") {
							$("#editWin").window("close");
							$("#deptList").datagrid("reload");
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
	
	function showEmp() {
		var deptStr = $("#deptList").datagrid("getSelected");
			if (deptStr) {
			$("#empList").datagrid({url:"<%=path %>/emp2/pager?depId=" + deptStr.depId});
			$("#empWin").window("open");
			} else {
				alert("没有选中");
			}
	}
	
	function confirmEmp() {
		var empStr = $("#empList").datagrid("getSelected");
		if (empStr) {
			$("#editEmpName").textbox("setValue", empStr.name);
			$("#editEmpId").val(empStr.empId);
			$("#empWin").window("close");
		} else {
			$.messager.alert("提示", "请选择一名负责人", "error");
		}
	}
	
	function closeEmp() {
		$("#empWin").window("close");
	}
	
	
	function status(value, row, index) {
		if (value == 0) {
			if ('${sessionScope.emp.role.name}' == "总经理") {
				return "不可用       " + "<a href='javascript:;' onclick=activationStatus('" + row.depId + "')>激活</a>";
			} else {
				return "不可用";
			}
		}
		if ('${sessionScope.emp.role.name}' == "总经理") {
			return "可用       " + "<a href='javascript:;' onclick=frostStatus('" + row.depId + "')>冻结</a>";
		} else {
			return "可用";
		}
}
	
	function frostStatus(id) {
		$.get("<%=path %>/dept/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#deptList").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}

	function activationStatus(id) {
		$.get("<%=path %>/dept/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#deptList").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
</script>

</head>
<body style="height:100%;">

<table id="deptList" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/dept/queryPage',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'depId',checkbox:true">编号</th>
				<th data-options="field:'depName',width:100">部门名称</th>
				<th data-options="field:'empName',width:80">负责人</th>
				<th data-options="field:'des',width:80">描述</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddDeptWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditDeptWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
	</div>
	<div id="addWin" class="easyui-window" title="添加部门" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<input type="number" style="display:none;" name="dept.status" value="1" />
			<table>
				<tr>
					<td style="width: 100px;">部门名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="dept.name" data-options="required:true,validType:'length[3,20]'"/></td>
				</tr>
				<tr>
					<td>部门描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="dept.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addDept();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="editWin" class="easyui-window" title="编辑部门" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" id="editDeptId" name="dept.depId" />
			<input type="hidden" id="editEmpId" name="dept.empId" />
			<table>
				<tr>
					<td style="width: 100px;">部门名称</td>
					<td><input class="easyui-validatebox easyui-textbox" id="editDeptName" name="dept.name" data-options="required:true,validType:'length[3,20]'"/></td>
				</tr>
				<tr>
					<td>负责人</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="editEmpName" data-options="required:true,'disabled':true"/>
						<a href="javascript:;" onclick="showEmp();">选择负责人</a>
					</td>
				</tr>
				<tr>
					<td>部门描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" id="editDes" name="dept.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editDept();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="empWin" class="easyui-window" title="选择负责人" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:auto;">
		<table id="empList" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb2'" style="height:auto;">
			<thead>
				<tr>
					<th data-options="field:'empId',checkbox:true">编号</th>
					<th data-options="field:'name',width:100">员工名</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="confirmEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeEmp();">取消</a>
		</div>
	</div>
</body>
</html>