<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.User,com.gs.service.*"%>
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
			UserService userService = new UserServiceImpl();
			User user = userService.queryByNamePwd(name, pwd);
			if (user != null) {
				session.setAttribute("user", user);
				
				response.sendRedirect("home.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	%>

</body>
</html>