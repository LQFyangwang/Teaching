<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<%
		String path = request.getContextPath();
	%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宏图OA系統</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=path %>/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=path %>/bootstrap/css/bootstrap-theme.css">
<!-- 一款bootstrap的图标插件-->
<link rel="stylesheet" href="<%=path %>/css/cikonss.css">

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">切换导航</span><span class="icon-bar"></span><span
								class="icon-bar"></span><span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<%=path  %>" style="margin-top: -10px;"> <img
							src="<%=path %>/images/logo.png" style="margin-top: -10px;" />
						</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="<%=path %>">首页</a></li>
							<li><a href="<%=path %>">产品</a></li>
							<li><a href="<%=path %>">案例</a></li>
							<li><a href="<%=path %>">博客</a></li>
							<li><a href="<%=path %>">帮助</a></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input class="form-control" type="text" />
							</div>
							<button type="submit" class="btn btn-default">搜索</button>
						</form>
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">更多<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="<%=path %>">管理网站</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-right" role="search"
							style="margin-right: 100px;">
							<a href="<%=path %>/show/empLogin" class="btn btn-success">员工登陆</a> 
							<a href="<%=path %>/show/stuLogin" class="btn btn-success">学生登陆</a>
						</form>
					</div>
				</nav>

			</div>
		</div>
	</div>
	<div style="float: left"></div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="carousel slide" id="carousel-68664">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-68664"></li>
					<li data-slide-to="1" data-target="#carousel-68664"></li>
					<li data-slide-to="2" data-target="#carousel-68664" class="active"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item">
						<img alt="" src="<%=path %>/images/background5.jpg" />
						<div class="carousel-caption">
							<h1></h1>
							<p></p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="<%=path %>/images/background6.jpg" />
						<div class="carousel-caption">
							<h1></h1>
							<p></p>
						</div>
					</div>
					<div class="item active">
						<div class="carousel-caption">
							<h1></h1>
							<p></p>
						</div>
						<img alt="" src="<%=path %>/images/background7.jpg" />

					</div>
				</div>
				<a class="left carousel-control" href="#carousel-68664" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
				</a> 
				<a class="right carousel-control" href="#carousel-68664" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1 style="text-align: center;">宏图公告</h1>
			</div>
		</div>


		<div class="row clearfix">
			<div class="list-group">
				<a href="#" class="list-group-item active"> 放假 通告： </a>
				<div class="list-group-item"></div>
				<div class="list-group-item">
					<h4 class="list-group-item-heading"></h4>
					<p class="list-group-item-text">
						关于元旦假期。。。。。。
					</p>
				</div>
				<div class="list-group-item"
					style="margin-right: 40px; text-align: right; color: red; width: 100%">
					2016-12-20 14:28:16</div>
				<a class="list-group-item active">发布人<span class="badge">公告管理员</span></a>
			</div>
		
			<div class="list-group">
				<a href="#" class="list-group-item active"> 表扬 通告： </a>
				<div class="list-group-item"></div>
				<div class="list-group-item">
					<h4 class="list-group-item-heading">陈俊</h4>
					<p class="list-group-item-text">
						扶老奶奶过马路，值得我们学习
					</p>
					<br /><br />
					<h4 class="list-group-item-heading">姚勇</h4>
					<p class="list-group-item-text">
						捡了1分钱上交了
					</p>
				</div>
				<div class="list-group-item"
					style="margin-right: 40px; text-align: right; color: red; width: 100%">
					2016-12-20 14:28:16</div>
				<a class="list-group-item active">发布人<span class="badge">公告管理员</span></a>
			</div>
		</div>

		<hr style="background: #0068B7; border: 1px;" />
		<div class="row" style="text-align: center;">
			Copyright © 15秋预科班 项目一组<br> This Blog is Licensed under a
			Creative Commons License. Some rights reserved.Powerd by
			Django/Python
		</div>
	</div>


	<!-- jQuery(necessary for Bootstrap's JavaScript plugins) -->
	<script src="<%=path %>/js/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>