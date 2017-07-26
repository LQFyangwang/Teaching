<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>今日值班</title>
</head>
<body>
	<table>
		<s:if test="#session.tdis">
			<s:iterator value="#session.tdis" var="tdi" status="s">
			<tr style="color: black;">
				<s:if test="#s.index == 2">
					<td style="width: 100px;text-align: center; " ><h4>值班班主任</h4></td>
				</s:if>
				<s:elseif test="#s.index == 3">
					<td style="width: 100px;text-align: center; " ><h4>总值班</h4></td>
				</s:elseif>
				<s:else>
					<td style="width: 100px;text-align: center; " ><h4>值班老师</h4></td>
				</s:else>
				<td style="width: 100px;text-align: center; "><h4>值班范围</h4></td>
				<td style="width: 100px;text-align: center; "><h4>电话</h4></td>
			</tr>
			<tr style="color: gray; font-size: 13px; border-bottom: 1px solid black;">
				<td style="width: 100px;text-align: center; ">${tdi.name }</td>
				<td style="width: 100px;text-align: center; ">${tdi.add }</td>
				<td style="width: 100px;text-align: center; ">${tdi.phone }</td>
			</tr>
		</s:iterator>
		</s:if>
		<s:else>
			<tr style="color: black; font-size: 16px;">
				<td style="text-align: center; ">今日没有值班信息</td>
			</tr>
		</s:else>
		
	</table>
</body>
</html>