<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生谈心记录</title>
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
	var jsonStr = '{"talkInfo.talkId":"_talkid", "talkInfo.empName":"_emp_name", "talkInfo.stuName":"_stu_name", "talkInfo.talkDay":"_talk_day", "talkInfo.des":"_des", "talkInfo.status":"_status", "talkInfo.empId":"_emp_id", "talkInfo.stuId":"_stu_id"}';
	
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
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
	}
	
	function clearForm(value){
		$('#addForm').form('clear'); // 清除表单的内容
		$("#addEmpName").textbox("setValue", value);
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
	
	function showAddTalk(value) {
		authority('com.ht.action.TalkAction.add', "", "add", value);
	}
	
	function addTalk() {
		if ($("#addForm").form("validate")) { // 表示验证通过才执行添加操作
			var stuId = document.getElementById("addStuId").value;
			if (stuId != null && stuId != "") {
				$.post("<%=path %>/talk/add",
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
	
	function showEditTalk() {
		var row = $("#list").datagrid("getSelected"); // 获取首个选中的数据
		// var rows = $("#list").datagrid("getSelections"); // 获取所有选中的数据
		if (row) {
			if (row.empName == '${session.emp.name }') {
				var jsonStr1 = jsonStr.replace("_talkid", row.talkId).replace("_emp_name", row.empName).replace("_stu_name", row.stuName).replace("_talk_day", row.talkDay).replace("_des", row.des).replace("_status", row.status).replace("_emp_id", row.empId).replace("_stu_id", row.stuId);
				authority('com.ht.action.TalkAction.edit', jsonStr1, "edit", "");
			} else {
				$.messager.alert("提示", "抱歉，您不能修改其他老师的记录，请联系" + row.empName + "老师自行修改谈心记录", "error");
			}
			
		} else {
			$.messager.alert("提示", "请选择你要编辑的一行数据", "error");
		}
	}
	
	function editTalk() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/talk/edit",
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
	
	function closeTalkWin() {
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
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.talkId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.talkId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/talk/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/talk/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function getReqByName(name, value) { // 调用get方法
		$.get("<%=path %>/talk/pagerByName?name=" + name + "&value=" + value,
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
			$.get("<%=path %>/talk/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
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
			url:'<%=path %>/talk/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'talkId',checkbox:true">编号</th>
				<th data-options="field:'empName',width:80">员工姓名</th>
				<th data-options="field:'stuName',width:80">学生姓名</th>
				<th data-options="field:'talkDay',width:140" formatter="formatterDate">谈心时间</th>
				<th data-options="field:'des',width:200">谈心描述</th>
				<th data-options="field:'status',width:80" formatter="status">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddTalk('${sessionScope.emp.name}');" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:;" onclick="showEditTalk();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			谈心时间段：
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
	
	<!-- 添加谈心记录的窗口 -->
	<div id="addWin" class="easyui-window" title="添加记录" data-options="iconCls:'icon-add', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="addForm">
		    	<input type="hidden" id="addStuId" name="talkInfo.stuId"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addEmpName" name="talkInfo.empName" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="addStuName" name="talkInfo.stuName" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;" onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>谈心时间:</td>
		    			<td><input class="easyui-datebox" name="talkInfo.talkDay" data-options="required:true, multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>谈心描述:</td>
		    			<td><input class="easyui-textbox" name="talkInfo.des" data-options="multiline:true" style="height:60px"></input></td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addTalk();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm('${sessionScope.emp.name }')">清除</a>
		    </div>
		</div>
	</div>
	
	
	<!-- 修改谈心记录 -->
	<div id="editWin" class="easyui-window" title="修改谈心记录" data-options="iconCls:'icon-edit', closed:true" style="width:500px;height:300px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="editForm">
		    	<input type="hidden" name="talkInfo.talkId" />
		    	<input type="hidden" id="editEmpId" name="talkInfo.empId"></input>
		    	<input type="hidden" id="editStuId" name="talkInfo.stuId"></input>
		    	<table>
		    		<tr>
		    			<td style="width: 80px;">员工姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="empName" name="talkInfo.empName" data-options="required:true, 'disabled':true" ></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>学习姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="editStuName" name="talkInfo.stuName" data-options="required:true, 'disabled':true"></input>
		    				<a href="javascript:;"  onclick="showCheckGrade();">选择学生</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>谈心时间:</td>
		    			<td><input class="easyui-datebox" name="talkInfo.talkDay" data-options="multiline:true, editable:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>谈心描述:</td>
		    			<td><input class="easyui-textbox" name="talkInfo.des" data-options="multiline:true" style="height:60px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>状态:</td>
						<td>
							<input class="easyui-combobox" id="status" name="talkInfo.status" data-options="required:true,
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
	    					<!-- <select class="easyui-combobox" name="talkInfo.status" id="status" style="width:100px;">
	    						<option value="1" selected="selected">激活</option>
	    						<option value="0">禁用</option>
	    					</select> -->
	    				</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px;padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="editTalk();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeTalkWin()">取消</a>
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