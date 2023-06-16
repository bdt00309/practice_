<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../_include/inc_menu.jsp"%>
<%@ include file="../../../_include/inc_header.jsp"%>
<%@ include file="_inc_top.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학사관리-시험상세목록</h2>

	<table border="1">
		<tr>
			<td>시험번호</td>
			<td>${resultDto.sihum_no }</td>
		</tr>
		<tr>
			<td>시험명</td>
			<td>${resultDto.sihum_name }</td>
		</tr>
		<tr>
			<td>시험날짜</td>
			<td>${resultDto.sihum_date }</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${resultDto.regi_date }</td>
		</tr>
</table>		
<div style="border:0px solid red; margin-top:10px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '');">목록</a>
|
<a href="#" onclick="move('chuga.do', '');">등록</a>
|
<a href="#" onclick="move('sujung.do', '${resultDto.sihum_no }');">수정</a>
|
<a href="#" onclick="move('sakje.do', '${resultDto.sihum_no }');">삭제</a>
</div>			
</body>
</html>