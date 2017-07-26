<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		out.print(df.format(Calendar.getInstance().getTime()));
	%>

</body>
</html>