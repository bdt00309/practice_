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
<h2>방명록 등록</h2>

<form name="form">
<input type="hidden" name="page" value="${page }">
<input type="hidden" name="searchGubun" value="${searchGubun }">
<input type="hidden" name="searchData" value="${searchData }">
<table border="1" width="60%">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content" style="width:300px; height: 100px;"></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" id="btnList">목록</button>
			<button type="button" id="btnChuga">확인</button>
		</td>
	</tr>
</table>
</form>


<script>
//$(document).ready(function() {
	//$("#name").focus();
	
	$("#btnList").click(function() {
		location.href="${path}/guestBook/list?page=${page}&searchGubun=${searchGubun}&searchData=${searchData}";
	});
	
	$("#btnChuga").click(function() {
		if(confirm('등록OK?')) {
			document.form.action="${path}/guestBook/chugaProc";
			document.form.method="post";
			document.form.submit();
		}
	});
//)};
</script>

</body>
</html>