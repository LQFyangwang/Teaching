<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		int a = 100;
		String b = "test";
		pageContext.setAttribute("c", "cccc");
		request.setAttribute("d", "dddd");
		session.setAttribute("e", "eeee");
		application.setAttribute("f", "ffff");
		out.println(a);
		out.println(b);
	%>
	
	<%=a %>
	<%=b %>
	<%-- 不能对pageContext, request, session, applicaiton中的属性进行输出，只能对成员变量，局部变量进行输出操作
		out.print();
	<%=c %>
	 --%>
	
	${a }
	${b }
	pageContext: ${c }
	request: ${d }
	session: ${e }
	application: ${f }
	
	${100 + 100 }
	
	${param.search_name }
	
	${100 eq 100 } <!-- 相等判断 -->
	${param.count < 100 }
	
	${empty(param.count) } <!-- 判断值是否为空 -->
	${empty(requestScope.aa)} <!-- 判断某个属性是否为空，如果空则返回true，否则返回false -->
	
	<br />
	
	<a href="<%=path %>/user/login_page">用户登录</a>
	
</body>
</html>