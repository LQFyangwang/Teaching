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
		out.print("<pre>");
		out.print("aa");
		out.println("bb");
		out.print("cc<br />");
		out.print("dd");
		out.newLine(); // 输出换行
		out.print("ee");
		out.print("</pre>");
	%>

</body>
</html>