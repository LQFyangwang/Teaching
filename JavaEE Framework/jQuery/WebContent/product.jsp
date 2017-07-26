<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script>
	function searchProduct() {
		var title = $("#title").val();
		$.get("<%=path %>/product?title=" + title,
			function(data) {
			var table = $("#product_results");
			table.html(""); // 把div内部的内容设置为空字符串
			$.each(data, function(idx, item) { // 返回的data是JSON对象的数组，所以需要each循环
				table.append("<tr><td>" + item.title + "</td></tr>");
			});
		}, "json");
		
		$("#removeAll").click(function() {
			// $("#product_results").remove(); // 移除元素经，包括其子元素
			$("#product_results").empty(); // 只移除子元素
		});
	}
</script>
</head>
<body>
	<input id="title" type="text" name="product_title" placeholder="请输入关键字" />
	<button onclick="searchProduct();">搜索</button>
	<div>
		<table id="product_results">
		
		</table>
	</div>
	<button id="removeAll">移除结果</button>
</body>
</html>