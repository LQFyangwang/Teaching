<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生就业情况</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>

<script>

	// 用字符串替换的方式匹配修改商品时的form表单自动赋值
	var jsonStr = '{"job.jobId":"_jobid", "job.stu.stuId":"_stuId", "job.stu.name":"_name", "job.company":"_company", "job.jobTitle":"_jobTitle", "job.salary":"_salary", "job.welfare":"_welfare", "job.address":"_address", "job.comPhone":"_comPhone", "job.hireDay":"_hireDay", "job.status":"_status"}';
	
	$(function() {
		setPagination("list");
	});
	
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
	
	function clearForm(){
		$('#addForm').form('clear'); // 清除表单的内容
	}
	
	function showCheckGrade() {
		$("#gradeWin").window("open"); // 打开班级窗口
		$("#grades").datagrid({url:'<%=path %>/grade/pager'});
	}
	
	function closeGradeWin() {
		$("#gradeWin").window("close");
	}
	
	function closeStuWin() {
		$("#stuWin").window("close");
	}
	
	function showAddJob() {
		authority('com.ht.action.JobAction.add', "", "add");
	}
	
	function addJob() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			var sutId = document.getElementById("addSutId").value;
			if (stuId != null && stuId != "") {
				$.post("<%=path %>/job/add",
				$("#addForm").serialize(), // Form表单序列化
				function(data) {
					if (data.result.result == "success") { //表示添加成功
						$("#addWin").window("close"); // 关闭指定窗口
						$("#list").datagrid("reload"); // 重新加载网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$("#addWin").window("close"); // 关闭指定窗口
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
			} else {
				$.messager.alert("提示", "请选择学生", "error");
			}
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
		
		
	}
	
	function showEditJob() {
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
	
		if (row) {
			var jsonStr1 = jsonStr.replace("_jobid", row.jobId).replace("_stuId", row.stu.stuId).replace("_name", row.stu.name).replace("_company", row.company).replace("_jobTitle", row.jobTitle).replace("_salary", row.salary).replace("_welfare", row.welfare).replace("_address", row.address).replace("_comPhone", row.comPhone).replace("_hireDay", row.hireDay).replace("_status", row.status);
			authority('com.ht.action.JobAction.edit', jsonStr1, "edit");
		} else {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		}
	}
	
	function editJob() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/job/edit",
				$("#editForm").serialize(),
				function(data) {
					if (data.result.result == "success") {
						$("#editWin").window("close"); // 关闭指定的窗口
						$("#list").datagrid("reload"); // 更新表格
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$("#editWin").window("close"); // 关闭指定窗口
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
	}
	
	function closeJobWin() {
		$("#editWin").window("close");
	}
	
	function showSelectStu() {
		var row = $("#grades").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			$("#stuWin").window("open"); // 打开学生窗口
			$("#selectStus").datagrid({url:'<%=path %>/stu/gradeIdByPager?gradeId=' + row.gradeId}); // 动态赋值
		} else {
			$.messager.alert("提示", "请选择班级", "error");
		}
	}
	
	function selectStu() {
		var row = $("#selectStus").datagrid("getSelected"); // 获取首个选中的数据
		if (row) {
			$("#gradeWin").window("close"); // 关闭学生窗口
			$("#stuWin").window("close"); // 关闭学生窗口
			document.getElementById("addStuId").value = row.stuId; // JS赋值
			$("#addStuName").textbox('setValue', row.name); //赋值
			document.getElementById("editStuId").value = row.stuId; // JS赋值
			$("#editStuName").textbox('setValue', row.name); //赋值
		} else {
			$.messager.alert("提示", "请选择学生", "error");
		}
	}
	
	function stuName(value, row, index) {
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
			return "<a href='javascript:;' onclick=frostStatus('" + row.jobId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.jobId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/job/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/job/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function grades(value, row, index) {
		return "<a href='javascript:;' onclick=students('" + row.gradeId + "')>所有学生</a>"
	}
	
	function students(id) {
		alert(id)
	}
	
	function queryByStuName(value,name){ // 根据学生姓名查询数据
	    if (value != "") {
	    	$.get("<%=path %>/job/pagerByStuName?stuName=" + value,
					function(data) {
						if (data.result.result == "success") {
							// $("#list").datagrid("reload"); // 更新表格
							$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
	    } else {
	    	$.messager.alert("提示", "请输入学生姓名", "error");
	    }
	}
	
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/job/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
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
			url:'<%=path %>/job/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'jobId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:80" formatter="stuName">学生姓名</th>
				<th data-options="field:'company',width:120">公司名称</th>
				<th data-options="field:'jobTitle',width:80">职位</th>
				<th data-options="field:'salary',width:60">月薪</th>
				<th data-options="field:'welfare',width:100">福利待遇</th>
				<th data-options="field:'address',width:80">公司地址</th>
				<th data-options="field:'comPhone',width:100">公司联系方式</th>
				<th data-options="field:'hireDay',width:140" formatter="formatterDate">入职时间</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddJob();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditJob();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			入职时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:queryByStuName,prompt:'输入学生姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'stuName',iconCls:'icon-man'">学生姓名</div>
			</div>
		</div>
	</div>
	
	<!-- 添加学生就业情况的窗口 -->
	<div id="addWin" class="easyui-window" title="添加记录" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:350px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<input type="hidden" id="addStuId" name="job.stu.stuId"></input>
		    	<table>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="job.stu.name" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司名称:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.company" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>职位:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.jobTitle" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>月薪:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-numberbox" name="job.salary" data-options="required:true, precision:2" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>福利待遇:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.welfare" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司地址:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.address" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司联系方式:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-textbox" type="text" name="job.comPhone" data-options="required:true, validType:'length[11, 11]'"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>入职时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="job.hireDay" data-options="required:true, editable:false"></input>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addJob();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		</div>
	</div>
	
	
	<!-- 修改学生就业记录 -->
	<div id="editWin" class="easyui-window" title="修改谈心记录" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:400px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="job.jobId" />
		    	<input type="hidden" id="editStuId" name="job.stu.stuId" />
		    	<table>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editStuName" name="job.stu.name" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司名称:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.company" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>职位:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.jobTitle" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>月薪:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-numberbox" name="job.salary" data-options="required:true, precision:2" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>福利待遇:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.welfare" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司地址:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="job.address" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>公司联系方式:</td>
		    			<td>
		    				<input class="easyui-validatebox easyui-textbox" type="text" name="job.comPhone" data-options="required:true, validType:'length[4, 11]'"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>入职时间:</td>
		    			<td>
		    				<input class="easyui-datebox" type="text" name="job.hireDay" data-options="required:true, editable:false"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>状态:</td>
						<td>
	    					<input class="easyui-combobox" id="status" name="job.status" data-options="required:true,
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
									'disabled':true"
									" />
	    				</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editJob();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeJobWin()">取消</a>
		    </div>
		</div>
	</div>
	
	<!-- 选择班级的窗口 -->
	<div id="gradeWin" class="easyui-window" title="选择班级" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择班级的DataGrid -->
		<table id="grades" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:85%;">
			<thead>
				<tr>
				<th data-options="field:'gradeId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">名称</th>
				<th data-options="field:'des',width:80">描述</th>
				<th data-options="field:'quantity',width:120">最大班级人数</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
			</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="showSelectStu();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeGradeWin()">取消</a>
		   </div>
	</div>
	
	<!-- 选择学生的窗口 -->
	<div id="stuWin" class="easyui-window" title="选择学生" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择学生的DataGrid -->
		<table id="selectStus" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb2'" style="height:85%;">
			<thead>
				<tr>
				<th data-options="field:'stuId',checkbox:true">编号</th>
					<th data-options="field:'stuNo',width:100">学号</th>
					<th data-options="field:'name',width:80">姓名</th>
					<th data-options="field:'role',width:120">班级角色</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="selectStu();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeStuWin()">取消</a>
		   	</div>
	</div>	

	
	
</body>
</html>