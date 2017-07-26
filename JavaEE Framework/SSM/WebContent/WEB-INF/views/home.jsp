<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	 ${sessionScope.user.email }
	 <form action="<%=path%>/user/leave">
	 	<input type="text" name="reason" />
	 	<input type="submit" />
	 </form>
</body>
</html>