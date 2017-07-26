<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页</title>
</head>
<body>

	<%@ include file="menu.jsp" %>
	
	<jsp:include page="menu.jsp">
		<jsp:param value="value1" name="param1"/>
	</jsp:include>
	
	<jsp:forward page="add_user.jsp">
		<jsp:param name="param1" value="test" />
	</jsp:forward>

</body>
</html>