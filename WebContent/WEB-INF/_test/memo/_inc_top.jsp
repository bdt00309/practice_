<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
function move(value1, value2) {
	location.href= "${path}/memo/"+ value1 + "?memoNo=" + value2;
}
</script>