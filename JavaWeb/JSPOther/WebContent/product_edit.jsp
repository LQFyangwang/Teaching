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
	<form action="<%=path %>/product/edit">
		<input type="hidden" name="id" value="${requestScope.product.id }"/>
		<input type="text" name="name" value="${requestScope.product.name }"/>
		<input type="text" name="price" value="${requestScope.product.price }"/>
		<input type="text" name="des" value="${requestScope.product.des }"/>
		<input type="submit"/>
	</form>
</body>
</html>