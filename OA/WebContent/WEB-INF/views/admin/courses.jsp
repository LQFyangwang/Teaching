<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	
	var jsonStr = '{"course.courseId":"_courseId","course.name":"_name","course.totalHour":"_totalHour","course.des":"_des","course.term":"_term","course.courseOrder":"_courseOrder","course.status":"_status"}';
	
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
						$("#status").combobox('setValue', 1);
					} else if (method == "update") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
	}
	
	function showAddCourse() {
		authority('com.ht.action.CourseAction.add', "", "add");
	}
	
	function addCourse() {
		if ($("#addForm").form("validate")) {
			$.post("<%=path %>/course/add",
				$("#addForm").serialize(),
				function (data) {
					if (data.result.result == "success") {
						$("#addWin").window("close"); // 关闭指定的窗口
						$("#list").datagrid("reload"); // 重新加载指定数据网格数据
						$.messager.alert("提示", data.result.message, "info");
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "info");
					}
			}, "json"
			);
		} else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}
	}
	
	function showEditCourseWin() {
		// var row = $("#list").datagrid("getSelected"); // 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if (rows.length > 1) {
			$.messager.alert("提示", "请先选择一个需要修改的课程", "info");
		} else if (rows.length == 1) {
			var row = rows[0];
			if (row) { // 有选中的行
				// var jsonStr = '{"product.id":"' + row.id + '","product.title":"' + row.title +'","product.price":"' + row.price +'","product.des":"' + row.des +'"}';
				var jsonStr1 = jsonStr.replace("_courseId", row.courseId).replace("_name", row.name).replace("_des", row.des).replace("_status", row.status).replace("_totalHour",row.totalHour).replace("_term",row.term).replace("_courseOrder",row.courseOrder);
				authority('com.ht.action.CourseAction.update', jsonStr1, "update");
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示", "请先选择一个需要修改的课程", "info");
		}
	}
	
/* 	function showEditProductWin1(index) {
		 var row = $('#list').datagrid('getData').rows[index];
		 if (row) { // 有选中的行
			// var jsonStr = '{"product.id":"' + row.id + '","product.title":"' + row.title +'","product.price":"' + row.price +'","product.des":"' + row.des +'"}';
			var jsonStr1 = jsonStr.replace("_id", row.id).replace("_title", row.title).replace("_price", row.price).replace("_des", row.des);
			$("#editForm").form("load", JSON.parse(jsonStr1));
		 	$("#editWin").window("open");
		}
	} */
	
	function editCourse() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/course/update",
					$("#editForm").serialize(),
					function (data) {
						if (data.result.result == "success") {
							$("#editWin").window("close"); // 关闭指定的窗口
							$("#list").datagrid("reload"); // 重新加载指定数据网格数据
							$.messager.alert("提示", data.result.message, "info");
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
				}, "json"
				);
		} else {
			$.messager.alert("提示", "请输入正确的表单数据", "info");
		}
	}
	
<%--	function removeProduct() {
		var row = $("#list").datagrid("getSelected");
			if (row) {
				$.messager.confirm("提示", "确定删除吗？", function (r) {
					if (r) { // 点击了确定按键
						$.get("<%=path %>/course/del?id=" + row.id, function (data) {
							$("#list").datagrid("reload");
						}, "json");
					}
				})
		} else {
			$.messager.alert("提示", "请先选择需要删除的商品", "info");
		}
	}
	--%>
	
	function formatterStatus(value, row, index) {
		if (row.status == 1) {
			return "可用"
		} else if (row.status == 0) {
			return "不可用";
		}
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.courseId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.courseId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/course/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/course/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function showDlg() {
		$("#dlg").dialog("open"); // 打开对话框   close关闭对话框 
	}
	
	function queryByCourseName(name, value) {
		$.get("<%=path %>/course/fuzzySearch?name=" + name + "&value=" + value, 
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
		}, "json")
	}
	
	function fuzzySearch(value,name){
		if (value != ""){
			if (name == "courseName"){
				queryByCourseName(name, value);
			}
		} else {
			$.messager.alert("提示", "请输入名称", "error");
		}
	}
	
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid" 
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/course/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,toolbar:'#tb'" style="height: 100%;">
		<thead>
			<tr>
				<th data-options="field:'courseId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">课程名称</th>
				<th data-options="field:'des',width:80">课程描述</th>
				<th data-options="field:'term',width:80">学期</th>
				<th data-options="field:'totalHour',width:80">总课时数</th>
				<th data-options="field:'courseOrder',width:80">课程顺序</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">课程状态</th>
				<th data-options="field:'operation',width:120" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddCourse();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditCourseWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
		
		<div>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
				<div data-options="name:'courseName',iconCls:'icon-hamburg-customers'">课程名称</div>
			</div>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加课程" data-options="closed:true" style="width:500px;height:300px;padding:15px;">
		<form id="addForm">
			<table>
				<tr>
					<td width="100px;">课程名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="course.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>总课时数</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.totalHour" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>学期</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.term" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>课程顺序</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.courseOrder" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="course.status" data-options="required:true,
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
									'disabled':true
									" />
					</td>
				</tr>
				<tr>
					<td>课程描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="course.des" style="height:70px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addCourse();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改课程" data-options="closed:true" style="width:500px;height:300px;">
		<form id="editForm">
			<input type="hidden" name="course.courseId" />
			<input type="hidden" name="course.status" />
			<table>
				<tr>
					<td width="100px;">课程名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="course.name" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>总课时数</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.totalHour" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>学期</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.term" data-options="required:true,precision:0"/></td>
				</tr>
				<tr>
					<td>课程顺序</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="course.courseOrder" data-options="required:true,precision:0"/></td>
				</tr>
<!-- 				<tr>
					<td>状态</td>
					<td>
						<input class="easyui-combobox" id="status" name="course.status" data-options="required:true,'editable':false,
									data:[
										{'id':1,
										'text':'可用'},
										{'id':0,
										'text':'不可用'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									disabled:true
									" />
					</td>
				</tr> -->
				<tr>
					<td>课程描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" name="course.des" style="height:70px;"/></td>
				</tr>
				<tr>	
					<td></td>
					<td>
						<a href="javascript:;" onclick="editCourse();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>