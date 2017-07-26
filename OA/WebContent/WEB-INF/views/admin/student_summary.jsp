<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生总结记录</title>
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
	var jsonStr = '{"summary.summaryId":"_summaryid", "summary.emp.name":"_emp_name", "summary.stu.name":"_stu_name", "summary.summaryDay":"_summary_day", "summary.des":"_des", "summary.status":"_status", "summary.emp.empId":"_emp_id", "summary.stu.stuId":"_stu_id", "summary.term":"_term"}';
	
	$(function() {
		setPagination("list");
	});
	
	function authority(methodName, jsonStr, method, empName) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addForm").form("clear"); // 清除表单的内容
						$("#addWin").window("open"); // 打开窗口
						$("#addEmpName").textbox("setValue", empName);
						var data = $('#term').combobox('getData');
						$("#term ").combobox('select',data[0].value);
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
	
	function showAddSummary(value) {
		authority("com.ht.action.SummaryAction.add", "", "add", value);
	}
	
	function addSummary() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			var stuId = document.getElementById("addStuId").value;
			if (stuId != null && stuId != "") {
				$.post("<%=path %>/summary/add",
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
				$.messager.alert("提示", "请选择学生", "error");
			}
			
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
		
		
	}
	
	function showEditSummary() {
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			if (row.emp.empId == '${session.emp.empId }') {
				var jsonStr1 = jsonStr.replace("_summaryid", row.summaryId).replace("_emp_name", row.emp.name).replace("_stu_name", row.stu.name).replace("_summary_day", row.summaryDay).replace("_des", row.des).replace("_status", row.status).replace("_emp_id", row.emp.empId).replace("_stu_id", row.stu.stuId).replace("_term", row.term);
				authority("com.ht.action.SummaryAction.edit", jsonStr1, "edit", "");
			} else {
				$.messager.alert("提示", "抱歉，您不能修改其他老师的记录，请联系" + row.emp.name + "老师自行修改总结记录", "error");
			}
			
		} else {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		}
	}
	
	function editSummary() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/summary/edit",
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
	
	function closeEditSummary() { // 关闭编辑窗口
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

	function status(value, row, index) {
		if (value == 1) {
			return "可用";
		}
		return "不可用";
	}
	
	function name(value, row, index) {
		return value.name;
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.summaryId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.summaryId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/summary/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/summary/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function getReqByName(name, value) { // 调用get方法
		$.get("<%=path %>/summary/pagerByName?name=" + name + "&value=" + value,
				function(data) {
					if (data.result.result == "success") {
						// $("#list").datagrid("reload"); // 更新表格
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function queryByName(value,name){ // 根据学生姓名查询数据
	    if (value != "") {
	    	if (name == "stuName") {
	    		getReqByName(name, value);
	    	} else if (name == "empName") {
	    		getReqByName(name, value);
	    	}
	    } else {
	    	$.messager.alert("提示", "请输入姓名", "error");
	    }
	}
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue');
		if (startDay != "" && endDay != "") {
			$.get("<%=path %>/summary/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
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
	
	function term(value) {
		if (value == 1) {
			return "第一个学期";
		} else if (value == 2) {
			return "第二个学期";
		} else if (value == 3) {
			return "第三个学期";
		} else if (value == 4) {
			return "第四个学期";
		}
		return "暂无";
	}
	
</script>
</head>
<body style="height: 100%;">
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/summary/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'summaryId',checkbox:true">编号</th>
				<th data-options="field:'emp',width:80" formatter="name">员工姓名</th>
				<th data-options="field:'stu',width:80" formatter="name">学生姓名</th>
				<th data-options="field:'summaryDay',width:140" formatter="formatterDate" formatter="formatterDate">总结时间</th>
				<th data-options="field:'des',width:200">总结详情</th>
				<th data-options="field:'term',width:80" formatter="term">学期</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddSummary('${sessionScope.emp.name}');" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditSummary();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			总结时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			    data-options="searcher:queryByName,prompt:'请输入姓名',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
			    <div data-options="name:'stuName',iconCls:'icon-hamburg-customers'">学生姓名</div>
			    <div data-options="name:'empName',iconCls:'icon-hamburg-hire-me'">老师姓名</div>
			</div>
		</div>
	</div>
	
	<!-- 添加总结记录的窗口 -->
	<div id="addWin" class="easyui-window" title="添加记录" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<input type="hidden" id="addStuId" name="summary.stu.stuId"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="summary.emp.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="summary.stu.name" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;" onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>总结时间:</td>
		    			<td><input class="easyui-datebox" name="summary.summaryDay" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>详情:</td>
		    			<td><input class="easyui-textbox" name="summary.des" data-options="multiline:true" style="height:60px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>学期:</td>
		    			<td>
		    				<select class="easyui-combobox" id="term" name="summary.term" style="width:100px;">
	    						<option value="1" selected="selected">第一个学期</option>
	    						<option value="2">第二个学期</option>
	    						<option value="3">第三个学期</option>
	    						<option value="4">第四个学期</option>
	    					</select>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addSummary();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		</div>
	</div>
	
	
	<!-- 修改总结记录 -->
	<div id="editWin" class="easyui-window" title="修改总结记录" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="summary.summaryId" />
		    	<input type="hidden" id="editEmpId" name="summary.emp.empId"></input>
		    	<input type="hidden" id="editStuId" name="summary.stu.stuId"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="summary.emp.name" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editStuName" name="summary.stu.name" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>总结时间:</td>
		    			<td><input class="easyui-datebox" name="summary.summaryDay" data-options="multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>总结详情:</td>
		    			<td><input class="easyui-textbox" name="summary.des" data-options="multiline:true" style="height:60px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>学期:</td>
		    			<td>
		    				<select class="easyui-combobox" name="summary.term" style="width:100px;">
	    						<option value="1" selected="selected">第一个学期</option>
	    						<option value="2">第二个学期</option>
	    						<option value="3">第三个学期</option>
	    						<option value="4">第四个学期</option>
	    					</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>状态:</td>
						<td>
							<input class="easyui-combobox" name="summary.status" data-options="required:true,
									data:[
										{'id':1,
										'text':'可用'},
										{'id':0,
										'text':'不可用'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									'disabled':true"
									" />
	    					<!-- <select class="easyui-combobox" name="summary.status" id="status" style="width:100px;">
	    						<option value="1" selected="selected">激活</option>
	    						<option value="0">禁用</option>
	    					</select> -->
	    				</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editSummary()">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeEditSummary()">取消</a>
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