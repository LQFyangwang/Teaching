<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%int i = 1; %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=path %>/empChecking/pager">1111</a>
<table border="1">
	<tr>
		<td width="auto">姓名</td>
		<%for (int j=1;j<31;j++) {%>
		<td align="center" width="30px;"><%=i %></td>
		<%i++;}%>
	</tr>
	<c:forEach items="${requestScope.checkings}" var="ck">
	<tr>
		<td>${ck.checkingId }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>