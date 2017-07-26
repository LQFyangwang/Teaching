<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=path %>/product/add_page">添加商品</a>
	<table>
	<c:forEach items="${requestScope.products }" var="p">
		<tr>
			<td>${p.id }</td>
			<td>${p.name }</td>
			<td>${p.price }</td>
			<td>${p.des }</td>
			<td>
			<c:choose>
				<c:when test="${empty(p.image)}">
					<img style="max-width:200px;max-height:100px;" src="<%=path %>/uploads/default.png" />
				</c:when>
				<c:otherwise>
					<img style="max-width:200px;max-height:100px;" src="<%=path %>/${p.image }" />
				</c:otherwise>
			</c:choose>
			</td>
			<td><a href="<%=path %>/product/edit_page?id=${p.id }">修改商品</a></td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>