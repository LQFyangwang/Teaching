<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path %>/user/edit" method="post">
	${key }
		<input type="text" name="name" value="${requestScope.user.name }"/>
		<input type="password" name="pwd" value="${requestScope.user.pwd }"/>
		<input type="submit" />
	</form>
</body>
</html>