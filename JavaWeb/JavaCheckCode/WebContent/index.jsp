<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script>
		function checkCode(obj) {
			// obj.src = 'check_code'; // 这个src地址会被浏览器缓存，如果没有更换，则意味着是同一个src，不做任何事情
			obj.src = 'check_code?t=' + Math.random(); // 每点击一次，换一个src
		}
	</script>
</head>
<body>
	<img src="check_code" onclick="checkCode(this);"/>
</body>
</html>