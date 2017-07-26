<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>打卡时间管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script>
	
	var jsonStr = '{"empCheckingInfo.checkingInfoId":"_checkingInfoId","empCheckingInfo.name":"_name","empCheckingInfo.checkingTime":"_checkingTime"}';
	$(function() {
		setPagination("list");
	});
	
	function showaddEmpCheckingInfoWin() {
		$("#addForm").form("clear"); // 清除表单里的所有数据
		$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	}
	
	function addCheckingInfo() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/empcheckinginfo/add",
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
	
	function showEditEmpCheckingInfoWin() {
		//var row = $('#list').datagrid("getSelected");
		var rows = $('#list').datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_checkingInfoId", row.checkingInfoId).replace("_name", row.name).replace("_checkingTime", row.checkingTime);
				$('#editForm').form("load", JSON.parse(jsonStr1));
				$('#editWin').window('open');
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		}
	}
	
	function EditCheckingInfo(){
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/empcheckinginfo/update",
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
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	

</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/empcheckinginfo/query',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'checkingInfoId',checkbox:true">打卡编号</th>
				<th data-options="field:'name',width:100">打卡名称</th>
				<th data-options="field:'checkingTime',width:100">打卡时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showaddEmpCheckingInfoWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditEmpCheckingInfoWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">打卡名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="empCheckingInfo.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>打卡时间</td>
					<td><input class="easyui-TimeSpinner" name="empCheckingInfo.checkingTime"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addCheckingInfo();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="empCheckingInfo.checkingInfoId" />
			<table>
				<tr>
					<td width="100px;">打卡名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="empCheckingInfo.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>打卡时间</td>
					<td><input class="easyui-TimeSpinner" name="empCheckingInfo.checkingTime"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="EditCheckingInfo();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>