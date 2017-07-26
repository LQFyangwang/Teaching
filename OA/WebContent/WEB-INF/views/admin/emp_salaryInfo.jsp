<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工工资发放情况</title>
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon-hamburg.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/themes/jeasyui.icons.hamburg.js"></script>

<script>

	// 用字符串替换的方式匹配修改商品时的form表单自动赋值
	var jsonStr = '{"salaryInfo.empId":"_emp_id", "salaryInfo.empName":"_emp_name", "salaryInfo.basicSalary":"_basic_salary", "salaryInfo.jobSalary":"_job_salary", "salaryInfo.extraSalary":"_extra_salary", "salaryInfo.subSalary":"_sub_salary", "salaryInfo.salaryDay":"_salary_day", "salaryInfo.salaryId":"_salary_id", "salaryInfo.salaryInfoId":"_salary_info_id"}';
	
	$(function() {
		setPagination("list");
	});
	
	function clearForm(){
		$('#addForm').form('clear'); // 清除表单的内容
	}
	
	function showCheckDept() { // 选择部门的窗口
		$("#deptWin").window("open"); // 打开班级窗口
		$("#depts").datagrid({url:'<%=path %>/dept/queryPage'}); // 通过这个Url加载数据
	}
	
	function closeDeptWin() { // 关闭部门的窗口
		$("#deptWin").window("close");
	}
	
	function closeEmpWin() { // 关闭员工的窗口
		$("#empWin").window("close");
	}
	
	function authority(methodName, jsonStr, method) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName, 
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addForm").form("clear"); // 清除表单的内容
						$("#addWin").window("open"); // 打开窗口
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
	}
	
	function showAddSalaryNew() {
		authority('com.ht.action.SalaryInfoAction1.add', "", "add");
	}
	
	
	function showAddSalary() { // 打开添加工资记录的窗口
		$("#addForm").form("clear"); // 清除表单的内容
		$("#addWin").window("open"); // 打开窗口
	}
	
	function addSalary() { // 执行添加工资的操作
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			var empId = document.getElementById("addEmpId").value;
			if (empId != null && empId != "") {
				$.post("<%=path %>/si/add",
				$("#addForm").serialize(), // Form表单序列化
				function(data) {
					if (data.result.result == "success") { //表示添加成功
						$("#addWin").window("close"); // 关闭指定窗口
						$("#list").datagrid("reload"); // 重新加载网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
			} else {
				$.messager.alert("提示", "请选择员工", "error");
			}
			
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
		
		
	}
	
	function showEditSalary() { // 执行编辑操作
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			var jsonStr1 = jsonStr.replace("_emp_id", row.empId).replace("_emp_name", row.empName).replace("_basic_salary", row.basicSalary).replace("_job_salary", row.jobSalary).replace("_extra_salary", row.extraSalary).replace("_sub_salary", row.subSalary).replace("_salary_day", row.salaryDay).replace("_salary_id", row.salaryId).replace("_salary_info_id", row.salaryInfoId);
			authority('com.ht.action.SalaryInfoAction1.edit', jsonStr1, "edit");
		} else {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		}
	}
	
	function editSalary() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/si/edit",
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
	
	function closeSalaryWin() {
		$("#editWin").window("close");
	}
	
	function showSelectEmp() {
		var row = $("#depts").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			$("#empWin").window("open"); // 打开学生窗口
			$("#selectEmps").datagrid({url:"<%=path %>/emp2/pager?depId=" + row.depId}); // 动态赋值
		} else {
			$.messager.alert("提示", "请选择部门", "error");
		}
	}
	
	function selectEmp() { // 选中员工
		var row = $("#selectEmps").datagrid("getSelected"); // 获取首个选中的数据
		if (row) {
			$("#empWin").window("close"); // 关闭员工窗口
			$("#deptWin").window("close"); // 关闭部门窗口
			document.getElementById("addEmpId").value = row.empId; // JS赋值
			$.get("<%=path %>/mysi/query_salaryinfo?id='"+row.empId+"'",
					function(data) {
					document.getElementById("addbasicSalary").value = data.rowss[0][2];
					document.getElementById("addjobSalary").value = data.rowss[0][3];
				}, "json");
			$("#addEmpName").textbox('setValue', row.name); //赋值
			document.getElementById("editEmpId").value = row.empId; // JS赋值
			$("#editEmpName").textbox('setValue', row.name); //赋值
		} else {
			$.messager.alert("提示", "请选择员工", "error");
		}
	}
	
	function queryByName(value,name){ // 根据学生姓名查询数据
	    if (value != "") {
	    	$.get("<%=path %>/si/pagerByName?empName=" + value,
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
			$.get("<%=path %>/si/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
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
	
	function status(value, row, index) {
		if (value == 0) {
			return "不可用";
		}
		return "可用";
	}
	
<%-- 	function exportSalary() {
		var grid = $('#list');  // 获取datagrid
		var options = grid.datagrid('getPager').data("pagination").options;  // 获取数据
		var curr = options.pageNumber;  // 获取当前第几页
		var total = options.total;  // 获取总页数
		$.get("<%=path %>/si/exportData?curr=" + curr + "&total1=" + total,
					function(data) {
						if (data.result.result == "success") {
							$.messager.alert("提示", data.result.message, "info");
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
				
	} --%>
</script>
</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/si/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'empId',checkbox:true">员工编号</th>
				<th data-options="field:'empName',width:80">员工姓名</th>
				<th data-options="field:'basicSalary',width:80">基本工资</th>
				<th data-options="field:'jobSalary',width:80">岗位工资</th>
				<th data-options="field:'extraSalary',width:80">奖励工资</th>
				<th data-options="field:'subSalary',width:80">扣罚工资</th>
				<th data-options="field:'shouldSalary',width:80">应发工资</th>
				<th data-options="field:'totalSalary',width:80">实发工资</th>
				<th data-options="field:'salaryDay',width:140" formatter="formatterDate">发放时间</th>
				<th data-options="field:'salaryId', hidden:true">工资编号</th>
				<th data-options="field:'salaryInfoId', hidden:true">工资信息编号</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddSalaryNew();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditSalary();" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:queryByName,prompt:'请输入员工姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'empName',iconCls:'icon-hamburg-hire-me'">老师姓名</div>
			</div>
			<a href="<%=path %>/si/exportData?number=1" class="easyui-linkbutton" iconCls="icon-undo" >导出工资条</a>
		</div>
	</div>
	
	<!-- 添加工资记录的窗口 -->
	<div id="addWin" class="easyui-window" title="添加员工工资发放记录" data-options="iconCls:'icon-add', closed:true" style="width:400px;height:200px;">
		<div>
		    <form id="addForm">
		    	<input type="hidden" id="addEmpId" name="salaryInfo.empId"></input>
		    	<input type="hidden" id="addbasicSalary" name="salaryInfo.basicSalary"></input>
		    	<input type="hidden" id="addjobSalary" name="salaryInfo.jobSalary"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 100px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="salaryInfo.empName" data-options="required:true, 'disabled':true" ></input>
		    				<a href="javascript:;" onclick="showCheckDept()">选择员工</a>
		    			</td>
		    		</tr>
		    		<!-- <tr>
		    			<td>基本工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.basicSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>岗位工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.jobSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr> -->
		    		<tr>
		    			<td>奖励工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.extraSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>扣罚工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.subSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>发放时间:</td>
		    			<td>
		    				<input class="easyui-datebox" name="salaryInfo.salaryDay" data-options="required:true, multiline:true, editable:false"></input>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addSalary();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		</div>
	</div>
	
	
	<!-- 修改员工工资记录 -->
	<div id="editWin" class="easyui-window" title="修改员工工资记录" data-options="iconCls:'icon-edit', closed:true" style="width:400px;height:200px;">
		<div>
		    <form id="editForm">
		    	<input type="hidden" id="editEmpId" name="salaryInfo.empId"></input>
		    	<input type="hidden" name="salaryInfo.salaryId"></input>
		    	<input type="hidden" name="salaryInfo.salaryInfoId"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 100px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editEmpName" name="salaryInfo.empName" data-options="required:true, 'disabled':true" ></input>
		    				<a href="javascript:;" onclick="showCheckDept()">选择员工</a>
		    			</td>
		    		</tr>
		    		<!-- <tr>
		    			<td>基本工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.basicSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>岗位工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.jobSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr> -->
		    		<tr>
		    			<td>奖励工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.extraSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>扣罚工资:</td>
		    			<td>
		    				<input class="easyui-numberbox easyui-validatebox" type="text" name="salaryInfo.subSalary" data-options="required:true, precision:2">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>发放时间:</td>
		    			<td>
		    				<input class="easyui-datebox" name="salaryInfo.salaryDay" data-options="required:true, multiline:true, editable:false"></input>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editSalary();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeSalaryWin()">取消</a>
		    </div>
		</div>
	</div>
	
	<!-- 选择部门的窗口 -->
	<div id="deptWin" class="easyui-window" title="选择部门" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择部门的DataGrid -->
		<table id="depts" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:85%;">
			<thead>
				<tr>
					<th data-options="field:'depId',checkbox:true">编号</th>
				<th data-options="field:'depName',width:80">部门名称</th>
				<th data-options="field:'empName',width:80">负责人</th>
				<th data-options="field:'des',width:180">描述</th>
				<th data-options="field:'status',width:60" formatter="status">状态</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="showSelectEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeDeptWin()">取消</a>
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
					<th data-options="field:'name',width:100">员工名</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="selectEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeEmpWin()">取消</a>
		</div>
	</div>
	
	
	

	
	
</body>
</html>