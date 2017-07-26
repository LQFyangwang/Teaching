<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script>

	var jsonStr = '{"grade.gradeId":"_gradeid","grade.name":"_name","grade.empId1":"_empId1","grade.empId2":"_empId2","grade.empId3":"_empId3","grade.emp1.name":"_emp1_name","grade.emp2.name":"_emp2_name","grade.emp3.name":"_emp3_name","grade.des":"_des","grade.quantity":"_quantity","grade.status":"_status"}';
	var gradeId = "";
	
	$(function() {
		setPagination("list");
	})
	
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
					} else if (method == "addStu") {
						var row = $('#list').datagrid("getSelected");
						if (row) {
							gradeId = row.gradeId;
							$('#officialStuWin').window('open');
							$("#gradeTotalStu").val(row.quantity);
							$.get("<%=path %>/stu/officialStuByPager1", function(data) {
								$('#officialStuForm').datagrid("loadData", data.rows);
							}, "json");
						} else {
							$.messager.alert("提示","请选择一个需要添加学生的班级", "info");
						}
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}

	
	function showAddGradeWin() {
		authority('com.ht.action.GradeAction.save', "", "add");
	}
	
	function addGrade() {
		if ($('#addForm').form("validate")) {
			$.post("<%=path %>/grade/save",
					$('#addForm').serialize(),
					function(data) {
						if (data.result.result == "success") {
							$('#addWin').window('close');
							$('#list').datagrid('reload');
							$.messager.alert("提示", data.result.message, "info");
						}else if(data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
			}, "json"
			);
		}else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}		
	}
	
	function showEditGradeWin() {
		var row = $('#list').datagrid("getSelected");
		if (row) {
			var jsonStr1 = jsonStr.replace("_gradeid", row.gradeId).replace("_name", row.name).replace("_empId1", row.empId1).replace("_empId2", row.empId2).replace("_empId3", row.empId3).replace("_emp1_name", row.emp1.name).replace("_emp2_name", row.emp2.name).replace("_emp3_name", row.emp3.name).replace("_des", row.des).replace("_quantity", row.quantity).replace("_status", row.status);
			authority('com.ht.action.GradeAction.update', jsonStr1, "edit");
		} else {
			$.messager.alert("提示","请选择一个需要修改的班级", "info");
		}
	}
	
	function editGrade() {
		if ($('#editForm').form("validate")) {
			$.post("<%=path %>/grade/update",
					$('#editForm').serialize(),
					function(data) {
						if (data.result.result == "success") {
							$('#editWin').window('close');
							$('#list').datagrid('reload');
							$.messager.alert("提示", data.result.message, "info");
						}else if(data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "info");
						}
			}, "json"
			);
		}else {
			$.messager.alert("提示", "请输入正确的数据", "info");
		}		
	}
	
	function showStuWin() {
		var row = $('#list').datagrid("getSelected");
		if (row) {
			$('#stuWin').window('open');
			$.get("<%=path %>/stu/gradeIdByPager?gradeId=" + row.gradeId, function(data) {
				$("#stuForm").datagrid("loadData", data.rows);
			}, "json");
			
		} else {
			$.messager.alert("提示","请选择一个需要查看的班级", "info");
		}
	}
	
	
	function showAddStuWin() {
		authority('com.ht.action.StuAction.officialStuByPager1', "", "addStu");
	}
	
	function addOfficialStu() {
		var rows = $('#officialStuForm').datagrid("getSelections");
		if (rows) {
			var ids = "";
			for (var i = 0, len = rows.length; i < len; i++) {
				if (ids == "") {
					ids = rows[i].stuId;
				} else {
					ids += "," + rows[i].stuId;
				}
			}
			var gradeTotalStu = $("#gradeTotalStu").val();
			$.post("<%=path %>/stu/addStusToGrade", 
					{"stuIds": ids,"gradeId":gradeId,"gradeTotalStu":gradeTotalStu},
					function (data) {
						if (data.result.result == "success") {
							$("#officialStuWin").window("close");
							$.messager.alert("提示", data.result.message, "info");
						} else {
							$.messager.alert("提示", data.result.message, "info");
						}
					}, "json");
		}
	}
	
	function closeOfficialStuWin() {
		$('#officialStuWin').window('close');
	}
	
	var check = 0;
	function showEmpWin(index) {
		check = index;
		$("#empWin").window("open");
		$("#empForm").datagrid({url:'<%=path %>/emp/queryEmpByRoleId?check=' + check});
	}
	
	function addEmp() {
		var row = $("#empForm").datagrid("getSelected");
		if (row) {
			$("#empForm").datagrid("reload");
			$("#empWin").window("close");
			if (check == 1) {
				document.getElementById("addEmpId1").value = row.empId; // JS赋值
				$("#addEmpName1").textbox('setValue', row.name); //赋值
				document.getElementById("editEmpId1").value = row.empId; // JS赋值
				$("#editEmpName1").textbox('setValue', row.name); //赋值
			} else if (check == 2) {
				document.getElementById("addEmpId2").value = row.empId; // JS赋值
				$("#addEmpName2").textbox('setValue', row.name); //赋值
				document.getElementById("editEmpId2").value = row.empId; // JS赋值
				$("#editEmpName2").textbox('setValue', row.name); //赋值
			} else if (check == 3) {
				document.getElementById("addEmpId3").value = row.empId; // JS赋值
				$("#addEmpName3").textbox('setValue', row.name); //赋值
				document.getElementById("editEmpId3").value = row.empId; // JS赋值
				$("#editEmpName3").textbox('setValue', row.name); //赋值
			}
		} else {
			$.messager.alert("提示", "请选择员工", "error");
		}
	}
	
	function closeEmpWin() {
		$("#empWin").window("close");
	}
	
	function formatterStatus(value, row, index) {
		if (row.status == 1) {
			return "可用"
		} else if (row.status == 0) {
			return "不可用";
		}
	}
	
	function formatterGender (value, row, index) {
		if (row.gender == "male") {
			return "男"
		} else if (row.gender == "female") {
			return "女";
		}
	}
	
	function formatterStuStatus(value, row, index) {
		if (row.stuStatus == "official") {
			return "正式"
		}
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.gradeId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.gradeId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path %>/grade/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/grade/activation?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}

	
	function formatterEmpName(value) {
		return value.name;	
	}
	
	function formatterDept(value) {
		return value.name;
	}
	
	function formatterRole(value) {
		return value.name
	}
	
	function formatterRoomName(value) {
		return value.name
	}
	
	function formatterRoomName(value) {
		if (value != null) {
			return value.name
		}else {
			return "暂无";
		}
	}
	
	function queryByGradeName(name, value) {
		$.get("<%=path %>/grade/fuzzySearch?name=" + name + "&value=" + value, 
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
		}, "json")
	}
	
	function fuzzySearch(value,name){ // 根据学生姓名查询数据
	    if (value != "") {
	    	if (name == "gradeName") {
	    		queryByGradeName(name, value);
	    	}
	    	/* if (name == "stuName") {
	    		getReqByName(name, value);
	    	} else if (name == "empName") {
	    		getReqByName(name, value);
	    	} */
	    } else {
	    	$.messager.alert("提示", "请输入名称", "error");
	    }
	}
	
</script>
</head>
<body style="height:100%;">
<%-- 
data-options="singleSelect" 是否支持单选
collapsible:true
rownumbers:true 是否显示行号
autoRowHeight:true 
pagination:true 是否显示页码
pageSize:20 每页显示多少条记录数  
 --%>
<table id="list" class="easyui-datagrid" 
			data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path %>/grade/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,toolbar:'#tb'" style="height: 100%;">
	<thead>
			<tr>
				<th data-options="field:'gradeId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">名称</th>
				<th data-options="field:'emp1',width:100" formatter="formatterEmpName">班主任</th>
				<th data-options="field:'emp2',width:100" formatter="formatterEmpName">任课老师</th>
				<th data-options="field:'emp3',width:100" formatter="formatterEmpName">辅导老师</th>
				<th data-options="field:'des',width:80">描述</th>
				<th data-options="field:'quantity',width:80">最大班级人数</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
	</thead>
</table>

<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="javascript:;" onclick="showAddGradeWin();" class="easyui-linkbutton" iconCls="icon-add">添加班级</a>
		<a href="javascript:;" onclick="showAddStuWin();" class="easyui-linkbutton" iconCls="icon-add">添加学生</a>
		<a href="javascript:;" onclick="showEditGradeWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		<a href="javascript:;" onclick="showStuWin();" class="easyui-linkbutton" iconCls="icon-search">查看学生</a>
	</div>
	<div>
		<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
		<div id="mm" style="width:120px">
			<div data-options="name:'gradeName',iconCls:'icon-hamburg-customers'">班级名称</div>
			<div data-options="name:'empName1',iconCls:'icon-hamburg-hire-me'">班主任</div>
			<div data-options="name:'empName2',iconCls:'icon-hamburg-hire-me'">任课老师</div>
		</div>
	</div>
</div>

<div class="easyui-window" id="addWin" title="添加班级" data-options="closed:true" style="width:450px;height:300px;">
		<form id="addForm">
			<input type="hidden" id="addEmpId1" name="grade.empId1" />
			<input type="hidden" id="addEmpId2" name="grade.empId2" />
			<input type="hidden" id="addEmpId3" name="grade.empId3" />
			<table>
				<tr>
					<td>班级名称</td>
					<td><input class="easyui-validatebox easyui-textbox" id="name" name="grade.name" /></td>
				</tr>
				<tr>
					<td>班主任</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="addEmpName1" name="grade.emp1.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(1);">选择班主任</a>
					</td>
				</tr>
				<tr>
					<td>任课老师</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="addEmpName2" name="grade.emp2.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(2);">选择任课老师</a>
					</td>
				</tr>
				<tr>
					<td>辅导老师</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="addEmpName3" name="grade.emp3.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(3);">选择辅导老师</a>
					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" id="des" name="grade.des" style="height:60px;"/></td>
				</tr>
				<tr>
					<td>班级总人数</td>
					<td><input class="easyui-validatebox easyui-textbox" id="quantity" name="grade.quantity" data-options="required:true,validType:'length[2,3]'" /></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="addGrade();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
</div>

<div class="easyui-window" id="editWin" title="修改班级" data-options="closed:true" style="width:450px;height:300px;">
		<form id="editForm">
			<input type="hidden" id="id" name="grade.gradeId" />
			<input type="hidden" id="editEmpId1" name="grade.empId1" />
			<input type="hidden" id="editEmpId2" name="grade.empId2" />
			<input type="hidden" id="editEmpId3" name="grade.empId3" />
			<table>
				<tr>
					<td>班级名称</td>
					<td><input class="easyui-validatebox easyui-textbox" id="name" name="grade.name" /></td>
				</tr>
				<tr>
					<td>班主任</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="editEmpName1" name="grade.emp1.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(1);">选择班主任</a>
					</td>
				</tr>
				<tr>
					<td>任课老师</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="editEmpName2" name="grade.emp2.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(2);">选择任课老师</a>
					</td>
				</tr>
				<tr>
					<td>辅导老师</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="editEmpName3" name="grade.emp3.name" data-options="'disabled':true"/>
						<a href="javascript:;" onclick="showEmpWin(3);">选择辅导老师</a>
					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td><input class="easyui-textbox" data-options="multiline:true" id="des" name="grade.des" style="height:60px;"/></td>
				</tr>
				<tr>
					<td>班级总人数</td>
					<td><input class="easyui-validatebox easyui-textbox" id="quantity" name="grade.quantity" data-options="required:true,validType:'length[2,20]'" /></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="editGrade();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
</div>
<div id="stuWin" class="easyui-window" title="查看班级学生" data-options="iconCls:'icon-search', closed:true" style="width:900px;height:450px;">
		<!-- 查看学生的DataGrid -->
		<table id="stuForm" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:100%;">
			<thead>
				<tr>
					<th data-options="field:'stuId',checkbox:true">编号</th>
					<th data-options="field:'stuNo',width:40">学号</th>
					<th data-options="field:'name',width:60">姓名</th>
					<th data-options="field:'idCard',width:80">身份证</th>
					<th data-options="field:'phone',width:80">手机</th>
					<th data-options="field:'qq',width:80">QQ</th>
					<th data-options="field:'wechat',width:80">微信</th>
					<th data-options="field:'school',width:80">毕业学校</th>
					<th data-options="field:'age',width:40">年龄</th>
					<th data-options="field:'birthday',width:80">生日</th>
					<th data-options="field:'gender',width:40" formatter="formatterGender">性别</th>
					<th data-options="field:'address',width:80">地址</th>
					<th data-options="field:'nation',width:80">籍贯</th>
					<th data-options="field:'residence',width:60">户口性质</th>
					<th data-options="field:'room',width:80" formatter="formatterRoomName">宿舍</th>
					<th data-options="field:'parentName',width:60">家长姓名</th>
					<th data-options="field:'parentPhone',width:80">家长电话</th>
					<th data-options="field:'startDay',width:100">入学时间</th>
					<th data-options="field:'emp',width:60" formatter="formatterEmpName">招生老师</th>
					<th data-options="field:'des',width:120">描述</th>
					<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
					<th data-options="field:'stuStatus',width:60" formatter="formatterStuStatus">学生状态</th>
					<th data-options="field:'role',width:60">班级角色</th>
				</tr>
			</thead>
		</table>
</div>
<div id="empWin" class="easyui-window" title="查看员工" data-options="iconCls:'icon-search', closed:true" style="width:800px;height:450px;">
	<table id="empForm" class="easyui-datagrid"
			data-options="toolbar:'#tb1',singleSelect:true,
			collapsible:true,
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:90%;">
		<thead>
			<tr>
				<th data-options="field:'empId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'gender',width:80" formatter="formatterGender">性别</th>
				<th data-options="field:'major',width:80">专业</th>
				<th data-options="field:'eduBack',width:80">学历</th>
				<th data-options="field:'dept',width:80" formatter="formatterDept">部门</th>
				<th data-options="field:'role',width:80" formatter="formatterRole">角色</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
			</tr>
		</thead>
	</table>
	<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addEmp();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeEmpWin()">取消</a>
	</div>
</div>
<div id="officialStuWin" class="easyui-window" title="查看正式学生" data-options="iconCls:'icon-search', closed:true" style="width:900px;height:450px;">
		<!-- 查看正式学生的DataGrid -->
		<table id="officialStuForm" class="easyui-datagrid"
				data-options="singleSelect:false,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height:90%;">
			<thead>
				<tr>
					<th data-options="field:'stuId',checkbox:true">编号</th>
					<th data-options="field:'stuNo',width:100">学号</th>
					<th data-options="field:'name',width:80">姓名</th>
					<th data-options="field:'idCard',width:80">身份证</th>
					<th data-options="field:'phone',width:80">手机</th>
					<th data-options="field:'qq',width:80">QQ</th>
					<th data-options="field:'wechat',width:80">微信</th>
					<th data-options="field:'school',width:80">毕业学校</th>
					<th data-options="field:'age',width:80">年龄</th>
					<th data-options="field:'birthday',width:80">生日</th>
					<th data-options="field:'gender',width:80" formatter="formatterGender">性别</th>
					<th data-options="field:'address',width:80">地址</th>
					<th data-options="field:'nation',width:80">籍贯</th>
					<th data-options="field:'residence',width:80">户口性质</th>
					<th data-options="field:'roomName',width:80">宿舍</th>
					<th data-options="field:'parentName',width:80">家长姓名</th>
					<th data-options="field:'parentPhone',width:80">家长电话</th>
					<th data-options="field:'startDay',width:100">入学时间</th>
					<th data-options="field:'empName',width:80">招生老师</th>
					<th data-options="field:'des',width:120">描述</th>
					<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
					<th data-options="field:'stuStatus',width:80" formatter="formatterStuStatus">学生状态</th>
					<th data-options="field:'role',width:120">班级角色</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
			<input type="hidden" id="gradeTotalStu" />
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addOfficialStu();">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeOfficialStuWin()">取消</a>
		</div>
</div>
</body>
</html>