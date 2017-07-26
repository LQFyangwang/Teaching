<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
		src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
		data-appid="101386023" data-redirecturi="http://localhost:8080/SocialProj/home.jsp" charset="utf-8"></script>
<script src="<%=path %>/js/json2.js"></script>
<title>Insert title here</title>
</head>
<body>

	<span id="qqLogin"></span>
	<button onclick="getOpenId();">获取OpenId和Access_token</button>
	<button onclick="getNickname();">获取昵称</button>
	<button onclick="addT();">发微博</button>
	<button onclick="getVip();">获取VIP信息</button>
	<script type="text/javascript">
	    QC.Login({
	       btnId:"qqLogin"	//插入按钮的节点id
	});
	    
	    var _accessToken;
	    var _openId;
	    function getOpenId() {
	    	QC.Login.getMe(function(openId, accessToken){
	    		_accessToken = accessToken;
	    		_openId  = openId;
	    		alert(openId);
	    		alert(accessToken);
	    	})
	    }
	    
	    function getNickname() {
	    	var data = QC.api("get_user_info", {})//指定接口访问成功的接收函数，s为成功返回Response对象
	    	.success(function(s){
	    		//成功回调，通过s.data获取OpenAPI的返回数据
	    		data = s.data;
	    		alert(data.nickname + ", " + data.gender + ", " + data.vip + ", " + data.figureurl + ", " + data.level);
	    	})
	    	//指定接口访问失败的接收函数，f为失败返回Response对象
	    	.error(function(f){
	    		//失败回调
	    		alert("获取用户信息失败！");
	    	})
	    	
	    }
	    
	   function addT() {
		   QC.api("add_t", {content : "#QQ互联JSSDK测试#曾经沧海难为水，除却巫山不是云。"})
		   .success(function (s) {
			 alert("成功发送微博");  
		   })
		   .error(function() {
			   
		   });
	   } 
	   
	   function getVip() {
		   QC.api("get_vip_info", {})
		   .success(function(s) {
			   alert(s.data.is_qq_vip);
		   });
	   }
	    
	</script>

	

</body>
</html>