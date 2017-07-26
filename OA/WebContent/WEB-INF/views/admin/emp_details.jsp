<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工详情</title>

</head>
<body>
	<table>
		<tr>
			<td style="padding: 5px;">出生日期:</td>
			<td style="width: 160px;">${requestScope.emp.birthday }</td>
			<td>手机号:</td>
			<td style="width: 160px;">${requestScope.emp.phone }</td>
			<td>QQ号:</td>
			<td style="width: 160px;">${requestScope.emp.qq }</td>
			<td>身份证号码：</td>
			<td style="width: 160px;">${requestScope.emp.idCard }</td>
		</tr>
		<tr>
			<td style="padding: 5px;">微信号:</td>
			<td style="width: 160px;">${requestScope.emp.wechat }</td>
			<td>家庭地址:</td>
			<td style="width: 160px;">${requestScope.emp.address }</td>
			<td>家庭联系人</td>
			<td style="width: 160px;">${requestScope.emp.contactName }</td>
			<td>家长电话:</td>
			<td style="width: 160px;">${requestScope.emp.contactPhone }</td>
		</tr>
		<tr>
			<td style="padding: 5px;">是否结婚:</td>
			<td style="width: 160px;">${requestScope.emp.married }</td>
			<td>开户行名称:</td>
			<td style="width: 160x;">${requestScope.emp.bankName}</td>
			<td>银行卡姓名:</td>
			<td style="width: 160px;">${requestScope.emp.accountName }</td>
			<td>离职时间:</td>
			<td style="width: 160x;">${requestScope.emp.resignDay}</td>
		</tr> 
		<tr>
			<td style="padding: 5px;">银行卡账号:</td>
			<td style="width: 160px;">${requestScope.emp.accountNo }</td>
			<td style="padding: 10px;">支付宝账号:</td>
			<td style="width: 160px;">${requestScope.emp.alipay }</td>
		</tr>
	</table>

</body>
</html>