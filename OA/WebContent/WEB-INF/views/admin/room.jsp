<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宿舍</title>
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path%>/js/json2.js"></script>
<script>

	var jsonStr = '{"room.roomId":"_roomid","room.name":"_name","room.stuId":"_stuId","room.stu.name":"_stu_name","room.quantity":"_quantity","room.status":"_status"}';
	var roomId = "";
	
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
					} else if (method == "addStu" ) {
						var row = $('#list').datagrid("getSelected");
						if (row) {
							roomId = row.roomId;
							$("#roomTotalStu").val(row.quantity);
							$('#gradeWin').window('open');
						} else {
							$.messager.alert("提示", "请选择一个需要添加学生的宿舍", "error");
						}
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}

	function showCheckGrade() {
		$("#stuWin").window("open");
		$.get("<%=path%>/stu/roomByPager?roomId=" + row.roomId, function(data) {
			$("#stuForm").datagrid("loadData", data.rows);
		}, "json");
	}

	function closeStuWin() {
		$("#stuWin").window("close");
	}

	function addRoomStu () {
		var row = $('#stuForm').datagrid("getSelected");
		if (row) {
			$("#stuWin").window("close");
			/* document.getElementById("addStuId").value = row.stuId; // JS赋值
			$("#addStuName").textbox('setValue', row.name); //赋值 */
			document.getElementById("editStuId").value = row.stuId; // JS赋值
			$("#editStuName").textbox('setValue', row.name); //赋值
		} else {
			$.messager.alert("提示", "请选择学生", "error");
		}
	}
	
	function showAddRoomWin() {
		authority('com.ht.action.RoomAction.save', "", "add");
	}
	
	function addRoom() {
		if ($('#addForm').form("validate")) {
			$.post("<%=path%>/room/save",
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
	
	function showEditRoomWin() {
		var row = $('#list').datagrid("getSelected");
		if (row) {
			jsonStr1 = jsonStr.replace("_roomid", row.roomId).replace("_name", row.name).replace("_quantity", row.quantity).replace("_status", row.status);
			if (row.stu == null) {
				jsonStr1 = jsonStr1.replace("_stu_name", "暂无").replace("_stuId", "");
			} else {
				jsonStr1 = jsonStr1.replace("_stu_name", row.stu.name).replace("_stuId", row.stuId);
			}
			authority('com.ht.action.RoomAction.update', jsonStr1, "edit");
		} else {
			$.messager.alert("提示","请选择一个需要修改的宿舍", "info");
		}
	}
	
	function editRoom() {
		if ($('#editForm').form("validate")) {
			$.post("<%=path%>/room/update",
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
			$.get("<%=path%>/stu/roomByPager?roomId=" + row.roomId, function(data) {
				$("#stuForm").datagrid("loadData", data.rows);
			}, "json");
			
		} else {
			$.messager.alert("提示","请选择一个需要查看的宿舍", "info");
		}
	}
	
	function grades(value, row, index) {
		return "<a href='javascript:;' onclick=showGradeStuWin('" + row.gradeId + "')>所有学生</a>"
	}
	
	function showAddStuWin() {
		authority('com.ht.action.StuAction.gradeByPager', "", "addStu");
	} 
	
	function showGradeStuWin(gradeId) {
		$('#gradeStuWin').window('open');
		$.get("<%=path%>/stu/gradeByPager?gradeId=" + gradeId, function(data){
			$("#gradeStuForm").datagrid("loadData", data.rows);
		}, "json");
	}
	
	function addStu() {
		var rows = $('#gradeStuForm').datagrid("getSelections");
		if (rows) {
			var ids = "";
			for (var i = 0, len = rows.length; i < len; i++) {
				if (ids == "") {
					ids = rows[i].stuId;
				} else {
					ids += "," + rows[i].stuId;
				}
			}
			var roomTotalStu = $("#roomTotalStu").val();
			$.post("<%=path %>/stu/addStusToRoom", 
					{"stuIds": ids,"roomId":roomId,"roomTotalStu":roomTotalStu},
					function (data) {
						if (data.result.result == "success") {
							$("#gradeStuWin").window("close");
							$("#gradeWin").window("close");
							$.messager.alert("提示", data.result.message, "info");
						} else {
							$.messager.alert("提示", data.result.message, "info");
						}
					}, "json");
		}
	}
	
	function closeGradeStuWin() {
		$('#gradeStuWin').window('close');
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
			return "<a href='javascript:;' onclick=frostStatus('" + row.roomId + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.roomId + "')>激活</a>";
	}
	
	function frostStatus(id) {
		$.get("<%=path%>/room/frost?id=" + id,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id) {
		$.get("<%=path %>/room/activation?id=" + id, function(data) {
			if (data.result.result == "success") {
				$("#list").datagrid("reload"); // 更新表格
			} else if (data.result.result == "fail") {
				$.messager.alert("提示", data.result.message, "error");
			}
		}, "json");
	}

	function formatterStuName(value) {
		if (value != null) {
			return value.name;
		} else {
			return '暂无';
		}
	}
	
	function formatterEmpName(value){
		return value.name;
	}
	
	function formatterGradeName(value){
		return value.name
	}
	
	function queryByRoomName(name, value) {
		$.get("<%=path %>/room/fuzzySearch?name=" + name + "&value=" + value, 
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
	    	if (name == "roomName") {
	    		queryByRoomName(name, value);
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
<body style="height: 100%;">
<%-- 
data-options="singleSelect" 是否支持单选
collapsible:true
rownumbers:true 是否显示行号
autoRowHeight:true 
pagination:true 是否显示页码
pageSize:20 每页显示多少条记录数  
 --%>
	<table id="list" class="easyui-datagrid" data-options="singleSelect:true,
			collapsible:true,
			url:'<%=path%>/room/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,toolbar:'#tb'" style="height: 100%;">
		<thead>
			<tr>
				<th data-options="field:'roomId',checkbox:true">编号</th>
				<th data-options="field:'name',width:100">名称</th>
				<th data-options="field:'stu',width:80" formatter="formatterStuName">宿舍长</th>
				<th data-options="field:'quantity',width:80">最大宿舍人数</th>
				<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
				<th data-options="field:'operation',width:80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="javascript:;" onclick="showAddRoomWin();" class="easyui-linkbutton" iconCls="icon-add">添加宿舍</a>
			<a href="javascript:;" onclick="showAddStuWin();" class="easyui-linkbutton" iconCls="icon-add">添加学生</a>
			<a href="javascript:;" onclick="showEditRoomWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
			<a href="javascript:;" onclick="showStuWin();" class="easyui-linkbutton" iconCls="icon-search">查看学生</a>
		</div>
		<div>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
			<div id="mm" style="width:120px">
				<div data-options="name:'roomName',iconCls:'icon-hamburg-customers'">宿舍名称</div>
			</div>
		</div>
	</div>

	<div class="easyui-window" id="addWin" title="添加宿舍" data-options="closed:true" style="width: 300px; height: 200px;">
		<form id="addForm">
			<input type="hidden" id="addStuId" name="room.stuId" />
			<table>
				<tr>
					<td>宿舍名称</td>
					<td><input class="easyui-validatebox easyui-textbox" id="name" name="room.name" /></td>
				</tr>
				<tr>
					<td>宿舍总人数</td>
					<td>
						<input class="easyui-validatebox easyui-numberbox" id="quantity" name="room.quantity" data-options="required:true,validType:'length[1,2]'" />
					</td>
				</tr>
				<tr>
					<td><a href="javascript:;" onclick="addRoom();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="easyui-window" id="editWin" title="修改宿舍" data-options="closed:true" style="width: 400px; height: 200px;">
		<form id="editForm">
			<input type="hidden" id="id" name="room.roomId" />
			<input type="hidden" id="editStuId" name="room.stuId" />
			<table>
				<tr>
					<td>宿舍名称</td>
					<td><input class="easyui-validatebox easyui-textbox" id="name" name="room.name" /></td>
				</tr>
				<tr>
					<td>宿舍长</td>
					<td>
						<input class="easyui-validatebox easyui-textbox" id="editStuName" name="room.stu.name" data-options="'disabled':true" />
						<a href="javascript:;" onclick="showCheckGrade();">选择学生</a>
					</td>
				</tr>
				<tr>
					<td>宿舍总人数</td>
					<td>
						<input class="easyui-validatebox easyui-numberbox" id="quantity" name="room.quantity" data-options="required:true,validType:'length[1,10]'" />
					</td>
				</tr>
				<tr>
					<td><a href="javascript:;" onclick="editRoom();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="gradeStuWin" class="easyui-window" title="查看班级学生" data-options="iconCls:'icon-search', closed:true" style="width: 900px; height: 450px;">
		<!-- 查看班级学生的DataGrid -->
		<table id="gradeStuForm" class="easyui-datagrid" data-options="singleSelect:false,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height: 90%;">
			<thead>
				<tr>
					<th data-options="field:'stuId',checkbox:true">编号</th>
					<th data-options="field:'stuNo',width:40">学号</th>
					<th data-options="field:'name',width:60">姓名</th>
					<!-- <th data-options="field:'idCard',width:80">身份证</th> -->
					<th data-options="field:'phone',width:80">手机</th>
					<th data-options="field:'qq',width:80">QQ</th>
					<th data-options="field:'wechat',width:80">微信</th>
					<!-- <th data-options="field:'school',width:80">毕业学校</th>
					<th data-options="field:'age',width:80">年龄</th>
					<th data-options="field:'birthday',width:80">生日</th>-->
					<th data-options="field:'gender',width:40" formatter="formatterGender">性别</th>
					<!--<th data-options="field:'address',width:80">地址</th>
					<th data-options="field:'nation',width:80">籍贯</th>
					<th data-options="field:'residence',width:80">户口性质</th>
					<th data-options="field:'parentName',width:80">家长姓名</th>
					<th data-options="field:'parentPhone',width:80">家长电话</th> -->
					<th data-options="field:'startDay',width:100" formatter="formatterDate">入学时间</th>
					<th data-options="field:'emp',width:60" formatter="formatterEmpName">招生老师</th>
					<th data-options="field:'des',width:120">描述</th>
					<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
					<th data-options="field:'stuStatus',width:60" formatter="formatterStuStatus">学生状态</th>
					<th data-options="field:'role',width:60">班级角色</th>
				</tr>
			</thead>
		</table>
		<div style="text-align: right; padding: 5px">
			<input type="hidden" id="roomTotalStu" />
			<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addStu();">确定</a>
			<a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeGradeStuWin()">取消</a>
		</div>
	</div>
	<div id="stuWin" class="easyui-window" title="查看宿舍学生" data-options="iconCls:'icon-search', closed:true" style="width: 900px; height: 450px;">
		<!-- 查看宿舍的学生的DataGrid -->
		<table id="stuForm" class="easyui-datagrid" data-options="singleSelect:true,
				collapsible:true,
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height: 90%;">
			<thead>
				<tr>
					<th data-options="field:'stuId',checkbox:true">编号</th>
					<th data-options="field:'stuNo',width:40">学号</th>
					<th data-options="field:'name',width:60">姓名</th>
					<!-- <th data-options="field:'idCard',width:80">身份证</th> -->
					<th data-options="field:'phone',width:80">手机</th>
					<th data-options="field:'qq',width:80">QQ</th>
					<th data-options="field:'wechat',width:80">微信</th>
				<!-- 	<th data-options="field:'school',width:80">毕业学校</th>
					<th data-options="field:'age',width:80">年龄</th>
					<th data-options="field:'birthday',width:80">生日</th> -->
					<th data-options="field:'gender',width:40" formatter="formatterGender">性别</th>
					<!-- <th data-options="field:'address',width:80">地址</th>
					<th data-options="field:'nation',width:80">籍贯</th>
					<th data-options="field:'residence',width:80">户口性质</th> -->
					<th data-options="field:'grade',width:80" formatter="formatterGradeName">班级</th>
					<!-- <th data-options="field:'parentName',width:80">家长姓名</th>
					<th data-options="field:'parentPhone',width:80">家长电话</th> -->
					<th data-options="field:'startDay',width:100">入学时间</th>
					<th data-options="field:'emp',width:60" formatter="formatterEmpName">招生老师</th>
					<th data-options="field:'des',width:120">描述</th>
					<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
					<th data-options="field:'stuStatus',width:60" formatter="formatterStuStatus">学生状态</th>
					<th data-options="field:'role',width:60">班级角色</th>
				</tr>
			</thead>
		</table>
		<div style="text-align: right; padding: 5px">
			<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="addRoomStu();">设为宿舍长</a>
			<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="closeStuWin()">取消</a>
		</div>
	</div>
	<!-- 选择班级的窗口 -->
	<div id="gradeWin" class="easyui-window" title="选择班级" data-options="iconCls:'icon-search', closed:true" style="width: 540px; height: 300px;">
		<!-- 选择班级的DataGrid -->
		<table id="grades" class="easyui-datagrid" data-options="singleSelect:true,
				collapsible:true,
				url:'<%=path%>/grade/pager',
				method:'get',
				rownumbers:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:20,
				toolbar:'#tb1'" style="height: 100%;">
			<thead>
				<tr>
					<th data-options="field:'gradeId',checkbox:true">编号</th>
					<th data-options="field:'name',width:100">名称</th>
					<th data-options="field:'des',width:80">描述</th>
					<th data-options="field:'quantity',width:120">最大班级人数</th>
					<th data-options="field:'status',width:80" formatter="formatterStatus">状态</th>
					<th data-options="field:'students',width:80" formatter="grades">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>