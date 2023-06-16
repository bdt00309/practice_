<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../../../_include/inc_menu.jsp" %>    
<%@ include file = "../../../_include/inc_header.jsp" %>  
<%@ include file = "_inc_top.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>학사관리_학생수정</h2>

<form name="form">
<input type="hidden" name="hakbun" value="${resultDto.hakbun }">
<table border="1">
	<tr>
		<td>이름</td>
		<td>${resultDto.name }</td>
	</tr>
		<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone" value="${resultDto.phone }"></td>
	</tr>
	<tr>
		<td>부모전화번호</td>
		<td><input type="text" name="parent_phone" value="${resultDto.parent_phone }"></td>
	</tr>
		
	<tr>
		<td>주소</td>
		<td>
			<input type="text" id="sample6_postcode" name="addr1" placeholder="우편번호" value="${resultDto.addr1 }">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="sample6_address" name="addr2" placeholder="주소" value="${resultDto.addr2 }"><br>
			<input type="text" id="sample6_detailAddress" name="addr3" placeholder="상세주소" value="${resultDto.addr3 }">
			<input type="text" id="sample6_extraAddress" name="addr4" placeholder="참고항목" value="${resultDto.addr4 }"><br>
		</td>
	</tr>
			<tr>
		<td colspan="2"><button type="button" onclick="save();">수정</button></td>
	</tr>
</table>
</form>

<script>
function save() {
	if(confirm('수정하시겠습니까?')) {
		document.form.action="${path}/haksaStudent_servlet/sujungProc.do";
		document.form.method="post";
		document.form.submit();
	}
}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

</body>
</html>