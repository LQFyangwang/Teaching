<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    %>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:out value="aa"></c:out>
	${requestScope.user }
	<c:set var="a" value="my_aa"></c:set> <!-- 在指定的作用域内设置一个attribute， 如果没有指定作用域，默认为pageScope -->
	${pageScope.a }
	${requestScope.a }
	<c:set var="b" value="bb" scope="request"></c:set>
	${requestScope.b }
	<c:remove var="b" scope="request"/>
	${requestScope.b }
	<c:catch var="java.lang.NumberFormatException">
		<%
			Integer.valueOf("ccc");
		%>
	</c:catch>
	
	<c:if test="${10 == 100 }">
		10 == 10
	</c:if>
	<c:if test="${requestScope.user == 'user' }">
		${requestScope.user }
	</c:if>
	
	<c:choose>
		<c:when test="${requestScope.user == 'user' }">user</c:when>
		<c:when test="${requestScope.user == 'admin' }">admin</c:when>
		<c:otherwise>other</c:otherwise>
	</c:choose>
	
	<table>
		<c:forEach items="${requestScope.products }" var="p" begin="0" step="1" varStatus="s">
			<tr>
				<td>${s.index + 1 }</td>
				<td>${p.name }</td>
			</tr>
		</c:forEach>
	</table>
	
	
	<%-- <c:import url="login.jsp"></c:import>
	
	<c:url value="/taglib.jsp"></c:url>
	
	<c:redirect url="login.jsp"></c:redirect> --%>
	
</body>
</html>