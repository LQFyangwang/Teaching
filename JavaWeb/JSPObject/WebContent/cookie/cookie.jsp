<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	
	Cookie cookie = new Cookie("cookie_name", "cookie_value"); // 实例化Cookie对象
	out.print(cookie.getMaxAge());
	response.addCookie(cookie); // 把cookie写入到客户端浏览器

	Cookie[] cookies = request.getCookies(); // 从浏览器端获取cookie
	if (cookies != null && cookies.length > 1) {
		for (Cookie c : cookies) {
			out.print(c.getName() + ": " + c.getValue()); // 从cookie中获取键和值
		}
	}
%>

</body>
</html>