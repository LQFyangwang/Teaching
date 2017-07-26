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
	Object countObj = application.getAttribute("count");
	if (countObj == null) { // 还没开始统计人数，则count为空
		application.setAttribute("count", 1);
	} else {// 已经访问数
		application.setAttribute("count", Integer.valueOf(countObj.toString()) + 1);
	}
	
	out.print("访问次数：" + application.getAttribute("count"));
	 %>
</body>
</html>