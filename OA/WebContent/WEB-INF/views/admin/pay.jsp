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

	var jsonStr = '{"pi.payId":"_payid","pi.empName":"_empname","pi.payTypeName":"_paytypename","pi.payDay":"_payday","pi.des":"_des","pi.pay":"_pay","pi.toName":"_toname","pi.toPhone":"_tophone","pi.payTypeStatus":"_paytypestatus","pi.payTypeId":"_paytypeid"}';
	
	var jsonStr2 = '{"pay.payId":"_payid","pay.emp.empId":"_empid","pay.pt.payTypeId":"_paytypeid","pay.payDay":"_payday","pay.des":"_des","pay.pay":"_pay","pay.toName":"_toname","pay.toPhone":"_tophone"}';
	var payCount = 0;
	$(function() {
		payCount = 0;
		setPagination("list");
	});
	
	function addCloseWin() {
		$("#addwin").window("close");
	}
	function editCloseWin() {
		$("#editwin").window("close");
	}
	
	function addPayInfoWin(value1,value2) {//添加
		$("#addForm").form("clear");//刷新数据
		$("#addempId").val(value1);
		$("#addempName").textbox("setValue",value2);
		$("#addwin").window("open");
	}
	
	function addPayInfo() {//添加是否成功
		if($("#addForm").form("validate")) {
			$.post("<%=path %>/pay/save",
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
	
	function editPayInfoWin(value1,value2) {//修改
		// var row = $("#list").datagrid("getSelected");// 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if(rows.length > 1) {
			$.messager.alert("提示","请选择一个需要修改的支出类别", "info");
		} else if(rows.length == 1) {
			var row = rows[0];
			if(row) {// 有选中的行
				var jsonStr3 = jsonStr.replace("_payid",row.payId).replace("_empname",row.empName).replace("_paytypename",row.payTypeName).replace("_payday",row.payDay).replace("_des",row.des).replace("_pay",row.pay).replace("_toname",row.toName).replace("_tophone",row.toPhone).replace("_paytypestatus",row.payTypeStatus).replace("_paytypeid",row.payTypeId);
				// var jsonStr4 = jsonStr2.replace("_payid",row.payId).replace("_empid",row.emp.empId).replace("_paytypeid",row.pt.payTypeId).replace("_payday",row.payDay).replace("_des",row.des).replace("_pay",row.pay).replace("_toname",row.toName).replace("_tophone",row.toPhone);
				// $("#editForm").form("load", JSON.parse(jsonStr3));
				$("#editpayId").val(row.payId);
				$("#editpayDay").datetimebox("setValue",row.payDay);
				$("#editpayTypeStatus").val(row.payTypeStatus);
				$("#editempId").val(value1);
				$("#editempName").textbox("setValue",value2);
				$("#editpayTypeName").combobox("setValue",row.payTypeName);
				$("#editdes").textbox("setValue",row.des);
				$("#editpay").textbox("setValue",row.pay);
				$("#edittoName").textbox("setValue",row.toName);
				$("#edittoPhone").textbox("setValue",row.toPhone);
				$.get("<%=path%>/paytype/query_type?ptId="+row.payTypeId,function(data){
					$("#editpayTypeId").combobox("loadData", data.cobox);
				}, "json");
				$("#editwin").window("open");
			}
		} else if(rows.length == 0) {
			$.messager.alert("提示","请先选择需要修改的支出信息", "info");
		}
	}
	
	function editPayInfo() {//修改是否成功
		if($("#editForm").form("validate")) {
			$.post("<%=path %>/pay/update",
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
		payCount = 0;
		$("#payName").val('搜索支出');
		if (value != null && value != "") {
			$.get("<%=path %>/pay/query_name?" + name + "=" + value,
					function(data) {
						if (data.result.result == "success") {
							$("#list").datagrid("loadData", data.rows);
						} else {
							$.messager.alert("提示", data.result.message, "info");
							$("#payCount").val(payCount);
							$("#list").datagrid("loadData", data.rows);
						}
			}, "json");
		} else {
			$.messager.alert("提示", "请输入查询条件", "info");
		}
	}
	
	function queryDesc(){
		payCount = 0;
		$("#list").datagrid({
			url:'<%=path %>/pay/query_desc'
			});
		$("#payName").val('总支出');
	}
	
	function queryNoDesc(){
		payCount = 0;
		$("#list").datagrid({
			url:'<%=path %>/pay/query_nodesc'
		});
		$("#payName").val('总支出');
	}
	
	function queryDay(){
		payCount = 0;
		<%-- $("#list").datagrid({
			toolbar:'#tb',
			url:'<%=path %>/pay/query_day',
			collapsible:true,
			method:'get',
			singleSelect:false,
			fit:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:10,
			columns : [ [ {  
                field : 'payId',checkbox:true,  
                width : 80,  
                title : '支出编号'  
            }, {  
                field : 'empName',  
                width : 80,  
                title : '员工姓名'  
            }, {  
                field : 'payTypeName',  
                width : 80,  
                title : '支出类型'
            }, {  
                field : 'payDay', 
                width : 80,
                title : '支出时间',
            }, {  
                field : 'des', 
                width : 80,
                title : '描述',
            }, {  
                field : 'pay', 
                width : 80,
                title : '支出金额',
            }, {  
                field : 'toName', 
                width : 80,
                title : '收款人姓名',
            }, {  
                field : 'toPhone', 
                width : 80,
                title : '收款人号码',
            } ] ]
		}); --%>
		$("#list").datagrid({url:'<%=path %>/pay/query_day'});
		$("#payName").val('本周支出');
	}
	
	function queryMonth() {
		payCount = 0;
		$("#list").datagrid({url:'<%=path %>/pay/query_month'});
		$("#payName").val('本月支出');
	}
	
	$(function(){
		$.get("<%=path%>/paytype/query_type",function(data){
			$("#combobox").combobox("loadData", data.cobox);
		}, "json");
	});
	$(function(){
		$.get("<%=path%>/emp/query_type",function(data){
			$("#empcombobox").combobox("loadData", data.cobox);
		}, "json");
	});
	
	
	function empName(value, row, index) {
		payCount += row.pay;
		$("#payCount").val(	payCount);
		return value;
	}
	
</script>
</head>
<body>
	<table id="list" class="easyui-datagrid" 
			data-options="toolbar:'#tb',
			url:'<%=path %>/pay/query_page',
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
				<th data-options="field:'payId',checkbox:true">类别编号</th>
				<th data-options="field:'empName'" width="80" formatter="empName">员工姓名</th>
				<th data-options="field:'payTypeName'" width="80">支出类型</th>
				<th data-options="field:'payDay'" width="80" formatter="formatterDate">支出时间</th>
				<th data-options="field:'des'" width="80">描述</th>
				<th data-options="field:'pay'" width="80">支出金额</th>
				<th data-options="field:'toName'" width="80">收款人姓名</th>
				<th data-options="field:'toPhone'" width="80">收款人号码</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto;">
		<div style="margin-buttom:5px;">
			<a href="javascript:;" onclick="addPayInfoWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }')" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="editPayInfoWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }')" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			<input id="search" class="easyui-searchbox" data-options="prompt:'搜索',menu:'#mm',searcher:doSearch" style="width:300px"></input>
			<div id="mm">
				<div data-options="name:'toName'">收款人姓名</div>
				<div data-options="name:'empPhone'">收款人号码</div>
				<div data-options="name:'payTypeName'">支出类型</div>
				<div data-options="name:'payName'">支出金额</div>
			</div>
			<a href="javascript:;" onclick="queryNoDesc()" class="easyui-linkbutton" iconCls="icon-search">升序</a>
			<a href="javascript:;" onclick="queryDesc()" class="easyui-linkbutton" iconCls="icon-search">降序</a>
			<a href="javascript:;" onclick="queryDay()" class="easyui-linkbutton" iconCls="icon-search">本周记录</a>
			<a href="javascript:;" onclick="queryMonth()" class="easyui-linkbutton" iconCls="icon-search">本月记录</a>	
			<input type="text" disabled="true" style="border:none; background-color:transparent;width:60px;font-size:15px;font-weight:bold;" id="payName" value="总支出 " />
			<input type="text" disabled="true" style="border:none; background-color:transparent;font-size:15px;font-weight:bold;" id="payCount" />
		</div>
	</div>
	
	<div id="addwin" class="easyui-window" title="添加" data-options="closed:true" style="width:300px;height:340px;">
		<form id="addForm">
			<input type="hidden" id="addempId" name="pay.emp.empId" />
			<table>
				<tr>
					<td width="70px">员工姓名: </td>
					<td><input class="easyui-textbox" type="text" id="addempName" data-options="required:true, 'disabled':true" ></input></td>
					<!-- <td>员工姓名: </td>
					<td><input id="empcombobox" class="easyui-combobox" name="pay.emp.empId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/></td> -->
				</tr>
				<tr>
					<td>支出类型: </td>
					<td><input id="combobox" class="easyui-combobox" name="pay.pt.payTypeId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/></td>
				</tr>
				<tr>
					<td>支出时间: </td>
					<td><input class="easyui-datetimebox" name="pay.payDay" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td>描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" name="pay.des" /></td>
				</tr>
				<tr>
					<td>支出金额: </td>
					<td><input class="easyui-numberbox easyui-validatebox" name="pay.pay" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>收款人姓名: </td>
					<td><input class="easyui-validatebox easyui-textbox" name="pay.toName" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>收款人号码: </td>
					<td><input class="easyui-numberbox easyui-validatebox" name="pay.toPhone" data-options="required:true,validType:'length[11,11]'" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="addPayInfo()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editwin" class="easyui-window" title="修改" data-options="closed:true" style="width:300px;height:340px;">
		<form id="editForm">
			<input type="hidden" id="editpayId" name="pay.payId" />
			<input type="hidden" id="editempId" name="pay.emp.empId" />
			<table>
				<tr>
					<td width="70px">员工姓名: </td>
					<td><input class="easyui-textbox" type="text" id="editempName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<!-- <tr>
					<td>员工姓名: </td>
					<td><input class="easyui-combobox combobox2" id="editempName"  name="pay.emp.empId" /></td>
				</tr> -->
				<tr>
					<td>支出类型: </td>
					<td><input class="easyui-combobox" id="editpayTypeId" name="pay.pt.payTypeId" data-options="required:true,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:'false'
					"/></td>
				</tr>
				<tr>
					<td>支出时间: </td>
					<td><input class="easyui-datetimebox" id="editpayDay" name="pay.payDay" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td>描述: </td>
					<td><input class="easyui-textbox" data-options="multiline:true" style="height:100px;" id="editdes" name="pay.des" /></td>
				</tr>
				<tr>
					<td>支出金额: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="editpay" name="pay.pay" data-options="required:true, precision:2" /></td>
				</tr>
				<tr>
					<td>收款人姓名: </td>
					<td><input class="easyui-validatebox easyui-textbox" id="edittoName" name="pay.toName" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>收款人号码: </td>
					<td><input class="easyui-numberbox easyui-validatebox" id="edittoPhone" name="pay.toPhone" data-options="required:true,validType:'length[11,11]'" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:;" onclick="editPayInfo()" class="easyui-linkbutton" icon="icon-ok">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>