<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gs.bean.*, com.gs.service.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String idStr = request.getParameter("id"); // 去获取由home.jsp页面的添加到购物车链接传递过来的商品的id
		if (idStr != null && !idStr.equals("")) {
			int id = Integer.valueOf(idStr);
			ProductService productService = new ProductServiceImpl();
			Product p = productService.queryById(id);
			if (p != null) {
				Object obj = session.getAttribute("productsInCart"); // 先从session里面获取是否有购物车信息
				List<Product> productsInCart = null;
				if (obj == null) { // 如果没有，则先实例化存放商品的list列表
					productsInCart = new ArrayList<Product>();
				} else { // 如果有，则直接把obj转化为存放商品的list列表
					productsInCart = (List) obj;
				}
				productsInCart.add(p);
				session.setAttribute("productsInCart", productsInCart);
			}
		}
		response.sendRedirect("home.jsp");
	%>

</body>
</html>