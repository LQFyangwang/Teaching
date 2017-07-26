<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生成绩查询</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	
	function clearForm(){ // 清除表单数据
		$('#checkForm').form('clear'); // 清除表单的内容
	}
	
	function showCheckGrade() { 
		$("#gradeWin").window("open"); // 打开班级窗口
		$("#grades").datagrid({url:'<%=path %>/grade/pager'});
	}
	
	function closeGradeWin() { // 关闭班级窗口
		$("#gradeWin").window("close");
	}

	function checkGrade() { // 选择班级
		var row = $("#grades").datagrid("getSelected"); // 获取首个选中的数据
		if (row) {
			$("#gradeName").textbox("setValue", row.name);
			$("#gradeId").val(row.gradeId);
			$("#gradeName1").textbox("setValue", row.name);
			$("#gradeId1").val(row.gradeId);
			$("#gradeWin").window("close");
		} else {
			$.messager.alert("提示", "请选择班级", "error");
		}
	}
	
	function showCheckCourse() { 
		$("#courseWin").window("open"); // 打开课程窗口
		$("#courses").datagrid({url:'<%=path %>/course/pager'});
	}
	
	function closeCourseWin() { // 关闭课程窗口
		$("#courseWin").window("close");
	}

	function checkCourse() { // 选择课程
		var row = $("#courses").datagrid("getSelected"); // 获取首个选中的数据
		if (row) {
			$("#courseName").textbox("setValue", row.name);
			$("#courseId").val(row.courseId);
			$("#courseName1").textbox("setValue", row.name);
			$("#courseId1").val(row.courseId);
			$("#courseWin").window("close");
		} else {
			$.messager.alert("提示", "请选择课程", "error");
		}
	}
	
	function searchChecking() { // 根据表单内容查找班级对应的考试信息
		var gradeId = document.getElementById("gradeId").value;
		var courseId = document.getElementById("courseId").value;
		if (gradeId != "" && courseId != "") {
			$.post("<%=path %>/score/gradeByPager",
				$("#checkForm").serialize(),
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows);  //动态取数据
						$("#checkWin").window("close"); // 关闭指定的窗口
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择班级和成绩", "error");
		}
	}
	
	function searchChecking1() { // 根据条件查询班级的考试信息
		var gradeId = document.getElementById("gradeId1").value;
		var courseId = document.getElementById("courseId1").value;
		if (gradeId != "" && courseId != "") {
			$.get("<%=path %>/score/gradeByPager?gradeId=" + gradeId + "&courseId=" + courseId,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows);  //动态取数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择班级和课程", "error");
		}
	}
	
	function showName(value) {
		return value.name;
	}
	
	function importScore() {
		alert("待完成，学生成绩的导入");
	}
	
	
</script>
</head>
<body style="height: 100%;">
	
	<!-- 选择查询条件 -->
	<div id="checkWin" class="easyui-window" title="选择查询条件" data-options="iconCls:'icon-search', closed:false" style="width:400px;height:200px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="checkForm">
		    	<input type="hidden" id="gradeId" name="gradeId" />
		    	<input type="hidden" id="courseId" name="courseId" />
		    	<table>
		    		<tr>
		    			<td>班级:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="gradeName" name="gradeName" data-options="required:true, 'disabled':true"></input>
							<a href="javascript:;" onclick="showCheckGrade();">选择班级</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>课程:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="courseName" name="courseName" data-options="required:true, 'disabled':true"></input>
							<a href="javascript:;" onclick="showCheckCourse();">选择课程</a>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="searchChecking();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
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
				toolbar:'#tb'" style="height:85%;">
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
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="checkGrade();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeGradeWin()">取消</a>
		</div>
	</div>
	
	<!-- 选择课程的窗口 -->
	<div id="courseWin" class="easyui-window" title="选择课程" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:300px;">
		<!-- 选择课程的DataGrid -->
		<table id="courses" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb2'" style="height:85%;">
			<thead>
				<tr>
					<th data-options="field:'courseId',checkbox:true">编号</th>
					<th data-options="field:'name',width:100">课程名称</th>
					<th data-options="field:'des',width:100">课程描述</th>
					<th data-options="field:'term',width:60">学期</th>
					<th data-options="field:'totalHour',width:60">总课时数</th>
					<th data-options="field:'courseOrder',width:60">课程顺序</th>
					<th data-options="field:'status',width:60" formatter="status">状态</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="checkCourse();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeCourseWin()">取消</a>
		</div>
	</div>
	
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			collapsible:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb1'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'scoreId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:100" formatter="showName">学生姓名</th>
				<th data-options="field:'course',width:100" formatter="showName">课程</th>
				<th data-options="field:'score',width:80">成绩</th>
				<th data-options="field:'testDay',width:120" formatter="formatterDate">考试时间</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb1" style="padding:5px;height:auto">
		<div>
			<input type="hidden" id="gradeId1" name="gradeId" />
			<input type="hidden" id="courseId1" name="courseId" />
			选择班级：
			<input class="easyui-textbox" type="text" id="gradeName1" name="gradeName" data-options="required:true, 'disabled':true"></input>
			<a href="javascript:;" onclick="showCheckGrade();">选择班级</a>
			选择课程：
			<input class="easyui-textbox" type="text" id="courseName1" name="courseName" data-options="required:true, 'disabled':true"></input>
			<a href="javascript:;" onclick="showCheckCourse();">选择课程</a>
	    	<a href="javascript:;" onclick="searchChecking1();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
	
	
	
	

	
	
</body>
</html>