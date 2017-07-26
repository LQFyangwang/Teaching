<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<s:property value="#attr.name"/> - ${requestScope.name }
	<s:property value="#attr.age"/> - ${requestScope.age }
	<s:property value="#attr.user.name"/> - ${requestScope.user.name }
	<s:property value="#attr.user.pwd"/> - ${requestScope.user.pwd }
	<hr />
	<s:property value="#request.name"/>
	<s:property value="#request.age"/>
	<s:property value="#request.user.name"/>
	<s:property value="#request.user.pwd"/>
	<hr />
	<s:property value="#session.user.name"/>
	<s:property value="#session.user.pwd"/>
	<hr />
	<s:property value="#application.user.name"/>
	<s:property value="#application.user.pwd"/>

</body>
</html>