<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="val/validate" method="post">
		<s:token name="token"></s:token>
		<s:fielderror fieldName="name" />
		<s:fielderror fieldName="age" />
		<s:fielderror fieldName="email" />
		<s:fielderror></s:fielderror>
		<input type="text" name="name" />
		<input type="text" name="age" />
		<input type="text" name="email" />
		
		<input type="submit" />
	
	</form>
	
</body>
</html>