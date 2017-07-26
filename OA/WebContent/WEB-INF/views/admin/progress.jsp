<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程进度管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>

var jsonStr = '{"progress.progressId":"_progressid","progress.grade.gradeId":"_gradeId","progress.grade.name":"_name", "progress.emp.empId":"_empId", "progress.emp.name":"_name", "progress.progressDay":"_progressDay", "progress.des":"_des", "progress.status":"_status"}';

function clearForm(){
	$('#addForm').form('clear'); // 清除表单的内容
}

function closeDepWin() {
	$("#depWin").window("close");
}

function closeEmpWin() {
	$("#empWin").window("close");
}

function showCheckDep() {
	$("#depWin").window("open"); // 打开班级窗口
}

$(function(){
	$.get("<%=path%>/gr/GradeTypeAll",function(data){
		$("#gradeType").combobox("loadData", data.results);
	}, "json");
});

$(function(){
	$.get("<%=path%>/gr/GradeTypeAll",function(data){
		$("#gradeTypes").combobox("loadData", data.results);
	}, "json");
});

function authority(methodName, jsonStr, method, empName, gradeId) {
	$.get("<%=path %>/auth/authority?methodName=" + methodName,
		function(data) {
			if (data.result.result == "success") {
				if (method == "add") {
					$("#addForm").form("clear"); // 清除表单的内容
					$("#addWin").window("open"); // 打开窗口
					$("#status").combobox('setValue', 1);
					$("#addEmpId").val('${sessionScope.emp.empId }');
					$("#addEmpName").textbox('setValue','${sessionScope.emp.name }');
				} else if (method == "update") {
					$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
					$("#editWin").window("open"); // 打开编辑的窗口
					
				}
			} else if (data.result.result == "fail") {
				$.messager.alert("提示", data.result.message, "error");
			}
	}, "json");
}

function showAddProgress() {
	authority('com.ht.action.ProgressAction.save', "", "add");
}

function showEditProgress() {
	
	// var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
	var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
	if (rows.length > 1) {
		$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
	} else if (rows.length == 1) {
		var row = rows[0];
		if (row) {
			var jsonStr1 = jsonStr.replace("_progressid", row.progressId).replace("_gradeId", row.grade.gradeId).replace("_name", row.grade.name).replace("_empId", row.emp.empId).replace("_name", row.emp.name).replace("_progressDay", row.progressDay).replace("_des", row.des).replace("_status", row.status);
			authority('com.ht.action.ProgressAction.update', jsonStr1, "update");
		}
	} else if (rows.length == 0) {
		$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
	}
}

function addProgress() {
	if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
		$.post("<%=path %>/progress/save",
			$("#addForm").serialize(), // Form表单序列化
			function(data) {
				if (data.result.result == "success") { //表示添加成功
					$("#addWin").window("close"); // 关闭指定窗口
					$("#list").datagrid("reload"); // 重新加载网格数据
					$.messager.alert("提示", data.result.message, "info");
				} else if (data.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	} else {
		$.messager.alert("提示", "请正确输入表单数据", "error");
	}
}

function editProgress() {
	if ($("#editForm").form("validate")) {
		$.post("<%=path %>/progress/update",
			$("#editForm").serialize(),
			function(data) {
				if (data.result.result == "success") {
					$("#editWin").window("close"); // 关闭指定的窗口
					$("#list").datagrid("reload"); // 更新表格
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
		$.messager.alert("提示", "请选择部门", "error");
	}
}

function selectEmp() {
	var row = $("#selectEmps").datagrid("getSelected"); // 获取首个选中的数据
	if (row) {
		$("#depWin").window("close"); // 关闭学生窗口
		$("#empWin").window("close"); // 关闭学生窗口
		$("#addEmpId").val(row.empId); // JS赋值
		$("#addEmpName").textbox('setValue', row.name); //赋值
		$("#editEmpId").val(row.empId); // JS赋值
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
		return "<a href='javascript:;' onclick=frostStatus('" + row.progressId + "')>冻结</a>";
	}
	return "<a href='javascript:;' onclick=activationStatus('" + row.progressId + "')>激活</a>";
}

function frostStatus(id) {
	$.get("<%=path %>/progress/frost?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}

function activationStatus(id) {
	$.get("<%=path %>/progress/activation?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
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

function GradeType(value){
	return value.name;
}

function queryByEmpName(value,name){ // 根据员工姓名查询数据
    if (value != "") {
    	$.get("<%=path %>/progress/pagerByEmpName?empName=" + value,
				function(data) {
					if (data.result.result == "success") {
						// $("#list").datagrid("reload"); // 更新表格
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
    } else {
    	$.messager.alert("提示", "请输入员工姓名", "error");
    }
}

</script>

</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/progress/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'progressId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:70" formatter="empName">任课老师</th>
				<th data-options="field:'grade',width:70" formatter="GradeType">班级</th>
				<th data-options="field:'progressDay',width:100" formatter="formatterDate">添加时间</th>
				<th data-options="field:'des', width:80">进度详情</th>
				<th data-options="field:'status',width:100" formatter="status">状态</th>
				<th data-options="field:'operation',width:100" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddProgress();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditProgress();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		
		<div>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:queryByEmpName,prompt:'输入姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'empName',iconCls:'icon-man'">任课老师姓名</div>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加课程进度" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<input type="hidden" id="addEmpId" name="progress.emp.empId"></input>
		    	<input type="hidden" id="addGradeId" name="progress.grade.gradeId"  />
		    	<table>
		    		<tr>
		    			<td width="100px;">任课老师:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="progress.emp.name" data-options="required:true, 'disabled':true"></input>
		    			</td>
		    		</tr>
		    		<tr>
						<td>班级:</td>
						<td>
					 		<input id="gradeType"class="easyui-combobox" name="gradeid" data-options="required:true,'editable':false,
							method:'get',
							valueField:'id',
							textField:'text',
							panelHeight:'auto'
						"/>
						</td>
					</tr>
		    		<tr>
		    			<td>添加时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="progress.progressDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
		    		<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="progress.status" data-options="required:true,
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
						<td>进度描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="progress.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addProgress();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm();">清除</a>
		    </div>
		   </div>
		  </div>
		  
	<div id="editWin" class="easyui-window" title="修改课程进度" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
			<input type="hidden"name="progress.progressId" />
			<input type="hidden" name="progress.status" />
			<input type="hidden" id="editEmpId" name="progress.emp.empId" />
		    	<table>
		    		<tr>
		    			<td width="100px;">任课老师:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editEmpName" name="progress.emp.name" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showCheckDep();">选择任课老师</a>
		    			</td>
		    		</tr>
		    		<tr>
						<td>班级:</td>
						<td>
					 		<input id="gradeTypes"class="easyui-combobox" name="progress.grade.gradeId" data-options="required:true,'editable':false,
							method:'get',
							valueField:'id',
							textField:'text',
							panelHeight:'auto'
						"/>
						</td>
					</tr>
		    		<tr>
		    			<td>添加时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="progress.progressDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
				<!-- 	<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="progress.status" data-options="required:true,'editable':false,
									data:[
										{'id':1,
										'text':'可用',
										'selected':true},
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
						<td>进度描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="progress.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editProgress();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		 </div>
	</div>
	
	<!-- 选择部门的窗口 -->
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
	</div>	

</body>
</html>