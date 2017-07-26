<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限不足提示页面</title>
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path %>/jquery-easyui/themes/icon.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		 //$.messager.alert("操作提示", "操作失败！","error");  
		 $.messager.alert("操作提示", "抱歉，您没有权限操作", "error", function (data) {  
	            if (data) {  
	                
	            } else {  
	            	window.parent.tabsClose();  
	            }  
	        });  
	})
</script>

</head>
<body style="height: 100%;">

	
</body>
</html>