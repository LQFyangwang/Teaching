<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*, java.util.*, com.gs.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Object obj = session.getAttribute("user");
		if (obj != null) {
			out.println("<div>欢迎您：" + ((User) obj).getName() +  "</div>");
			out.println("<a href='cart.jsp'>我的购物车</a>");
		} else {
			response.sendRedirect("login.jsp");
		}
	
	%>

	
	<div>
		<h3>所有商品</h3>
		<%
			ProductService productService = new ProductServiceImpl();
			List<Product> products = productService.queryAll();
			if (products.size() > 0) {
				out.println("<table>");
				
				for (Product p : products) {
					out.print("<tr>");
					out.print("<td>" + p.getId() + "</td>");
					out.print("<td>" + p.getName() + "</td>");
					out.print("<td>" + p.getPrice() + "</td>");
					out.print("<td>" + p.getDes() + "</td>");
					out.print("<td><a href='add_cart.jsp?id=" + p.getId() + "'>添加到购物车</a>");
					out.print("</tr>");
				}
				
				out.println("</table>");
			}
		%>
	</div>
</body>
</html>