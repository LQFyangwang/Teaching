<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*, java.util.*, com.gs.service.*"%>
    
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
	<div>
		<%=((User)session.getAttribute("user")).getName() %>
		<a href="<%=path %>/user/showcart">我的购物车</a>
		<h3>所有商品</h3>
		<%
			List<Product> products = (List) request.getAttribute("products");
			if (products.size() > 0) {
				out.println("<table>");
				
				for (Product p : products) {
					out.print("<tr>");
					out.print("<td>" + p.getId() + "</td>");
					out.print("<td>" + p.getName() + "</td>");
					out.print("<td>" + p.getPrice() + "</td>");
					out.print("<td>" + p.getDes() + "</td>");
					out.print("<td><a href='addcart?id=" + p.getId() + "'>添加到购物车</a>");
					// /JSPServletProj/user/addcart?id=1
					out.print("</tr>");
				}
				
				out.println("</table>");
			}
		%>
	</div>
</body>
</html>