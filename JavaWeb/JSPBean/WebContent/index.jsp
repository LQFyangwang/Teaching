<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		User user = new User();
		user.setId(1001);
		user.setName("1001");
		user.setPwd("1001");
		out.print(user);
	%>

</body>
</html>