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
<h2>성적 등록</h2>
<form name="form">
<table border="1">
	<tr>
		<td>학번</td>
		<td>
	
		<select name="hakbun">

		<option value=""> 선택 </option>
		<c:forEach var="Mdto" items="${studentList }">
		<option value="${Mdto.hakbun }">${Mdto.name }</option>
		</c:forEach>	
		</select>

		</td>
		
	</tr>
	<tr>
		<td>시험명</td>
		<td>
		<select name="sihum_no">
		<option value=""> 선택 </option>
		
		
<c:forEach var="Sdto" items="${sihumList }">
		<option value="${Sdto.sihum_no }">${Sdto.sihum_name }</option>
</c:forEach>
		</select>
		</td>
		
	</tr>
	<tr>
		<td>국어</td>
		<td><input type="text" name="kor"></td>		
	</tr>
	<tr>
		<td>영어</td>
		<td><input type="text" name="eng"></td>		
	</tr>
		<tr>
		<td>수학</td>
		<td><input type="text" name="mat"></td>		
	</tr>
	<tr>
		<td>과학</td>
		<td><input type="text" name="sci"></td>		
	</tr>
	<tr>
		<td>역사</td>
		<td><input type="text" name="his"></td>		
	</tr>

	<tr>
		<td colspan="2">
		<button type="button" onclick="save();">등록</button></td>		
	</tr>
</table>
</form>

<script>
function save() {
	if(confirm('등록OK?')) {
		document.form.action='${path}/haksaSungjuk_servlet/chugaProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>