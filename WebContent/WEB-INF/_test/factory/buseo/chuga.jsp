<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../../_include/inc_header.jsp" %>
<%@ include file = "../../../_include/inc_menu.jsp" %>
<%@ include file = "_inc_top.jsp" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>부서관리_부서등록</h2>

<form name="form">
	<table border="1">
		<tr>
			<td>부서명</td>
			<td><input type="text" name="buseoName"></td>
		</tr>

		<tr>
			<td colspan="2">
			<button type="button" onClick="save();">등록하기</button>
		</tr>
	</table>
</form>

<script>
function save() {
	if(confirm('등록OK?')) {
		document.form.action='${path}/buseo_servlet/chugaProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>