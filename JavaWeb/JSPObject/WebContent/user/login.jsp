<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="do_login.jsp" method="post">
		<input type="text" name="name" />
		<input type="password" name="pwd" />
		<input type="submit" />
	</form>
	
	<%
		String servletName = ((HttpServlet) page).getServletName();
	%>
	
	<%=servletName %>
	
	<%
		pageContext.setAttribute("currPage", "aaa"); // 出了当前页面，其他页面不可能通过currPage这个key值来获取对应的Value
	%>
	
	<%
		out.print("session id: " + session.getId());
	%>
	
</body>
</html>