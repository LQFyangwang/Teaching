<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String param1 = request.getParameter("param1");
    	if (param1 != null) {
    %>
    	<%=param1 %>
    <%
    	} else {
    %>
    <%out.println("param1为null"); %>
    <%
    	}
    %>

	<div id="menu">
		<ul>
			<li>menu1</li>
			<li>menu2</li>
			<li>menu3</li>
		</ul>
	</div>