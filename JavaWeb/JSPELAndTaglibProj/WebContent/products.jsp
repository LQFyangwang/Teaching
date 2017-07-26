<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.gs.bean.Product"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.products.size() > 100 }">
			<table>
				<c:forEach items="${requestScope.products }" var="p">
					<tr>
						<td>${p.id }</td>
						<td>${p.name }</td>
						<td>${p.price }</td>
						<td>${p.des }</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>暂无商品</c:otherwise>
	</c:choose>
	
</body>
</html>