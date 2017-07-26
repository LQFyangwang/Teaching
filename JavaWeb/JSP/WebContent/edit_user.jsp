<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<input type="hidden" name="userId" value="${param.userId }" />
		<input type="text" name="userName" value="${param.userName }" />
	</form>
	根据这里获取到的userId查询出用户的其他信息，并且列在input里面，供用户查看与修改
</body>
</html>