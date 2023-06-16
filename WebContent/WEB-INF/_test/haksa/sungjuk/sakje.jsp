<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학사관리_성적삭제</h2>

<form name="form">
<input type="hidden" name="sungjuk_no" value="${resultDto.sungjuk_no }">
<table border="1">
	<tr>
		<td>학번</td>
		<td>${resultDto.hakbun }</td>
	</tr>
	<tr>
		<td>학번</td>
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
	<tr>
		<td colspan="2">
		<button type="button" onclick="save();">삭제</button></td>		
	</tr>
</table>
</form>

<script>
function save() {
	if(confirm('삭제OK?')) {
		document.form.action="${path}/haksaSungjuk_servlet/sakjeProc.do";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
</body>
</html>