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
<h2>학사관리-시험수정</h2>
<form name="form">
<input type="hidden" name="sihum_no" value="${resultDto.sihum_no }">
<table border="1">
	<tr>
		<td>시험번호</td>
		<td>${resultDto.sihum_no }</td>		
	</tr>
	<tr>
		<td>시험명</td>
		<td><input type="text" name="sihum_name" value="${resultDto.sihum_name }"></td>		
	</tr>
	<tr>
		<td>시험날짜</td>
		<td><input type="date" name="sihum_date" value="${resultDto.sihum_date }"></td>		
	</tr>
	<tr>
		<td>등록일</td>
		<td>${resultDto.regi_date }</td>		
	</tr>
	<tr>
		<td colspan="2">
		<button type="button" onclick="save();">수정</td>		
	</tr>
</table>
</form>
<script>
function save() {
	if(confirm('수정OK?')) {
		document.form.action="${path}/haksaSihum_servlet/sujungProc.do";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
</body>
</html>