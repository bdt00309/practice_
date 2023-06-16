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
<h2>직원관리_직원목록</h2>

<c:choose>
	<c:when test="${searchData != ''} ">
		* 검색어 <font style="color:red; font-weight:bold;">"${searchData }"</font>로 검색된 목록: ${fn:length(list)}건<br>
	</c:when>
	
	<c:otherwise>
	* 전체목록: ${totalRecord}건
	</c:otherwise>

</c:choose>

<table border="1">
	<tr>
		<td>사번</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>입사일</td>
		<td>급여</td>
		<td>부서명</td>
		<td>직위</td>
		<td>등록일</td>
	</tr>
	<c:if test="${totalRecord == 0 }">
		<tr>
			<td colspan="9">등록된 내용이 없습니다.</td>
		</tr>
	</c:if>

	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.sabun }</td>
			<td><a href="#" onclick="move('view.do', '${dto.sabun }', '${nowPage }', '${searchGubun }', '${searchData }');">${dto.name }</a></td>
			<td>${dto.phone }</td>
			<td>${dto.email }</td>
			<td>${dto.hireDate }</td>
			<td>${dto.salary }</td>
			<td>${dto.buseoName }(${dto.buseoNo})</td>
			<td>${dto.positionName }(${dto.positionNo })</td>
			<td>${dto.regiDate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="9">	
			<form name="searchForm">
			<select name ="searchGubun">
			<c:choose>
				<c:when test="${searchGubun == 'name'}">
					<option value="">--선택--</option>
					<option value="name" selected>이름</option>
					<option value="phone">전화번호</option>
					<option value="name_phone">이름+전화번호</option>
				</c:when>
					<c:when test="${searchGubun == 'phone'}">
					<option value="">--선택--</option>
					<option value="name">이름</option>
					<option value="phone" selected>전화번호</option>
					<option value="name_phone">이름+전화번호</option>
				</c:when>
					<c:when test="${searchGubun == 'name_phone'}">
					<option value="">--선택--</option>
					<option value="name">이름</option>
					<option value="phone">전화번호</option>
					<option value="name_phone" selected>이름+전화번호</option>
				</c:when>
					<c:otherwise>
					<option value="" selected>--선택--</option>
					<option value="name">이름</option>
					<option value="phone">전화번호</option>
					<option value="name_phone">이름+전화번호</option>
				</c:otherwise>
			</c:choose>
			</select>
			&nbsp;
			<input type="text" name="searchData" value="${searchData}">
			&nbsp;
			<button type="button" onclick="search();">검색</button>
			</form>
		</td>
	</tr>
</table>
<script>
function search() {
	if(confirm('검색OK?')) {
		document.searchForm.action="${path}/employee_servlet/list.do";
		document.searchForm.method="get";
		document.searchForm.submit();
	}
}
</script>
	
	
<div style="border:0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onclick="move('list.do', '', '${nowPage }');">목록</a>
|
<a href="#" onclick="move('chuga.do', '', '${nowPage }');">등록</a>
|
</div>		
	



<div style="border:0px solid red; padding-top:20px; width:80%;" align="center">	

<c:if test="${nowPage == 1}">
	[첫 페이지]
</c:if>
<c:if test="${nowPage > 1}">
	<a href="#" onclick="move('list.do', '', '1', '${searchGubun}', '${searchData}');">[첫 페이지]</a>
</c:if>

	&nbsp;
	
	<c:if test="${nowBlock > 1}">
		<a href="#" onclick="move('list.do', '', '${prePage }', '${searchGubun}', '${searchData}');">[이전 10개]</a>
	</c:if>
	
	<a href="#">[이전 10개]</a>
	&nbsp;
	
	<c:forEach var="k" begin="${startBlock}" end="${lastBlock }">
		<c:choose>
			<c:when test="${k==nowPage }">
				<font style="color:red; font-weight: bold;">[${k }]</font>
			</c:when>
			
			<c:otherwise>
				<a href="#" onclick="move('list.do', '', '${k }', '${searchGubun}', '${searchData}');">${k }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	&nbsp;
	<a href="#">[다음 10개]</a>
	&nbsp;
<c:if test="${nowBlock == totalBlock}">
	[끝 페이지]
</c:if>
<c:if test="${nowBlock < totalBlock}">
	<a href="#" onclick="move('list.do', '', '1', '${searchGubun}', '${searchData}');">[끝 페이지]</a>
	&nbsp;
</c:if>
	
	
	
</div>
</body>
</html>