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
<title>学生端考勤管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>

<script>

	$(function() {
		setPagination("list");
		$('#file').filebox({buttonAlign:'left'});
	});
	
	function stuName(value, row, index) { // 学生姓名
		return value.name;
	}

	function searchChecking() { // 根据条件查询班级的考勤信息
		var month = $('#month').combobox('getValue');
		$.get("<%=path %>/sc/pagerByStu?month=" + month,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
						// $("#list").datagrid("loadData", data.rows);  //动态取数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
				}, "json");
	}
	
	function importData() {
		var file = $("#file").filebox('getValue');
		if (file != null && file != "") {
			 // 判断是否是Excel格式
            var reg = /^.*\.(?:xls|xlsx)$/i;//文件名可以带空格
            if (reg.test(file)) {
            	<%-- $.post("<%=path %>/sc/importData",
    					$("#importForm").serialize(), // Form表单序列化
    					function(data) {
    						if (data.result.result == "success") { //表示添加成功
    							$.messager.alert("提示", data.result.message, "info");
    						} else if (data.result.result == "fail") {
    							$.messager.alert("提示", data.result.message, "error");
    						}
    					}, "json"); --%>
    			$('#importForm').ajaxSubmit({
                		url: '<%=path%>/sc/importData', // 文件上传的url
                 		type: 'post', // 提交方式
                        dataType: 'json', // 返回的数据类型
                        beforeSend: function () { // 提交前触发的方法
                        	/*  $("#addBtn").text("正在添加...");
                        	$("#addBtn").attr("disabled", "true"); */
                        },
                        success: function (data) { // 提交后返回的结果
                        	if (data.result.result == "success") {
                            	/* $("#addWin").window("close");
                            	dataGridReload("list");
                              	$("#addForm").form("clear"); */
                              	$("#list").datagrid("reload"); // 更新表格
                               	$.messager.alert("提示", data.result.message, "info");
                             } else {
                                $.messager.alert("提示", data.result.message, "error");
                             }
                       },
                        complete: function () { // 上传完毕之后触发的方法
                        	/*  $("#addBtn").text("确认");
                           	$("#addBtn").removeAttr("disabled"); */
                       	}
    			 });
            } else {
            	$.messager.alert("提示", "请上传Excel格式的文件", "error");
            }
			
		} else {
			$.messager.alert("提示", "请选择你要导入的文件", "error");
		}
	}
	
	
	
</script>
</head>
<body style="height: 100%;">
	
	<!-- Datagrid表格 -->
	<table id="list" class="easyui-datagrid"
			data-options="singleSelect:true,
			url:'<%=path %>/sc/pagerByStu',
			method:'get',
			collapsible:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,
			toolbar:'#tb1'" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'stuCheckingId',checkbox:true">编号</th>
				<th data-options="field:'stu',width:80" formatter="stuName">学生姓名</th>
				<th data-options="field:'checkingDay',width:160" formatter="formatterDate">考勤日期</th>
				<th data-options="field:'checking1',width:100">上午</th>
				<th data-options="field:'checking2',width:100">下午</th>
				<th data-options="field:'checking3',width:100">晚上</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tb1" style="padding:5px;height:auto">
		<s:if test="#session.stu.role == '学习委员'">
			<div style="margin-bottom:5px">
				<form id="importForm" enctype="multipart/form-data">
					<a href="<%=path %>/sc/exportData?number=1" class="easyui-linkbutton" iconCls="icon-undo" plain="true">导出考勤</a>
					<a href="javascript:;" onclick="importData()" class="easyui-linkbutton" iconCls="icon-redo" plain="true">导入考勤</a>
					<input class="easyui-filebox" id="file" name="file" data-options="prompt:'请选择文件',buttonText:'选择文件'" style="width:20%">
				</form>
			</div>
		</s:if>
		<div>
			月份:
			<select class="easyui-combobox" name="month" id="month" style="width:100px;">
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
	    	<a href="javascript:;" onclick="searchChecking();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	
	
	
	
	

	
	
</body>
</html>