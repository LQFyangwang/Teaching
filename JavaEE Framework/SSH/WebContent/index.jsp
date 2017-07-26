<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	index
	<shiro:principal />
	<shiro:hasRole name="admin">admin</shiro:hasRole>
	<shiro:hasPermission name="user:add">customer add</shiro:hasPermission>
</body>
</html>