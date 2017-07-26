<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统公告管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script>
	
	var jsonStr = '{"notice.noticeId":"_noticeId","notice.noticetype.name":"_noticetype","notice.noticetype.noticeTypeId":"_typeId","notice.name":"_name","notice.typeId":"_typeId","notice.des":"_des","notice.noticeDay":"_noticeDay","notice.status":"_status","notice.emp.empId":"_empId", "notice.emp.name":"_emp_name"}';
	$(function() {
		setPagination("list");
		//类型下拉菜单的级联查询
		$.get("<%=path%>/noticetype/type",
			function(data){
			$("#type").combobox("loadData", data.request);
		}, "json");
		
	});
	
	formatterDate1 = function (date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		var hor = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
	};
	

	function authority(methodName, jsonStr, method) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addForm").form("clear"); // 清除表单的内容
						$("#addWin").window("open"); // 打开窗口
						$("#addEmpName").textbox('setValue','${sessionScope.emp.name }');
						$("#noticeDay").datetimebox('setValue', formatterDate1(new Date()));
					} else if (method == "edit") {
							$('#editForm').form("load", JSON.parse(jsonStr));
							$('#editWin').window('open');	
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showaddNoticeWin() {
		authority("com.ht.action.NoticeAction.add", "", "add");
		
	}
	
	function addNotice() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/notice/add",
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
	
	function showEditNoticeWin() {
		var rows = $('#list').datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) {// 有选中的行
				var jsonStr1 = jsonStr.replace("_noticeId", row.noticeId).
				replace("_name", row.name).
				replace("_status", row.status).
				replace("_typeId", row.typeId).
				replace("_des", row.des).
				replace("_noticetype", row.noticetype.name).
				replace("_typeId",row.noticetype.noticeTypeId).
				replace("_empId", row.emp.empId).
				replace("_noticeDay", row.noticeDay).
				replace("_emp_name", row.emp.name);
				$.get("<%=path%>/noticetype/type?rowID=" + row.noticetype.noticetypeId,
						function(data){
						$("#typeId").combobox("loadData", data.request);
					}, "json");
				if(row.emp.empId !="${sessionScope.emp.empId}"){
					$.messager.alert("温馨提示","你不可以修改其他员工的公告哟", "info");
				}
				else{	
					authority("com.ht.action.NoticeAction.update", jsonStr1, "edit");
				}
				
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示","请选择一个需要修改的公告", "info");
		}
	}
	
	function EditNotice(){
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/notice/update",
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
			return "<a href='javascript:;' onclick=frostStatus('" + row.noticeId + "','" + row.emp.empId + "')>冻结</a>";
		}
			return "<a href='javascript:;' onclick=activationStatus('" + row.noticeId + "','" + row.emp.empId + "')>激活</a>";
	}
	
	function frostStatus(id,ids) {
		if(ids !="${sessionScope.emp.empId}" ){
			$.messager.alert("温馨提示","你不可以修改其他员工的公告状态哟", "info");
		}else {
		$.get("<%=path %>/notice/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		}
	}
	
	function activationStatus(id,ids) {
		if(ids !="${sessionScope.emp.empId}" ){
			$.messager.alert("温馨提示","你不可以修改其他员工的公告状态哟", "info");
		}else {
		$.get("<%=path %>/notice/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		}
	}
	
	function queryByDay() { //根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/notice/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
					function(data) {
						if (data.result.result == "success") {
							$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择时间段", "error");
		}
	}
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	
	function showType(value){
		return value.name;
	}
	
	function empName(value) {
		return value.name;
	}
	
	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/notice/query',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'noticeId',checkbox:true">编号</th>
				<th data-options="field:'name',width:130">公告名称</th>
				<th data-options="field:'des',width:370">公告详情</th>
				<th data-options="field:'noticetype',width:80"  formatter="showType">公告类型</th>
				<th data-options="field: 'noticeDay',width:130" formatter="formatterDate">发布时间</th>
				<th data-options="field: 'emp',width:80" formatter="empName">发布人</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">公告状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto"> 
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showaddNoticeWin();" class="easyui-linkbutton" iconCls="icon-add">添加公告</a>
			<a href="javascript:;" onclick="showEditNoticeWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑公告</a>
		</div>
		<div>
			时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
		
	</div>
	
	<div id="addWin" class="easyui-window" title="添加公告" data-options="closed:true" style="width:500px;height:300px;">
		<form id="addForm">
		<input type="hidden" id="addEmpId" name="notice.emp.empId" />
			<table>
				<tr>
					<td width="100px;">发布人</td>
					<td><input class="easyui-validatebox easyui-textbox" id="addEmpName" data-options="required:true,validType:'length[2,20]', disabled:true"/></td>
				</tr>
				<tr>
					<td>公告名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="notice.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>公告详情</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="notice.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td>公告类型</td>
					<td><input id="type" class="easyui-combobox" name="notice.typeId" data-options="required:true,'editable':false,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						selected:'true'
						 "/>
					</td>
				</tr>
				<tr>
					<td>发布时间</td>
					<td><input class="easyui-datetimebox" id="noticeDay" name="notice.noticeDay" data-options="editable:false"/></td>
				</tr>
				 <!-- <tr>
					<td>公告状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="notice.status" data-options="required:true,
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
						<a href="javascript:;" onclick="addNotice();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改公告" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="notice.noticeId" />
			<input type="hidden" id="editEmpId" name="notice.emp.empId" />
			<input type="hidden" name="notice.status" />
			<table>
				<tr>
					<td width="100px;">发布人</td>
					<td><input class="easyui-validatebox easyui-textbox"  name="notice.emp.name" data-options="required:true,validType:'length[2,20]', disabled:true"/></td>
				</tr>
				<tr>
					<td>公告名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="notice.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>公告详情</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="notice.des" style="height:100px;"/></td>
				</tr>
				<tr>
					<td>公告类型</td>
					<td><input id="typeId" class="easyui-combobox" name="notice.typeId" data-options="required:true,'editable':false,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						selected:'true' "/>
					</td>
				</tr>
				<tr>
					<td>发布时间</td>
					<td><input class="easyui-datetimebox" id="noticeDay" name="notice.noticeDay" data-options="editable:false"/></td>
				</tr>
				<!-- <tr>
					<td>公告状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="notice.status" data-options="required:true,'editable':false,disabled:true,
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
						<a href="javascript:;" onclick="EditNotice();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>