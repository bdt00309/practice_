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
<h2>직원관리_직원삭제</h2>

<form name="form">
<input type="hidden" name="sabun" value="${resultDto.sabun }">
<input type="hidden" name="searchGubun" value="${searchGubun }">
<input type="hidden" name="searchData" value="${searchData }">
	<table border="1">
		<tr>
			<td>이름</td>
			<td>${resultDto.name }</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${resultDto.phone }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${resultDto.email }</td>
		</tr>
		<tr>
			<td>입사일</td>
			<td>${resultDto.hireDate }</td>
		</tr>

		<tr>
			<td>급여</td>
			<td>${resultDto.salary }</td>
		</tr>
		<tr>
			<td>부서</td>
			<td>${resultDto.buseoName }</td>
		</tr>
		<tr>
			<td>직위</td>
			<td>${resultDto.positionName }</td>
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
		document.form.action='${path}/employee_servlet/sakjeProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>


</body>
</html>