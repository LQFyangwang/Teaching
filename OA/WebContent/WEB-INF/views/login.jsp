<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录</title>
<style type="text/css">
.code {
	font-family: Arial;
	font-style: italic;
	color: red;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 8px;
	font-weight: bolder;
	font-size: 18px;
}

.unchanged {
	border: 0;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/login_bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
<script>
	var code = ""; //在全局 定义验证码  
	function createCode() {
		code = "";
		var codeLength = 4;//验证码的长度  
		var checkCode = document.getElementById("checkCode");
		var selectChar = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的  
		for (var i = 0; i < codeLength; i++) {
			var charIndex = Math.floor(Math.random() * 32);
			code += selectChar[charIndex];
		}
		if (checkCode) {
			checkCode.className = "code";
			checkCode.value = code;
		}
	}
	
	function checkLength() {
		var email = document.getElementById("email").value;
		var pwd = document.getElementById("pwd").value;
		var code1 = document.getElementById("code").value;
		var error = document.getElementById("error");
		if (email != "" && pwd != "" && code1 != "") {
			if (code != "" && code1.toLowerCase() == code.toLowerCase()) {
				return true;
			} else {
				error.innerHTML = "验证码有误";
				return false;
			}
		} else {
			error.innerHTML = "账号密码和验证码都不能为空";
		}
		return false;
	}
</script>
</head>
<body style="background-size: 100%;"
	background="<%=path%>/images/grass.jpg">
	<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1>
					<small>登录</small>
				</h1>
			</div>
			<div class="login-content ">
				<div class="form">
					<form action="#" method="post"
						onsubmit="return checkLength();">
						<div class="form-group">
							<div class="col-xs-12  ">
								<span style="color:red" id="error"></span>
								<div class="input-group">
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-user"></span></span> <input type="email"
										id="email" name="email" class="form-control"
										placeholder="登入邮箱">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-lock"></span></span> <input
										type="password" id="pwd" name="pwd" class="form-control"
										placeholder="密&nbsp;&nbsp;码">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</span> 
									<input type="text" id="code" name="code" class="form-control1"
										placeholder="&nbsp;&nbsp;&nbsp;验证码"> 
									<input type="button" onclick="createCode()" readonly="readonly"
										id="checkCode" class="unchanged"
										style="width: 120px; height: 40px; background-image: url('<%=path%>/images/0.jpg');"
										value="点击获取验证码" />
								</div>
							</div>
						</div>
						<div class="form-group form-actions">
							<div class="col-xs-4 col-xs-offset-4 ">
								<button type="submit" class="btn btn-sm btn-info">
									<span class="glyphicon glyphicon-off"></span> 登&nbsp;&nbsp;录
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>