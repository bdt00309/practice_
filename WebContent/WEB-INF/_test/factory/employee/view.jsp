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
<h2>직원관리_직원상세정보</h2>

<form name="form">
	<table border="1">
		<tr>
			<td>사번</td>
			<td>${resultDto.sabun }</td>
		</tr>
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
			<td>${resultDto.buseoName }(${resultDto.buseoNo})</td>
		</tr>
		<tr>
			<td>직위</td>
			<td>${resultDto.positionName }(${resultDto.positionNo })</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${resultDto.regiDate }</td>
		</tr>

	</table>
</form>
<hr>
<div style="border:0px solid red; margin-top:10px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '', '${searchGubun }', '${searchData }');">목록</a>
|
<a href="#" onclick="move('chuga.do', '', '${searchGubun }', '${searchData }');">등록</a>
|
<a href="#" onclick="move('sujung.do', '${resultDto.sabun }', '${searchGubun }', '${searchData }');">수정</a>
|
<a href="#" onclick="move('sakje.do', '${resultDto.sabun }', '${searchGubun }', '${searchData }');">삭제</a>
</div>	

</body>
</html>