<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物品领用</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
$(function() {
	setPagination("list");
});

function showAddGoodsAPPWin() {
	$("#addForm").form("clear"); // 清除表单里的所有数据
	$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	$("#addEmpName").textbox('setValue', '${sessionScope.emp.name}'); //赋值
	$("#EmpId").textbox('setValue', '${sessionScope.emp.empId }'); //赋值
}

function addGoodsUse() {
	var Quantity = $('#quantity2').textbox('getValue');
	var Quantity1 = $('#quantity').textbox('getValue');
	if ($("#addForm").form("validate")) {
		if((parseInt(Quantity) <= parseInt(Quantity1)) && ((parseInt(Quantity) != 0))){
				var str = $("#addGoodsName").textbox("getValue");
				$.post("<%=path %>/goodsUse/GoodsUseSave?goodsName=" + str,
						$("#addForm").serialize(),
						function (data) {
							if (data.result.result == "success") {
								$("#addWin").window("close"); // 关闭指定的窗口
								$("#list").datagrid("reload"); // 重新加载指定数据网格数据
								$.messager.alert("提示", data.result.message, "info");
							} else if (data.result.result == "fail") {
								$.messager.alert("提示", data.result.message, "info");
							}
					}, "json");
		}else{
			$.messager.alert("温馨提示", "领用的数量大于物品库存数量(不能输入零值),请联系管理员添加库存哟", "info");
			$("#addWin").window("close"); // 关闭指定的窗口
		}
		
	} else {
		$.messager.alert("提示", "请输入正确的数据", "info");
	}
}

function returnDayFormatter(value, row, index){
	if(row.returnDay == null || row.returnDay == ""){
		return "";
	}
	return row.returnDay;
}
function showEmp(){
	$("#EmpWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
}

function showGoods(){
	$("#goods").datagrid({url:"<%=path %>/goods/goodsPage"});
	$("#GoodsWin").window("open");
}


function addGoods(){
	var row = $("#goods").datagrid("getSelected"); // 获取首个选中的数据
	if(row){
		if (row.status == 1) {
			$("#GoodsWin").window("close");
			$("#addGoodsName").textbox('setValue', row.name); //赋值
			$("#GoodsId").textbox('setValue', row.goodsId); //赋值
			$("#quantity").textbox('setValue',row.quantity);
		} else {
			$.messager.alert("提示", "对不起，该用品已被禁用！", "info");
		}
	} else {
		$.messager.alert("提示", "请选择你要领用的物品！", "info");
	}
}

function returnStatus(value, row, index) {
	if (value == 1) {
		return "已归还";
	} else if (value == 2) {
		var empId1 = '${sessionScope.emp.empId}';
		var empId2 = row.empId;
		if (empId1 == empId2) {
		return "待归还    " + "<a href='javascript:;' onclick=updateReturn()>归还</a>";
		}
		return "待归还";
	} else if (value == 0) {
		return "暂无";
	}
}

function checkStatus(value, row, index) {
	if (value == 0) {
		return "未同意";
	} else if (value == 1) {
		return "已同意";
	} else if (value == 2) {
		var roleName = '${sessionScope.emp.role.name}';
		if (roleName == "后勤主任") {
		return "待审核    " + "<a href='javascript:;' onclick=acceptCheck()>同意</a>    " + "<a href='javascript:;' onclick=refuseCheck()>回绝</a>";
		}
		return "待审核";
	}
}

function updateReturn() {
	var row = $("#list").datagrid("getSelected");
	if (row) {
		$.get("<%=path %>/goodsUse/updateReturn?goodsUse.useId=" + row.useId + "&goodsUse.goodsId=" + row.goodsId + "&goodsUse.quantity=" + row.quantity + "&goodsName=" + row.goodsName,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
}

function acceptCheck() {
	var row = $("#list").datagrid("getSelected");
	if (row) {
		$.get("<%=path %>/goodsUse/acceptCheck?id=" + row.useId  + "&goodsUse.quantity=" + row.quantity + "&goodsName=" + row.goodsName,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
}

function refuseCheck() {
	var row = $("#list").datagrid("getSelected");
	if (row) {
		$.get("<%=path %>/goodsUse/refuseCheck?id=" + row.useId,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
}
</script>
</head>
<body  style="height: 100%;">

	<div id="tab" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddGoodsAPPWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
		</div>
	</div>
	
	<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tab',singleSelect:true,
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
				<th data-options="field:'useDay',width:100" formatter="formatterDate">领用时间</th>
				<th data-options="field:'quantity',width:100">领用数量</th>
				<th data-options="field:'ereturnDay',width:100" formatter="formatterDate">预计归还时间</th>
				<th data-options="field:'returnDay',width:100" formatter="returnDayFormatter" formatter="formatterDate">归还时间</th>
				<th data-options="field:'returnStatus',width:100" formatter="returnStatus">归还状态</th>
				<th data-options="field:'checkStatus',width:120" formatter="checkStatus">领用审核状态</th>
			</tr>
		</thead>
	</table>
	
	
	<div id="addWin" class="easyui-window" title="添加用品领用" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<div>
		    <form id="addForm">
		    	<input class="easyui-textbox" type="hidden" id="EmpId" name="emp.empId"></input>
		    	<input class="easyui-textbox" type="hidden" id="GoodsId" name="goods.goodsId"></input>
		    	<input class="easyui-textbox" type="hidden"  id="quantity" name="goods.quantity"></input>
		    	<table>
		    		<tr>
		    			<td width="100px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="emp.name" data-options="required:true, 'disabled':true"></input>
		    			</td>
		    		</tr>
		    			<tr>
		    			<td>领用用品名称:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-numberbox" type="text" id="addGoodsName" name="goods.name" data-options="required:true,precision:0,'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showGoods();">选择用品</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>领用用品数量:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id ="quantity2" name="goodsUse.quantity" data-options="required:true"></input>
		    			</td>
		    		</tr>
					<tr>
						<td>预估归还时间</td>
						<td><input class="easyui-datetimebox" name="goodsUse.ereturnDay" data-options="editable:false"/></td>
					</tr>
		    	<tr>   
		    		<td></td> 
					<td>
						<a href="javascript:;" onclick="addGoodsUse();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
		    	</table>
		    </form>
		</div>
	</div>
	

	<div id="GoodsWin" class="easyui-window" title="请选择用品" data-options="iconCls:'icon-search', closed:true" style="width:500px;height:350px;">
		<table id="goods" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'name',width:100">用品名称</th>
				<th data-options="field:'quantity',width:100">库存量</th>
			</tr>
		</thead>
	</table>
	<div>
		<a href="javascript:;" onclick="addGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
	</div>
</div>

</body>
</html>