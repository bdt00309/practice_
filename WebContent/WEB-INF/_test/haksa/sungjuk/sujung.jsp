<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>성적 수정</h2>
<form name="form">
<input type="hidden" name="sungjuk_no" value="${resultDto.sungjuk_no }">
<table border="1">
	<tr>
		<td>학번</td>
		<td>
		<select name="hakbun">
		<option value=""> 선택 </option>
		<option value="${resultDto.hakbun }" selected>${resultDto.name }</option>
		</select>
		</td>
		
	</tr>
	<tr>
		<td>시험명</td>
		<td>
		<select name="sihum_no">
		<option value=""> 선택 </option>
		<option value="${resultDto.sihum_no }" selected>${resultDto.sihum_name }</option>
		</select>
		</td>
		
	</tr>
	<tr>
		<td>국어</td>
		<td><input type="text" name="kor" value="${resultDto.kor }"></td>		
	</tr>
	<tr>
		<td>영어</td>
		<td><input type="text" name="eng"value="${resultDto.eng }"></td>		
	</tr>
		<tr>
		<td>수학</td>
		<td><input type="text" name="mat" value="${resultDto.mat }"></td>		
	</tr>
	<tr>
		<td>과학</td>
		<td><input type="text" name="sci" value="${resultDto.sci }"></td>		
	</tr>
	<tr>
		<td>역사</td>
		<td><input type="text" name="his" value="${resultDto.his }"></td>		
	</tr>

	<tr>
		<td colspan="2">
		<button type="button" onclick="save();">수정</button></td>		
	</tr>
</table>
</form>

<script>
function save() {
	if(confirm('수정OK?')) {
		document.form.action="${path}/haksaSungjuk_servlet/sujungProc.do";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
</body>
</html>