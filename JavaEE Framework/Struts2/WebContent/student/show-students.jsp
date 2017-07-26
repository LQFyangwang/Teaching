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
	<s:iterator value="#request.stus" var="stu">
		id:${stu.id }<br />
		email:${stu.email }<br />
		gender:${stu.gender }<br />
		des:${stu.des }<br />
		<a href="<%=path %>/stu/show?id=${stu.id }">显示详情</a><br />
	</s:iterator>
</body>
</html>