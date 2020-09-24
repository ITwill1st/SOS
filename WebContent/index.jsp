<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/jquery.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body { padding-top: 30px; }
header {text-align: right;}
h1 {text-align: center;}

</style>
<%
	request.setCharacterEncoding("UTF-8");
	String mem_id = (String)session.getAttribute("mem_id");
	String mem_email = (String)session.getAttribute("mem_email");
	String mem_name = (String)session.getAttribute("mem_name");
	String mem_phone = (String)session.getAttribute("mem_phone");
	MemberBean mb=(MemberBean)session.getAttribute("memberInfo");

%>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
	<header>
		<%if(mem_id == null){
			%> 
			<a href="MemberLoginForm.me">로그인</a>&nbsp;|| &nbsp;<a href="NonLogin.me">비회원</a> || &nbsp;<a href="MemberJoinForm.me">회원가입</a>
			<%
		}else{%>
		<%
 			if(mem_phone == null){
 				%> 
				<a href="MyInfo.me"><%=mem_email %>님</a>&nbsp; || &nbsp;<a href="MemberList.me">관리자 페이지</a>&nbsp; || &nbsp;<a href="MemberLogout.me">로그아웃</a>
			<%}else{
				%> 
				<a href="MyInfo.me"><%=mem_id %>님</a>&nbsp; || &nbsp;<a href="MemberList.me">관리자 페이지</a>&nbsp; || &nbsp;<a href="MemberLogout.me">로그아웃</a>
			<%}	
	 	}%> 
	 	
	 	
	</header>
	<h1>MainControll</h1>
    <ul>
	<li><a href="OrderMain.or">Order</a></li>
	<li><a href="RsvMain.rsv">RsvMain</a></li>
	<li><a href="Login.me">Login</a></li>
	<li><a href="Join.me">Join</a></li>
	<li><a href="MyInfo.me">MyInfo</a></li>
	<li><a href="MyOrderList.me">MyOrderList</a></li>
	<li><a href="MyRsvList.rsv">MyRsvList</a></li>
	<li><a href="MyProfile.me">MyProfile</a></li>
	<li><a href="TablesMain.tb">Tables</a></li>
	<li><a href="ProductMGM.po">ProductMGM</a></li>
	<li><a href="ProfitAnalysis.pa">ProfitAnalysis</a></li>
    </ul>
	
  </div>
</nav>
	
<script type="text/javascript">

 $(document).ready(function() {
	 
	 $.getJSON('itemInfo.jsp', function(rdata) {
	// itemInfo에 뿌려져있는 정보 가져올 것
		 $.each(rdata, function(index, item) {
			 
			 var img = "<img src='product/productUpload/"+item.img+"' width =150/>";
			 // img 변수 선언 
			 $('table').append("<tr><td rowspan='4'>"+img+"</td><td>번호</td><td>"+item.num+"</td></tr><tr><td>메뉴가격</td><td>"+item.price+"</td></tr><tr><td>메뉴이름</td><td>"+item.name+"</td></tr><tr><td>메뉴설명</td><td>"+item.info+"</td></tr>");
			 
			
				
		 });
		 
	});
	
	 console(log);
	 
});

</script>

<table border="1">
<tr><td>메뉴사진</td><td>항목</td><td>내용</td></tr>


</table>
	
</body>
</html>