<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug /> <!-- Struts提供的调试 -->
	<s:iterator value="#{'a_key':'a_value','b_key':'b_value'}" var="v">
		<s:property value="#v.key"/>
		<s:property value="#v.value"/>
		${requestScope.v.key } <!-- var所定义变量是放在request中的 -->
		${v.value }
	</s:iterator>
	<s:iterator value="{'a_key','b_key'}" var="vv" begin="1">
		<s:property value="#vv"/>
		${vv }
	</s:iterator>
	<s:iterator value="#request.users.{^#this.no % 2 == 0}" var="user" status="status">
		<!-- 筛选，#this表示迭代过程中的每一个对象，^#this.no 做判断，如果符合条件，则获取此条件的数据，不符合条件则不管，只返回从前往后的第一个结果
			$#this从后往前的第一个结果
		 -->
		<s:property value="#status.index + 1" />
		<s:property value="#user.name" />
		<s:property value="#user.pwd" />
		${status.index + 1 }
		${user.name }
		${user.pwd }
		<br />
	</s:iterator>
	
	
	<s:property value="#request.users[1].name" /> <!-- 指定获取某个索引上的数据 -->
	<s:property value="#request.users[1].getName()" /> <!-- 可以直接调用某个方法 -->
	
	<s:property value="[0].users[0].name" />
	
	
	<s:push value="'aaa'">
		<s:property value="[0].top" /> <!-- aaa -->
		<s:property value="[1].top" /> <!-- TagAction class -->
	</s:push>
	
	<s:property value="#request.ok" /> ${ok }
	<s:if test="#request.ok">
		ok is true
	</s:if>
	<s:else>
		ok is false
	</s:else>
	
	<s:include value="include.jsp"></s:include>
	
	<s:append var="a">
		<s:param value="{'中国', '美国', '日本'}"></s:param>
		<s:param value="{'中国1', '美国1', '日本1'}"></s:param>
	</s:append>
	<s:iterator value="#request.a" var="aa">
		${aa }
	</s:iterator>
	
	<s:merge var="b">
		<s:param value="{'中国', '美国', '日本'}"></s:param>
		<s:param value="{'中国1', '美国1', '日本1'}"></s:param>
	</s:merge>
	<s:iterator value="#request.b" var="bb">
		${bb }
	</s:iterator>
	
	<s:bean name="com.gs.bean.User" var="u">
		<s:property value="#request.u.setName('aaa')" />
		<s:property value="#request.u.name" />
		<s:param name="pwd" value="123456" />
	</s:bean>
	<s:property value="#request.u.pwd" />
	
	<s:set name="nameAA" value="'test'" scope="session">
	</s:set>
	<s:property value="#attr.nameAA" />
	
	<s:generator separator="," val="'a,b,c,d'" var="strArray"></s:generator>
	<s:iterator value="#strArray" var="str">
		${str }
	</s:iterator>
	
	<s:subset source="{'a', 'b', 'c', 'd'}" start="1" count="2" var="newArray">
		<s:iterator var="a">
			${a }
		</s:iterator>
	</s:subset>
	
	<s:bean name="com.gs.comparator.MyCompatator" var="MyCompatator" />
	<s:sort comparator="#MyCompatator" source="{2, 3, 10, 5, 4}" var="array">
		<s:iterator var="a">
			${a }
		</s:iterator>
	</s:sort>
	
	<s:bean name="com.gs.comparator.StringComparator" var="StringComparator" />
	<s:sort comparator="#StringComparator" source="{'jsxy_001','jsxy_004','jsxy_002'}" var="array">
		<s:iterator var="a">
			${a }
		</s:iterator>
	</s:sort>
	
	<s:debug /> 
</body>
</html>