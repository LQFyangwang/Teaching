<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	%>
	
	<jsp:useBean id="product" class="com.gs.bean.Product"></jsp:useBean>
	
	<jsp:setProperty property="*" name="product" />
	
	<%
		ProductService ps = new ProductServiceImpl();
		ps.add(product); // 直接使用jsp:useBean所指定的id值
	%>
	
</body>
</html>