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
		Object nameObj = request.getAttribute("name");
		String name = null;
		if (nameObj != null) {
			name = nameObj.toString();
		}
	%>
	欢迎您：<%=name %>
</body>
</html>