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
<h2>방명록 삭제</h2>

<form name="form">
<input type="hidden" name="no" value="${resultDto.no }" >
<input type="hidden" name="page" value="${page }">
<input type="hidden" name="searchGubun" value="${searchGubun }">
<input type="hidden" name="searchData" value="${searchData }">
<table border="1">
	<tr>
		<td>이름</td>
		<td>${resultDto.name }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${resultDto.email }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${resultDto.content }</td>
	</tr>
	<tr>
		<td colspan="2"><button type="button" onclick="btnSakje();">삭제</button></td>
	</tr>
</table>
</form>

<script>
//$(document).ready(function() {
	//$("#passwd").focus();
	
	$("#btnSakje").click(function() {
		if(confirm('삭제OK?')) {
			document.form.action="${path}/guestBook/sakjeProc";
			document.form.method="post";
			document.form.submit();
		}
	});
	
//)};
</script>

</body>
</html>