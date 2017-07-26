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
		String idStr = request.getParameter("id");
		if (idStr != null && !idStr.equals("")) {
			int id = Integer.valueOf(idStr);
			Object obj = session.getAttribute("productsInCart");
			if (obj != null) {
				List<Product> products = (List) obj;
				Iterator<Product> ite = products.iterator();
				while (ite.hasNext()) {
					Product p = ite.next();
					if (p.getId() == id) {
						ite.remove();
					}
				}
				if (products.size() >0) {
					session.setAttribute("productsInCart", products);
				} else {
					session.setAttribute("productsInCart", null);
				}
				response.sendRedirect("cart.jsp");
			}
		}
	%>

</body>
</html>