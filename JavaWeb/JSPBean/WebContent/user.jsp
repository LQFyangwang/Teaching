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
	
	<jsp:setProperty property="id" name="u" value="10010"/>
	<jsp:setProperty property="name" name="u" value="10011"/>
	<jsp:setProperty property="pwd" name="u" value="10012"/>
	
	id:<jsp:getProperty property="id" name="u"/><br />
	name:<jsp:getProperty property="name" name="u"/><br />
	pwd:<jsp:getProperty property="pwd" name="u"/>

</body>
</html>