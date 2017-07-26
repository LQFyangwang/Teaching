<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	var selectStr;
	var jsonCheck = '"check.checkId":"_checkId", "check.checkTime":"_checkTime", "check.weekDay":"_weekDay", "check.des":"_des", ';
	var jsonEmp = '"check.emp.empId":"_emp_empId", "check.emp.name":"_emp_name", ';
	var jsonGrade = '"check.grade.gradeId":"_grade_gradeId", "check.grade.name":"_grade_name", ';
	var jsonRoom = '"check.room.roomId":"_room_roomId", "check.room.name":"_room_name"'
	var jsonStr = '{' + jsonCheck + jsonEmp + jsonGrade + jsonRoom + '}';
	$(function() {
		setPagination("list");
	});
	
	function authority(methodName, jsonStr, method) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addWin").window("open"); // 打开窗口
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showAddCheckWin(value1, value2) {
		$("#addForm").form("clear"); // 清除表单的内容
		$("#addEmpId").val('${sessionScope.emp.empId}');
		$("#addEmpName").textbox("setValue", '${sessionScope.emp.name}');
		$('#checkTime').datetimebox('setValue', getNowFormatDate());
		$("#addWeekDay").combobox('setValue', 1);
		authority('com.ht.action.CheckAction.add', "", "add");
	}
	
	function addCheck() {
		var roomName = $("#addRoomName").textbox("getValue");
		var gradeName = $("#addGradeName").textbox("getValue");
		var checkTime = $("#checkTime").datetimebox("getValue");
		var currTime = getNowFormatDate();
		if (checkTime < currTime) {
			if ((roomName != null && roomName != "") || (gradeName != null && gradeName != "")) {
				if ($("#addForm").form("validate")) {
					$.post("<%=path %>/check/add",
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
			} else {
				$.messager.alert("提示", "从班级或宿舍选择一个", "info");
			}
		} else {
			$.messager.alert("提示", "巡查时间不能大于当前时间!", "info");
		}
	}
	
	function showEditCheckWin(value1, value2) {
		var row2 = $('#list').datagrid('getSelected');
		if (row2) {
			var empId = value1;
			var empId2 = row2.emp.empId;
			var jsonStr1;
			if (row2.grade == null) {
				jsonStr1 = jsonStr.replace("_checkId", row2.checkId).replace("_checkTime", row2.checkTime).replace("_des", row2.des).replace("_weekDay", row2.weekDay).replace("_emp_empId", row2.emp.empId).replace("_emp_name", row2.emp.name).replace("_grade_gradeId", "").replace("_grade_name", "").replace("_room_roomId", row2.room.roomId).replace("_room_name", row2.room.name);
			} else if (row2.room == null) {
				jsonStr1 = jsonStr.replace("_checkId", row2.checkId).replace("_checkTime", row2.checkTime).replace("_des", row2.des).replace("_weekDay", row2.weekDay).replace("_emp_empId", row2.emp.empId).replace("_emp_name", row2.emp.name).replace("_grade_gradeId", row2.grade.gradeId).replace("_grade_name", row2.grade.name).replace("_room_roomId", "").replace("_room_name", "");
			}
			if (empId == empId2) {
				$("#editCheckId").val(row2.checkId);
				$("#editEmpId").val('${sessionScope.emp.empId}');
				$("#editEmpName").textbox("setValue", '${sessionScope.emp.name}');
				authority('com.ht.action.CheckAction.update', jsonStr1, "edit");
			} else {
				$.messager.alert("提示", "没有权限修改其它员工的巡查！", "info");
			}
		} else {
			$.messager.alert("提示", "请先选择一个需要修改的巡查", "info");
		}
	}
	
	function editCheck() {
		var checkTime = $("#editCheckTime").datetimebox("getValue");
		var currTime = getNowFormatDate();
		if (checkTime < currTime) {
			if ($("#editForm").form("validate")) {
				$.post("<%=path %>/check/update",
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
		} else {
			$.messager.alert("提示", "巡查时间不能大于当前时间!", "info");
		}
	}
	
	function objectName(value, row, index) {
		if (value != null) {
		return value.name;
		} else {
			return "";
		}
	}
	
	function weekDay(value, row, index) {
		if(value == "1") 
			return "星期一";
		else if(value == "2") 
			return "星期二"
		else if(value == "3") 
			return "星期三"
		else if(value == "4") 
			return "星期四"
		else if(value == "5") 
			return "星期五"
		else if(value == "6") 
			return "星期六"
	}
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
	}
	
	function showSelect(aa) {
		selectStr = aa;
		if (selectStr == 1 || selectStr == 2) {
		var gradeIdStr = $("#addGradeId").val();
		var roomIdStr = $("#addRoomId").val();
		} else {
			var gradeIdStr = $("#editGradeId").val();
			var roomIdStr = $("#editRoomId").val();
		}
		if (selectStr == 1) {
			if (roomIdStr == "" || roomIdStr == null) {
			$("#list2").datagrid({url:"<%=path %>/grade/pager"});
			$("#gradeWin").window("open");
			} else {
				$.messager.alert("提示", "已经选择了宿舍，只能巡查班级或宿舍其中一个", "error");
			}
		} else if (selectStr == 2) {
			if (gradeIdStr == null || gradeIdStr == "") {
			$("#list3").datagrid({url:"<%=path%>/room/pager"});
			$("#roomWin").window("open");
			} else {
				$.messager.alert("提示", "已经选择了班级，只能巡查班级或宿舍其中一个", "error");
			}
		} else if (selectStr == 3) {
			if (roomIdStr == "" || roomIdStr == null) {
				$.get("<%=path %>/grade/pager", function(data) {
					$("#list2").datagrid("loadData", data.rows);
				}, "json");
				$("#gradeWin").window("open");
				} else {
					$.messager.alert("提示", "已经修改了宿舍，只能修改巡查班级或宿舍其中一个", "error");
				}
		} else if (selectStr == 4) {
			if (gradeIdStr == null || gradeIdStr == "") {
				$.get("<%=path%>/room/pager", function(data) {
					$("#list3").datagrid("loadData", data.rows);
				}, "json");
				$("#roomWin").window("open");
				} else {
					$.messager.alert("提示", "已经修改了班级，只能修改班级或宿舍其中一个", "error");
				}
		}
	}
	
	function confirmSelect(bb) {
		if (bb == 1) {
			var selectGrade = $('#list2').datagrid('getSelected');
			if (selectGrade) {
				if(selectStr == 1) {
					$("#addGradeId").val(selectGrade.gradeId);
					$("#addGradeName").textbox("setValue", selectGrade.name);
				} else {
					$("#editGradeId").val(selectGrade.gradeId);
					$("#editGradeName").textbox("setValue", selectGrade.name);
				}
				closeSelect(1);
			} else {
				$.messager.alert("提示", "请选择一个班级", "error");
			}
		} else {
			var selectRoom = $('#list3').datagrid('getSelected');
			if (selectRoom) {
				if (selectStr == 2) {
					$("#addRoomId").val(selectRoom.roomId);
					$("#addRoomName").textbox("setValue", selectRoom.name);
				} else {
					$("#editRoomId").val(selectRoom.roomId);
					$("#editRoomName").textbox("setValue", selectRoom.name);
				}
				closeSelect(2);
			} else {
				$.messager.alert("提示", "请选择一个宿舍", "error");
			}
		}
	}
	
	function closeSelect(aa) {
		if (aa == 1) {
		$("#gradeWin").window("close");
		} else if(aa == 2) {
			$("#roomWin").window("close");
		}
	}
	
	function queryByDay() { // 根据时间段查询数据
		var startDay = $("#startDay").textbox('getValue');
		var endDay = $("#endDay").textbox('getValue')
		if (startDay != "" && endDay != "") {
			if (endDay > startDay) {
				$.get("<%=path %>/check/pagerByDay?startDay=" + startDay + "&endDay=" + endDay,
						function(data) {
							if (data.result.result == "success") {
								// $("#list").datagrid("reload"); // 更新表格
								$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
							} else if (data.result.result == "fail") {
								$.messager.alert("提示", data.result.message, "error");
							}
					}, "json");
			} else {
				$.messager.alert("提示", "结束时间不能在开始时间之前！", "error");
			}
		} else {
			$.messager.alert("提示", "请选择时间段", "error");
		}
	}
	
	function queryByName(value){ // 根据学生姓名查询数据
	    if (value != "") {
	    	$.get("<%=path %>/check/pagerByName?value=" + value,
					function(data) {
						if (data.result.result == "success") {
							// $("#list").datagrid("reload"); // 更新表格
							$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
						} else if (data.result.result == "fail") {
							$.messager.alert("提示", data.result.message, "error");
						}
				}, "json");
	    } else {
	    	$.messager.alert("提示", "请输入姓名", "error");
	    }
	}
	
</script>
</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/check/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'checkId',checkbox:true">编号</th>
				<th data-options="field:'weekDay',width:80" formatter="weekDay">周几</th>
				<th data-options="field:'emp',width:80" formatter="objectName">巡查老师</th>
				<th data-options="field:'checkTime',width:100" formatter="formatterDate">巡查时间</th>
				<th data-options="field:'grade',width:80" formatter="objectName">巡查班级</th>
				<th data-options="field:'room',width:80" formatter="objectName">巡查宿舍</th>
				<th data-options="field:'des',width:80">巡查描述</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddCheckWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }');" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditCheckWin('${sessionScope.emp.empId }', '${sessionScope.emp.name }');" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
		<div>
			巡查时间段：
			<input class="easyui-datebox" id="startDay" name="startDay" data-options="multiline:true, editable:false"></input>
			至
			<input class="easyui-datebox" id="endDay" name="endDay" data-options="multiline:true, editable:false"></input>
			<a href="javascript:;" onclick="queryByDay();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<input id="ss" class="easyui-searchbox" style="width:150px" data-options="searcher:queryByName,prompt:'请输入员工姓名'"></input>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加巡查" data-options="closed:true" style="width:500px;height:auto;">
		<form id="addForm">
		<input type="hidden" id="addEmpId" name="check.emp.empId" />
		<input type="hidden" id="addGradeId" name="check.grade.gradeId" />
		<input type="hidden" id="addRoomId" name="check.room.roomId" />
			<table>
				<tr>
					<td style="width: 100px;">周几</td>
					<td>
						<select class="easyui-combobox" name="check.weekDay" id="addWeekDay" style="width:100px;" data-options="required:true, 'editable':false">
		    				<option value="1">星期一</option>
		    				<option value="2">星期二</option>
		    				<option value="3">星期三</option>
		    				<option value="4">星期四</option>
		    				<option value="5">星期五</option>
		    			</select>
					</td>
				</tr>
				<tr>
					<td>员工名</td>
					<td><input class="easyui-textbox" type="text" id="addEmpName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>巡查时间:</td>
		    		<td><input class="easyui-datetimebox" id="checkTime" name="check.checkTime" data-options="multiline:true, editable:false"></input></td>
		    	</tr>
		    	<tr>
					<td>班级</td>
					<td>
						<input class="easyui-textbox" id="addGradeName" name="check.grade.name" data-options="editable:false" ></input>
						<a href="javascript:;" onclick="showSelect(1);">选择班级</a>
					</td>
				</tr>
				<tr>
					<td>宿舍</td>
					<td>
						<input class="easyui-textbox" id="addRoomName" name="check.room.name" data-options="editable:false" ></input>
						<a href="javascript:;" onclick="showSelect(2);">选择宿舍</a>
					</td>
				</tr>
				<tr>
					<td>巡查描述</td>
					<td><input class="easyui-textbox" id="addDes" name="check.des" data-options="multiline:true, 'required':true," style="height:60px"></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="addCheck();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改巡查" data-options="closed:true" style="width:500px;height:auto;">
		<form id="editForm">
		<input type="hidden" id="editCheckId" name="check.checkId" />
		<input type="hidden" id="editEmpId" name="check.emp.empId" />
		<input type="hidden" id="editGradeId" name="check.grade.gradeId" />
		<input type="hidden" id="editRoomId" name="check.room.roomId" />
			<table>
				<tr>
					<td style="width: 100px;">周几</td>
					<td>
						<select class="easyui-combobox" name="check.weekDay" id="editWeekDay" style="width:100px;" data-options="required:true,'editable':false">
		    				<option value="1">星期一</option>
		    				<option value="2">星期二</option>
		    				<option value="3">星期三</option>
		    				<option value="4">星期四</option>
		    				<option value="5">星期五</option>
		    			</select>
					</td>
				</tr>
				<tr>
					<td>员工名</td>
					<td><input class="easyui-textbox" type="text" id="editEmpName" data-options="required:true, 'disabled':true" ></input></td>
				</tr>
				<tr>
		    		<td>巡查时间:</td>
		    		<td><input class="easyui-datetimebox" id="editCheckTime" name="check.checkTime" data-options="multiline:true, 'required':true, 'editable':false"></input></td>
		    	</tr>
		    	<tr>
					<td>班级</td>
					<td>
						<input class="easyui-textbox" type="text" id="editGradeName" name="check.grade.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelect(3);">选择班级</a>
					</td>
				</tr>
				<tr>
					<td>宿舍</td>
					<td>
						<input class="easyui-textbox" type="text" id="editRoomName" name="check.room.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelect(4);">选择宿舍</a>
					</td>
				</tr>
				<tr>
					<td>巡查描述</td>
					<td><input class="easyui-textbox" id="editDes" name="check.des" data-options="multiline:true, 'required':true" style="height:60px"></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editCheck();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 选择班级的窗口 -->
	<div id="gradeWin" class="easyui-window" title="选择班级" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:auto;">
		<!-- 选择班级的DataGrid -->
		<table id="list2" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb2'" style="height:auto;">
			<thead>
				<tr>
					<th data-options="field:'gradeId',checkbox:true">编号</th>
					<th data-options="field:'name',width:100">班级</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="confirmSelect(1);">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeSelect(1);">取消</a>
		</div>
	</div>
		<!-- 选择宿舍的窗口 -->
	<div id="roomWin" class="easyui-window" title="选择宿舍" data-options="iconCls:'icon-search', closed:true" style="width:540px;height:auto;">
		<!-- 选择宿舍的DataGrid -->
		<table id="list3" class="easyui-datagrid"
				data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb3'" style="height:auto;">
			<thead>
				<tr>
					<th data-options="field:'roomId',checkbox:true">编号</th>
					<th data-options="field:'name',width:100">宿舍</th>
				</tr>
			</thead>
		</table>
		<div style="text-align:right;padding:5px">
		    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="confirmSelect(2);">确定</a>
		    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeSelect(2);">取消</a>
		</div>
	</div>
</body>
</html>