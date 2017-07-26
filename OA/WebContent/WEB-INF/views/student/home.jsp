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
<title>学生首页</title>
<style>
<!-- 
@import url(<%=path %>/css/bootstrap.min.css);/*这里是通过@import引用CSS的样式内容*/ 
--> 
</style> 
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="<%=path%>/css/main.css" />
<link rel="stylesheet" href="<%=path%>/jquery-easyui/themes/icon-hamburg.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path%>/jquery-easyui/themes/jeasyui.icons.hamburg.js"></script>


<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		tabCloseEven();
		$('#p1').panel('refresh', '<%=path %>/show/todayDuty');
	});
	
	function refreshHome() {
		window.location.href = "<%=path %>/show/stuRefresh";
	}
	
	function exit() {
		window.location.href = "<%=path %>/show/stuExit";
		/* $.messager.confirm("提示", "确定退出吗？", function(r) {
			if (r) {
				$.messager.alert("提示", "感谢您的使用，再见", "info");
			}
		}); */
	}
	
	function weekDay(value, row, index) {
		if (value == "1")  {
			return "周一";
		} else if (value == "2") {
			return "周二";
		} else if (value == "3") {
			return "周三";
		} else if (value == "4") {
			return "周四";
		} else if (value == "5") {
			return "周五";
		} else if (value == "6") {
			return "周六";
		} else if (value == "7") {
			return "周日";
		}
	}
	
	function empName(value, row, index) {
		if (value != null) {
			return value.name;
		}
		return "暂无";
	}
	
	function dutyAdd(value, row, index) {
		if(value != null && value != "") {
			return value;
		}
		return "暂无";
	}
	
	function changeSkin(osel) {
		var url = "<%=path%>/jquery-easyui/themes/" + osel.options[osel.selectedIndex].value + "/easyui.css";
		var link = document.createElement("link");
		 link.rel = "stylesheet";
		 link.type = "text/css";
		 link.href = url;
		 document.getElementsByTagName("head")[0].appendChild(link);
	}
	
	function tabsClose(){  
	    var tab=$('#tabs').tabs('getSelected');//获取当前选中tabs  
	    var index = $('#tabs').tabs('getTabIndex',tab);//获取当前选中tabs的index  
	    $('#tabs').tabs('close',index);//关闭对应index的tabs  
	} 
	
	function realSysTime(clock){ 
		var now=new Date(); //创建Date对象 
		var year=now.getFullYear(); //获取年份 
		var month=now.getMonth(); //获取月份 
		var date=now.getDate(); //获取日期 
		var day=now.getDay(); //获取星期 
		var hour=now.getHours(); //获取小时 
		var minu=now.getMinutes(); //获取分钟 
		var sec=now.getSeconds(); //获取秒钟 
		if (sec < 10) {
			sec = "0" + sec;
		}
		if (minu < 10) {
			minu = "0" + minu;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day]; //获取中文的星期 
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
		clock.innerHTML=time; //显示系统时间 
		} 
		window.onload=function(){ 
		window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
		} 
	
	
</script>

</head>
<body class="easyui-layout">
	<!-- 顶部 -->
	<div data-options="region:'north'" style="height:60px">
		<img src="<%=path %>/images/logo.png" class="logo" />
		<div style ="position:absolute;left:500px;top:20px;"><span style = "font-size:20px;">当前时间：<span id = "clock" style = "font-size:20px;"></span></span></div>
		<div class="welcome">
			<span style="font-weight: bold;">身份：<span style="color: red;">${sessionScope.stu.role }</span> &nbsp;&nbsp;&nbsp;欢迎您, <span style="color: red;">${sessionScope.stu.name }</span> &nbsp;&nbsp;&nbsp;</span>
			<a href="javascript:;" onclick="refreshHome();">刷新主页</a>
			<a href="javascript:;" onclick="exit();">安全退出</a>
			<select style="border: none;" name="changeSkin" onChange="changeSkin(this)">
				<option value="default">默认主题</option>
				<option value="gray">灰色主题</option>
				<option value="black">黑色主题</option>
			</select>
		</div>
	</div>
	
	<!-- 中间部分 -->
	<div data-options="region:'west',split:true" title="菜单" style="width:250px;">
		<div class="easyui-accordion menu" style="width:250px;">
			
			
			<div title="学生管理" class="menu1" data-options="iconCls:'icon-hamburg-customers'" style="padding:10px;">
				<ul class="easyui-tree">
					<li><a href="javascript:;" onclick="addTab('成绩查询', '<%=path %>/show/student_score');">成绩查询</a></li>
					<li><a href="javascript:;" onclick="addTab('请假查询', '<%=path %>/show/student_leave');">请假查询</a></li>
					<li><a href="javascript:;" onclick="addTab('反馈查询', '<%=path %>/show/student_feedback');">反馈查询</a></li>
					<li><a href="javascript:;" onclick="addTab('就业查询', '<%=path %>/show/student_job');">就业查询</a></li>
					<li><a href="javascript:;" onclick="addTab('谈心查询', '<%=path %>/show/student_talk');">谈心查询</a></li>
					<li><a href="javascript:;" onclick="addTab('考勤查询', '<%=path %>/show/student_checking');">考勤查询</a></li>
					<li><a href="javascript:;" onclick="addTab('总结查询', '<%=path %>/show/student_summary');">总结查询</a></li>
				</ul>
			</div>
			<div title="教务查询" class="menu1" data-options="iconCls:'icon-hamburg-library'" style="padding:10px;">
				<ul class="easyui-tree">
					<li><a href="javascript:;" onclick="addTab('查看课程', '<%=path %>/show/showCourse');">查看课程</a></li>
					<li><a href="javascript:;" onclick="addTab('查看课程进度查询', '<%=path %>/show/Progress');">查看课程进度查询</a></li>
				</ul>
			</div>
			
			<div title="系统设置" class="menu1" data-options="iconCls:'icon-hamburg-settings'" style="padding:10px;">
				<ul class="easyui-tree">
					<li><a href="javascript:;" onclick="addTab('修改密码', '<%=path %>/show/stuUpdatePwd');">修改密码</a></li>
					<li><a href="javascript:;" onclick="addTab('查看系统公告查询', '<%=path %>/show/notice');">查看系统公告查询</a></li>
					
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
			<div title="主页" data-options="iconCls:'icon-hamburg-home'">
				<!-- 最新公告 -->
				<div class="cs-home-remark" style="padding-left: 70px; padding-top: 20px;">
					<div id="p" class="easyui-panel" title="最新公告" style="width:987px;height:255px;" data-options="footer:'#ft'">
				    	<!-- 中间的轮播图 -->
						<div class="row clearfix">
							<div class="col-md-12">
								<div class="carousel slide" id="carousel-322161">
									<ol class="carousel-indicators">
										<li class="active" data-slide-to="0" data-target="#carousel-322161">
										</li>
										<li data-slide-to="1" data-target="#carousel-322161">
										</li>
										<li data-slide-to="2" data-target="#carousel-322161">
										</li>
									</ol>
									<div class="carousel-inner">
										<s:iterator value="#session.notices" var="notice" status="s">
											<s:if test="#s.index == 1">
												<div class="item active">
													<img alt="" src="<%=path %>/images/background22.jpg" />
													<div class="carousel-caption" style="font-size: 16px;color: black">
														<h3>
															${notice.name }
														</h3>
														<p>
															${notice.des }
														</p>
														<p style="text-align: right;">
															${notice.emp.name }（宣）
														</p>
														<p style="text-align: center;">
															${notice.noticeDay }
														</p>
													</div>
												</div>
											</s:if>
											<s:else>
												<div class="item">
													<img alt="" src="<%=path %>/images/background22.jpg" />
													<div class="carousel-caption" style="font-size: 16px;color: black">
														<h3>
															${notice.name }
														</h3>
														<p>
															${notice.des }
														</p>
														<p style="text-align: right;">
															${notice.emp.name }（宣）
														</p>
														<p style="text-align: center;">
															${notice.noticeDay }
														</p>
													</div>
												</div>
											</s:else>
										</s:iterator>
									</div> 
									<a class="left carousel-control" href="#carousel-322161" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> 
									<a class="right carousel-control" href="#carousel-322161" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
								</div>
							</div>
						</div>
				    </div>
				    <div id="ft" style="padding:5px; text-align: right; ">
				    </div>
				    
				    <div style="margin-top: 30px;">
				   	 	<!-- 今日值班 -->
				   	 	<div style="float: left; margin-right: 50px;">
							<div id="p1" class="easyui-panel" title="今日值班" style="width:350px;height:285px;padding:10px;"
									data-options="
										tools:[{
											iconCls:'icon-reload',
											handler:function(){
												$('#p1').panel('refresh', '<%=path %>/show/todayDuty');
											}
										}]
									">
							</div>
				   	 	</div>
				   	 	
				    	<!-- 本周值班 -->
				    	<div>
				    		<table class="easyui-datagrid" title="本周值班安排" style="width:590px;height:210px;"
									data-options="singleSelect:true,collapsible:true,url:'<%=path %>/duty/pager',method:'get'">
								<thead>
									<tr>
										<th data-options="field:'weekDay',width:50" formatter="weekDay">周几</th>
										<th data-options="field:'emp1',width:65" formatter="empName">值班老师</th>
										<th data-options="field:'add1',width:65" formatter="dutyAdd">值班范围</th>
										<th data-options="field:'emp2',width:65" formatter="empName">值班老师</th>
										<th data-options="field:'add2',width:65" formatter="dutyAdd">值班范围</th>
										<th data-options="field:'emp3',width:70" formatter="empName">值班班主任</th>
										<th data-options="field:'add3',width:65" formatter="dutyAdd">值班范围</th>
										<th data-options="field:'emp4',width:65" formatter="empName">总值班</th>
										<th data-options="field:'add4',width:65" formatter="dutyAdd">值班范围</th>
									</tr>
									
								</thead>
							</table>
					    </div>
				    	<p style="clear: both;"></p>
				    </div>
				    
				</div>
			</div>
		</div>
	</div>
	
	<!-- 底部 -->
	<div data-options="region:'south',split:true" class="bottom" style="height:50px;">Copyright © 2016 - 2017 15秋预科班项目小组一版权所有</div>
	
	<!-- 菜单窗口 -->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseleft">关闭左边选项卡</div>
		<div id="mm-tabcloseright">关闭右边选项卡</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>