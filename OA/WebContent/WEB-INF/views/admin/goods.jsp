<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用品管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
var jsonStr = '{"goods.goodsId":"_goodsId","goods.name":"_name","goods.quantity":"_quantity"'
	+ ',"goods.des":"_des","goods.unitPrice":"_unitPrice","goods.buyDay":"_buyDay","goods.status":"_status"'
	+ ',"goods.goodsTypeId":"_goodsTypeId","goods.goodsTypeName":"_goodsTypeName"}';
	
	$(function() {
		setPagination("list");
	});
	function authority(methodName, jsonStr, method) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "goodsAdd") {
						$("#addForm").form("clear"); // 清除表单里的所有数据
						$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
					} else if (method == "goodsUpdate") {
						$('#editForm').form("load", JSON.parse(jsonStr));
						$('#EditWin').window('open');
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showAddGoodsWin() {
		authority("com.ht.action.GoodsAction.goodsAdd", "", "goodsAdd");
	}
	
	$(function(){
		$.get("<%=path%>/goods/goodsType",function(data){
			$("#goodsType").combobox("loadData", data.results);
		}, "json");
	});
	
	function formatterStatus(value, row, index) {
		if (row.status == 1) {
			return "可用    " + "<a href='javascript:;' onclick=frostStatus('" + row.goodsId + "')>冻结</a>";
		} else if (row.status == 0) {
			return "不可用    " + "<a href='javascript:;' onclick=activationStatus('" + row.goodsId + "')>激活</a>";
		}
	}

	function frostStatus(id) {
		$.get("<%=path %>/goods/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}


	function activationStatus(id) {
		$.get("<%=path %>/goods/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function addGoods() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/goods/goodsAdd",
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
	
	function EditGoods(){
		if ($('#editForm').form("validate")) {
			$.post("<%=path %>/goods/goodsUpdate",
					$('#editForm').serialize(),
					function(data) {
						if (data.result.result == "success") {
							$('#EditWin').window('close');
							$('#list').datagrid('reload');
							$.messager.alert("提示", data.result.message, "info");
						}else if(data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
			}, "json"
			);
		}else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}		
	}
	function showEditGoodsWin() {
		var row = $('#list').datagrid("getSelected");
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_goodsId", row.goodsId).replace("_name",row.name).
				replace("_quantity", row.quantity).replace("_des", row.des).
				replace("_unitPrice", row.unitPrice).replace("_buyDay", row.buyDay).
				replace("_goodsTypeId",row.goodsTypeId).replace("_goodsTypeName",row.goodsTypeName).
				replace("_status", row.status);
				authority("com.ht.action.GoodsAction.goodsUpdate", jsonStr1, "goodsUpdate");
				$.get("<%=path%>/goods/goodsType?TypeId="+ row.goodsTypeId,function(data){
					$("#goodsTypeId").combobox("loadData", data.results);
				}, "json");
			} else {
				$.messager.alert("提示","请选择一个需要修改的用品", "info");
		}
	}
	
</script>
</head>
<body style="height:100%;">
	<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/goods/goodsPage',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'goodsId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">用品名称</th>
				<th data-options="field:'des',width:150">用品描述</th>
				<th data-options="field:'quantity',width:150">用品总数</th>
				<th data-options="field:'unitPrice',width:150">用品单价</th>
				<th data-options="field:'buyDay',width:150" formatter="formatterDate">购买时间</th>
				<th data-options="field:'goodsTypeName',width:150">用品类型</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">物品状态</th>
			</tr>
		</thead>
	</table>
		
		
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddGoodsWin();" class="easyui-linkbutton" iconCls="icon-add">添加用品</a>
			<a href="javascript:;" onclick="showEditGoodsWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑用品</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加用品" data-options="closed:true" style="width:500px;height:350px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">用品名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="goods.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>用品价格</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="goods.unitPrice" data-options="required:true,precision:2"/></td>
				</tr>
				<tr>
					<td>用品总数</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="goods.quantity" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>用品类型</td>
					<td>
					 <input id="goodsType" class="easyui-combobox" name="goods.goodsTypeId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/>
					</td>
				</tr>
				<tr>
					<td>用品描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="goods.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="EditWin" class="easyui-window" title="编辑用品" data-options="closed:true" style="width:500px;height:350px;">
		<form id="editForm">
		<input type="hidden" name="goods.goodsId" />
			<table>
				<tr>
					<td width="100px;">用品名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="goods.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>用品价格</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="goods.unitPrice" data-options="required:true,precision:2,editable:false"/></td>
				</tr>
				<tr>
					<td>用品总数</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="goods.quantity" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>购买时间</td>
					<td><input class="easyui-datetimebox" name="goods.buyDay" data-options="editable:false,required:'true'"/></td>
				</tr>
				<tr>
					<td>用品类型</td>
					<td>
					 <input id="goodsTypeId" class="easyui-combobox" name="goods.goodsTypeId" data-options="required:true,editable:false,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/>
					</td>
				</tr>
				<tr>
					<td>用品描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="goods.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="EditGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>