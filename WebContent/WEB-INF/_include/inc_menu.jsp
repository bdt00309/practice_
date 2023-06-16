<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
body {background: lightyellow;}
ul { background: lightgray; padding: 6px; }
li {display: inline; padding: 10px 20px; }
li a {padding: 5px 10px; color: black; text-decoration: none; }
li a:hover { background: darkgray; }
li.dropdown {display: inline-block; }
.dropdown-content { display: none; position: absolute; background: ghostwhite: box-shadow: 0px 8px 16px 0px rgba(0,0,0,0,2); }
.dropdown-content a {display:block; padding: 12px 16px; text-decoration: none; text-align: lefft; color: black; }
.dropdown-content a:hover {background: lavender; }
.dropdown:hover .dropdown-content {display:block; } 

</style>


<ul>
	<li class="dropdown">
		<a href="${path }">Home</a>    
	</li>
	&nbsp;
	<li class="dropdown">
		<a href="#">학사</a>
			<div class="dropdown-content">
				<a href="${path }/haksaStudent_servlet/list.do">학생관리</a>
				<a href="${path }/haksaSihum_servlet/list.do">시험관리</a>
				<a href="${path }/haksaSungjuk_servlet/list.do">성적관리</a>
			</div>
	</li>

	<li class="dropdown">
		<a href="#">인사</a>
			<div class="dropdown-content">
				<a href="${path }/employee_servlet/list.do">직원관리</a>
				<a href="${path }/buseo_servlet/list.do">부서관리</a>
				<a href="${path }/position_servlet/list.do">직책관리</a>
			</div>
	</li>
	
	<li class="dropdown">
		<a href="#">남기는 글</a>
			<div class="dropdown-content">
				<a href="${path }/memo/list">방명록</a>
			</div>
	</li>
</ul>
<hr>