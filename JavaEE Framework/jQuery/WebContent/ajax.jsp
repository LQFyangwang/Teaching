<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/main.css"></link>
<script src="js/jquery-3.1.1.min.js"></script>

<script>

	var xmlhttp;
	
	function getXMLHttpRequest() {
		if (window.XMLHttpRequest) { // 判断浏览器是否支持XMLHttpRequest对象
			xmlhttp = new XMLHttpRequest(); // 如果支持，则创建XMLHttpRequest对象
		} else {
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function testGet() {
		getXMLHttpRequest();
		xmlhttp.open("get", "<%=path %>/user1?name=user1&hobby=跑步"); // 使用get请求访问<%=path %>/user这个地址
		xmlhttp.send(); // 发送请求
		// XMLHttpRequest对象的处理过程
		// 当XMLHttpRequest处理不同的处理过程中，它的readyState属性值不一样
		// 0：未初始化（对象已经建立，但是未初始化，即尚未调用open方法创建http请求）
		// 1：初始化（对象已经建立，但是未调用send方法发送http请求）
		// 2：发送数据（send方法已经被调用，但是当前的状态以及http头未知）
		// 3：数据传送中（已经接收部分数据，因为响应及http头不全，这时通过response系统方法获取部分数据会出现错误）
		// 4：传送完成（数据完毕，此时可以通过response系统方法获取完整的回应数据）
		xmlhttp.onreadystatechange=function() { // 把服务端返回的数据放在data参数上
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 当请求成功返回数据
				var jsonStr = xmlhttp.responseText; // 获取XMLHttpRequest请求对象从服务端所返回的数据
				var jsonObj = eval("(" + jsonStr + ")");
				// 假设通过jquery访问服务端获取到值，服务端不需要做转发或重定向
				$("#name").val(jsonObj.name);
				$("#pwd").val(jsonObj.pwd);
				$("#age").val(jsonObj.age);
				$("#hobby").val(jsonObj.hobby);
		  	}
		}
	}
	
	function testPost() {
		getXMLHttpRequest();
		xmlhttp.open("post", "<%=path %>/user1");
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); // 使用form表单的形式来提交数据到服务端
		xmlhttp.send("name=user2&hobby=读书");
		xmlhttp.onreadystatechange=function() { // 把服务端返回的数据放在data参数上
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 当请求成功返回数据
				var jsonStr = xmlhttp.responseText; // 获取XMLHttpRequest请求对象从服务端所返回的数据
				var jsonObj = eval("(" + jsonStr + ")");
				// 假设通过jquery访问服务端获取到值，服务端不需要做转发或重定向
				$("#name").val(jsonObj.name);
				$("#pwd").val(jsonObj.pwd);
				$("#age").val(jsonObj.age);
				$("#hobby").val(jsonObj.hobby);
		  	}
		}
	}
	
	function testJqueryGet() {
		$.get("<%=path %>/user1?name=user1&hobby=跑步", 
			function(data) { // data已经是一个JSON对象
				$("#name").val(data.name);
				$("#pwd").val(data.pwd);
				$("#age").val(data.age);
				$("#hobby").val(data.hobby);
		}, "json"); // 第一个参数是提交到哪个url，第二个参数是整个请求完毕后，客户端用哪个方法 来处理，data就是从服务端返回的数据，第三个参数表示从服务端返回的data是什么类型的数据
	}
	
	function testJqueryPost() {
		$.post("<%=path %>/user1", 
			{"name":"user2","hobby":"阅读"},
			function(data) {
				$("#name").val(data.name);
				$("#pwd").val(data.pwd);
				$("#age").val(data.age);
				$("#hobby").val(data.hobby);
		}, "json"); // 第一个参数是提交到哪个url，第二个是传递到服务端的参数，第三个参数是整个请求完毕后，客户端用哪个方法 来处理，data就是从服务端返回的数据，第三个参数表示从服务端返回的data是什么类型的数据
	}
	
	function testJSON() {
		var jsonStr = "{'name':'test','age':20,'gender':'男'}";
		var jsonObj = eval("(" + jsonStr + ")"); // 把JSON字符串转换成JSON对象
		alert(jsonObj.name + jsonObj.age + jsonObj.gender); // JSON对象就能直接通过.引用属性
	}
	
	function testFormPost() {
		var name = $("#name1").val();
		var pwd = $("#pwd1").val();
		var age = $("#age1").val();
		var hobby = $("#hobby1").val();
		$.post("<%=path %>/user1",
			{"name":name,"hobby":hobby},
			function(data) {
				$("#name1").val(data.name);
				$("#pwd1").val(data.pwd);
				$("#age1").val(data.age);
				$("#hobby1").val(data.hobby);
				alert(data.hobby);
			}, "json"
		);
	}
	
	function testFormPost1() {
		$.post("<%=path %>/user1",
			$("#form").serialize(), // 直接从表单中获取所有的数据并组装成JSON格式的对象
			function(data) {
				$("#name1").val(data.name);
				$("#pwd1").val(data.pwd);
				$("#age1").val(data.age);
				$("#hobby1").val(data.hobby);
				alert(data.hobby);
			}, "json"
		);
	}
	
</script>

</head>
<body>	
		Name: <input id="name" type="text" name="name"/>
		Pwd: <input id="pwd" type="password" name="pwd"/>
		Age: <input id="age" type="text" name="age"/>
		Hobby: <input id="hobby" type="text" name="hobby"/>
		<button onclick="testGet();">使用GET请求从服务端获取输入值并把值显示到页面</button>
		<button onclick="testPost();">使用POST请求从服务端获取输入值并把值显示到页面</button>
		<button onclick="testJqueryGet();">使用jQuery GET请求从服务端获取输入值并把值显示到页面</button>
		<button onclick="testJqueryPost();">使用jQuery POST请求从服务端获取输入值并把值显示到页面</button>
		<button onclick="testJSON();">JSON测试</button>
		
		<form id="form">
			Name1: <input id="name1" type="text" name="name"/>
			Pwd1: <input id="pwd1" type="password" name="pwd"/>
			Age1: <input id="age1" type="text" name="age"/>
			Hobby1: <input id="hobby1" type="text" name="hobby"/>
			<input type="button" value="从服务端获取输入值并把值显示到页面" onclick="testFormPost();"/>
			<input type="button" value="从服务端获取输入值并把值显示到页面" onclick="testFormPost1();"/>
		</form>
</body>
</html>