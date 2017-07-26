<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	 ${sessionScope.user.email }
	 请假流程<a href="<%=path %>/user/deploy?procName=leave_process">部署流程</a>
	  物品申购流程<a href="<%=path %>/user/deploy?procName=goods_apply">部署流程</a>
	   请假流程1<a href="<%=path %>/user/deploy?procName=leave_proc">部署流程</a>
	 
	 <a href="<%=path %>/user/del">删除流程</a>
	 ${requestScope.tasks }
	
	 <c:if test="${requestScope.tasks != null }">
	 	请假理由：${requestScope.des }, 
		<a href="<%=path %>/user/check">审核通过</a>
	</c:if>
</body>
</html>