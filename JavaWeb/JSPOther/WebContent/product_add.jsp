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
<!-- 如果需要上传文件，则form表单必需有enctype属性，属性值为multipart/form-data -->
	<form action="<%=path %>/product/add" method="post" enctype="multipart/form-data">
		<input type="text" name="name" />
		<input type="text" name="price" />
		<input type="text" name="des" />
		<input type="file" name="image" /><!-- 文件上传控件 -->
		<input type="submit"/>
	</form>
</body>
</html>