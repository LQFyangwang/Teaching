<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.gs.bean.Product"%>
    
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
		int totalPage = Integer.valueOf(request.getAttribute("totalPage").toString());
		int currentPageNo = Integer.valueOf(request.getAttribute("currentPage").toString());
		int nextPage = currentPageNo + 1;
		int previousPage = currentPageNo - 1;
		if (previousPage == 0 ){
			previousPage = 1;
		}
	%>
	<a href="<%=path %>/product/pager">首页</a>
	<a href="<%=path %>/product/pager?pageNo=<%=previousPage %>">上一页</a>
	<a href="<%=path %>/product/pager?pageNo=<%=nextPage %>">下一页</a>
	<a href="<%=path %>/product/pager?pageNo=<%=totalPage %>">尾页</a>
	
	<a href="<%=path %>/product/pager?pageNo=5">第5页</a>
	
	
	总页数：<%=totalPage %>
</body>
</html>