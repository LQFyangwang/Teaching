<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/datagrid-detailview.js"></script>
<script>
var jsonStr = '{"emp.empId":"_empId","emp.dept.depId":"_depId",' +

'"emp.name":"_name","emp.pwd":"_pwd","emp.idCard":"_idCard",'+

'"emp.nation":"_nation","emp.gender":"_gender","emp.fingerNo":"_fingerNo",' +

'"emp.birthday":"_birthday","emp.email":"_email","emp.phone":"_phone", '+

'"emp.qq":"_qq", "emp.wechat":"_wechat","emp.address":"_address","emp.married":"_married",' +

'"emp.contactName":"_contactName","emp.contactPhone":"_contactPhone","emp.college":"_college",' +

'"emp.major":"_major","emp.eduBack":"_eduBack","emp.bankName":"_bankName","emp.accountName":"_accountName",' +

'"emp.accountNo":"_accountNo", "emp.alipay":"_alipay","emp.hireDay":"_hireDay","emp.resignDay":"_resignDay",'+

'"emp.status":"_status", "emp.dept.name":"_deptName", "emp.role.name":"_roleName", "emp.role.roleId":"_roleId"}';


$(function() {
	setPagination("list");
	
	$('#list').datagrid({
        view: detailview,
        detailFormatter:function(index,row){
            return '<div class="ddv" style="padding:5px 0"></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            var empStr = row.birthday + "," + row.phone + "," + row.qq + "," + row.idCard +"," 
            	+ row.wechat + "," + row.address + "," + row.married + "," + row.contactName + "," 
            	+ row.contactPhone + "," + row.bankName +"," + row.accountName + "," + row.accountNo+ "," 
            	+row.alipay + "," + row.resignDay;
            	;
            ddv.panel({
                height:200,
                border:false,
                cache:false,
                href:'<%=path %>/ed/empDetails?empStr=' + empStr,
                onLoad:function(){
                    $('#list').datagrid('fixDetailRowHeight',index);
                }
            });
            $('#list').datagrid('fixDetailRowHeight',index);
        }
    });
});

function addEmp() {
	if ($("#addForm").form("validate")) {
		$.post("<%=path %>/emp/empSave",
			$("#addForm").serialize(),
			function (data) {
				if (data.result.result == "success") {
					$("#addWin").window("close"); // 关闭指定的窗口
					$("#list").datagrid("reload"); // 重新加载指定数据网格数据
					$.messager.alert("提示", data.result.message, "info");
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json"
		);
	} else {
		$.messager.alert("提示", "请输入正确的数据", "error");
	}
}

function authority(methodName, jsonStr, method) {
	$.get("<%=path %>/auth/authority?methodName=" + methodName,
		function(data) {
			if (data.result.result == "success") {
				if (method == "empSave") {
					$("#addForm").form("clear"); // 清除表单的内容
					var gender = $('#addGender').combobox('getData'); // 性别下拉框设置默认第一个
					$("#addGender ").combobox('select',gender[0].value);
					var married = $('#addMarried').combobox('getData'); // 是否已婚下拉框设置默认第一个选中
					$("#addMarried ").combobox('select',married[0].value);
					$.get("<%=path %>/dt/DeptTypeAll",function(data) {
						$("#addDept").combobox("loadData", data.results);
					}, "json");
					$.get("<%=path%>/rt/RoleTypeAll",function(data){	
						$("#roleType").combobox("loadData", data.results);
					}, "json");
					$("#addWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
				} else if (method == "empUpdate") {
					$("#editForm").form("load", JSON.parse(jsonStr)); // 把JSON对象row的值自动赋值给form表单
					$("#editWin").window("open"); // 打开编辑的窗口
				}
			} else if (data.result.result == "fail") {
				$.messager.alert("提示", data.result.message, "error");
			}
		}, "json");
}
function showAddEmpWin() {
	authority("com.ht.action.EmpAction.empSave","","empSave");
}
function showEditEmpWin(){
	var rows = $('#list').datagrid("getSelections"); // 获取数据表格中被选中的行数据
	if (rows.length > 1) {
		$.messager.alert("提示","请选择一个需要修改的员工", "info");
	} else if (rows.length == 1) {
		var row = rows[0];
		if (row) {
			var jsonStr1 = jsonStr.replace("_empId", row.empId).replace("_depId", row.dept.depId).
			replace("_name", row.name).replace("_pwd", row.pwd).replace("_idCard", row.idCard).
			replace("_nation", row.nation).replace("_gender", row.gender).replace("_fingerNo", row.fingerNo).
			replace("_birthday", row.birthday).replace("_email", row.email).replace("_phone", row.phone).
			replace("_qq", row.qq).replace("_wechat", row.wechat).replace("_address", row.address).
			replace("_married", row.married).replace("_contactName",row.contactName).replace("_contactPhone",row.contactPhone).
			replace("_college",row.college).replace("_major",row.major).replace("_eduBack",row.eduBack).replace("_bankName",row.bankName).
			replace("_accountName",row.accountName).replace("_accountNo",row.accountNo).replace("_alipay",row.alipay).replace("_hireDay",row.hireDay).
			replace("_resignDay",row.resignDay).replace("_status",row.status).replace("_deptName",row.dept.name).replace("_roleName",row.role.name).replace("_roleId",row.role.roleId);
            $("#editForm").form("load", JSON.parse(jsonStr1));
        	$.get("<%=path %>/dt/DeptTypeAll?deptRowId="+row.dept.deptId,function(data) {
        		$("#deptypeId").combobox("loadData", data.results);
        	}, "json");
        	$.get("<%=path%>/rt/RoleTypeAll?RoleID="+ row.role.roleId,function(data){	
        		$("#roleTypeId").combobox("loadData", data.results);
        	}, "json");
            $("#editWin").window("open");
		}
	} else if (rows.length == 0) {
		$.messager.alert("提示","请选择一个需要修改的员工", "info");
	}
}

function editEmp(){
	if ($('#editForm').form("validate")) {
		$.post("<%=path %>/emp/empUpdate",
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

function Empgender(value, row, index){
	if(row.gender == "female"){
		return "女"
	}else if(row.gender == "male"){
		return "男"
	}
}
function resignDayFormatter(value, row, index){
	if(row.resignDay==null){
		return "暂未离职";
	}
}
function operation(value, row, index) {
	if(row.status == 1){
		return "<a href='javascript:;' onclick=frostStatus('" + row.empId + "')>冻结</a>"
	}
	return "<a href='javascript:;' onclick=activationStatus('" + row.empId + "')>激活</a>";
}

function frostStatus(id) {
	$.get("<%=path %>/emp/frost?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}


function activationStatus(id) {
	$.get("<%=path %>/emp/activation?id=" + id,
			function(data) {
				if (data.result.result == "success") {
					$("#list").datagrid("reload"); // 更新表格
				} else if (data.result.result == "fail") {
					$.messager.alert("提示", data.result.message, "error");
				}
		}, "json");
}
	function depName(value) {
		return value.name;
	}

	function RoleName(value){
		return value.name;
	}
	
	
	function fuzzySearch(value,name){ // 根据员工姓名查询数据
	    if (value != "") {
	    	if (name == "sports") {
	    		queryByEmpName(value);
	    	}
	    } else {
	    	$.messager.alert("提示", "请输入名称", "error");
	    }
	}
	
	function queryByEmpName(value) {
		$.get("<%=path %>/emp/fuzzySearch?name=" + value, 
				function(data) {
						$("#list").datagrid("loadData", data.rows); // 动态加载表格数据
		}, "json")
	}
</script>
</head>
<body style="height:100%;">
	<table id="list" class="easyui-datagrid"
			data-options="toolbar:'#tb',
			singleSelect:true,
			collapsible:true,
			url:'<%=path %>/emp/empPager',
			method:'get',
			rownumbers:true,
			autoRowHeight:true,
			pagination:true,
			pageSize:20" style="height:100%;">
		<thead>
			<tr>
				<th data-options="field:'empId',checkbox:true">编号</th>
				<th data-options="field:'name',width:60">姓名</th>
				<th data-options="field:'gender',width:40" formatter="Empgender">性别</th>
				<!-- <th data-options="field:'birthday',width:120">出生日期</th>
				<th data-options="field:'idCard',width:150">身份证号码</th> -->
				<th data-options="field:'nation',width:40">籍贯</th>
				<th data-options="field:'phone',width:80">手机号</th>
				<!--<th data-options="field:'qq',width:80">QQ号</th>
				<th data-options="field:'wechat',width:80">微信号</th>
				<th data-options="field:'address',width:180">家庭地址</th>
				<th data-options="field:'married',width:50">是否结婚</th>
				<th data-options="field:'contactName',width:100">家庭联系人姓名</th>
				<th data-options="field:'contactPhone',width:100">家庭联系人手机号</th>-->
				<th data-options="field:'college',width:100">毕业院校</th>
				<th data-options="field:'major',width:80">专业</th>
				<th data-options="field:'eduBack',width:50">学历</th>
				<!-- <th data-options="field:'bankName',width:80">开户行名称</th>
				<th data-options="field:'accountName',width:80">银行卡姓名</th>
				<th data-options="field:'accountNo',width:180">银行账号</th>
				<th data-options="field:'alipay',width:80">支付宝账号</th> -->
				<th data-options="field:'fingerNo',width:60">指纹编号</th>
				<th data-options="field:'dept',width:70" formatter="depName">部门</th>
				<th data-options="field:'role',width:70" formatter="RoleName">角色</th>
				<th data-options="field:'email',width:80">邮箱</th>
				<th data-options="field:'hireDay',width:70" formatter="formatterDate">入职时间</th>
			<!-- 	<th data-options="field:'resignDay',width:150" formatter="resignDayFormatter">离职时间</th> -->
				<th data-options="field:'status',width:40" formatter="formatterStatus">状态</th>
				<th data-options="field:'operation',width:40" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" onclick="showAddEmpWin();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
			<a href="javascript:;" onclick="showEditEmpWin();" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
			<input id="ss" class="easyui-searchbox" style="width:300px"
			   data-options="searcher:fuzzySearch,prompt:'请输入名称',menu:'#mm'"></input>
		<div id="mm" style="width:120px">
			<div data-options="name:'sports',iconCls:'icon-hamburg-customers'">员工姓名</div>
		</div>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加员工" data-options="closed:true" style="width:600px;height:450px;">
		<form id="addForm">
				<table>
					<tr>
						<td>姓名</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.name" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>性别</td>
						<td>
                 			<select class="easyui-combobox" id="addGender" name="emp.gender" style="width:170px;" data-options="editable:false">
	    						<option value="male">男</option>
	    						<option value="female">女</option>
	    					</select>
						</td>
					</tr>
					<tr>
						<td>出生日期</td>
						<td><input class="easyui-datebox" name="emp.birthday" data-options="editable:false,required:'true'"/></td>
						<td>身份证号码</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
					</tr>
					<tr>
						<td>籍贯</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.nation" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>手机号</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.phone" data-options="required:true,validType:'length[11,11]'"/></td>
					</tr>
					<tr>
						<td>QQ号码</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.qq" data-options="required:true,validType:'length[6,11]'"/></td>
						<td>微信号</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.wechat" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>
						<td>家庭地址</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.address" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>是否结婚</td>
						<td>
							<select class="easyui-combobox" id="addMarried" name="emp.married" style="width:170px;" data-options="editable:false">
		    						<option value="是">是</option>
		    						<option value="否">否</option>
		    				</select>
						</td>
					</tr>
					<tr>
						<td>家庭联系人姓名</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.contactName" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>家庭联系人手机号</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.contactPhone" data-options="required:true,validType:'length[11,11]'"/></td>
					</tr>
					<tr>
						<td>毕业院校</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.college" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>专业</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.major" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>	
						<td>学历</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.eduBack" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>开户行名称</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.bankName" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>
						<td>银行卡姓名</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.accountName" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>银行卡账号</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.accountNo" data-options="required:true,validType:'length[19,19]'"/></td>
					</tr>
					<tr>
						<td>支付宝账号</td>
						<td><input class="easyui-validatebox easyui-numberbox" name="emp.alipay" data-options="required:true,validType:'length[1,20]'"/></td>
						<td>指纹编号</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.fingerNo" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>
						<td>所属部门</td>
						<td>
						 <input id="addDept" class="easyui-combobox" name="emp.dept.depId" data-options="required:true,
							valueField:'id',
							editable:'false',
							textField:'text',
							panelHeight:'auto',
							editable:false
						"/>
						</td>
						<td>角色类型</td>
					<td>
					 <input id="roleType" class="easyui-combobox" name="emp.role.roleId"  data-options="required:true,
					 	editable:'false',
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					"/>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input class="easyui-datebox" name="emp.hireDay" data-options="editable:false,required:'true'"/></td>
					<td>邮箱</td>
					<td><input class="easyui-validatebox easyui-textbox"  name="emp.email" data-options="prompt:'请输入邮箱',required:true,validType:'email'"></td>
				</tr>
				<tr>    
					<td>
						<a href="javascript:;" onclick="addEmp();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<div id="editWin" class="easyui-window" title="修改用户" data-options="closed:true" style="width:600px;height:450px;">
		<form id="editForm">
			<input type="hidden" name="emp.empId" />
			<input type="hidden" name="emp.roleId"/>
			<input type="hidden" name="emp.pwd"/>
			<table>
				<tr>
					<td>姓名</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.name" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>性别</td>
					<td>
						<select class="easyui-combobox" name="emp.gender" id="status" style="width:170px;">
	    						<option value="female">女</option>
	    						<option value="male">男</option>
	    				</select>
					</td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input class="easyui-datebox" name="emp.birthday" data-options="editable:false"/></td>
					<td>身份证号码</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.idCard" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>籍贯</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.nation" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>手机号</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.phone" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>QQ号码</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.qq" data-options="required:true"/></td>
					<td>微信号</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.wechat" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>家庭地址</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.address" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>是否结婚</td>
					<td>
						<select class="easyui-combobox" name="emp.married" id="status" style="width:100px;">
	    						<option value="是">是</option>
	    						<option value="否">否</option>
	    				</select>
					</td>
				</tr>
				<tr>
					<td>家庭联系人姓名</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.contactName" data-options="required:true,validType:'length[1,20]'"/></td>
					<td>家庭联系人手机号</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.contactPhone" data-options="required:true,validType:'length[1,20]'"/></td>
				</tr>
				<tr>
					<td>毕业院校</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.college" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>专业</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.major" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>学历</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.eduBack" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>开户行名称</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.bankName" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>银行卡姓名</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.accountName" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>银行卡账号</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.accountNo" data-options="required:true,validType:'length[1,20]'"/></td>
				</tr>
				<tr>
					<td>支付宝账号</td>
					<td><input class="easyui-validatebox easyui-numberbox" name="emp.alipay" data-options="required:true,validType:'length[1,20]'"/></td>
					<td>指纹编号</td>
					<td><input class="easyui-validatebox easyui-textbox" name="emp.fingerNo" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-validatebox textbox"  name="emp.email" data-options="prompt:'请输入邮箱',required:true,validType:'email'"></td>
					<td><input class="easyui-textbox" type="hidden" name="pwd" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38"></td>
				</tr>
				<tr>
					<td>部门类型</td>
					<td>
					 <input id="deptypeId" class="easyui-combobox" name="emp.dept.depId" data-options="required:true,
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:'false'
					"/>
					</td>
					<td>角色类型</td>
					<td>
					 <input id="roleTypeId" class="easyui-combobox" name="emp.role.roleId" data-options="required:true,
						method:'get',
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:'false'
					"/>
					</td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input class="easyui-datebox" name="emp.hireDay" data-options="editable:false"/></td>
					<td>离职时间</td>
					<td><input class="easyui-datebox" name="emp.resignDay" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="editEmp();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
					</td>
				</tr>
				<tr>    
			</table>
		</form>
	</div>
	
</body>
</html>