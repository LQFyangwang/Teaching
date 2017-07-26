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
	/**
		$(function(){});在页面加载完毕就会执行里面的函数
	*/
	$(function() {
		alert("jquery");
	});
		
	function testId() {
		var input1 = document.getElementById("name"); // dom对象
		alert("dom对象的input值" + input1.value);
		var input2 = $("#name"); // 获取的是jQuery对象
		alert("jQuery对象直接使用属性" + input2.value); // jQuery对象与DOM对象不一样，jQuery没有DOM对象的属性
		alert("jQuery使用函数来输出属性" + input2.val()); // jQuery对象虽然没有DOM对象的属性，但是是对DOM对象的封装，能够通过函数返回想要的属性值
		alert($(input1).val()); // $(dom对象) 把DOM对象转化成jQuery对象
		var pwd = $('#pwd');// 通过id选择器获取jquery对象
	}	

	function testClass() {
		var myAgeClass = $(".my_age"); // 返回DOM对象的数组
		alert(myAgeClass.length); // 通过length属性获取数组的的长度
		for (var i = 0, len = myAgeClass.length; i < len; i++) {
			alert(myAgeClass[i].value); // myAgeClass[i]也是 DOM对象
		}
		for (var i = 0, len = myAgeClass.length; i < len; i++) {
			alert($(myAgeClass[i]).val());
		}
		// 对数组进行循环操作，each的第一个参数是数组，第二个参数是function函数
		// function函数可以有两个参数，第一个参数接收索引，第二个参数接收对象
		$.each(myAgeClass, function(idx, obj) {
			alert(obj.value)
		});
		//对数组进行循环扣篮，each的第一个参数是数组，第二个参数是function函数
		// function函数没有参数，在函数内部this表示数组中循环每一个对象
		$.each(myAgeClass, function() {
			alert($(this).val() + "=");// $(this)就是把每次循环拿到的dom对象转换成jquery对象
		})
	}
	
	function testElement() {
		var aArray = $("a");
		$.each(aArray, function() {
			alert(this.href + ", " + $(this).attr("title")); // attr("属性") 获取指定标签的指定属性的值
		});
	}
	
	function testGet() {
		// 假设通过jquery访问服务端获取到值，服务端不需要做转发或重定向
		var name = "abc";
		var pwd = "123456";
		var age = 20;
		var hobby = "running";
		
		$("#name").val(name);
		$("#pwd").val(pwd);
		$("#age").val(age);
		$("#hobby").val(hobby);
	}
	
</script>

</head>
<body>	
	<form action="<%=path %>/user" method="post">
		Name: <input id="name" type="text" name="name" value="${requestScope.user.name }"/>
		Pwd: <input id="pwd" type="password" name="pwd" value="${requestScope.user.pwd }"/>
		Age: <input id="age" type="text" name="age" class="my_age" value="${requestScope.user.age }"/>
		Hobby: <input id="hobby" type="text" name="hobby" class="my_age" value="${requestScope.user.hobby }"/>
		<a href="http://www.abc.com" title="www.abc.com">abc.com</a>
		<a href="http://www.abcd.com" title="www.abcd.com">abcd.com</a>
		<button onclick="testId();">ID选择器</button>
		<button onclick="testClass();">类选择器</button>
		<button onclick="testElement();">标签选择器</button>
		<input type="submit" value="从服务端获取输入值并把值显示到页面" />
	</form>
</body>
</html>