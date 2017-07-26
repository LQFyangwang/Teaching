<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	${sessionScope.user } <a href="<%=path %>/user/logout">安全退出</a>
	<shiro:hasRole name="customer">
		<a href="#">Customer</a>
	</shiro:hasRole>
	<shiro:hasPermission name="customer:delete">
		<a href="#">删除用户</a>
	</shiro:hasPermission>
</body>
</html>