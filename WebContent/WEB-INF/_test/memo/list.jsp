<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../_include/inc_header.jsp" %>
<%@ include file = "../../_include/inc_menu.jsp" %>
<%@ include file = "_inc_top.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>메모 목록</h2>
<table border="1">
	<tr>
		<td>No</td>
		<td>이름</td>	
		<td>내용</td>
		<td>날짜</td>
	</tr>
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.memoNo }</td>
			<td><a href="#" onclick="move('view', '${dto.memoNo }');">${dto.writer }</a></td>	
			<td>${dto.content }</td>
			<td>${dto.regiDate }</td>
		</tr>
	</c:forEach>
</table>
<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onclick="move('list', '');">목록</a>
|
<a href="#" onclick="move('chuga', '');">등록</a>
|
</div>	
</body>
</html>