<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path %>/user/login" method="post">
		<div style="color:red;font-size:12px;">${requestScope.errorMsg }</div>
		<input type="text" name="name" />
		<input type="password" name="pwd" />
		<input type="submit" />
	</form>
</body>
</html>