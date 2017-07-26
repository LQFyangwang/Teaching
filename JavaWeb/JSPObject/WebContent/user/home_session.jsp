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
		Object nameObj = session.getAttribute("name");
		if (nameObj == null) {
			response.sendRedirect("login_session.jsp");
		} else {
			String name = null;
			if (nameObj != null) {
				name = nameObj.toString();
				out.print(name);
			}
		}
	%>
</body>
</html>