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
			// 登录成功，则跳转到home.jsp
			request.setAttribute("name", name);
			// request.removeAttribute("name"); // 移除属性
			request.getRequestDispatcher("/user/home.jsp").forward(request, response);
		} else {
			// 登录失败，则跳转回登录页面
			// request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
		}
	%>

</body>
</html>