<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工反馈管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	
		var jsonStr = '{"empfeedback.feedBackId":"_feedBackId", "empfeedback.emp.empId":"_empId", "empfeedback.emp.name":"_name", "empfeedback.feedBackDay":"_feedBackDay", "empfeedback.des":"_des", "empfeedback.status":"_status"}';
	
		$(function() {
			setPagination("list");
		});
		
		function clearForm(){
			$('#addForm').form('clear'); // 清除表单的内容
		}
		
		function showCheckDep() {
			$("#depWin").window("open"); // 打开班级窗口
		}
		
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
							$("#name").textbox('setValue','${sessionScope.emp.name }');
						} else if (method == "update") {
							$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
							$("#editWin").window("open"); // 打开编辑的窗口
						}
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
		}
		
		function showAddJob() {
			authority('com.ht.action.EmpFeedBackAction.add', "", "add");
		}
		
		function addEmpFeedBack() {
			if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
				$.post("<%=path %>/empfeedback/add",
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
		
		function showEditEmpFeedBack() {
			// var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
			var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
			if (rows.length > 1) {
				$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
			} else if (rows.length == 1) {
				var row = rows[0];
				if (row) {
					var jsonStr1 = jsonStr.replace("_feedBackId", row.feedBackId).replace("_empId", row.emp.empId).replace("_name", row.emp.name).replace("_feedBackDay", row.feedBackDay).replace("_des", row.des).replace("_status", row.status);
					if(row.emp.empId !="${sessionScope.emp.empId}"){
						$.messager.alert("温馨提示","你不可以修改其他员工的反馈信息哟", "info");
					} else {
						authority('com.ht.action.EmpFeedBackAction.update', jsonStr1, "update");
					}
				}
			} else if (rows.length == 0) {
				$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
			}
		}
		
		function editEmpFeedBack() {
			if ($("#editForm").form("validate")) {
				$.post("<%=path %>/empfeedback/update",
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
				document.getElementById("addEmpId").value = row.empId; // JS赋值
				$("#addEmpName").textbox('setValue', row.name); //赋值
				document.getElementById("editEmpId").value = row.empId; // JS赋值
				$("#editEmpName").textbox('setValue', row.name); //赋值
			} else {
				$.messager.alert("提示", "请选择员工", "error");
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
				return "<a href='javascript:;' onclick=frostStatus('" + row.feedBackId + "','" + row.emp.empId + "')>冻结</a>";
			}
			return "<a href='javascript:;' onclick=activationStatus('" + row.feedBackId + "','" + row.emp.empId + "')>激活</a>";
		}
		
		function frostStatus(id,ids) {
			if(ids !="${sessionScope.emp.empId}" ){
				$.messager.alert("温馨提示","你不可以修改其他员工的公告状态哟", "info");
			}else {
			$.get("<%=path %>/empfeedback/frost?id=" + id,
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
			$.get("<%=path %>/empfeedback/activation?id=" + id,
					function(data) {
						if (data.result.result == "success") {
							$("#list").datagrid("reload"); // 更新表格
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
			}
		}
		
		function deps(value, row, index) {
			return "<a href='javascript:;' onclick=students('" + row.depId + "')>所有学生</a>"
		}
		
		function students(id) {
			alert(id)
		}
		
		function queryByEmpName(value,name){ // 根据员工姓名查询数据
		    if (value != "") {
		    	$.get("<%=path %>/empfeedback/pagerByEmpName?empName=" + value,
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
		
		function queryByDay() { // 根据时间段查询数据
			var startDay = $("#startDay").textbox('getValue');
			var endDay = $("#endDay").textbox('getValue');
			if (startDay != "" && endDay != "") {
				$.get("<%=path %>/empfeedback/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
						function(data) {
							if (data.result.result == "success") {
								// $("#list").datagrid("reload"); // 更新表格
								$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
							} else if (data.result.result == "fail") {
								$.messager.alert("提示", data.result.message, "error");
							}
					}, "json");
			} else {
				$.messager.alert("提示", "请选择时间段", "error");
			}
		}
		
</script>

</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/empfeedback/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'feedBackId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:70" formatter="empName">员工姓名</th>
				<th data-options="field:'feedBackDay',width:100" formatter="formatterDate">反馈时间</th>
				<th data-options="field:'des', width:80">反馈详情</th>
				<th data-options="field:'status',width:100" formatter="status">状态</th>
				<th data-options="field:'operation',width:100" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddJob();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditEmpFeedBack();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			反馈时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:queryByEmpName,prompt:'输入员工姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'empName',iconCls:'icon-man'">老师姓名</div>
			</div>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加反馈" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		    <form id="addForm">
		    	<input type="hidden" id="addEmpId" name="empfeedback.emp.empId"></input>
		    	<table>
		    		<tr>
		    			<td width="100px;">反馈人:</td>
		    			<td>
							<input class="easyui-validatebox easyui-textbox" id="name" data-options="required:true,validType:'length[2,20]', disabled:true"/>
		    			</td>
		    		</tr>
		    		<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="empfeedback.status" data-options="required:true,
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
						<td>反馈描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="empfeedback.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addEmpFeedBack();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm();">清除</a>
		    </div>
		   </div>
		  
	<div id="editWin" class="easyui-window" title="修改反馈" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="empfeedback.feedBackId" />
		    	<input type="hidden" name="empfeedback.status" />
		    	<input type="hidden" id="editEmpId" name="empfeedback.emp.empId" />
		    	<table>
		    		<tr>
		    			<td width="100px;">反馈人:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editEmpName" name="empfeedback.emp.name" data-options="required:true, 'disabled':true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>研反馈时间:</td>
		    			<td>
		    				<input class="easyui-datetimebox" type="text" name="empfeedback.feedBackDay" data-options="editable:false"></input>
		    			</td>
		    		</tr>
<!-- 					<tr>
						<td>状态</td>
						<td>
						<input class="easyui-combobox" id="status" name="empfeedback.status" data-options="required:true,'editable':false,
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
						<td>反馈描述</td>
						<td><input class="easyui-textbox" data-options="multiline:true" name="empfeedback.des" style="height:70px;"/></td>
					</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editEmpFeedBack();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		  </div>
		 </div>	

</body>
</html>