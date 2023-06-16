<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../../_include/inc_header.jsp" %>
<%@ include file = "../../../_include/inc_menu.jsp" %>
<%@ include file = "_inc_top.jsp" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>직책관리_직책목록</h2>

<table border="1">
	<tr>
		<td>직책번호</td>
		<td>직책명</td>
		<td>등록일</td>
	</tr>
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.positionNo }</td>
			<td><a href="#" onclick="move('view.do', '${dto.positionNo }');">${dto.positionName }</a></td>
			<td>${dto.regiDate }</td>
		</tr>
	</c:forEach>
</table>	
	
<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '');">목록</a>
|
<a href="#" onclick="move('chuga.do', '');">등록</a>
|
</div>		



	
	
	
	
</body>
</html>