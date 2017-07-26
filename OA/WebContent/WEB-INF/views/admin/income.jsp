<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	var incomeCount = 0;
	var jsonStr = '{"ii.incomeId":"_incomeid","ii.empName":"_empname","ii.incomeTypeName":"_incometypename","ii.incomeDay":"_incomeday","ii.des":"_des","ii.income":"_income","ii.stuName":"_stuname","ii.incomeTypeStatus":"_incometypestatus"}';
	
	var jsonStr2 = '{"income.incomeId":"_incomeid","income.empId":"_empid","income.incomeTypeId":"_incometypeid","income.incomeDay":"_incomeday","income.des":"_des","income.income":"_income","income.stuName":"_stuname"}';

	$(function() {
		setPagination("list");
	});
	
	function addCloseWin() {
		$("#addwin").window("close");
	}
	function editCloseWin() {
		$("#editwin").window("close");
	}
	
	function addIncomeInfoWin(value,value2) {//添加
		$("#addForm").form("clear");//刷新数据
		$("#addempId").val(value);
		$("#addempName").textbox("setValue",value2)
		$("#addwin").window("open");
	}
	
	function addIncomeInfo() {//添加是否成功
		if($("#addForm").form("validate")) {
			$.post("<%=path %>/income/save",
				$("#addForm").serialize(),
				function(data) {
					if(data.result.result == "success") {
						$("#addwin").window("close");//关闭指定的窗口
						$("#list").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if(data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "info");
					}
			}, "json");
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
	function editIncomeInfoWin(value,value2) {//修改
		// var row = $("#list").datagrid("getSelected");// 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if(rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的支出类别", "info");
		} else if(rows.length == 1) {
			var row = rows[0];
			if(row) {// 有选中的行
				var jsonStr3 = jsonStr.replace("_incomeid",row.incomeId).replace("_empname",row.empName).replace("_incometypename",row.incomeTypeName).replace("_incomeday",row.incomeDay).replace("_des",row.des).replace("_income",row.income).replace("_toname",row.stuName).replace("_incometypestatus",row.incomeTypeStatus);
				// var jsonStr4 = jsonStr2.replace("_incomeid",row.incomeId).replace("_empid",row.empId).replace("_incometypeid",row.incomeTypeId).replace("_incomeday",row.incomeDay).replace("_des",row.des).replace("_income",row.income).replace("_stuName",row.stuname);
				$("#editincomeId").val(row.incomeId);
				$("#editincomeDay").datetimebox("setValue",row.incomeDay);
				$("#editincomeTypeStatus").val(row.incomeTypeStatus);
				$("#editempId").val(value);
				$("#editempName").textbox("setValue",value2);
				$("#editincomeTypeName").combobox("setValue",row.incomeTypeName);
				$("#editdes").textbox("setValue",row.des);
				$("#editincome").textbox("setValue",row.income);
				$("#editstuName").combobox("setValue",row.stuName);
				$.get("<%=path%>/incometype/query_type?itId="+row.incomeTypeId,function(data){
					$("#editincomeTypeId").combobox("loadData", data.cobox);
				}, "json");
				$.get("<%=path%>/stu/query_type?stuId="+row.stuId,function(data){
					$("#editstuId").combobox("loadData", data.cobox);
				}, "json");
				$("#editwin").window("open");
			}
		} else if(rows.length == 0) {
			$.messager.alert("提示","请先选择需要修改的支出类别", "info");
		}
	}
	
	function editIncomeInfo() {//修改是否成功
		if($("#editForm").form("validate")) {
			$.post("<%=path %>/income/update",
					$("#editForm").serialize(),
					function(data) {
						if(data.result.result == "success") {
							$("#editwin").window("close");//关闭指定的窗口
							$("#list").datagrid("reload"); // 重新加载指定数据网格数据
							$.messager.alert("提示", data.result.message, "info");
						} else if(data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
				}, "json");
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
	function doSearch(value,name){
		incomeCount = 0;
		$("#incomeName").val('搜索收入');
		if (value != null && value != "") {
			$.get("<%=path %>/income/query_name?" + name + "=" + value,
					function(data) {
						if (data.result.result == "success") {
							$("#list").datagrid("loadData", data.rows);
						} else {
							$.messager.alert("提示", data.result.message, "info");
							$("#incomeCount").val(payCount);
							$("#list").datagrid("loadData", data.rows);
						}
			}, "json");
		} else {
			$.messager.alert("提示", "请输入查询条件", "info");
		}
	}
	
	$(function(){
		$.get("<%=path%>/incometype/query_type",function(data){
			$("#combobox").combobox("loadData", data.cobox);
		}, "json");
	});
	
	$(function(){
		$.get("<%=path%>/emp/query_type",function(data){
			$("#empcombobox").combobox("loadData", data.cobox);
		}, "json");
	});
	
	$(function(){
		$.get("<%=path%>/stu/query_type",function(data){
			$("#stucombobox").combobox("loadData", data.cobox);
		}, "json");
	});
	
	
	function queryDay(){
		incomeCount = 0;
		$("#list").datagrid({url:'<%=path %>/income/query_day'});
		$("#incomeName").val('本周收入');
	}
	
	function queryMonth(){
		incomeCount = 0;
		$("#list").datagrid({url:'<%=path %>/income/query_month'});
		$("#incomeName").val('本月收入');
	}
	
	function empName(value, row, index) {
		 incomeCount += row.income;
		$("#incomeCount").val(incomeCount);
		return value;
	}
</script>
</head>
<body>
	<table id="list" class="easyui-datagrid" 
			data-options="toolbar:'#tb',
			url:'<%=path %>/income/query_page',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,<!-- 分页按钮是否显示固定在底部 -->
			<!-- fitColumns:true,<!-- 表格面积是否全部占满 -->-->
			rownumbers:true,<!-- 是否显示序列号 -->
			autoRowHeight:true,
			pagination:true,
			pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'incomeId',checkbox:true">类别编号</th>
				<th data-options="field:'empName'" width="80" formatter="empName">员工姓名</th>
				<th data-options="field:'incomeTypeName'" width="80">收入类型</th>
				<th data-options="field:'incomeDay'" width="80" formatter="formatterDate">收入时间</th>
				<th data-options="field:'des'" width="80">描述</th>
				<th data-options="field:'income'" width="80">收入金额</th>
				<th data-options="field:'stuName'" width="80">学生姓名</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto;">
		<div style="margin-buttom:5px;">
			<a href="javascript:;" onclick="addIncomeInfoWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }')" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="editIncomeInfoWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }')" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			<input id="search" class="easyui-searchbox" data-options="prompt:'搜索',menu:'#mm',searcher:doSearch" style="width:300px"></input>
			<div id="mm">
				<div data-options="name:'stuName'">学生姓名</div>
				<div data-options="name:'incomeTypeName'">收入类型</div>
				<div data-options="name:'incomeName'">收入金额</div>
				<div data-options="name:'des'">描述</div>
			</div>
			<a href="javascript:;" onclick="queryDay()" class="easyui-linkbutton" iconCls="icon-search">本周记录</a>
			<a href="javascript:;" onclick="queryMonth()" class="easyui-linkbutton" iconCls="icon-search">本月记录</a>	
			<input type="text" disabled="true" style="border:none; background-color:transparent;width:60px;font-size:15px;font-weight:bold;" id="incomeName" value="总收入 " />
			<input type="text" disabled="true" style="border:none; background-color:transparent;font-size:15px;font-weight:bold;" id="incomeCount" />
		</div>
	</div>
	
	<div id="addwin" class="easyui-window" title="添加" data-options="closed:true" style="width:300px;height:320px;">
		<form id="addForm">
			<input type="hidden" id="addempId" name="income.emp.empId" />
			<table>
				<tr>
					<td width="60px">员工姓名: </td>
					<td><input class="easyui-textbox" type="text" id="addempName" data-options="required:true, 'disabled':true" ></input></td>
					<!-- <td><input id="empcombobox" class="easyui-combobox" name="income.emp.empId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/></td> -->
				</tr>
				<tr>
					<td>收入类型: </td>
					<td><input id="combobox" class="easyui-combobox" name="income.it.incomeTypeId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/></td>
				</tr>
				<tr>
					<td>收入时间: </td>
					<td><input class="easyui-datetimebox" name="income.incomeDay" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td>描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" name="income.des" /></td>
				</tr>
				<tr>
					<td>收入金额: </td>
					<td><input class="easyui-numberbox easyui-validatebox" name="income.income" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>学生姓名: </td>
					<td><input id="stucombobox" class="easyui-combobox" name="income.stu.stuId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="addIncomeInfo()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editwin" class="easyui-window" title="修改" data-options="closed:true" style="width:300px;height:320px;">
		<form id="editForm">
			<input type="hidden" id="editincomeId" name="income.incomeId" />
			<input type="hidden" id="editempId" name="income.emp.empId" />
			<table>
				<tr>
					<td width="60px;">员工姓名: </td>
					<td><input class="easyui-textbox" type="text" id="editempName" data-options="required:true, 'disabled':true" ></input></td>
					<!-- <td><input class= "easyui-combobox combobox2" id="editempName"  name="income.emp.empId" /></td> -->
				</tr>
				<tr>
					<td>收入类型: </td>
					<td><input class="easyui-combobox" id="editincomeTypeId" name="income.it.incomeTypeId" data-options="required:true,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:'false'
					"/></td>
				</tr>
				<tr>
					<td>收入时间: </td>
					<td><input class="easyui-datetimebox" id="editincomeDay" name="income.incomeDay" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td>描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" id="editdes" name="income.des" /></td>
				</tr>
				<tr>
					<td>收入金额: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="editincome" name="income.income" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>学生姓名: </td>
					<td><input class="easyui-combobox" id="editstuId" name="income.stu.stuId" data-options="required:true,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:'false'
					"/></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="editIncomeInfo()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>