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
<h2>학사관리-시험등록</h2>
<form name="form">
<table border="1">
	<tr>
		<td>시험명</td>
		<td><input type="text" name="sihum_name"></td>		
	</tr>
	<tr>
		<td>시험날짜</td>
		<td><input type="date" name="sihum_date"></td>		
	</tr>
	<tr>
		<td colspan="2">
		<button type="button" onclick="save();">등록</td>		
	</tr>
</table>
</form>
<script>
function save() {
	if(confirm('등록OK?')) {
		document.form.action="${path}/haksaSihum_servlet/chugaProc.do";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
</body>
</html>