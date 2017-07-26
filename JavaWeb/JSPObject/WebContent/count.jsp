<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计在线人数</title>
</head>
<body>

<%!
	Map<String, Boolean> onlineMap = new HashMap<String, Boolean>();
%>

<%
	Object countObj = application.getAttribute("count");
	if (countObj == null) { // 还没开始统计人数，则count为空
		application.setAttribute("count", 1);
	} else {// 已经有访问数
		application.setAttribute("count", Integer.valueOf(countObj.toString()) + 1);
	}
	
	out.print("访问次数：" + application.getAttribute("count"));
	
	Object onlineObj = application.getAttribute("online");
	
	if (onlineObj == null) {
		application.setAttribute("online", 1);
	} else {
		// 获取ip地址
		String ip = request.getLocalName(); // 服务器
		String ip1 = request.getRemoteHost(); // 客户端ip地址
		if (onlineMap.containsKey(ip1)) {
			
		} else {
			onlineMap.put(ip1, Boolean.TRUE);
			application.setAttribute("online", Integer.valueOf(onlineObj.toString()) + 1);
		}
	}
	
	out.print("在线人数：" + application.getAttribute("online"));
	
	out.println("major version: " + application.getMajorVersion());
	out.println("minor version: " + application.getMinorVersion());
	
%>

</body>
</html>