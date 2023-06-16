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
<h2>사원관리_사원등록</h2>

<form name="form">
<input type="hidden" name="searchGubun" value="${searchGubun }">
<input type="hidden" name="searchData" value="${searchData }">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>입사일</td>
			<td><input type="date" name="hireDate"></td>
		</tr>

		<tr>
			<td>급여</td>
			<td><input type="text" name="salary"></td>
		</tr>
		<tr>
			<td>부서</td>
			
			<td>
			<c:forEach var="buseoDto" items="${buseoList }">
				<input type="radio" name="buseoNo" value="${buseoDto.buseoNo }">${buseoDto.buseoName }&nbsp;
			</c:forEach>
			</td>
			
		</tr>
		<tr>
			<td>직위</td>
			<td>
				<c:forEach var="positionDto" items="${positionList }">
					<input type="radio" name="positionNo" value="${positionDto.positionNo }">${positionDto.positionName }&nbsp;
				</c:forEach>
			</td>
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
		document.form.action='${path}/employee_servlet/chugaProc.do';
		document.form.method='post';
		document.form.submit();
	}
}
</script>

</body>
</html>