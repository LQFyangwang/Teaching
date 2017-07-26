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
	String name = request.getParameter("name");
	String pwd = request.getParameter("pwd");
	if (name != null && !name.equals("") && pwd != null && !pwd.equals("")) {
		Cookie cookieName = new Cookie("jspobject_user_name", name);
		Cookie cookiePwd = new Cookie("jspobject_user_pwd", pwd);
		response.addCookie(cookieName);
		response.addCookie(cookiePwd);
	}
%>

</body>
</html>