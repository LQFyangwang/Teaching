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

	<form action="con/my-con" method="post">
		<s:fielderror fieldName="array"></s:fielderror>
		<input type="text" name="array" value="a,b,c" />
		<input type="submit" />
	</form>
</body>
</html>