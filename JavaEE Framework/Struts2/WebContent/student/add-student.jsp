<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<s:form action="save" method="post">
		<s:hidden name="stu.id"></s:hidden>
		<s:textfield id="email" name="stu.email" label="邮箱" cssClass="my_input"></s:textfield>
		<s:password id="pwd" name="stu.pwd" label="密码"></s:password>
		<s:radio name="stu.gender" listKey="key" listValue="value" list="#{'male':'男','female':'女'}" label="性别"></s:radio>
		<s:checkbox name="stu.hasJob" value="hasJob" label="是否参加工作"></s:checkbox>
		<s:checkboxlist name="stu.hobby" list="{'跑步', '阅读', '运动'}"></s:checkboxlist>
		<s:textarea name="stu.des" label="描述" cols="20" rows="4"></s:textarea>
		<s:select name="stu.job" list="#{'none':'--请选择--','developer':'软件工程师','account':'会计'}" label="职业"></s:select>
		<s:combobox name="stu.game" list="{'暴力摩托','王者荣耀'}"></s:combobox>
		<s:file name="stu.icon" label="头像"></s:file>
		<s:submit value="确认"/>
		<s:reset value="重置"/>
	</s:form>
</body>
</html>