<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <%
   		String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path %>/up/up" method="post" enctype="multipart/form-data">
		<s:fielderror></s:fielderror>
		${requestScope.message }
		<input type="file" name="file" />
		<input type="submit" />
	</form>
</body>
</html>