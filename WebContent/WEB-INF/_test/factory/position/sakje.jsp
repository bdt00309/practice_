<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "../../../_include/inc_header.jsp" %>
<%@ include file = "_inc_top.jsp" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>직책관리_직책삭제</h2>

<form name="form">
<input type="hidden" name="positionNo" value="${resultDto.positionNo }">
	<table border="1">
		<tr>
			<td>부서번호</td>
			<td>${resultDto."positionNo" }</td>
		</tr>
		<tr>
			<td>부서명</td>
			<td>${resultDto.positionName }</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${resultDto.regiDate }</td>
		</tr>
					
		<tr>
			<td colspan="2">
			<button type="button" onClick="save();">삭제하기</button>
		</tr>
	</table>
</form>

<script>
function save() {
	if(confirm('삭제OK?')) {
		document.form.action='${path}/position_servlet/sakjeProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>


</body>
</html>