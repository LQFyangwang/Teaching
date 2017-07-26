<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script>
	
	var jsonStr = '{"role.roleId":"_roleId","role.name":"_name","role.des":"_des","role.status":"_status","role.emp.empId":"_empId","role.emp.name":"_name"}';
	
	$(function() {
		setPagination("list");
	});
	
	function showCheckDep(roleId) {
			$("#empWin").window("open"); // 打开所有员工窗口
			$("#selectEmps").datagrid({url:'<%=path %>/emp/empIdByPager?id=' + roleId});
	}

	function showaddRoleWin() {//添加窗口
		$("#addForm").form("clear"); // 清除表单里的所有数据
		$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
		$("#status").combobox('setValue', 1);//状态默认可用
	}
	
	function addRole() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/role/add",
				$("#addForm").serialize(),
				function (data) {
					if (data.result.result == "success") {
						$("#addWin").window("close"); // 关闭指定的窗口
						$("#list").datagrid("reload"); // 重新加载指定数据网格数据
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
	
	function showEditRoleWin() {
		//var row = $('#list').datagrid("getSelected");
		var rows = $('#list').datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示","请选择一个需要编辑的角色", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_roleId", row.roleId)
				.replace("_name", row.name)
				.replace("_des", row.des)
				.replace("_status", row.status);
				$('#editForm').form("load", JSON.parse(jsonStr1));
				$('#editWin').window('open');
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示","请选择一个需要编辑的角色", "info");
		}
	}
	
	function EditRole(){
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/role/update",
					$("#editForm").serialize(),
					function (data) {
						if (data.result.result == "success") {
							$("#editWin").window("close"); // 关闭指定的窗口
							$("#list").datagrid("reload"); // 重新加载指定数据网格数据
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
	
	function formatterStatus(value, row, index) {
		if (row.status == 1) {
			return "可用"
		} else if (row.status == 0) {
			return "不可用";
		}
	}
	
	function operation(value, row, index) {
		if (row.status == 1){
			return "<a href='javascript:;' onclick=frostStatus('" + row.roleId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.roleId + "')>激活</a>";
	}
	
	
	function details(value, row, index){
		if (row.roleId == "4028da265981b09d015981d91d6f0001") {
			return "";
		}
		return "<a href='javascript:;' onclick=showCheckDep('" + row.roleId + "')>详情</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/role/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/role/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function queryByroleName(name,value){
		$.get("<%=path %>/role/fuzzySearch?name=" + name + "&value=" + value, 
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
		}, "json")
	}
	
	function fuzzySearch(value,name){
		if (value != ""){
			if (name == "roleName"){
				queryByroleName(name, value);
			}
		} else {
			$.messager.alert("提示", "请输入名称", "error");
		}
	}
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	
	function depName(value) {
		return value.name;
	}
	
	function RoleName(value){
		return value.name;
	}
	
	function shutdownWin(){
		$("#empWin").window("close");//关闭所有员工窗口
	}
	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/role/query',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'roleId',checkbox:true">角色编号</th>
				<th data-options="field:'name',width:80">角色名称</th>
				<th data-options="field:'des',width:200">角色描述</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">角色状态</th>
				<th data-options="field:'details',width:80" formatter="details">所有员工</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showaddRoleWin();" class="easyui-linkbutton" iconCls="icon-add">添加角色</a>
			<a href="javascript:;" onclick="showEditRoleWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑角色</a>
		</div>
		<div>
		<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:fuzzySearch,prompt:'请输角色名称',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'roleName',iconCls:'icon-man'">角色名称</div>
			</div>
	</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加角色" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">角色名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="role.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="role.des" style="height:100px;"/></td>
				</tr>
				<!-- <tr>
					<td>角色状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="role.status" data-options="required:true,
									data:[
										{'id':1,
										'text':'可用',
										'selected':true},
										{'id':0,
										'text':'不可用'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									'disabled':true
									" />
					</td>
				</tr> -->
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addRole();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改角色" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="role.roleId" />
			<input type="hidden" name="role.status" />
			<table>
				<tr>
					<td width="100px;">角色名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="role.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="role.des" style="height:100px;"/></td>
				</tr>
				<!-- <tr>
					<td>角色状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="role.status" data-options="required:true,'editable':false,disabled:true,
									data:[
										{'id':1,
										'text':'可用'},
										{'id':0,
										'text':'不可用'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									" />
					</td>
				</tr> -->
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="EditRole();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 所有员工的窗口 -->
	<div id="empWin" class="easyui-window" title="所有员工" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<table id="selectEmps" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:85%;">
			<thead>
				<tr>
				<th data-options="field:'empId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100" >姓名</th>
				<th data-options="field:'role',width:100" formatter="RoleName">角色名称</th>
				<th data-options="field:'dept',width:80" formatter="depName">部门</th>
				<th data-options="field:'status',width:100" formatter="formatterStatus">状态</th>
			</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="shutdownWin()">关闭</a>
		   </div>
	</div>
	
</body>
</html>