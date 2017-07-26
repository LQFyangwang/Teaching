<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>

<script>
	function check() {
		var name = document.getElementById("name").value;
		var pwd = document.getElementById("pwd").value;
		if ( name != undefined && name != '' && pwd != undefined && pwd != '') {
			return true; // 表单没有错误，则返回true，让表单提交
		}
		var span = document.getElementById("errMsg");
		span.innerHTML = "用户名和密码必须输入";
		return false; // 表单出现错误，则返回false，不让表单提交
	}
</script>

</head>
<body>

	<div style="margin:auto; width:400px; height:auto;">
		<h3>用户注册</h3>
		<!-- onsubmit在提交的时候调用check()方法，如果check方法返回true，则提交表单，如果返回false，则不提交表单并且把错误信息显示到id为errMsg的span标签内部 -->
		<form action="reg" method="post" onsubmit="return check();">
			<span id="errMsg" style="color:red;font-size:12px;"></span>
			<table>
			<tr>
				<td><label for="name">用户名</label></td>
				<td>
					<input id="name" type="text" name="name" placeholder="请输入用户名" />
				</td>
			</tr>
			<tr>
				<td><label for="pwd">密码</label></td>
				<td>
					<input id="pwd" type="password" name="pwd" placeholder="请输入密码" />
				</td>
			</tr>
			<tr>
				<td><label>性别</label></td>
				<td>
					<input type="radio" name="gender" value="male" />男
					<input type="radio" name="gender" value="female" />女
				</td>
			</tr>
			<tr>
				<td><label>爱好</label></td>
				<td>
					<input type="checkbox" name="hobby" value="running" />跑步
					<input type="checkbox" name="hobby" value="sleeping" />睡觉
					<input type="checkbox" name="hobby" value="reading" />读书
				</td>
			</tr>
			<tr>
				<td>区域</td>
				<td>
					<select name="address">
						<option value="gz">章贡区</option>
						<option value="xg">兴国</option>
						<option value="nd">宁都</option>
						<option value="nh">南海</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>汽车</td>
				<td>
					<select name="cars" multiple="multiple">
						<option value="bmw">宝马</option>
						<option value="benz">奔驰</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>个人说明</td>
				<td>
					<textarea name="info" placeholder="请输入个人说明"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="确认" /></td>
				<td><input type="reset" value="重填" /></td>
			</tr>
			</table>
		</form>
	</div>

</body>
</html>