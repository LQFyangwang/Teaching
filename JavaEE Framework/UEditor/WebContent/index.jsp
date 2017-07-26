<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
     <!-- 配置文件 -->
    <script type="text/javascript" src="<%=path %>/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=path %>/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
    	function getContent() {
    		alert(UE.getEditor('ueditor').getContent());
    	}
    </script>
</head>

<body>
    <!-- 实例化编辑器 -->
    <script id="ueditor" name="content" type="text/plain" style="width:800px;height:400px;">
    </script>
    
    <script type="text/javascript">
        var ue = UE.getEditor('ueditor', {
            autoHeight: false
        });
        ue.ready(function() { // 整个编辑器准备好后设置其他属性
            ue.setHeight(300);
        });
    </script>
    
    <a href="javascript:;" onclick="getContent()">获取内容</a>
    
</body>

</html>