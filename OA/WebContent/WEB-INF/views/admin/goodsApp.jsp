<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物品申购</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
<%@taglib uri="/struts-tags"  prefix="s"%>
$(function() {
	setPagination("list");
});

var jsonStr = '{"goodsApp.goodsAppId":"_goodsAppId","goodsApp.appDay":"_appDay"'
	+ ',"goodsApp.goodsName":"_goodsName","goodsApp.quantity":"_quantity"'
	+ ',"goodsApp.des":"_des","goodsApp.status":"_status","goodsApp.appStatus":"_appStatus"'
	+ ',"goodsApp.goodsTypeId":"_goodsTypeId"}';
	
function showAddGoodsAPPWin() {
	$("#addForm").form("clear"); // 清除表单里的所有数据
	$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	$.get("<%=path%>/goods/goodsType",function(data){
		$("#goodsType").combobox("loadData", data.results);
	}, "json");
	$("#addEmpName").textbox('setValue', '${sessionScope.emp.name}'); //赋值
	$("#EmpId").textbox('setValue','${sessionScope.emp.empId}');
}

function addGoodsApp() {
	if ($("#addForm").form("validate")) {
		$.post("<%=path %>/goodsApp/addGoodsAPP",
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

function showEmp(){
	$("#EmpWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
}

function addEmp(){
	var row = $("#emp").datagrid("getSelected"); // 获取首个选中的数据
	if(row){
		$("#EmpWin").window("close"); // 打开指定的window， open表示打开，close表示关闭
		
	}
}

function showEditGoodsAPPWin(){
	$("#editTable").show();
	var row = $('#list').datagrid("getSelected");
			if (row) { // 有选中的行
				$.get("<%=path%>/goods/goodsType",function(data){
					$("#goodsType2").combobox("loadData", data.results);
				}, "json");
				var jsonStr1 = jsonStr.replace("_goodsAppId", row.goodsAppId)
				.replace("_appDay", row.appDay).replace("_goodsName",
						row.goodsName).replace("_quantity", row.quantity)
				.replace("_des", row.des).replace("_status", row.status)
				.replace("_appStatus", row.appStatus).replace("_goodsTypeId", row.goodsTypeId);
				$("#editPrice").numberbox("setValue", row.price);
				$("#editAppDay").datetimebox("setValue", row.appDay);
				$("#editForm").form("load", JSON.parse(jsonStr1));
			 	$("#editWin").window("open");
	} else {
		$.messager.alert("提示", "请先选择一个需要修改的用品", "info");
	}
}

function editGoodsApp() {
	if ($("#editForm").form("validate")) {
		$.post("<%=path %>/goodsApp/update",
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
	var empId1 = '${sessionScope.emp.empId}';
	var empId2 = row.empId;
	if (empId1 == empId2) {
		if (row.status == 1) {
			return "可用    " + "<a href='javascript:;' onclick=frostStatus('" + row.goodsAppId + "')>冻结</a>"
		} else if (row.status == 0) {
			return "不可用    " + "<a href='javascript:;' onclick=activationStatus('" + row.goodsAppId + "')>激活</a>";
		}
	} else { 
		if (row.status == 1) {
			return "可用";
		} else {
			return "不可用";
		}
	}
}

function formatterAppStatus(value, row, index) {
	if (row.appStatus == 0) {
		return "审核失败";
	} else if (row.appStatus == 1) {
		return "审核成功"
	} else if (row.appStatus == 2) {
		return "审核中";
	}
}

function formatterAppStatus2(value, row, index) {
	if (row.appStatus == 0) {
		return "审核失败";
	} else if (row.appStatus == 1) {
		return "审核成功";
	} else if (row.appStatus == 2) {
		var roleName = '${sessionScope.emp.role.name}'
		if ('${sessionScope.emp.role.name == "总经理"}' || '${sessionScope.emp.role.name == "后勤主任"}') {
		return "审核中    " + "<a href='javascript:;' onclick=updateAppStatus1()>接受</a>   " + "<a href='javascript:;' onclick=updateAppStatus2('" + row.goodsAppId + "')>回绝</a>";
		} else {
			
		}
	}
}

function frostStatus(id) {
	$.get("<%=path %>/goodsApp/frost?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}


function activationStatus(id) {
	$.get("<%=path %>/goodsApp/activation?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}


function updateAppStatus1(row) {
	var row = $("#list").datagrid("getSelected");
	$.get("<%=path %>/goodsApp/acceptApp?id=" + row.goodsAppId + "&goodsApp.goodsName=" + row.goodsName + "&goodsApp.goodsTypeId=" + row.goodsTypeId + "&goodsApp.quantity=" + row.quantity + "&goodsApp.price=" + row.price + "&goodsApp.empId=" + row.empId + "&goodsApp.des=" + row.des,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function updateAppStatus2(id) {
	$.get("<%=path %>/goodsApp/refuseApp?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}
</script>
</head>
<body style="height:100%; width:100%;">
	<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tab',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/goodsApp/goodsAppPager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'empId',checkbox:true">编号</th>
				<th data-options="field:'empName',width:100">申购员工名称</th>
				<th data-options="field:'quantity',width:80">申购用品数量</th>
				<th data-options="field:'price',width:80">用品单价</th>
				<th data-options="field:'goodsName',width:80">申购名称</th>
				<th data-options="field:'des',width:80">申购描述</th>
				<th data-options="field:'appDay',width:80" formatter="formatterDate">申购时间</th>
				<c:if test="${sessionScope.emp.role.name != '总经理' && sessionScope.emp.role.name != '后勤主任' }">
				<th data-options="field:'appStatus',width:80" formatter="formatterAppStatus">申购状态</th>
				</c:if>
				<c:if test="${sessionScope.emp.role.name == '总经理' || sessionScope.emp.role.name == '后勤主任' }">
				<th data-options="field:'appStatus',width:120" formatter="formatterAppStatus2">申购状态</th>
				</c:if>
				<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tab" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddGoodsAPPWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditGoodsAPPWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加申购" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<div>
		    <form id="addForm">
		    	<input class="easyui-textbox" type="hidden" name="goodsApp.empId" id="EmpId"></input>
		    	<table>
		    		<tr>
		    			<td width="100px;">申购员工:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="emp.name" data-options="required:true, 'disabled':true"></input>
		    			</td>
		    		</tr>
		    		<tr>
						<td>申购用品类型</td>
						<td>
						 <input id="goodsType" class="easyui-combobox" name="goodsApp.goodsTypeId" data-options="required:true,
							method:'get',
							valueField:'id',
							textField:'text',
							panelHeight:'auto'
						"/>
						</td>
					</tr>
		    		<tr>
		    			<td>申购用品名称:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="goodsApp.goodsName" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>用品单价:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-numberbox" name="goodsApp.price" data-options="required:true,precision:2"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>申购用品数量:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="goodsApp.quantity" data-options="required:true"></input>
		    			</td>
		    		</tr>
					<tr>
						<td>申购描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="goodsApp.des" style="height:100px;"/></td>
					</tr>
				<tr>
		    	<tr>   
		    		<td></td> 
					<td>
						<a href="javascript:;" onclick="addGoodsApp();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
		    	</table>
		    </form>
		</div>
	</div>
	
	<div id="editWin" class="easyui-window" title="编辑申购" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:350px;">
		<div>
		    <form id="editForm">
		    	<input class="easyui-textbox" type="hidden"  name="goodsApp.empId" value="${sessionScope.emp.empId}"></input>
		    	<input class="easyui-textbox" type="hidden"  name="goodsApp.appStatus"></input>
		    	<input class="easyui-textbox" type="hidden"  name="goodsApp.status"></input>
		    	<input class="easyui-textbox" type="hidden" name="goodsApp.goodsAppId"></input>
		    	
		    	<table>
		    		<tr>
						<td width="100px;">申购用品类型</td>
						<td>
						 <input id="goodsType2" class="easyui-combobox" name="goodsApp.goodsTypeId" data-options="editable:false,required:true,
							method:'get',
							valueField:'id',
							textField:'text',
							panelHeight:'auto'
						"/>
						</td>
					</tr>
		    		<tr>
		    			<td>申购用品名称:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="goodsApp.goodsName" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>用品单价:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-numberbox" id="editPrice" name="goodsApp.price" data-options="required:true,precision:2"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>申购用品数量:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="goodsApp.quantity" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
						<td>申购时间</td>
						<td><input class="easyui-datetimebox" id="editAppDay" name="goodsApp.appDay" data-options="required:true, editable:false"></input></td>
					</tr>
					<tr>
						<td>申购描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="goodsApp.des" style="height:100px;"/></td>
					</tr>
				<tr>
		    	<tr>   
		    		<td></td> 
					<td>
						<a href="javascript:;" onclick="editGoodsApp();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
		    	</table>
		    </form>
		</div>
	</div>
	
	
</body>
</html>