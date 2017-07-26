<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="u" class="com.gs.bean.User" scope="session"></jsp:useBean>
	
	id:<jsp:getProperty name="u" property="id" />

</body>
</html>