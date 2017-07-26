<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生考勤管理</title>
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
		$("#month").combobox('setValue', 1);
	}
	
	function showCheckGrade() { 
		$("#gradeWin").window("open"); // 打开班级窗口
		$("#grades").datagrid({url:'<%=path %>/grade/pager'});
	}
	
	function closeGradeWin() { // 关闭班级窗口
		$("#gradeWin").window("close");
	}

	function stuName(value, row, index) { // 学生姓名
		return value.name;
	}

	function status(value, row, index) { // 状态
		if (value == 1) {
			return "可用";
		}
		return "不可用";
		
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
	
	function searchChecking() { // 根据表单内容查找班级对应的考勤信息
		var gradeId = document.getElementById("gradeId").value;
		var month = $('#month').combobox('getValue');
		$("#month1").combobox('setValue', month);
		if (gradeId != "" && month != '') {
			$.post("<%=path %>/sc/gradeByPager",
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
			$.messager.alert("提示", "请选择班级和月份", "error");
		}
	}
	
	function searchChecking1() { // 根据条件查询班级的考勤信息
		var gradeId = document.getElementById("gradeId1").value;
		var month = $('#month1').combobox('getValue');
		if (gradeId != "") {
			$.get("<%=path %>/sc/gradeByPager?gradeId=" + gradeId + "&month=" + month,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows);  //动态取数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
		} else {
			$.messager.alert("提示", "请选择班级", "error");
		}
	}
	
	
</script>
</head>
<body style="height: 100%;">
	
	<!-- 选择查询条件 -->
	<div id="checkWin" class="easyui-window" title="选择查询条件" data-options="iconCls:'icon-search', closed:false" style="width:400px;height:200px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="checkForm">
		    	<input type="hidden" id="gradeId" name="gradeId" />
		    	<table>
		    		<tr>
		    			<td>班级:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" id="gradeName" name="gradeName" data-options="required:true, 'disabled':true"></input>
							<a href="javascript:;" onclick="showCheckGrade();">选择班级</a>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>月份:</td>
		    			<td>
		    				<select class="easyui-combobox" name="month" id="month" style="width:100px;" data-options="editable:false">
	    						<option value="1">1月</option>
	    						<option value="2">2月</option>
	    						<option value="3">3月</option>
	    						<option value="4">4月</option>
	    						<option value="5">5月</option>
	    						<option value="6">6月</option>
	    						<option value="7">7月</option>
	    						<option value="8">8月</option>
	    						<option value="9">9月</option>
	    						<option value="10">10月</option>
	    						<option value="11">11月</option>
	    						<option value="12">12月</option>
	    					</select>
	    					<!-- <input class="easyui-combobox" id="month" name="month" data-options="required:true,
									data:[
										{'id':1,
										'text':'1月'},
										{'id':2,
										'text':'2月'},
										{'id':3,
										'text':'3月'},
										{'id':4,
										'text':'4月'},
										{'id':5,
										'text':'5月'},
										{'id':6,
										'text':'6月'},
										{'id':7,
										'text':'7月'},
										{'id':8,
										'text':'8月'},
										{'id':9,
										'text':'9月'},
										{'id':10,
										'text':'10月'},
										{'id':11,
										'text':'11月'},
										{'id':12,
										'text':'12月'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									" /> -->
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
				<th data-options="field:'stuCheckingId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:100" formatter="stuName">学生姓名</th>
				<th data-options="field:'checkingDay',width:100" formatter="formatterDate">考勤日期</th>
				<th data-options="field:'checking1',width:100">上午</th>
				<th data-options="field:'checking2',width:100">下午</th>
				<th data-options="field:'checking3',width:100">晚上</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb1" style="padding:5px;height:auto">
		<div>
			<input type="hidden" id="gradeId1" name="gradeId" />
			选择班级：
			<input class="easyui-textbox" type="text" id="gradeName1" name="gradeName" data-options="required:true, 'disabled':true"></input>
			<a href="javascript:;" onclick="showCheckGrade();">选择班级</a>
			月份:
			<select class="easyui-combobox" name="month" id="month1" style="width:100px;">
	    		<option value="1">1月</option>
	    		<option value="2">2月</option>
	    		<option value="3">3月</option>
	    		<option value="4">4月</option>
	    		<option value="5">5月</option>
	    		<option value="6">6月</option>
	    		<option value="7">7月</option>
	    		<option value="8">8月</option>
	    		<option value="9">9月</option>
	    		<option value="10">10月</option>
	    		<option value="11">11月</option>
	    		<option value="12">12月</option>
	    	</select>
	    	<a href="javascript:;" onclick="searchChecking1();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
	
	
	
	

	
	
</body>
</html>