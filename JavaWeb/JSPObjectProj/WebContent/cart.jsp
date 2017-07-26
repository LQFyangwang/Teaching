<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Object obj = session.getAttribute("productsInCart");
		if (obj == null) {
			out.print("购物车中暂无商品");
		} else {
			List<Product> products = (List) obj;
			out.println("<table>");
			
			for (Product p : products) {
				out.print("<tr>");
				out.print("<td>" + p.getId() + "</td>");
				out.print("<td>" + p.getName() + "</td>");
				out.print("<td>" + p.getPrice() + "</td>");
				out.print("<td>" + p.getDes() + "</td>");
				out.print("<td><a href='remove_cart.jsp?id=" + p.getId() + "'>从购物车移除</a>");
				out.print("</tr>");
			}
			
			out.println("</table>");
		}
	%>

</body>
</html>