<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正式学生</title>
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

	var jsonStr = '{"stu.stuId":"_stuid","stu.gender":"_gender","stu.roomId":"_roomid","stu.gradeId":"_gradeid","abcdef":"_abcdef","stu.stuNo":"_stuno","stu.emp.empId":"_empid","stu.name":"_name","stu.idCard":"_idcard","stu.phone":"_phone","stu.qq":"_qq","stu.wechat":"_wechat","stu.school":"_school","stu.birthday":"_birthday","stu.address":"_address","stu.nation":"_nation","stu.residence":"_residence","stu.parentName":"_parentname","stu.parentPhone":"_parentphone","stu.startDay":"_startday","stu.des":"_des","stu.empId":"_empid","stu.status":"_status","stu.stuStatus":"_stustatus","stu.role":"_role"}';

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
						var gender = $('#gender').combobox('getData');
						$('#gender').combobox('select', gender[0].value);
						var role = $('#role').combobox('getData');
						$('#role').combobox('select', role[0].value);
						$("#addEmpName").textbox('setValue', '${sessionScope.emp.name }');
					} else if (method == "edit") {
						$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
						$("#editWin").window("open"); // 打开编辑的窗口
					}
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
			}, "json");
	}
	
	function showAddStuWin() {
		authority('com.ht.action.StuAction.save?flag=3', "", "add");
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
				$.messager.prompt('缴费提示', '请缴纳学费，正式学生基础费用是￥3500', function(r){
					if (r){
						if (!isNaN(r)) {
							$.post("<%=path %>/stu/save?flag=3&r=" + r,
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
			$.messager.alert("提示", "请输入正确的数据", "info");
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
			var jsonStr1 = jsonStr.replace("_stuid", row.stuId).replace("_gender", row.gender).replace("_abcdef", row.pwd).replace("_stuno", row.stuNo).replace("_empid", row.emp.empId).replace("_name", row.name).replace("_idcard", getValue(row.idCard)).replace("_phone", row.phone).replace("_qq", getValue(row.qq)).replace("_wechat", getValue(row.wechat)).replace("_school", getValue(row.school)).replace("_birthday", row.birthday).replace("_address", getValue(row.address)).replace("_nation", getValue(row.nation)).replace("_residence", getValue(row.residence)).replace("_parentname", getValue(row.parentName)).replace("_parentphone", getValue(row.parentPhone)).replace("_startday", row.startDay).replace("_des", getValue(row.des)).replace("_status", row.status).replace("_stustatus", row.stuStatus).replace("_role", getValue(row.role));
			if (row.roomId == null || row.roomId == '' || row.roomId == 'null') {
				jsonStr1 = jsonStr1.replace('"stu.roomId":"_roomid",', '');
			} else {
				jsonStr1 = jsonStr1.replace('"stu.roomId":"_roomid",', '"stu.roomId":"' + getValue(row.roomId) + '",');
			}
			if (row.gradeId == null || row.gradeId == '' || row.gradeId == 'null') {
				jsonStr1 = jsonStr1.replace('"stu.gradeId":"_gradeid",', '');
			} else {
				jsonStr1 = jsonStr1.replace('"stu.gradeId":"_gradeid",', '"stu.gradeId":"' + getValue(row.gradeId) + '",');
			}
			authority('com.ht.action.StuAction.update?flag=3', jsonStr1, "edit");
			
		} else {
			$.messager.alert("提示","请选择一个需要修改的学生", "error");
		}
	}
	
	function editStu() {
		if ($('#editForm').form("validate")) {
			$.post("<%=path %>/stu/update?flag=3",
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
	

	function formatterRoomName(value) {
		if (value != null) {
			return value.name;
		} else {
			return "暂无";
		}
	}
	
	function formatterGradeName(value) {
		if (value != null) {
			return value.name;
		} else {
			return "暂无";
		}
	}
	
	function queryByStuName(name, value) {
		$.get("<%=path %>/stu/fuzzySearch3?name=" + name + "&value=" + value, 
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
			url:'<%=path %>/stu/officialStuByPager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20,toolbar:'#tb'" style="height: 100%;">
	<thead>
			<tr>
				<th data-options="field:'stuId',checkbox:true">编号</th>
				<th data-options="field:'stuNo',width:60">学号</th>
				<th data-options="field:'name',width:60">姓名</th>
				<!-- <th data-options="field:'idCard',width:80">身份证</th>
				<th data-options="field:'phone',width:80">手机</th>
				<th data-options="field:'qq',width:80">QQ</th>
				<th data-options="field:'wechat',width:80">微信</th> -->
				<th data-options="field:'school',width:120">毕业学校</th>
				<th data-options="field:'age',width:40">年龄</th>
				<th data-options="field:'birthday',width:70" formatter="formatterDate">生日</th>
				<th data-options="field:'gender',width:40" formatter="formatterGender">性别</th>
				<!-- <th data-options="field:'address',width:80">地址</th>
				<th data-options="field:'nation',width:80">籍贯</th>
				<th data-options="field:'residence',width:80">户口性质</th> -->
				<th data-options="field:'grade',width:80" formatter="formatterGradeName">班级</th>
				<th data-options="field:'room',width:80" formatter="formatterRoomName">宿舍</th>
				<!-- <th data-options="field:'parentName',width:80">家长姓名</th>
				<th data-options="field:'parentPhone',width:80">家长电话</th> -->
				<th data-options="field:'startDay',width:70" formatter="formatterDate">入学时间</th>
				<th data-options="field:'emp',width:60" formatter="formatEmpName">招生老师</th>
				<th data-options="field:'des',width:120">描述</th>
				<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
				<th data-options="field:'stuStatus',width:60" formatter="formatterStuStatus">学生状态</th>
				<th data-options="field:'role',width:60">班级角色</th>
				<th data-options="field:'operation',width:40" formatter="operation">操作</th>
			</tr>
	</thead>
</table>

<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="javascript:;" onclick="showAddStuWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
		<a href="javascript:;" onclick="showEditStuWin()" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
	</div>
	<div>
		<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
		<div id="mm" style="width:120px">
			<div data-options="name:'stuName',iconCls:'icon-hamburg-customers'">学生姓名</div>
		</div>
	</div>
</div>

<div class="easyui-window" id="addWin" title="添加学生" data-options="closed:true" style="width:600px;height:370px;">
		<form id="addForm">
			<table>
				<tr>
					<td style="padding: 5px;">招生老师</td>
					<td><input class="easyui-textbox" id="addEmpName" name="stu.emp.name" data-options="disabled:true" /></td>
					<td style="padding: 5px;">性别</td>
					<td>
						<select class="easyui-combobox" name="stu.gender" id="gender" style="width:173px;">
		    				<option value="male" selected="selected">男</option>
		    				<option value="female">女</option>
		    			</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 5px;">学生姓名</td>
					<td><input class="easyui-textbox" id="name" name="stu.name" data-options="required:true"/></td>
					<td style="padding: 5px;">地址</td>
					<td><input class="easyui-textbox" id="address" name="stu.address" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">生日</td>
					<td><input class="easyui-datebox" id="birthday" name="stu.birthday" data-options="required:true, editable:false"/></td>
					<td style="padding: 5px;">籍贯</td>
					<td><input class="easyui-textbox" id="nation" name="stu.nation" /></td>				
				</tr>
				<tr>
					<td style="padding: 5px;">身份证</td>
					<td><input class="easyui-validatebox easyui-textbox" id="idCard" name="stu.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
					<td style="padding: 5px;">户口性质</td>
					<td><input class="easyui-textbox" id="residence" name="stu.residence" /></td>				
				</tr>
				<tr>
					<td style="padding: 5px;">手机号</td>
					<td><input class="easyui-validatebox easyui-numberbox" id="phone" name="stu.phone" data-options="required:true,validType:'length[11,11]'"/></td>
					<td style="padding: 5px;">家长姓名</td>
					<td><input class="easyui-textbox" id="parentName" name="stu.parentName" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">QQ号</td>
					<td><input class="easyui-textbox" id="qq" name="stu.qq" /></td>
					<td style="padding: 5px;">家长电话</td>
					<td><input class="easyui-textbox" id="parentPhone" name="stu.parentPhone" data-options="validType:'length[11,11]'" /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">微信号</td>
					<td><input class="easyui-textbox" id="wechat" name="stu.wechat" /></td>
					<td style="padding: 5px;">入学时间</td>
					<td><input class="easyui-datebox" id="startDay" name="stu.startDay" data-options="required:true"  /></td>
				</tr>
				<tr>
					<td style="padding: 5px;">毕业学校</td>
					<td><input class="easyui-textbox" id="school" name="stu.school" /></td>
					<td style="padding: 5px;">描述</td>
					<td><input class="easyui-textbox" id="des" name="stu.des" /></td>
				</tr>
				<%-- <tr>
					<td style="padding: 5px;">班级角色</td>
					<td>
						<select class="easyui-combobox" name="stu.role" id="role" style="width:173px;">
		    				<option value="学生" selected="selected">学生</option>
		    				<option value="班长">班长</option>
		    				<option value="学习委员">学习委员</option>
		    				<option value="生活委员">生活委员</option>
		    				<option value="体育委员">体育委员</option>
		    				<option value="纪律委员">纪律委员</option>
		    				<option value="文艺委员">文艺委员</option>
		    			</select>
					</td>
				</tr> --%>
				<tr>
					<td>
						<a href="javascript:;" onclick="addStu();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
</div>

<div class="easyui-window" id="editWin" title="修改学生" data-options="closed:true" style="width:600px;height:370px;">
	<form id="editForm">
		<input type="hidden" id="id" name="stu.stuId" />
		<input type="hidden" id="stuNo" name="stu.stuNo" />
		<input type="hidden" id="empId" name="stu.emp.empId" />
		<input type="hidden" id="abcdef" name="abcdef" />
		<input type="hidden" id="gradeId" name="stu.gradeId" /> 
		<input type="hidden" id="roomId" name="stu.roomId" />
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
				<td style="padding: 5px;">生日</td>
				<td><input class="easyui-datebox" id="editBirthday" name="stu.birthday" data-options="required:true, editable:false" /></td>
				<td style="padding: 5px;">地址</td>
				<td><input class="easyui-textbox" id="address" name="stu.address" /></td>
			</tr>
			<tr>
				<td style="padding: 5px;">身份证</td>
				<td><input class="easyui-validatebox easyui-textbox" id="idCard" name="stu.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
				<td style="padding: 5px;">籍贯</td>
				<td><input class="easyui-textbox" id="nation" name="stu.nation" /></td>
			</tr>
			<tr>
				<td style="padding: 5px;">手机号</td>
				<td><input class="easyui-validatebox easyui-numberbox" id="phone" name="stu.phone" data-options="required:true,validType:'length[11,11]'"/></td>
				<td style="padding: 5px;">户口性质</td>
				<td><input class="easyui-textbox" id="residence" name="stu.residence" /></td>
			</tr>
			<tr>
				<td style="padding: 5px;">QQ号</td>
				<td><input class="easyui-textbox" id="qq" name="stu.qq" /></td>
				<td style="padding: 5px;">家长姓名</td>
				<td><input class="easyui-textbox" id="parentName" name="stu.parentName" /></td>
			</tr>
			<tr>
				<td style="padding: 5px;">微信号</td>
				<td><input class="easyui-textbox" id="wechat" name="stu.wechat" /></td>
				<td style="padding: 5px;">家长电话</td>
				<td><input class="easyui-validatebox easyui-textbox" id="parentPhone" name="stu.parentPhone" data-options="validType:'length[11,11]'"/></td>
			</tr>
			<tr>
				<td style="padding: 5px;">毕业学校</td>
				<td><input class="easyui-textbox" id="school" name="stu.school" /></td>
				<td style="padding: 5px;">描述</td>
				<td><input class="easyui-textbox" id="des" name="stu.des" /></td>
			</tr>
			<tr>
				<td style="padding: 5px;">入学时间</td>
				<td><input class="easyui-validatebox easyui-datebox" id="startDay" name="stu.startDay" /></td>
				<td style="padding: 5px;">班级角色</td>
				<td>
					<input class="easyui-combobox" id="role" name="stu.role" data-options="
									data:[
										{'id':'学生',
										'text':'学生'},
										{'id':'班长',
										'text':'班长'},
										{'id':'学习委员',
										'text':'学习委员'},
										{'id':'生活委员',
										'text':'生活委员'},
										{'id':'体育委员',
										'text':'体育委员'},
										{'id':'纪律委员',
										'text':'纪律委员'},
										{'id':'文艺委员',
										'text':'文艺委员'}
									],
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									'editable':false
									" />
				</td>
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