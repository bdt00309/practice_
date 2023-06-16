<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../../_include/inc_menu.jsp" %>   
<%@ include file = "_inc_top.jsp" %>  
<%@ include file = "../../../_include/inc_header.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>학사관리_학생상세목록</h2>


<table border="1" width="80%">

	<tr>
		<td>학번</td>
		<td>${resultDto.hakbun }</td>
	</tr>
	<tr>	
		<td>이름</td>
		<td>${resultDto.name }</td>
	</tr>
	<tr>	
		<td>연락처</td>
		<td>${resultDto.phone }</td>
	</tr>
	<tr>	
		<td>부모연락처</td>
		<td>${resultDto.parent_phone }</td>
	<tr>	
	<tr>	
		<td>주소</td>
		<td>
			${resultDto.addr1 }
			${resultDto.addr2 }
			${resultDto.addr3 }
			${resultDto.addr4 }
		</td>
	<tr>
		<td>등록일</td>
		<td>${resultDto.regi_date }</td>
	</tr>
</table>

<div style="border:0px solid red; margin-top:10px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '');">목록</a>
|
<a href="#" onclick="move('chuga.do', '');">등록</a>
|
<a href="#" onclick="move('sujung.do', '${resultDto.hakbun }');">수정</a>
|
<a href="#" onclick="move('sakje.do', '${resultDto.hakbun }');">삭제</a>
</div>	


</body>
</html>