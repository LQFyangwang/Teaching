<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告类型管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/datagrid-detailview.js"></script>
<script>
	
	var jsonStr = '{"noticeType.noticeTypeId":"_noticeTypeId","noticeType.name":"_name","noticeType.des":"_des","noticeType.status":"_status"}';
	
	$(function() {
		setPagination("list");
	});
	
	function showaddNoticeTypeWin() {
		$("#addForm").form("clear"); // 清除表单里的所有数据
		$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
		$("#status").combobox('setValue', 1);
	}
	
	function addNoticeType() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/noticetype/add",
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
	
	function showEditNoticeTypeWin() {
		//var row = $('#list').datagrid("getSelected");
		var rows = $('#list').datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_noticeTypeId", row.noticeTypeId).replace("_name", row.name).replace("_des", row.des).replace("_status", row.status);
				$('#editForm').form("load", JSON.parse(jsonStr1));
				$('#editWin').window('open');
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		}
	}
	
	function EditNoticeType(){
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/noticetype/update",
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
	
	function removeActivity() {
		var row = $("#list").datagrid("getSelected");
			if (row) {
				$.messager.confirm("提示", "确定冻结吗？", function (r) {
					if (r) { // 点击了确定按键
						$.get("<%=path %>/product/del?id=" + row.id, function (data) {
							$("#list").datagrid("reload");
						}, "json");
					}
				})
		} else {
			$.messager.alert("提示", "请先选择需要冻结的公告", "info");
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
		if(row.status == 1){
			return "<a href='javascript:;' onclick=frostStatus('" + row.noticeTypeId + "')>冻结</a>"
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.noticeTypeId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/noticetype/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/noticetype/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	
	function queryBynoticeTypeName(name,value){
		$.get("<%=path %>/noticetype/fuzzySearch?name=" + name + "&value=" + value, 
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
			if (name == "noticeTypeName"){
				queryBynoticeTypeName(name, value);
			}
		} else {
			$.messager.alert("提示", "请输入名称", "error");
		}
	}
	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/noticetype/querypager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'noticeTypeId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">公告名称</th>
				<th data-options="field:'des',width:200">公告描述</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">公告状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showaddNoticeTypeWin();" class="easyui-linkbutton" iconCls="icon-add">添加系统公告</a>
			<a href="javascript:;" onclick="showEditNoticeTypeWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑系统公告</a>
		</div>
	<div>
		<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:fuzzySearch,prompt:'请输入公告名称',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'noticeTypeName',iconCls:'icon-man'">公告名称</div>
			</div>
	</div>
</div>	
	
	
	<div id="addWin" class="easyui-window" title="添加系统公告" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">公告名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="noticeType.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>公告描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="noticeType.des" style="height:100px;"/></td>
				</tr>
				<!-- <tr>
					<td>公告状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="noticeType.status" data-options="required:true,
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
						<a href="javascript:;" onclick="addNoticeType();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="编辑系统公告" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="noticeType.noticeTypeId" />
			<input type="hidden" name="noticeType.status" />
			<table>
				<tr>
					<td width="100px;">公告名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="noticeType.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>公告描述</td>
					<td><input class="easyui-textbox"data-options="multiline:true" name="noticeType.des" style="height:100px;"/></td>
				</tr>
				<!-- <tr>
					<td>公告状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="noticeType.status" data-options="required:true,'editable':false,disabled:true,
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
				</tr> -->
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="EditNoticeType();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>