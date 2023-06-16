<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../_include/inc_header.jsp" %>
<%@ include file = "../../_include/inc_menu.jsp" %>
<%@ include file = "_inc_top.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>방명록 목록</h2>
<table border="0">
	
	<c:forEach var="dto" items="${list }">
		<tr>
			<td style="padding: 0 0 20px 0">
			
			<table border="1">
			<tr>
				<td>이름</td>	
				<td>${dto.name }</td>
				<td>날짜</td>	
				<td>${dto.regiDate }</td>
			</tr>
			<tr>
				<td>이메일</td>	
				<td colspan="3">${dto.email }</td>
			</tr>	
			<tr>
				<td>내용</td>	
				<td colspan="4">${dto.content }
						<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
							<a href="#" onclick="move('sujung', '${dto.no}', '${page}', '${searchGunbun}', '${searchData}');">수정</a> / 
							<a href="#" onclick="move('sakje', '${dto.no}', '${page}', '${searchGunbun}', '${searchData}');">삭제</a>
						</div>
					</td>
				</tr>	
			</table>
			
			</td>
			</tr>
	</c:forEach>
</table>
<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onclick="move('list', '', '1', '${searchGunbun}', '${searchData}');">목록</a>
|
<a href="#" onclick="move('chuga', '', '${page}', '${searchGunbun}', '${searchData}');">등록</a>
|
</div>	

<div style="border:0px solid red; padding-top:20px; width:80%;" align="center">
<form name="searchForm">
<select name="searchGubun">
<option value="">--선택--</option>
<option value="name">이름</option>
<option value="content">내용</option>
<option value="name_content">이름+내용</option>
</select>
&bnsp;
<input tupe="text" name="searchData">
&bnsp;
<button type="button" id="btnSearch">검색</button>
</form>
<script>
//$(document).ready(function() {
	//$("#name").focus();
	
	$("#btnSearch").click(function() {
		if(confirm('검색OK?')) {
			document.form.action="${path}/guestBook/list";
			document.form.method="post";
			document.form.submit();
		}
	});
//)};
</script>



</div>


</body>
</html>