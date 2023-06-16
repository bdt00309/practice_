<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "../../../_include/inc_menu.jsp" %>   
<%@ include file = "_inc_top.jsp" %>  
<%@ include file = "../../../_include/inc_header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학사관리_성적상세목록</h2>

<form name="form">
<input type="hidden" name="sungjuk_no" value="${resultDto.sungjuk_no }">
<table border="1">
	<tr>
		<td>학번</td>
		<td>${resultDto.hakbun }</td>
		
	</tr>
	<tr>
		<td>이름</td>
		<td>${resultDto.name }</td>
		
	</tr>
	<tr>
		<td>시험명</td>
		<td>${resultDto.sihum_name }</td>
		
	</tr>
	<tr>
		<td>국어</td>
		<td>${resultDto.kor }</td>		
	</tr>
	<tr>
		<td>영어</td>
		<td>${resultDto.eng }</td>		
	</tr>
		<tr>
		<td>수학</td>
		<td>${resultDto.mat }</td>		
	</tr>
	<tr>
		<td>과학</td>
		<td>${resultDto.sci }</td>		
	</tr>
	<tr>
		<td>역사</td>
		<td>${resultDto.his }</td>		
	</tr>
		<tr>
		<td>총점</td>
		<td>${resultDto.tot }</td>		
	</tr>
		<tr>
		<td>평균</td>
		<td>${resultDto.avg }</td>		
	</tr>
		<tr>
		<td>등급</td>
		<td>${resultDto.grade }</td>		
	</tr>

</table>
</form>

<div style="border:0px solid red; margin-top:10px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '');">목록</a>
|
<a href="#" onclick="move('chuga.do', '');">등록</a>
|
<a href="#" onclick="move('sujung.do', '${resultDto.sungjuk_no }');">수정</a>
|
<a href="#" onclick="move('sakje.do', '${resultDto.sungjuk_no }');">삭제</a>
</div>	


</body>
</html>