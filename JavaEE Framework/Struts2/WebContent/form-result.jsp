<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	id:${requestScope.stu.id }<br />
	email:${requestScope.stu.email }<br />
	pwd:${requestScope.stu.pwd }<br />
	gender:${requestScope.stu.gender }<br />
	hasJob:${requestScope.stu.hasJob }<br />
	<s:iterator value="#request.stu.hobby" var="h">
		${h }
	</s:iterator>
	<br />
	des:${requestScope.stu.des }<br />
	<s:iterator value="#request.stu.job" var="j">
		${j }
	</s:iterator>
	<br />
	game:${requestScope.stu.game }<br />
</body>
</html>