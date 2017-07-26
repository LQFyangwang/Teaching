<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物品类型</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script>
	
	var jsonStr = '{"goodsType.goodsTypeId":"_goodsTypeId","goodsType.name":"_name","goodsType.des":"_des","goodsType.status":"_status"}';
	
	$(function() {
		setPagination("list");
	});
	
	function showaddGoodsTypeWin() {
		$("#addForm").form("clear"); // 清除表单里的所有数据
		$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	}
	
	function addgoodsType() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/goodsType/goodsTypeSave",
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
	
	function showEditGoodsTypeWin() {
		var row = $('#list').datagrid("getSelected");
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_goodsTypeId", row.goodsTypeId).replace("_name", row.name).replace("_des", row.des).replace("_status", row.status);
				$('#editForm').form("load", JSON.parse(jsonStr1));
				$('#editWin').window('open');
		} else {
			$.messager.alert("提示","请选择一个需要修改的物品类型", "info");
		}
	}
	
	function EditGoodsType(){
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/goodsType/goodsUpdate",
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
			return "可用    " + "<a href='javascript:;' onclick=frostStatus('" + row.goodsTypeId + "')>冻结</a>"
		} else if (row.status == 0) {
			return "不可用    " + "<a href='javascript:;' onclick=activationStatus('" + row.goodsTypeId + "')>激活</a>";
		}
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/goodsType/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/goodsType/activation?id=" + id,
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
	

	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/goodsType/goodsTypePage',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'goodsTypeId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">物品类型名称</th>
				<th data-options="field:'des',width:150">物品类型描述</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">物品类型状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showaddGoodsTypeWin();" class="easyui-linkbutton" iconCls="icon-add">添加物品类型</a>
			<a href="javascript:;" onclick="showEditGoodsTypeWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑物品类型</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加物品类型" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">物品类型名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="goodsType.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>物品类型描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="goodsType.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addgoodsType();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改用品类型" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="goodsType.goodsTypeId" />
			<table>
				<tr>
					<td width="100px;">类型名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="goodsType.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>描述</td>
					<td><input class="easyui-textbox"data-options="multiline:true" name="goodsType.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="EditGoodsType();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>