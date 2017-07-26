<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预定学生</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/datagrid-detailview.js"></script>
<script>

	var jsonStr = '{"stu.stuId":"_stuid","stu.emp.empId":"_empid","stu.name":"_name","stu.idCard":"_idcard","stu.phone":"_phone","stu.qq":"_qq","stu.wechat":"_wechat","stu.school":"_school","stu.birthday":"_birthday","stu.gender":"_gender","stu.address":"_address","stu.nation":"_nation","stu.residence":"_residence","stu.parentName":"_parentname","stu.parentPhone":"_parentphone","stu.des":"_des","stu.status":"_status","stu.stuStatus":"_stustatus"}';
	
	$(function() {
		setPagination("list");
		
		 $('#list').datagrid({
             view: detailview,
             detailFormatter:function(index,row){
                 return '<div class="ddv" style="padding:5px 0"></div>';
             },
             onExpandRow: function(index,row){
                 var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
                 ddv.panel({
                     height:105,
                     border:false,
                     cache:false,
                     href:'<%=path %>/stu/details?stuId=' + row.stuId,
                     onLoad:function(){
                         $('#list').datagrid('fixDetailRowHeight',index);
                     }
                 });
                 $('#list').datagrid('fixDetailRowHeight',index);
             }
         });
		
	})
	
	function authority(methodName, jsonStr, method) {
		$.get("<%=path %>/auth/authority?methodName=" + methodName,
			function(data) {
				if (data.result.result == "success") {
					if (method == "add") {
						$("#addForm").form("clear"); // 清除表单的内容
						$("#addWin").window("open"); // 打开窗口
						var data = $('#gender').combobox('getData');
						$('#gender').combobox('select', data[0].value);
						$("#addEmpName").textbox('setValue', '${sessionScope.emp.name }');
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					} else if(method = "official") {
						var row = $("#list").datagrid("getSelected");
						if (row) {
							$.messager.prompt('缴费提示', '请缴纳学费，正式学生基础费用是￥3000', function(r){
								if (r) {
									if (!isNaN(r)) {
										$.post("<%=path %>/stu/updateStuStatus?flag=3&r=" + r + "&stuId=" + row.stuId,
												function(data) {
													if (data.result.result == "success") {
														$('#list').datagrid('reload');
														$.messager.alert("提示", data.result.message, "info");
													}else if(data.result.result == "fail") {
														$.messager.alert("提示", data.result.message, "info");
													}
										}, "json"
										);
									} else {
										$.messager.alert("提示", "请输入正确的金额", "info");
									}
								}
							});
						} else {
							$.messager.alert("提示","请选择一个需要转为正式的学生", "info");
						}
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showAddStuWin() {
		authority('com.ht.action.StuAction.save?flag=2', "", "add");
	}
	
	function datedaxiao(t1, t2) {
		var strs1 = new Array(); //定义一数组
		strs1 = t1.split("-"); //字符分割
		var strs2 = new Array(); //定义一数组
		strs2 = t2.split("-"); //字符分割
		if (strs1[0] > strs2[0]) {
			return false;
		} else if (strs1[0] < strs2[0]) {
			return true;
		} else {
		}
		if (strs1[1] > strs2[1]) {
			return false;
		} else if (strs1[1] < strs2[1]) {
			return true;
		} else {
		}
		if (strs1[2] > strs2[2]) {
			return false;
		} else if (strs1[2] < strs2[2]) {
			return true;
		} else {
		}
		return true;
	}
	
	function addStu() {
		if ($('#addForm').form("validate")) {
			$('#addWin').window('close');
			var birthday = $("#birthday").datebox('getValue');
			var newDate = new Date();
			if (datedaxiao(birthday, formatterDate(newDate))) {
				$.messager.prompt('缴费提示', '请缴纳学费，预定学生基础费用是￥500', function(r){
					if (r){
						if (!isNaN(r)) {
								$.post("<%=path %>/stu/save?flag=2&r=" + r,
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
							
						} else {
							$.messager.alert("提示", "请输入正确的金额", "info");
						}
					}
				});
			} else {
				$.messager.alert("提示", "请选择正确的生日", "error");
			}
		}else {
			$.messager.alert("提示", "请输入正确的数据", "error");
		}		
	}
	
	function getValue(value) {
		if (value == null || value == 'null') {
			return "";
		} else {
			return value;
		}
	}
	
	function showEditStuWin() {
		var row = $('#list').datagrid("getSelected");
		if (row) {
			var jsonStr1 = jsonStr.replace("_stuid", row.stuId).replace("_empid", row.emp.empId).replace("_name", row.name).replace("_idcard", getValue(row.idCard)).replace("_phone", row.phone).replace("_qq", getValue(row.qq)).replace("_wechat", getValue(row.wechat)).replace("_school", getValue(row.school)).replace("_birthday", row.birthday).replace("_gender", row.gender).replace("_address", getValue(row.address)).replace("_nation", getValue(row.nation)).replace("_residence", getValue(row.residence)).replace("_parentname", getValue(row.parentName)).replace("_parentphone", getValue(row.parentPhone)).replace("_des", getValue(row.des)).replace("_status", row.status).replace("_stustatus", row.stuStatus);
			authority('com.ht.action.StuAction.update?flag=2', jsonStr1, "edit");
		} else {
			$.messager.alert("提示","请选择一个需要修改的学生", "info");
		}
	}
	
	function editStu() {
		if ($('#editForm').form("validate")) {
			$.post("<%=path %>/stu/update?flag=2",
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
	
	function official() {
		authority('com.ht.action.StuAction.updateStuStatus', "", "official");
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
		if (row.stuStatus == "reserve") {
			return "预定"
		}
	}
	
	function operation(value, row, index) {
		if (row.status == 1) {
			return "<a href='javascript:;' onclick=frostStatus('" + row.stuId + "','" + row.emp.empId + "','" + row.stuStatus + "')>冻结</a>";
		}
		return "<a href='javascript:;' onclick=activationStatus('" + row.stuId + "','" + row.emp.empId + "','" + row.stuStatus + "')>激活</a>";
	}
	
	function frostStatus(id, stuEmpId, stuStatus) {
		$.get("<%=path %>/stu/frost?id=" + id + "&stuEmpId=" + stuEmpId + "&stuStatus=" + stuStatus,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function activationStatus(id, stuEmpId, stuStatus) {
		$.get("<%=path %>/stu/activation?id=" + id + "&stuEmpId=" + stuEmpId + "&stuStatus=" + stuStatus,
				function(data) {
					if (data.result.result == "success") {
						$("#list").datagrid("reload"); // 更新表格
					} else if (data.result.result == "fail") {
						$.messager.alert("提示", data.result.message, "error");
					}
			}, "json");
	}
	
	function formatEmpName(value) {
		if (value != null) {
			return value.name;	
		}
	}
	
	function queryByStuName(name, value) {
		$.get("<%=path %>/stu/fuzzySearch2?name=" + name + "&value=" + value, 
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
	    	if (name == "stuName") {
	    		queryByStuName(name, value);
	    	}
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
			url:'<%=path %>/stu/reserveStuByPager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,toolbar:'#tb'" style="height: 100%;">
	<thead>
			<tr>
				<th data-options="field:'stuId',checkbox:true">编号</th>
				<th data-options="field:'name',width:60">姓名</th>
				<!-- <th data-options="field:'idCard',width:80">身份证</th>
				<th data-options="field:'phone',width:80">手机</th>
				<th data-options="field:'qq',width:80">QQ</th>
				<th data-options="field:'wechat',width:80">微信</th> -->
				<th data-options="field:'school',width:120">毕业学校</th>
				<th data-options="field:'age',width:40">年龄</th>
				<th data-options="field:'birthday',width:120" formatter="formatterDate">生日</th>
				<th data-options="field:'gender',width:40" formatter="formatterGender">性别</th>
				<!-- <th data-options="field:'address',width:80">地址</th>
				<th data-options="field:'nation',width:80">籍贯</th>
				<th data-options="field:'residence',width:80">户口性质</th>
				<th data-options="field:'parentName',width:80">家长姓名</th>
				<th data-options="field:'parentPhone',width:80">家长电话</th> -->
				<th data-options="field:'des',width:130">描述</th>
				<th data-options="field:'emp',width:60" formatter="formatEmpName">招生老师</th>
				<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
				<th data-options="field:'stuStatus',width:60" formatter="formatterStuStatus">学生状态</th>
				<th data-options="field:'operation',width:40" formatter="operation">操作</th>
			</tr>
	</thead>
</table>

<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="javascript:;" onclick="showAddStuWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
		<a href="javascript:;" onclick="showEditStuWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		<a href="javascript:;" onclick="official();" class="easyui-linkbutton" iconCls="icon-add">转为正式学生</a>
	</div>
	<div>
		<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
		<div id="mm" style="width:120px">
			<div data-options="name:'stuName',iconCls:'icon-hamburg-customers'">学生姓名</div>
		</div>
	</div>
</div>

<div class="easyui-window" id="addWin" title="添加学生" data-options="closed:true" style="width:600px;height:350px;">
		<form id="addForm">
			<table>
				<tr>
					<td style="padding: 5px;">招生老师</td>
					<td><input class="easyui-textbox" id="addEmpName" name="stu.emp.name" data-options="disabled:true" /></td>
					<td style="padding: 5px;">生日</td>
					<td><input class="easyui-datebox" id="birthday" name="stu.birthday" data-options="required:true, editable:false"/></td>
				</tr>
				<tr>
					<td style="padding: 5px;">学生姓名</td>
					<td><input class="easyui-textbox" id="name" name="stu.name" data-options="required:true"/></td>
					<td style="padding: 5px;">性别</td>
					<td>
						<select class="easyui-combobox" name="stu.gender" id="gender" style="width:173px;">
		    				<option value="male" selected="selected">男</option>
		    				<option value="female">女</option>
		    			</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 5px;">身份证</td>
					<td><input class="easyui-validatebox easyui-textbox" id="idCard" name="stu.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
					<td style="padding: 5px;">地址</td>
					<td><input class="easyui-textbox" id="address" name="stu.address" /></td>				
				</tr>
				<tr>
					<td style="padding: 5px;">手机号</td>
					<td><input class="easyui-validatebox easyui-numberbox" id="phone" name="stu.phone" data-options="required:true,validType:'length[11,11]'"/></td>
					<td style="padding: 5px;">籍贯</td>
					<td><input class="easyui-textbox" id="nation" name="stu.nation" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">QQ号</td>
					<td><input class="easyui-textbox" id="qq" name="stu.qq" /></td>
					<td style="padding: 5px;">户口性质</td>
					<td><input class="easyui-textbox" id="residence" name="stu.residence" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">微信号</td>
					<td><input class="easyui-textbox" id="wechat" name="stu.wechat" /></td>
					<td style="padding: 5px;">家长姓名</td>
					<td><input class="easyui-textbox" id="parentName" name="stu.parentName" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">毕业学校</td>
					<td><input class="easyui-textbox" id="school" name="stu.school" /></td>
					<td style="padding: 5px;">家长电话</td>
					<td><input class="easyui-validatebox easyui-textbox" id="parentPhone" name="stu.parentPhone" data-options="validType:'length[11,11]'" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">描述</td>
					<td><input class="easyui-textbox" id="des" name="stu.des" /></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="addStu();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
</div>

<div class="easyui-window" id="editWin" title="修改学生" data-options="closed:true" style="width:600px;height:350px;">
		<form id="editForm">
			<input type="hidden" id="id" name="stu.stuId" />
			<input type="hidden" id="empId" name="stu.emp.empId" />
			<table>
				<tr>
					<td style="padding: 5px;">学生姓名</td>
					<td><input class="easyui-validatebox easyui-textbox" id="name" name="stu.name" data-options="required:true"/></td>
					<td style="padding: 5px;">性别</td>
					<td>
						<input class="easyui-combobox" id="gender" name="stu.gender" data-options="required:true,
									data:[
										{'id':'male',
										'text':'男'},
										{'id':'female',
										'text':'女'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									'editable':false
									" />
					</td>
				</tr>
				<tr>
					<td style="padding: 5px;">身份证</td>
					<td><input class="easyui-validatebox easyui-textbox" id="idCard" name="stu.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
					<td style="padding: 5px;">地址</td>
					<td><input class="easyui-textbox" id="address" name="stu.address" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">手机号</td>
					<td><input class="easyui-validatebox easyui-numberbox" id="phone" name="stu.phone" data-options="required:true,validType:'length[11,11]'"/></td>
					<td style="padding: 5px;">籍贯</td>
					<td><input class="easyui-textbox" id="nation" name="stu.nation" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">QQ号</td>
					<td><input class="easyui-textbox" id="qq" name="stu.qq" /></td>
					<td style="padding: 5px;">户口性质</td>
					<td><input class="easyui-textbox" id="residence" name="stu.residence" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">微信号</td>
					<td><input class="easyui-textbox" id="wechat" name="stu.wechat" /></td>
					<td style="padding: 5px;">家长姓名</td>
					<td><input class="easyui-textbox" id="parentName" name="stu.parentName" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">毕业学校</td>
					<td><input class="easyui-textbox" id="school" name="stu.school" /></td>
					<td style="padding: 5px;">家长电话</td>
					<td><input class="easyui-validatebox easyui-textbox" id="parentPhone" name="stu.parentPhone" data-options="validType:'length[11,11]'" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">描述</td>
					<td><input class="easyui-textbox" id="des" name="stu.des" /></td>
					<td style="padding: 5px;">生日</td>
					<td><input class="easyui-datebox" id="editBirthday" name="stu.birthday" data-options="required:true, editable:false"/></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="editStu();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
</div>
</body>
</html>