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
<h2>부서관리_부서상세정보</h2>

<form name="form">
	<table border="1">
		<tr>
			<td>부서번호</td>
			<td>${resultDto.buseoNo }</td>
		</tr>
		<tr>
			<td>부서명</td>
			<td>${resultDto.buseoName }</td>
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
<a href="#" onclick="move('list.do', '');">목록</a>
|
<a href="#" onclick="move('chuga.do', '');">등록</a>
|
<a href="#" onclick="move('sujung.do', '${resultDto.buseoNo }');">수정</a>
|
<a href="#" onclick="move('sakje.do', '${resultDto.buseoNo }');">삭제</a>
</div>	

</body>
</html>