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
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	var jsonStr1 = '{"esi.empId":"_empid","esi.deptName":"_deptname","esi.roleName":"_rolename","esi.name":"_name","esi.idCard":"_idcard","esi.gender":"_gender","esi.bankName":"_bankname","esi.accountName":"_accountname","esi.accountNo":"_accountno","esi.basicSalary":"_basicsalary","esi.jobSalary":"_jobsalary"},"esi.salaryinfoId":"_salaryinfoid"';
	
	var jsonStr2 = '{"si.salaryinfoId":"_salaryinfoid","si.emp.empId":"_empid","si.basicSalary":"_basicsalary","si.jobSalary":"_jobsalary"}';
	
	$(function() {
		setPagination("list");
	});
	
	function addCloseWin() {
		$("#addwin").window("close");
	}
	function editCloseWin() {
		$("#editwin").window("close");
	}
	
	function addSalaryInfoWin() {//添加工资基本信息
		$("#addForm").form("clear");//刷新数据
		$("#addwin").window("open");
	}
	
	function addSalaryInfo() {//添加工资基本信息是否成功
		if($("#addForm").form("validate")) {
			$.post("<%=path %>/mysi/save",
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
	
	function editSalaryInfoWin1() {//修改工资基本信息
		// var row = $("#list").datagrid("getSelected");// 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if(rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的员工工资", "info");
		} else if(rows.length == 1) {
			var row = rows[0];
			if(row) {// 有选中的行
				var jsonStr3 = jsonStr1.replace("_empid",row.empId).replace("_deptname",row.deptName).replace("_rolename",row.roleName).replace("_name",row.name).replace("_idcard",row.idCard).replace("_gender",row.gender).replace("_bankname",row.bankName).replace("_accountname",row.accountName).replace("_accountno",row.accountNo).replace("_basicsalary",row.baSicsalary).replace("_jobsalary",row.jobSalary).replace("_salaryinfoid",row.salaryinfoId);
				$("#editempId").val(row.empId);
				$("#editsalaryinfoId").val(row.salaryinfoId);
				$("#editbankName").textbox("setValue",row.bankName);
				$("#editaccountNo").textbox("setValue",row.accountNo);
				$("#editbaSicsalary").textbox("setValue",row.basicSalary);
				$("#editjobSalary").textbox("setValue",row.jobSalary);
				$("#editwin2").window("open");
			}
		} else if(rows.length == 0) {
			$.messager.alert("提示","请先选择需要修改的员工工资", "info");
		}
	}
	
	function editSalaryInfo() {//修改工资基本信息是否成功
		if($("#editForm2").form("validate")) {
			$.post("<%=path %>/mysi/update",
					$("#editForm2").serialize(),
					function(data) {
						if(data.result.result == "success") {
							$("#editwin2").window("close");//关闭指定的窗口
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
	
	function showEmp(){
		$("#EmpWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
	}

	function addEmp(){
		var row = $("#emp").datagrid("getSelected"); // 获取首个选中的数据
		if(row){
			$("#addEmpName").textbox('setValue', row.name); //赋值
			$("#EmpId").val(row.empId); //赋值
			$.get("<%=path %>/mysi/query_salaryinfo?id='"+row.empId+"'",
					function(data) {
						if(data.rowss != "") {
							$.messager.alert("提示", "您已经添加了该员工的基本工资", "info");
							
						}
				}, "json");
			$("#EmpWin").window("close"); 
		}
	}
	
	function gender(value, row, index) {
		if(row.gender == "female"){
			return "女"
		}else if(row.gender == "male"){
			return "男"
		}
	}
	
	function doSearch(value,name){
		$("#list").datagrid({
			toolbar:'#tb',
			url:'<%=path %>/mysi/query_name?'+name+'='+value,
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:10,
			columns : [ [ {  
                field : 'empId',checkbox:true,  
                width : 80,  
                title : '员工编号'  
            }, {  
                field : 'name',  
                width : 80,  
                title : '员工姓名'  
            }, {  
                field : 'gender',  
                width : 80,  
                title : '员工性别'
            }, {  
                field : 'roleName', 
                width : 80,
                title : '员工角色',
            }, {  
                field : 'deptName', 
                width : 80,
                title : '员工部门',
            }, {  
                field : 'idCard', 
                width : 120,
                title : '身份证号',
            }, {  
                field : 'bankName', 
                width : 80,
                title : '开户行名称',
            }, {  
                field : 'accountName', 
                width : 80,
                title : '银行卡姓名',
            }, {  
                field : 'accountNo', 
                width : 150,
                title : '银行账号',
            }, {  
                field : 'basicSalary', 
                width : 80,
                title : '基本工资',
            }, {  
                field : 'jobSalary', 
                width : 80,
                title : '岗位工资',
            } ] ]
		});
	}
	
</script>
</head>
<body>
	<table id="list" class="easyui-datagrid" 
			data-options="toolbar:'#tb',
			url:'<%=path %>/mysi/query_page',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,<!-- 分页按钮是否显示固定在底部 -->
			<!-- fitColumns:true,<!-- 表格面积是否全部占满 -->-->
			rownumbers:true,<!-- 是否显示序列号 -->
			autoRowHeight:true,
			pagination:true,
			pageSize:10">
		<thead>
			<tr>
				<th data-options="field:'empId',checkbox:true">员工编号</th>
				<th data-options="field:'name'" width="80">员工姓名</th>
				<th data-options="field:'gender'" width="80" formatter="gender">员工性别</th>
				<th data-options="field:'roleName'" width="80">员工角色</th>
				<th data-options="field:'deptName'" width="80">员工部门</th>
				<th data-options="field:'idCard'" width="120">身份证号</th>
				<th data-options="field:'bankName'" width="80">开户行名称</th>
				<th data-options="field:'accountName'" width="80">银行卡姓名</th>
				<th data-options="field:'accountNo'" width="150">银行账号</th>
				<th data-options="field:'basicSalary'" width="80">基本工资</th>
				<th data-options="field:'jobSalary'" width="80">岗位工资</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto;">
		<div style="margin-buttom:5px;">
			<a href="javascript:;" onclick="addSalaryInfoWin()" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="editSalaryInfoWin1()" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			<input id="search" class="easyui-searchbox" data-options="prompt:'搜索',menu:'#mm',searcher:doSearch" style="width:300px"></input>
			<div id="mm">
				<div data-options="name:'name'">员工姓名</div>
				<div data-options="name:'bankName'">开户银行</div>
				<div data-options="name:'accountNo'">银行账号</div>
				<div data-options="name:'jobSalary'">岗位工资</div>
			</div>
		</div>
	</div>
	
	<div id="addwin" class="easyui-window" title="添加" data-options="closed:true" style="width:310px;height:150px;">
		<form id="addForm">
			<input type="hidden" name="mysi.salaryinfoId" />
			<input type="hidden" name="mysi.emp.empId" id="EmpId" />
			<table>
				<tr>
					<td width="60px">员工姓名: </td>
					<td>
						<input class="easyui-textbox" type="text" id="addEmpName" name="mysi.emp.name" data-options="required:true, 'disabled':true"></input>
		    			<a href="javascript:;"  onclick="showEmp();">选择员工</a>
					</td>
				</tr>
				<tr>
					<td>基本工资: </td>
					<td><input class="easyui-numberbox easyui-validatebox" name="mysi.basicSalary" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>岗位工资: </td>
					<td><input class="easyui-numberbox easyui-validatebox" name="mysi.jobSalary" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addSalaryInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editwin2" class="easyui-window" title="修改" data-options="closed:true" style="width:300px;height:270;">
		<form id="editForm2">
			<input type="hidden" name="mysi.salaryinfoId" id="editsalaryinfoId" />
			<input type="hidden" name="mysi.emp.empId" id="editempId" />
			<table>
				<tr>
					<td width="70px">开户行名称: </td>
					<td><input class="easyui-validatebox easyui-textbox" id="editbankName" name="mysi.emp.bankName" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>银行账号: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="editaccountNo" name="mysi.emp.accountNo" data-options="required:true,validType:'length[19,19]'" /></td>
				</tr>
				<tr>
					<td>基本工资: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="editbaSicsalary" name="mysi.basicSalary" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>岗位工资: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="editjobSalary" name="mysi.jobSalary" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editSalaryInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
		
		<div id="EmpWin" class="easyui-window" title="选择员工" data-options="closed:true" style="width:488px;height:380;background-color:lightBlue;">
			<table id="emp" class="easyui-datagrid"
				data-options="toolbar:'#tba',singleSelect:true,
				url:'<%=path %>/emp/empPage',
				singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:10">
				<thead>
					<tr>
						<th data-options="field:'name',width:100">姓名</th>
					</tr>
				</thead>
			</table>
			<div style="text-align:right;padding:5px">
				<a href="javascript:;" onclick="addEmp();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
			</div>
		</div>
	</div>
</body>
</html>