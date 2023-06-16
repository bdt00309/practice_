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
<h2>부서관리_부서수정</h2>

<form name="form">
<input type="text" name="buseoNo" value="${resultDto.buseoNo }">
	<table border="1">
		<tr>
			<td>부서번호</td>
			<td>${resultDto.buseoNo }</td>
		</tr>
		<tr>
			<td>부서명</td>
			<td><input type="text" name="buseoName" value="${resultDto.buseoName }"></td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" onClick="save();">수정하기</button>
		</tr>
	</table>
</form>

<script>
function save() {
	if(confirm('수정OK?')) {
		document.form.action='${path}/buseo_servlet/sujungProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>