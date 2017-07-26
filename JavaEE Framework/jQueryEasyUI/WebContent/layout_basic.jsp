<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="jquery-easyui/themes/icon.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<div class="easyui-layout" style="width:600px;height:300px;">
		<div data-options="region:'north'" style="height:50px">上部</div>
		<div data-options="region:'south',split:false" style="height:50px;">下部</div>
		<div data-options="region:'east',split:true" title="其他" style="width:100px;">右部</div>
		<div data-options="region:'west',split:true" title="菜单" style="width:100px;">左部</div>
		<div data-options="region:'center',title:'主页',iconCls:'icon-ok'">
			主内容区
		</div>
	</div>

</body>
</html>