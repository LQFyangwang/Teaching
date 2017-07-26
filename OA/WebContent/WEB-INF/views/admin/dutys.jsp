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
var empStr = "";
var jsonEmp1 = '"duty.emp1.name":"_emp1_name","duty.add1":"_add1","duty.emp1.empId":"_emp1_empId",';
var jsonEmp2 = '"duty.emp2.name":"_emp2_name","duty.add2":"_add2","duty.emp2.empId":"_emp2_empId",';
var jsonEmp3 = '"duty.emp3.name":"_emp3_name","duty.add3":"_add3","duty.emp3.empId":"_emp3_empId",';
var jsonEmp4 = '"duty.emp4.name":"_emp4_name","duty.add4":"_add4","duty.emp4.empId":"_emp4_empId",';
var jsonEmp5 = '"duty.emp5.name":"_emp5_name","duty.add5":"_add5","duty.emp5.empId":"_emp5_empId",';
var jsonEmp6 = '"duty.emp6.name":"_emp6_name","duty.add6":"_add6","duty.emp6.empId":"_emp6_empId",';
var jsonEmp7 = '"duty.emp7.name":"_emp7_name","duty.add7":"_add7","duty.emp7.empId":"_emp7_empId",';
var jsonEmp8 = '"duty.emp8.name":"_emp8_name","duty.add8":"_add8","duty.emp8.empId":"_emp8_empId"}';
var jsonStr = '{"duty.dutyId":"_dutyId","duty.weekDay":"_weekDay",' + jsonEmp1 + jsonEmp2 + jsonEmp3 + jsonEmp4 + jsonEmp5 + jsonEmp6 + jsonEmp7 + jsonEmp8;
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
	
	function showEditDutyWin() {
		$("#editTable").show();
		// var row = $("#list").datagrid("getSelected"); // 获取数据表格中被选中的行数据
		var rows = $("#list").datagrid("getSelections");
		if (rows.length == 1) {
			var row = rows[0];
			if (row) { // 有选中的行
				var jsonStr1 = jsonStr.replace("_dutyId", row.dutyId).replace("_weekDay", row.weekDay);
				if (row.emp1 != null) {
					jsonStr1 = jsonStr1.replace("_emp1_name", row.emp1.name).replace("_add1", row.add1).replace("_emp1_empId", row.emp1.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp1_name", "").replace("_add1", "").replace("_emp1_empId", "");
				}
				if (row.emp2 != null) {
					jsonStr1 = jsonStr1.replace("_emp2_name", row.emp2.name).replace("_add2", row.add2).replace("_emp2_empId", row.emp2.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp2_name", "").replace("_add2", "").replace("_emp2_empId", "");
				}
				if (row.emp3 != null) {
					jsonStr1 = jsonStr1.replace("_emp3_name", row.emp3.name).replace("_add3", row.add3).replace("_emp3_empId", row.emp3.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp3_name", "").replace("_add3", "").replace("_emp3_empId", "");
				}
				if (row.emp4 != null) {
						jsonStr1 = jsonStr1.replace("_emp4_name", row.emp4.name).replace("_add4", row.add4).replace("_emp4_empId", row.emp4.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp4_name", "").replace("_add4", "").replace("_emp4_empId", "");
				}
				if (row.emp5 != null) {
					jsonStr1 = jsonStr1.replace("_emp5_name", row.emp5.name).replace("_add5", row.add5).replace("_emp5_empId", row.emp5.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp5_name", "").replace("_add5", "").replace("_emp5_empId", "");
				}
				if (row.emp6 != null) {
					jsonStr1 = jsonStr1.replace("_emp6_name", row.emp6.name).replace("_add6", row.add6).replace("_emp6_empId", row.emp6.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp6_name", "").replace("_add6", "").replace("_emp6_empId", "");
				}
				if (row.emp7 != null) {
					jsonStr1 = jsonStr1.replace("_emp7_name", row.emp7.name).replace("_add7", row.add7).replace("_emp7_empId", row.emp7.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp7_name", "").replace("_add7", "").replace("_emp7_empId", "");
				}
				if (row.emp8 != null) {
					jsonStr1 = jsonStr1.replace("_emp8_name", row.emp8.name).replace("_add8", row.add8).replace("_emp8_empId", row.emp8.empId);
				} else {
					jsonStr1 = jsonStr1.replace("_emp8_name", "").replace("_add8", "").replace("_emp8_empId", "");
				}
				authority("com.ht.action.DutyAction.update", jsonStr1, "edit")
			}
		} else if (rows.length == 0) {
			$.messager.alert("提示", "请先选择一个需要修改的商品", "info");
		}
	}
	
	function editDuty() {
		if ($("#editForm").form("validate")) {
			$.post("<%=path %>/duty/update",
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
	
	function empName(value, row, index) {
		if (value != null) {
			if (value.name != "") {
				return value.name;
			}
		} else {
		return "暂无";
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
		else if(value == "7") 
			return "星期日";
	}
	
 	function dutyAdd(value, row, index) {
		if(value != null && value != "")
			return value;
		return "暂无";
	}
	
	function showSelectDept(aa) {
		empStr = aa;
			$("#list2").datagrid({url:"<%=path %>/dept/queryPage"});
		$("#selectDept").window("open");
	}
	
	function closeSelect(aa) {
		if (aa == 1) {
		$("#selectDept").window("close");
		} else if(aa == 2) {
			$("#selectEmpWin").window("close");
		}
	}
	
	function confirmDept() {
		var selectDept = $("#list2").datagrid("getSelected"); // 获取首个选中的数据
		if (selectDept) {
			$("#selectEmpWin").window("open"); // 打开学生窗口
			$("#list3").datagrid({url:"<%=path %>/emp2/pager?depId=" + selectDept.depId}); // 动态赋值
			$("#selectDept").window("close"); // 打开学生窗口
		} else {
			$.messager.alert("提示", "请选择部门", "error");
		}
	}
	
	function confirmEmp() {
		var selectEmp = $("#list3").datagrid("getSelected"); // 获取首个选中的数据
		if (selectEmp) {
			if(empStr == '1') {
			$("#empId1").val("" + selectEmp.empId);
			$("#empName1").textbox("setValue", selectEmp.name);
			} else if(empStr == '2') {
				$("#empId2").val("" + selectEmp.empId);
				$("#empName2").textbox("setValue", selectEmp.name);
			} else if(empStr == '3') {
				$("#empId3").val("" + selectEmp.empId);
				$("#empName3").textbox("setValue", selectEmp.name);
			} else if(empStr == '4') {
				$("#empId4").val("" + selectEmp.empId);
				$("#empName4").textbox("setValue", selectEmp.name);
			} else if(empStr == '5') {
				$("#empId5").val("" + selectEmp.empId);
				$("#empName5").textbox("setValue", selectEmp.name);
			} else if(empStr == '6') {
				$("#empId6").val("" + selectEmp.empId);
				$("#empName6").textbox("setValue", selectEmp.name);
			} else if(empStr == '7') {
				$("#empId7").val("" + selectEmp.empId);
				$("#empName7").textbox("setValue", selectEmp.name);
			} else if(empStr == '8') {
				$("#empId8").val("" + selectEmp.empId);
				$("#empName8").textbox("setValue", selectEmp.name);
			}
			$("#selectEmpWin").window("close");
		} else {
			$.messager.alert("提示", "请选择员工", "error");
		}
	}
</script>

</head>
<body style="height:100%;">

<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/duty/pager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'',checkbox:true">编号</th>
				<th data-options="field:'weekDay',width:80" formatter="weekDay">周几</th>
				<th data-options="field:'add1',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp1',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add2',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp2',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add3',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp3',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add4',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp4',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add5',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp5',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add6',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp6',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add7',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp7',width:80" formatter="empName">值班老师</th>
				<th data-options="field:'add8',width:80" formatter="dutyAdd">值班范围</th>
				<th data-options="field:'emp8',width:80" formatter="empName">值班老师</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showEditDutyWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		</div>
	</div>
	
	<div id="editWin" class="easyui-window" title="修改值班" data-options="closed:true" style="width:500px;height:auto;">
		<form id="editForm">
			<input type="hidden" id="dutyId" name="duty.dutyId" />
			<input type="hidden" id="empId1" name="duty.emp1.empId" />
			<input type="hidden" id="empId2" name="duty.emp2.empId" />
			<input type="hidden" id="empId3" name="duty.emp3.empId" />
			<input type="hidden" id="empId4" name="duty.emp4.empId" />
			<input type="hidden" id="empId5" name="duty.emp5.empId" />
			<input type="hidden" id="empId6" name="duty.emp6.empId" />
			<input type="hidden" id="empId7" name="duty.emp7.empId" />
			<input type="hidden" id="empId8" name="duty.emp8.empId" />
			
			<table id="editTable">
				<tr>
					<td style="width: 100px;">周几</td>
					<td><input class="easyui-validatebox easyui-textbox" name="duty.weekDay" data-options="required:true,validType:'length[1,1]', editable:false"/></td>
				</tr>
				<tr>
					<td>地点1</td>
				<td><input class="easyui-validatebox easyui-textbox" name="duty.add1" data-options="validType:'length[2,10]'"/></td>
				</tr>
				<tr>
					<td>员工1</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName1" name="duty.emp1.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('1');">选择员工1</a>
					</td>
				</tr>
				<tr>
					<td>地点2</td>
				<td><input class="easyui-validatebox easyui-textbox" name="duty.add2" data-options="validType:'length[2,10]'"/></td>
				</tr>
				<tr>
					<td>员工2</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName2" name="duty.emp2.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('2');">选择员工2</a>
					</td>
				</tr>
				<tr>
					<td>地点3</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add3" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工3</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName3" name="duty.emp3.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('3');">选择员工3</a>
					</td>
				</tr>
				<tr>
					<td>地点4</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add4" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工4</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName4" name="duty.emp4.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('4');">选择员工4</a>
					</td>
				</tr>
				<tr>
					<td>地点5</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add5" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工5</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName5" name="duty.emp5.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('5');">选择员工5</a>
					</td>
				</tr>
				<tr>
					<td>地点6</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add6" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工6</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName6" name="duty.emp6.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('6');">选择员工6</a>
					</td>
				</tr>
				<tr>
					<td>地点7</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add7" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工7</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName7" name="duty.emp7.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('7');">选择员工7</a>
					</td>
				</tr>
				<tr>
					<td>地点8</td>
				<td>
					<input class="easyui-validatebox easyui-textbox" name="duty.add8" data-options="validType:'length[2,10]'"/>
				</td>
				</tr>
				<tr>
					<td>员工8</td>
					<td>
						<input class="easyui-textbox" type="text" id="empName8" name="duty.emp8.name" data-options="required:true, 'disabled':true" ></input>
						<a href="javascript:;" onclick="showSelectDept('8');">选择员工8</a>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" onclick="editDuty();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 选择部门的窗口 -->
	<div id="selectDept" class="easyui-window" title="选择部门" data-options="closed:true" style="width:488px;height:auto;padding:5px;background-color:lightBlue;">
						<table id="list2" class="easyui-datagrid"
							data-options="toolbar:'#tb2',
							singleSelect:true,
							collapsible:true,
							rownumbers:true,
							autoRowHeight:true,
							pagination:true,
							pageSize:20" style="height:auto;">
						<thead>
							<tr>
								<th data-options="field:'depId',checkbox:true">部门编号</th>
								<th data-options="field:'depName',width:80">部门名</th>
							</tr>
						</thead>
					</table>
					<div style="text-align:right;padding:5px">
				    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="confirmDept();">确定</a>
				    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeSelect(1)">取消</a>
				   </div>
			</div>
	<!-- 选择员工的窗口 -->
	<div id="selectEmpWin" class="easyui-window" title="选择员工" data-options="closed:true" style="width:488px;height:auto;padding:5px;background-color:lightBlue;">
						<table id="list3" class="easyui-datagrid"
							data-options="toolbar:'#tb2',
							singleSelect:true,
							collapsible:true,
							rownumbers:true,
							autoRowHeight:true,
							pagination:true,
							pageSize:20" style="height:auto;">
						<thead>
							<tr>
								<th data-options="field:'empId',checkbox:true">员工编号</th>
								<th data-options="field:'name',width:80">员工名</th>
							</tr>
						</thead>
					</table>
					<div style="text-align:right;padding:5px">
				    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="confirmEmp();">确定</a>
				    <a href="javascript:void(0)" data-options="iconCls:'icon-clear'" class="easyui-linkbutton" onclick="closeSelect(2)">取消</a>
				   </div>
			</div>
</body>
</html>