<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/main.css"></link>
<script src="js/jquery-3.1.1.min.js"></script>
<script>
	function testShow() {
		$("#div1").show();
	}
	
	function testHide() {
		$("#div1").hide();
	}


	function testFadeIn() {
		$("#div1").fadeIn(5000); // 整个显示的过程花费5000ms
	}
	
	function testFadeOut() {
		$("#div1").fadeOut(5000);
	}
	
	function testSlideUp() {
		$("#div1").slideUp();
	}
	
	function testSlideDown() {
		$("#div1").slideDown();
	}
	
	function testFadeTo() {
		$("#div1").fadeTo("slow", 0.25);
	}
	
	function testCSSChange() {
		$("#div1").css("background-color", "yellow");
		$("#div1").css("height", "50px");
	}
	
	function testAnimate() {
		$("#div1").animate({
		    left:'250px',
		    opacity:'0.5',
		    height:'150px',
		    width:'300px'
		}, 5000);
	}
	
	function testCSSClass() {
		$("#div2").removeClass("div2");
		$("#div2").addClass("div3");
	}
	
	$(function() {
		$("#ad").fadeIn(3000);
		var a = setInterval(function() {
			$("#ad").fadeOut(3000);
		}, 10000); // 10s后执行function
		$("#btn1").click(function() { // 给指定的元素添加一个鼠标点击事件，
			testCSSClass();
		});
		/**
		blur() 触发、或将函数绑定到指定元素的 blur 事件 
change() 触发、或将函数绑定到指定元素的 change 事件 
click() 触发、或将函数绑定到指定元素的 click 事件 
dblclick() 触发、或将函数绑定到指定元素的 double click 事件 
focus() 触发、或将函数绑定到指定元素的 focus 事件 
keydown() 触发、或将函数绑定到指定元素的 key down 事件 
keypress() 触发、或将函数绑定到指定元素的 key press 事件 
keyup() 触发、或将函数绑定到指定元素的 key up 事件 
mousedown() 触发、或将函数绑定到指定元素的 mouse down 事件 
mouseenter() 触发、或将函数绑定到指定元素的 mouse enter 事件 
mouseleave() 触发、或将函数绑定到指定元素的 mouse leave 事件 
mousemove() 触发、或将函数绑定到指定元素的 mouse move 事件 
mouseout() 触发、或将函数绑定到指定元素的 mouse out 事件 
mouseover() 触发、或将函数绑定到指定元素的 mouse over 事件 
mouseup() 触发、或将函数绑定到指定元素的 mouse up 事件 

		*/
	});
	
</script>
</head>
<body>

	<div id="div1" style="display:none;background-color:green;">我是淡入的DIV</div>
	
	<button onclick="testShow();">直接显示</button>
	<button onclick="testHide();">直接隐藏</button>
	<button onclick="testFadeIn();">淡入效果</button>
	<button onclick="testFadeOut();">淡出效果</button>
	<button onclick="testFadeTo();">淡出到效果</button>
	<button onclick="testSlideUp();">SlideUp</button>
	<button onclick="testSlideDown();">SlideDown</button>
	<button onclick="testCSSChange();">修改CSS样式</button>
	<button onclick="testAnimate();">动画效果</button>
	
	<div id="ad" style="display:none;height:150px;background-color:green;"></div>
	
	<div id="div2" class="div2"></div>
	<button id="btn1">替换class样式</button>

</body>
</html>