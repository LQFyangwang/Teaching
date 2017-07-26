<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	id:${requestScope.stu.id }<br />
	email:${requestScope.stu.email }<br />
	gender:${requestScope.stu.gender }<br />
	des:${requestScope.stu.des }<br />
	<a href="<%=path %>/stu/edit?id=${requestScope.stu.id }">编辑学生</a>
</body>
</html>