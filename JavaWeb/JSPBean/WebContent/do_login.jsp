<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="user" class="com.gs.bean.User"></jsp:useBean>
	
	<jsp:setProperty property="*" name="user"/>
	 
	<!-- 
	<jsp:setProperty property="name" name="user" param="name"/>
	<jsp:setProperty property="pwd" name="user" param="pwd" />
	-->
	
	<jsp:getProperty property="name" name="user"/>
	<jsp:getProperty property="pwd" name="user" />
	
</body>
</html>