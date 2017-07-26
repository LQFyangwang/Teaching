<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>研讨会管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>

	var jsonStr = '{"meetting.meettingId":"_meettingid", "meetting.emp.empId":"_empId", "meetting.emp.name":"_name", "meetting.meettingDay":"_meettingDay", "meetting.createdDay":"_createdDay", "meetting.des":"_des", "meetting.status":"_status"}';

	$(function() {
		setPagination("meettinglist");
	});
	
	function clearForm(){
		$('#addForm').form('clear'); // 清除表单的内容
	}
	
/* 	function showCheckDep() {
		$("#depWin").window("open"); // 打开班级窗口
	} */
	
	function closeDepWin() {
		$("#depWin").window("close");
	}
	
	function closeEmpWin() {
		$("#empWin").window("close");
	}
	
	function authority(methodName, jsonStr, method, empName) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addForm").form("clear"); // 清除表单的内容
						$("#addWin").window("open"); // 打开窗口
						$("#status").combobox('setValue', 1);
						$("#addEmpId").val('${sessionScope.emp.empId }');
						$("#addEmpName").textbox('setValue','${sessionScope.emp.name }');
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showAddMeetting(value) {
		authority('com.ht.action.MeettingAction.add', "", "add", value);
	}
	
	function addMeetting() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			$.post("<%=path %>/meetting/add",
				$("#addForm").serialize(), // Form表单序列化
				function(data) {
					if (data.result.result == "success") { //表示添加成功
						$("#addWin").window("close"); // 关闭指定窗口
						$("#meettinglist").datagrid("reload"); // 重新加载网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
		
		
	}
	
	function showEditMeetting() {
		// var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		var rows = $("#meettinglist").datagrid("getSelections"); // 获取所有选中的数据
		if (rows.length > 1) {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) {
				var jsonStr1 = jsonStr.replace("_meettingid", row.meettingId).replace("_empId", row.emp.empId).replace("_name", row.emp.name).replace("_meettingDay", row.meettingDay).replace("_meettingcreatedDay", row.meettingcreatedDay).replace("_des", row.des).replace("_status", row.status);
				authority('com.ht.action.MeettingAction.update', jsonStr1, "edit");
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		}
	}
	
	function editMeetting() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/meetting/update",
				$("#editForm").serialize(),
				function(data) {
					if (data.result.result == "success") {
						$("#editWin").window("close"); // 关闭指定的窗口
						$("#meettinglist").datagrid("reload"); // 更新表格
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
	}
	
	function showSelectEmp() {
		var row = $("#deps").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			$("#empWin").window("open"); // 打开学生窗口
			$.get("<%=path %>/emp2/pager?depId=" + row.depId,
					function(data) {
						$("#selectEmps").datagrid("loadData", data.rows); // 动态赋值
				}, "json");
		} else {
			$.messager.alert("提示", "请选择班级", "error");
		}
	}
	
	function selectEmp() {
		var row = $("#selectEmps").datagrid("getSelected"); // 获取首个选中的数据
		if (row) {
			$("#depWin").window("close"); // 关闭学生窗口
			$("#empWin").window("close"); // 关闭学生窗口
			document.getElementById("addEmpId").value = row.empId; // JS赋值
			$("#addEmpName").textbox('setValue', row.name); //赋值
			document.getElementById("editEmpId").value = row.empId; // JS赋值
			$("#editEmpName").textbox('setValue', row.name); //赋值
		} else {
			$.messager.alert("提示", "请选择学生", "error");
		}
	}
	
	function empName(value, row, index) {
		return value.name;
	}

	function status(value, row, index) {
		if (value == 1) {
			return "可用";
		}
		return "不可用";
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.meettingId + "','" + row.emp.empId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.meettingId + "','" + row.emp.empId + "')>激活</a>";
	}
	
	function frostStatus(id,ids) {		
		$.get("<%=path %>/meetting/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
							$("#meettinglist").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		}
	
	function activationStatus(id,ids) {
		$.get("<%=path %>/meetting/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#meettinglist").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		}
	
	function deps(value, row, index) {
		return "<a href='javascript:;' onclick=students('" + row.depId + "')>所有学生</a>"
	}
	
	function students(id) {
		alert(id)
	}
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/meetting/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
					function(data) {
						if (data.result.result == "success") {
							$("#meettinglist").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择时间段", "error");
		}
	}
	
	function queryByName(value){ // 根据学生姓名查询数据
	    if (value != "") {
	    	$.get("<%=path %>/meetting/pagerByName?value=" + value,
					function(data) {
						if (data.result.result == "success") {
							// $("#list").datagrid("reload"); // 更新表格
							$("#meettinglist").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
	    } else {
	    	$.messager.alert("提示", "请输入姓名", "error");
	    }
	}


</script>

</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="meettinglist" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/meetting/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'meettingId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:70" formatter="empName">主持人姓名</th>
				<th data-options="field:'meettingDay',width:100" formatter="formatterDate">研讨会时间</th>
				<th data-options="field:'createdDay',width:100" formatter="formatterDate">添加时间</th>
				<th data-options="field:'des', width:80">研讨会详情</th>
				<th data-options="field:'status',width:100" formatter="status">状态</th>
				<th data-options="field:'operation',width:100" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddMeetting();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditMeetting();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:150px" data-options="searcher:queryByName,prompt:'请输入主持人姓名'"></input>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加研讨会" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<input type="hidden" id="addEmpId" name="meetting.emp.empId"></input>
		    	<table>
		    		<tr>
		    			<td width="100px;">主持人:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-textbox" id="addEmpName" data-options="required:true,validType:'length[2,20]', disabled:true"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>研讨会时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="meetting.meettingDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>添加时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="meetting.createdDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
		    		<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="meetting.status" data-options="required:true,
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
					</tr>
					<tr>
						<td>研讨会描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="meetting.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addMeetting();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm();">清除</a>
		    </div>
		   </div>
		  </div>
		    
	<div id="editWin" class="easyui-window" title="修改研讨会" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="meetting.meettingId" />
		    	<input type="hidden" id="editEmpId" name="meetting.emp.empId" />
		    	<input type="hidden" name="meetting.status"/>
		    	<table>
		    		<tr>
		    			<td width="100px;">主持人:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editEmpName" name="meetting.emp.name" data-options="required:true, 'disabled':true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>研讨会时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="meetting.meettingDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>添加时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="meetting.createdDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
<!-- 					<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="meetting.status" data-options="required:true,'editable':false,
									data:[
										{'id':1,
										'text':'可用'},
										{'id':0,
										'text':'不可用'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									disabled:true
									" />
						</td>
					</tr> -->
		    		<tr>
						<td>研讨会描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="meetting.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editMeetting();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		 </div>
	</div>
	
	<%-- <!-- 选择部门的窗口 -->
	<div id="depWin" class="easyui-window" title="选择部门" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择部门的DataGrid -->
		<table id="deps" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				url:'<%=path %>/dept/queryPage',
				method:'get',
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:85%;">
			<thead>
				<tr>
				<th data-options="field:'depId',checkbox:true">编号</th>
				<th data-options="field:'depName',width:100">名称</th>
			</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="showSelectEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeDepWin()">取消</a>
		   </div>
	</div>
	
	<!-- 选择员工的窗口 -->
	<div id="empWin" class="easyui-window" title="选择员工" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择员工的DataGrid -->
		<table id="selectEmps" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb2'" style="height:85%;">
			<thead>
				<tr>
					<th data-options="field:'empId',checkbox:true">编号</th>
					<th data-options="field:'name',width:80">姓名</th>

				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="selectEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeEmpWin()">取消</a>
		   	</div>
	</div>	 --%>
	
</body>
</html>