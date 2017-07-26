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
		session.setAttribute("session", "session value"); // 设置 
	
		out.println(session.getAttribute("session")); // 获取
		
		session.removeAttribute("session"); // 移除 
		
		out.println(session.getAttribute("session")); // 获取（没有此属性）
		
		session.setAttribute("session", "session value"); // 设置 
		
		out.println(session.getAttribute("session")); // 获取
		
		session.invalidate(); // 使session失效
		
		// out.println(session.getAttribute("session")); // 获取（错误）
		
		session.setMaxInactiveInterval(10); // 多少秒后把session设置为不可活动状态
		
	%>

</body>
</html>