<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
function move(value1, value2, value3, value4, value5) {
	var imsiUrl = "";
	imsiUrl += "${path}/guestBook/"+ value1;
	imsiUrl += "?no=" + value2;
	imsiUrl += "&page=" + value3;
	imsiUrl += "&searchGubun="+ value4;
	imsiUrl += "&searchData=" + value5;

	
	location.href= imsiUrl;
}
</script>