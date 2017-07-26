<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>

	var jsonStr = '{"it.incomeTypeId":"_id","it.name":"_name","it.des":"_des","it.status":"_status"}';

	$(function() {
		setPagination("list");
	});
	
	function addCloseWin() {
		$("#addwin").window("close");
	}
	function editCloseWin() {
		$("#editwin").window("close");
	}
	
	function addIncomeTypeWin() {//添加
		$("#addForm").form("clear");//刷新数据
		$("#addwin").window("open");
	}
	
	function addIncomeType() {//添加是否成功
		if($("#addForm").form("validate")) {
			$.post("<%=path %>/incometype/save",
				$("#addForm").serialize(),
				function(data) {
					if(data.result.result == "success") {
						$("#addwin").window("close");//关闭指定的窗口
						$("#list").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if(data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "info");
					}
			}, "json");
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
	function editIncomeTypeWin() {//修改
		// var row = $("#list").datagrid("getSelected");// 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if(rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的支出类别", "info");
		} else if(rows.length == 1) {
			var row = rows[0];
			if(row) {// 有选中的行
				var jsonStr2 = jsonStr.replace("_id",row.incomeTypeId).replace("_name",row.name).replace("_des",row.des).replace("_status",row.status);
				$("#editForm").form("load", JSON.parse(jsonStr2));
				$("#editwin").window("open");
			}
		} else if(rows.length == 0) {
			$.messager.alert("提示","请先选择需要修改的支出类别", "info");
		}
	}
	
	function editIncomeType() {//修改是否成功
		if($("#editForm").form("validate")) {
			$.post("<%=path %>/incometype/update",
					$("#editForm").serialize(),
					function(data) {
						if(data.result.result == "success") {
							$("#editwin").window("close");//关闭指定的窗口
							$("#list").datagrid("reload"); // 重新加载指定数据网格数据
							$.messager.alert("提示", data.result.message, "info");
						} else if(data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
	function operation(value, row, index) {
		return "<a href='javascript:;' onclick=frostStatus('" + row.incomeTypeId + "')>冻结</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/incometype/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/incometype/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function queryFreeze() {//查看冻结
		$("#list").datagrid({
			toolbar:'#tb',
			url:'<%=path %>/incometype/query_status',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:10,
			columns : [ [ {  
                field : 'incomeTypeId',checkbox:true,  
                width : 80,  
                title : '类别编号'  
            }, {  
                field : 'name',  
                width : 80,  
                title : '名称'  
            }, {  
                field : 'des',  
                width : 80,  
                title : '描述'
            }, {  
                field : 'status', 
                width : 80,
                title : '状态',
                formatter : function(value, row, index) {  
                    if (row.status == 1) {  
                        return "可用";  
                    } else if(row.status == 0) {  
                        return "不可用";  
                    }  
                }
            }, {  
                field : 'operation',  
                width : 50,  
                title : '操作',
                formatter : function(value, row, index) {
            		return "<a href='javascript:;' onclick=activationStatus('" + row.incomeTypeId + "')>激活</a>";
        		}
            } ] ]
		});
	}
	
	function doSearch(value,name){
		$("#list").datagrid({
			toolbar:'#tb',
			url:'<%=path %>/incometype/query_name?'+name+'=' + value,
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:10,
			columns : [ [ {  
                field : 'incomeTypeId',checkbox:true,  
                width : 80,  
                title : '类别编号'  
            }, {  
                field : 'name',  
                width : 80,  
                title : '名称'  
            }, {  
                field : 'des',  
                width : 80,  
                title : '描述'
            }, {  
                field : 'status', 
                width : 80,
                title : '状态',
                formatter : function(value, row, index) {  
                    if (row.status == 1) {  
                        return "可用";  
                    } else if(row.status == 0) {  
                        return "不可用";  
                    }  
                }
            }, {  
                field : 'operation',  
                width : 50,  
                title : '操作',
                formatter : function(value, row, index) {
            		return "<a href='javascript:;' onclick=frostStatus('" + row.incomeTypeId + "')>冻结</a>";
        		}
            } ] ]
		});
	}
	
	function formatterStatus(value, row, index) {
		if (row.status == 1) {
			return "可用"
		} else if (row.status == 0) {
			return "不可用";
		}
	}
	function nofreeze() {
		$("#list").datagrid({
			toolbar:'#tb',
			url:'<%=path %>/incometype/query_page',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:10,
			columns : [ [ {  
                field : 'incomeTypeId',checkbox:true,  
                width : 80,  
                title : '类别编号'  
            }, {  
                field : 'name',  
                width : 80,  
                title : '名称'  
            }, {  
                field : 'des',  
                width : 80,  
                title : '描述'
            }, {  
                field : 'status', 
                width : 80,
                title : '状态',
                formatter : function(value, row, index) {  
                    if (row.status == 1) {  
                        return "可用";  
                    } else if(row.status == 0) {  
                        return "不可用";  
                    }  
                }
            }, {  
                field : 'operation',  
                width : 50,  
                title : '操作',
                formatter : function(value, row, index) {
            		return "<a href='javascript:;' onclick=frostStatus('" + row.incomeTypeId + "')>冻结</a>";
        		}
            } ] ]
		});
	}
</script>
</head>
<body>
	<table id="list" class="easyui-datagrid" 
			data-options="toolbar:'#tb',
			url:'<%=path %>/incometype/query_page',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,<!-- 分页按钮是否显示固定在底部 -->
			<!-- fitColumns:true,<!-- 表格面积是否全部占满 -->-->
			rownumbers:true,<!-- 是否显示序列号 -->
			autoRowHeight:true,
			pagination:true,
			pageSize:10">
		<thead>
			<tr>
				<th data-options="field:'incomeTypeId',checkbox:true">类别编号</th>
				<th data-options="field:'name'" width="80">名称</th>
				<th data-options="field:'des'" width="80">描述</th>
				<th data-options="field:'status'" width="80" formatter="formatterStatus">状态</th>
				<th data-options="field:'operation'" width="50" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto;">
		<div style="margin-buttom:5px;">
			<a href="javascript:;" onclick="addIncomeTypeWin()" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="editIncomeTypeWin()" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			<a href="javascript:;" onclick="nofreeze()" class="easyui-linkbutton" iconCls="icon-search">查看未冻结</a>
			<a href="javascript:;" onclick="queryFreeze()" class="easyui-linkbutton" iconCls="icon-search">查看冻结</a>
			<input id="search" class="easyui-searchbox" data-options="prompt:'搜索',menu:'#mm',searcher:doSearch" style="width:300px"></input>
			<div id="mm">
				<div data-options="name:'name'">名称</div>
				<div data-options="name:'des'">描述</div>
			</div>
		</div>
	</div>
	
	<div id="addwin" class="easyui-window" title="添加" data-options="closed:true" style="width:300px;height:230px;">
		<form id="addForm">
			<input type="hidden" name="it.status" />
			<table>
				<tr>
					<td width="60px">收入名称: </td>
					<td><input class="easyui-validatebox easyui-textbox" name="it.name" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>收入描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" name="it.des" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="addIncomeType()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editwin" class="easyui-window" title="修改" data-options="closed:true" style="width:300px;height:230px;">
		<form id="editForm">
			<input type="hidden" name="it.incomeTypeId" />
			<input type="hidden" name="it.status" />
			<table>
				<tr>
					<td width="60px">收入名称: </td>
					<td><input class="easyui-validatebox easyui-textbox" name="it.name" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>收入描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" name="it.des" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="editIncomeType()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>