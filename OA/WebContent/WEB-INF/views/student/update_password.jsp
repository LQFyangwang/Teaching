<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生修改密码</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path %>/css/main.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/main.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>

<script>
	
	function clearForm(){ // 清除表单数据
		$('#pwdForm').form('clear'); // 清除表单的内容
	}
	
	function determine() { // 修改密码
		var ordPwd = $('#ordPwd').textbox('getValue');
		var newPwd = $('#newPwd').textbox('getValue');
		var detPwd = $('#detPwd').textbox('getValue');
		if ($("#pwdForm").form("validate")) {
			if (newPwd != detPwd) {
				$.messager.alert("提示", "您输入的两次密码不匹配", "error");
			} else {
				$.post("<%=path %>/student/updatePwd",
						$("#pwdForm").serialize(), // Form表单序列化
						function(data) {
							if (data.result.result == "success") { 
								$.messager.alert("提示", data.result.message, "info");
							} else {
								$.messager.alert("提示", data.result.message, "error");
							}
						}, "json");
			}
		} else {
			$.messager.alert("提示", "请正确输入表单数据", "error");
		}
	}
	
	
</script>
</head>
<body style="height: 100%;">
	
	<!-- 修改密码窗口-->
	<div id="pwdWin" class="easyui-window" title="修改密码" data-options="iconCls:'icon-edit', closed:false" style="width:400px;height:200px;">
		<div style="padding:0px 60px 20px 0px">
		    <form id="pwdForm">
		    	<table>
		    		<tr>
		    			<td>旧密码:</td>
		    			<td>
		    				<input class="easyui-textbox easyui-validatebox" type="password" id="ordPwd" name="ordPwd" data-options="required:true, validType:'length[6, 20]'" placeholder="请输入旧密码"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>新密码:</td>
		    			<td>
		    				<input class="easyui-textbox easyui-validatebox" type="password" id="newPwd" name="newPwd" data-options="required:true, validType:'length[6, 20]'" placeholder="请输入新密码"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>确认密码:</td>
		    			<td>
		    				<input class="easyui-textbox easyui-validatebox" type="password" id="detPwd" name="detPwd" data-options="required:true, validType:'length[6, 20]'" placeholder="确认密码"></input>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="margin-left: 60px; padding:5px">
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="determine();">确定</a>
		    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		    </div>
		</div>
	</div>

	
	
	
	
	
	

	
	
</body>
</html>