<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<script>
function move(value1, value2) {
	location.href= "${path}/haksaStudent_servlet/"+ value1 + "?hakbun=" + value2;
}
</script>