<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path %>/product/add" method="post">
		名称：<input type="text" name="name" /><br />
		价格：<input type="text" name="price" /><br />
		描述：<textarea name="des"></textarea><br />
		<input type="submit" />
	</form>
</body>
</html>