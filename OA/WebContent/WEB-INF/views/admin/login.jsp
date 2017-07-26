<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/login.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/admin.css" />
<title>管理员登入</title>
<script type="text/javascript">
	function checkLength() {
		var pwd = document.getElementById("adminPwd").value; //获取密码框值
		var email = document.getElementById("adminEmail").value;
		var p_test = new RegExp("^[0-9a-zA-Z]{6,16}$", "ig");
		var e_test = new RegExp("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$", "ig");
		if (!p_test.test(pwd)) {
			alert("请输入6~16的密码！");
			return false;
		} else if (!e_test.test(email)) {
			alert("请输入正确的邮箱!");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="background-size: 100%;"
	background="<%=path%>/images/adminlogin.jpg">

<div class="container" style="margin-top: 200px;">
	<section id="content">
		<form action="<%=path %>/admin/login" method="post" onsubmit="return checkLength()">
			<s:token name="token"></s:token>
			<h1>管理员登入</h1>
			<div>
				<input type="email" placeholder="邮箱" required="required" name="emp.email" id="adminEmail" />
			</div>
			<div>
				<input type="password" placeholder="密码" required="required" name="emp.pwd" id="adminPwd" />
			</div>
			<div class="button1">
				<input type="submit" value="登入" style="margin-left: 120px;" />
				<a href="<%=path %>" style="margin-left: 55px; margin-bottom: 20px;" >返回首页</a>
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>