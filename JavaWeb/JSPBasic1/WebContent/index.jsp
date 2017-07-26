<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 客户端可见的html注释 -->
	<%-- 客户端不可见的注释 --%>

	<%!
		//
		/**
		*/
		int a = 100;
		int b = 200;
		int cc = 3000;
		public void add() {
			System.out.println(a + "+" + b + "=" + (a + b));
		}
		
		public String minus() {
			return a + " - " + b + "=" + (a - b);
		}
	%>

	<%
		int a = 100;
		int b = 200;
		out.println("这是&nbsp;&nbsp;&nbsp;&nbsp;java程序片断&gt;");
		out.print(a + "+" + b + "=" + (a + b));
		add();
	%>
	
	<%
		int c = 1000; 
	%>
	<%=c %>
	<%=cc %>
	<%=minus()%>
	
	<a href="home.jsp">个人中心</a>
	
	

</body>
</html>