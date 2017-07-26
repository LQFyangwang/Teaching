<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
    	String path = request.getContextPath();
    %>
    <%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon-hamburg.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/themes/jeasyui.icons.hamburg.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<script>
	function showAddEmpWin() {
		$("#EmpWin").window("open"); // 打开指定的window， open表示打开，close表示关闭
		$("#EmpName").textbox('setValue', '${sessionScope.emp.name}'); //赋值
		$("#Day").textbox('setValue','${sessionScope.emp.birthday}')//赋值
	}
	
	function editEmp(){
		if ($('#editForm').form("validate")) {
			$.post("<%=path %>/emp/empInformat",
					$('#editForm').serialize(),
					function(data) {
						if (data.result.result == "success") {
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
</script>
</head>
<body>
<!--
	<table>
	<tr>
		<td>
		<h4>姓名:</h4>
				<s:if test="{#session.emp.name!=null}">
					${sessionScope.emp.name }
				</s:if>
				<s:else>
					暂无
				</s:else>
		</td>
	</tr>	
	</table >
	<h6>姓名:
	<s:if test="{#session.emp.name!=null}">
		${sessionScope.emp.name }
	</s:if>
	<s:else>
		暂无
	</s:else>
	</h6>
	<h6>性别：
		<s:if test="{#session.emp.gender =='male'}">
			男
		</s:if>
		<s:elseif test="{#session.emp.gender =='female'}">
			女
		</s:elseif>
	</h6>
	<h6>手机号码:
		<s:if test="{#session.emp.phone !=null}">
			${sessionScope.emp.phone }
		</s:if>
		<s:else>
			未填写手机号码
		</s:else>
	</h6>
	<h6>QQ号码:
		<s:if test="{#session.emp.qq.isEmpty()}">
			${sessionScope.emp.qq }
		</s:if>
		<s:else>
			未填写QQ号码
		</s:else>
	</h6>
	<h6>微信号:
		<s:if test="{#session.emp.wechat.isEmpty()}">
			${sessionScope.emp.wechat }
		</s:if>
		<s:elseif test="{#session.emp.wechat==null}">
			未填写微信号
		</s:elseif>
	</h6>
	<h6>家庭地址:
		<s:if test="{#session.emp.address.isEmpty()}">
			${sessionScope.emp.address}
		</s:if>
		<s:else>
			未填写家庭地址
		</s:else>
	</h6>
	<h6>出生日期:
		<s:if test="{#session.emp.birthday!=null}">
			${sessionScope.emp.birthday }
		</s:if>
		<s:else>
			未填写出生日期
		</s:else>
	</h6>
	<h6>邮箱:
		<s:if test="{#session.emp.email.isEmpty()}">
			${sessionScope.emp.email }
		</s:if>
	</h6>
	<a href="javascript:;" onclick="showAddEmpWin();" class="easyui-linkbutton" iconCls="icon-edit">修改信息</a>  -->
	
	<div id="EmpWin" class="easyui-window" title="个人信息" data-options="closed:false" style="width:600px;height:450px;">
		<form id="editForm">
			<input type="hidden" name="emp.empId" value="${sessionScope.emp.empId }" />
			<input type="hidden" name="emp.email" value="${sessionScope.emp.email }"/>
			<input type="hidden" name="emp.pwd" value="${sessionScope.emp.pwd }"/>
			<input type="hidden" name="emp.dept.depId" value="${sessionScope.emp.dept.depId }"/>
			<input type="hidden" name="emp.role.roleId" value="${sessionScope.emp.role.roleId }"/>
			<input type="hidden" name="emp.fingerNo" value="${sessionScope.emp.fingerNo}"/>
			<input type="hidden" name="emp.status" value="${sessionScope.emp.status }"/>
			<table>
				<tr>
					<td>姓名</td>
					<td><input class="easyui-validatebox easyui-textbox" id="EmpName" name="emp.name"  value ="${sessionScope.emp.name}" data-options="required:true,validType:'length[2,20]'"/></td>
					<td>性别</td>
					<td>
						<select class="easyui-combobox" name="emp.gender" id="status"  style="width:100px;">
								<s:if test='{#session.emp.gender =="male"}'>
									<option value="male">男</option> 
								</s:if>
								<s:if test='{#session.emp.gender =="female"}'>
									<option value="female">女</option>
								</s:if>
	    				</select>
					</td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input class="easyui-datetimebox" id="Day" name="emp.birthday" value="${sessionScope.emp.birthday}" data-options="editable:false"/></td>
					<td>手机号码</td>
					<td><input class="easyui-validatebox easyui-textbox" id="Empphone" name="emp.phone" value="${sessionScope.emp.phone }" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
				<tr>
					<td>QQ号</td>
					<td><input class="easyui-validatebox easyui-textbox" id="qq" name="emp.qq" data-options="required:true,validType:'length[2,20]'" value="${sessionScope.emp.qq }"/></td>
					<td>微信号</td>
					<td><input class="easyui-validatebox easyui-textbox" id="wechat" name="emp.wechat" data-options="required:true,validType:'length[2,20]'" value="${sessionScope.emp.wechat }"/></td>
				</tr>
				<tr>
						<td>身份证号码</td>
						<td><input class="easyui-validatebox easyui-numberbox"  value="${sessionScope.emp.idCard }" name="emp.idCard" data-options="required:true,validType:'length[18,18]'"/></td>
						<td>籍贯</td>
						<td><input class="easyui-validatebox easyui-textbox"  value="${sessionScope.emp.nation }" name="emp.nation" data-options="required:true,validType:'length[2,20]'"/></td>
				</tr>
					<tr>
						<td>家庭地址</td>
						<td><input class="easyui-validatebox easyui-textbox" name="emp.address" value="${sessionScope.emp.address }" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>是否结婚</td>
						<td>
							<select class="easyui-combobox" name="emp.married" id="status" style="width:100px;">
		    					<s:if test='{#session.emp.married =="是"}'>
		    						<option value="是">是</option>
								</s:if>
								<s:if test='{#session.emp.married =="否"}'>
									<option value="否">否</option>
								</s:if>
		    				</select>
						</td>
					</tr>
					<tr>
						<td>家庭联系人姓名</td>
						<td><input class="easyui-validatebox easyui-textbox" value="${sessionScope.emp.contactName }" name="emp.contactName" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>家庭联系人手机号</td>
						<td><input class="easyui-validatebox easyui-numberbox" value="${sessionScope.emp.contactPhone }"  name="emp.contactPhone" data-options="required:true,validType:'length[11,11]'"/></td>
					</tr>
					<tr>
						<td>毕业院校</td>
						<td><input class="easyui-validatebox easyui-textbox" value="${sessionScope.emp.college }" name="emp.college" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>专业</td>
						<td><input class="easyui-validatebox easyui-textbox" value="${sessionScope.emp.major }" name="emp.major" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>	
						<td>学历</td>
						<td><input class="easyui-validatebox easyui-textbox" value="${sessionScope.emp.eduBack }" name="emp.eduBack" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>开户行名称</td>
						<td><input class="easyui-validatebox easyui-textbox"  value="${sessionScope.emp.bankName }" name="emp.bankName" data-options="required:true,validType:'length[2,20]'"/></td>
					</tr>
					<tr>
						<td>银行卡姓名</td>
						<td><input class="easyui-validatebox easyui-textbox" value="${sessionScope.emp.accountName }" name="emp.accountName" data-options="required:true,validType:'length[2,20]'"/></td>
						<td>银行卡账号</td>
						<td><input class="easyui-validatebox easyui-numberbox" value="${sessionScope.emp.accountNo }" name="emp.accountNo" data-options="required:true,validType:'length[19,19]'"/></td>
					</tr>
					<tr>
						<td>支付宝账号</td>
						<td><input class="easyui-validatebox easyui-numberbox" value="${sessionScope.emp.alipay }" name="emp.alipay" data-options="required:true,validType:'length[1,20]'"/></td>
					</tr>
				<tr>
					<td>入职时间</td>
					<td><input class="easyui-datetimebox" name="emp.hireDay"  value="${sessionScope.emp.hireDay }" data-options="editable:false"/></td>
					<td>离职时间</td>
					<td><input class="easyui-datetimebox" name="emp.resignDay"  value="${sessionScope.emp.resignDay }" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td>
						<a href="javascript:;" onclick="editEmp();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">保存</a>
					</td>
				</tr>
			</table>	
		</form>
	</div>
</body>
</html>