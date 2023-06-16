<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../../_include/inc_menu.jsp" %>    
<%@ include file = "../../../_include/inc_header.jsp" %>  
<%@ include file = "_inc_top.jsp" %>      
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학사관리-시험목록</h2>

<table border="1">
	<tr>
		<td>시험번호</td>
		<td>시험명</td>
		<td>시험날짜</td>
		<td>등록일</td>
	</tr>
<c:if test="${fn:length(list) == 0 }">
	<tr>
		<td colspan="5" style="width:300px; height:100px;">등록된 내용이 없습니다.</td>
	</tr>
</c:if>	
<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.sihum_no }</td>
		<td><a href="#" onclick="move('view.do', '${dto.sihum_no }');">${dto.sihum_name }</a></td>
		<td>${dto.sihum_date }</td>
		<td>${dto.regi_date }</td>
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