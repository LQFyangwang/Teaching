<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String userName = "";
	String userPwd = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals("jspobject_user_name")) {
				userName = cookie.getValue();
			} else if (name.equals("jspobject_user_pwd")) {
				userPwd = cookie.getValue();
			}
		}
	}
%>

<form action="do_login.jsp" method="post">
		<input type="text" name="name" value="<%=userName %>"/>
		<input type="password" name="pwd" value="<%=userPwd %>"/>
		<input type="submit" />
	</form>
</body>
</html>