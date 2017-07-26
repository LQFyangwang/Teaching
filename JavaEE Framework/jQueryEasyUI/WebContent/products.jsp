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
<link rel="stylesheet" href="jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/main.js"></script>

<script>
	$(function() {
		setPagination("list");
	});
	
	function showAddProductWin() {
		$("#addForm").form("clear"); // 清除表单里的所有数据
		$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	}
	
	function addProduct() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/product/add",
				$("#addForm").serialize(),
				function (data) {
					if (data.result == "success") {
						$("#addWin").window("close"); // 关闭指定的窗口
						$("#list").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.message, "info");
					} else if (data.result == "fail") {
						$.messager.alert("提示", data.message, "info");
					}
			}, "json"
			);
		} else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}
	}
	
	function showEditProductWin() {
		var row = $("#list").datagrid("getSelected"); // 获取数据表格中被选中的行数据
		if (row) { // 有选中的行
			$("#editForm").form("load", row);
		 	$("#editWin").window("open");
		} else {
			$.messager.alert("提示", "请先选择需要修改的商品", "info");
		}
	}
	
	function editProduct() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/product/edit",
					$("#editForm").serialize(),
					function (data) {
						if (data.result == "success") {
							$("#editWin").window("close"); // 关闭指定的窗口
							$("#list").datagrid("reload"); // 重新加载指定数据网格数据
							$.messager.alert("提示", data.message, "info");
						} else if (data.result == "fail") {
							$.messager.alert("提示", data.message, "info");
						}
				}, "json"
				);
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
	function removeProduct() {
		var row = $("#list").datagrid("getSelected");
		if (row) {
			$.messager.confirm("提示", "确定删除吗？", function (r) {
				if (r) { // 点击了确定按键
					$.get("<%=path %>/product/del?id=" + row.id, function (data) {
						$("#list").datagrid("reload");
					}, "json");
				}
			})
		} else {
			$.messager.alert("提示", "请先选择需要删除的商品", "info");
		}
	}
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',singleSelect:true,
			collapsible:true,
			url:'<%=path %>/product/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true">编号</th>
				<th data-options="field:'title',width:100">名称</th>
				<th data-options="field:'price',width:80">价格</th>
				<th data-options="field:'des',width:80">描述</th>
				<th data-options="field:'typeName',width:80">类型</th>
				<th data-options="field:'pDateStr',width:80">生产日期</th>
				<th data-options="field:'enterDateStr',width:80">入库时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddProductWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditProductWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
			<a href="javascript:;" onclick="removeProduct();" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
			<a href="javascript:;" onclick="showDlg();" class="easyui-linkbutton" iconCls="icon-search">显示对话框</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加商品" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
			<table>
				<tr>
					<td>商品名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="title" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="price" data-options="required:true,precision:2"/></td>
				</tr>
				<tr>
					<td>商品类型</td>
					<td>
					<%--
					<input class="easyui-validatebox easyui-combobox" name="type" data-options="required:true,
						data:[{'id':'a','text':'A'},{'id':'b','text':'B'}],
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/>
					 --%>
					 <input class="easyui-combobox" name="type" data-options="required:true,
						url:'<%=path %>/pt/all',
						method:'get',
						valueField:'depId',
						textField:'text',
						panelHeight:'auto'
					"/>
					</td>
				</tr>
				<tr>
					<td>生产日期</td>
					<td><input class="easyui-datebox" name="pDateStr"></td>
				</tr>
				<tr>
					<td>入库时间</td>
					<td><input class="easyui-datetimebox" name="enterDateStr"></td>
				</tr>
				<tr>
					<td>商品描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="addProduct();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改商品" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="id" />
			<table>
				<tr>
					<td>商品名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="title" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="price" data-options="required:true,precision:2"/></td>
				</tr>
				<tr>
					<td>商品类型</td>
					<td>
					 <input class="easyui-combobox" name="type" data-options="required:true,
						url:'<%=path %>/pt/all',
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/>
					</td>
				</tr>
				<tr>
					<td>生产日期</td>
					<td><input class="easyui-datebox" name="pDateStr"></td>
				</tr>
				<tr>
					<td>入库时间</td>
					<td><input class="easyui-datetimebox" name="enterDateStr"></td>
				</tr>
				<tr>
					<td>商品描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="editProduct();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg" class="easyui-dialog" title="超级电商" data-options="closed:true" style="width:400px;height:200px;padding:10px">
		这是一个对话框
	</div>

</body>
</html>