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
	
		// 先判断Session里是否已经有登录的用户，如果有，则直接跳转到home.jsp
		// 如果没有，再执行登录操作
		Object userNameObj = session.getAttribute("name");
		if (userNameObj == null) { // 没有用户登录，此时需要进行登录操作
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			if (name != null && !name.equals("") && pwd != null && !pwd.equals("")) {
				// 登录成功，则跳转到home.jsp
				session.setAttribute("name", name);// 如果登录成功，则把用户信息保存到session会话中
				request.getRequestDispatcher("/user/home_session.jsp").forward(request, response);
			} else {
				// 登录失败，则跳转回登录页面
				// request.getRequestDispatcher("/user/login.jsp").forward(request, response);
				response.sendRedirect("login_session.jsp");
			}
		} else { // 如果已经有登录用户，直接跳转
			request.getRequestDispatcher("/user/home_session.jsp").forward(request, response);
		}
	
		
	%>

</body>
</html>