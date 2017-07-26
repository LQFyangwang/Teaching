<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
    
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

 <!-- 配置文件 -->
    <script type="text/javascript" src="<%=path %>/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=path %>/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
    	function getContent() {
    		alert(UE.getEditor('ueditor_container').getContent());
    	}
    </script>
    <script type="text/javascript">
        var ue = UE.getEditor('ueditor_container', {
            autoHeight: false
        });
        ue.ready(function() { // 整个编辑器准备好后设置其他属性
            ue.setHeight(300);
        });
    </script>
</head>
<body>

	<shiro:hasRole name="admin">admin</shiro:hasRole>
	<shiro:hasPermission name="customer:add">customer add</shiro:hasPermission>

	<script id="ueditor_container" name="content" type="text/plain" style="width:800px;height:400px;">
    </script>

</body>
</html>