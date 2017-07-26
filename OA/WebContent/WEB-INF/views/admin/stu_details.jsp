<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生详情</title>

</head>
<body>
	<table>
		<tr>
			<td style="padding: 5px;">身份证:</td>
			<td style="width: 160px;">${requestScope.stu.idCard }</td>
			<td>手机号:</td>
			<td style="width: 160px;">${requestScope.stu.phone }</td>
			<td>QQ号:</td>
			<td style="width: 160px;">${requestScope.stu.qq }</td>
		</tr>
		<tr>
			<td style="padding: 5px;">微信号:</td>
			<td style="width: 160px;">${requestScope.stu.wechat }</td>
			<td>家长姓名:</td>
			<td style="width: 160px;">${requestScope.stu.parentName }</td>
			<td>家长电话:</td>
			<td style="width: 160px;">${requestScope.stu.parentPhone }</td>
		</tr>
		<tr>
			<td style="padding: 5px;">地址:</td>
			<td style="width: 160px;">${requestScope.stu.address }</td>
			<td>籍贯:</td>
			<td style="width: 160x;">${requestScope.stu.nation }</td>
			<td>户口性质:</td>
			<td style="width: 160px;">${requestScope.stu.residence }</td>
		</tr>
	</table>

</body>
</html>