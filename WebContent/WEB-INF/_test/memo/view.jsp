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

<h2>메모 상세보기</h2>
<input type="hidden" name="memoNo" value="${resultDto.memoNo }" >
<table border="1">
	<tr>
		<td>No</td>
		<td>${resultDto.memoNo }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${resultDto.writer }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${resultDto.content }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${resultDto.regiDate }</td>
	</tr>

</table>
<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onclick="move('list', '');">목록</a>
|
<a href="#" onclick="move('chuga', '');">등록</a>
|
<a href="#" onclick="move('sujung', '${resultDto.memoNo}');">수정</a>
|
<a href="#" onclick="move('sakje', '${resultDto.memoNo}');">삭제</a>
</div>	
</body>
</html>