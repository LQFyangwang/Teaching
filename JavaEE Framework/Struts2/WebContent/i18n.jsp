<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<s:form>
		<s:text name="test"></s:text>
		<s:textfield name="test" key="test"></s:textfield><!-- 通过去获取国际化资源文件来显示不同的语言 -->
	</s:form>
</body>
</html>