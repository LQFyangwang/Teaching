<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*"%>
    
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
	<%
		session.setAttribute("user", new User());
		session.removeAttribute("user");
	%>
	上传文件的最大值：<%=application.getAttribute("max_file_size") %><br />
	<a href="<%=path %>/product/add_page">去添加商品</a>
</body>
</html>