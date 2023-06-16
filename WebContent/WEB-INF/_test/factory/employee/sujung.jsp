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
<h2>직원관리_직원수정</h2>

<form name="form">
<input type="hidden" name="searchGubun" value="${searchGubun }">
<input type="hidden" name="searchData" value="${searchData }">
<input type="hidden" name="sabun" value="${resultDto.sabun }">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${resultDto.name }"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="${resultDto.phone }"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${resultDto.email }"></td>
		</tr>
		<tr>
			<td>입사일</td>
			<td><input type="date" name="hireDate" value="${resultDto.hireDate }"></td>
		</tr>

		<tr>
			<td>급여</td>
			<td><input type="text" name="salary" value="${resultDto.salary }"></td>
		</tr>
		<tr>
			<td>부서</td>
			<td><input type="text" name="buseoNo" value="${resultDto.buseoName }"></td>
		</tr>
		<tr>
			<td>직위</td>
			<td><input type="text" name="positionNo" value="${resultDto.positionName }"></td>
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
		document.form.action='${path}/employee_servlet/sujungProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>