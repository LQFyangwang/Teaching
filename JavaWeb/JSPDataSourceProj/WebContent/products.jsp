<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.gs.bean.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Product> products = (List) request.getAttribute("products");
		if (products.size() > 0) {
			out.print("<table>");
			for (Product p : products) {
				out.print("<tr>");
				out.print("<td>" + p.getId() + "</td>");
				out.print("<td>" + p.getName() + "</td>");
				out.print("<td>" + p.getPrice() + "</td>");
				out.print("<td>" + p.getDes() + "</td>");
				out.print("</tr>");
			}
			out.print("</table>");
		} else {
			out.println("暂无商品信息");
		}
	%>
</body>
</html>