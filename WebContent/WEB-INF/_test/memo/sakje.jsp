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
<h2>메모 삭제</h2>

<form name="form">
<input type="hidden" name="no" value="${resultDto.no }" >
<table border="1">
	<tr>
		<td>작성자</td>
		<td>${resultDto.writer }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${resultDto.content }</td>
	</tr>
	<tr>
		<td colspan="2"><button type="button" onclick="save();">삭제</button></td>
	</tr>
</table>
</form>

<script>
function save() {
	if(confirm('삭제OK?')) {
		document.form.action="${path}/memo/sakjeProc";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
</body>
</html>