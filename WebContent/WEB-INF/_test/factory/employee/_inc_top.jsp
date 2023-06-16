<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <script>
function move(value1, value2, value3, value4, value5) {
	location.href= "${path}/employee_servlet/"+ value1 + "?sabun=" + value2 + "&nowPage=" + value3 + "&searchGubun=" + value4 + "&searchData=" + value5 ;
}
</script> -->

<!-- 
<script>
function move(value1, value2) {
	location.href= "${path}/employee_servlet/"+ value1 + "?sabun=" + value2;
}
</script> -->

<script>
function move(value1, value2, value3) {
	var moveurl = "";
	
	moveurl += "${path}/employee_servlet/" + value1;
	moveurl += "?sabun=" + value2;
	
	moveurl += "&page="+ value3;
	moveurl += "&searchGubun=${searchGubun}";
	moveurl += "&searchData=${searchData}";
	
	location.href= moveurl; 
}
</script>