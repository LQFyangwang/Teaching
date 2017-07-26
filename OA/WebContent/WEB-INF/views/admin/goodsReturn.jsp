<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物品归还</title> 
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
function operation() {
	return "<a href='javascript:;' onclick='ReturnGoodsUse()';>归还</a>"
}

$(function() {
	setPagination("list");
});

function ReturnGoodsUse(id){

}
</script>
</head>
<body style="height:100%;">
<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tba',singleSelect:true,
			collapsible:true,
			url:'<%=path %>/goodsUse/GoodsUsePage',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'useId',checkbox:true">编号</th>
				<th data-options="field:'empName',width:100 ,'disabled':true">员工姓名</th>
				<th data-options="field:'goodsName',width:100,'disabled':true">领用用品名称</th>
				<th data-options="field:'useDay',width:100,'disabled':true" formatter="formatterDate">领用时间</th>
				<th data-options="field:'quantity',width:100,'disabled':true">领用数量</th>
				<th data-options="field:'operation',width:100" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!--  <div id="addWin" class="easyui-window" title="选择员工" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tba',singleSelect:true,
			collapsible:true,
			url:'<%=path %>/goodsUse/GoodsUsePage',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'useId',checkbox:true">编号</th>
				<th data-options="field:'empName',width:100">员工姓名</th>
				<th data-options="field:'goodsName',width:100">领用用品名称</th>
				<th data-options="field:'useDay',width:100">领用时间</th>
				<th data-options="field:'quantity',width:100">领用数量</th>
				<th data-options="field:'ereturnDay',width:100">预计归还时间</th>
				<th data-options="field:'returnDay',width:100">归还时间</th> 
			</tr>
		</thead>
	</table>
	</div>
	-->
</body>
</html>