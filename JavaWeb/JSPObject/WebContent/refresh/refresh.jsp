<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	refresh page...
	
	<%
		response.setIntHeader("refresh", 3); // 刷新自己
		
		response.setHeader("refresh", "5;http://www.baidu.com"); // 多久后跳转到指定的页面
	%>

</body>
</html>