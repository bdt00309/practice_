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
<h2>직책관리_직책수정</h2>

<form name="form">
<input type="text" name="positionNo" value="${resultDto.positionNo }">
	<table border="1">
		<tr>
			<td>직책번호</td>
			<td>${resultDto.positionNo }</td>
		</tr>
		<tr>
			<td>직책명</td>
			<td><input type="text" name="buseoName" value="${resultDto.positionName }"></td>
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
		document.form.action='${path}/position_servlet/sujungProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>