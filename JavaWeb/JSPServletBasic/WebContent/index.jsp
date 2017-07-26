<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="register.jsp">立即注册</a>
	<div style="color:green; border:1px silver solid;">
		It's a page from jsp
	</div>
	<form action="service" method="get">
		<input type="text" name="name" />
		<input type="submit" value="Get提交"/>
	</form>
	
	<form action="service" method="post">
		<input type="text" name="name" />
		<input type="submit" value="Post提交"/>
	</form>
</body>
</html>