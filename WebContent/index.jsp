<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	header {
		text-align: right;
	}
	
	h1 {
		text-align: center;
	}
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
	<header>
		
		
		<%if(mem_id == null){
			%> 
			<a href="MemberLoginForm.me">로그인</a>&nbsp; || &nbsp;<a href="MemberJoinForm.me">회원가입</a>
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
	<h1><a href="OrderMain.or">Order</a></h1>
	<h1><a href="RsvMain.rsv">RsvMain</a></h1>
	<h1><a href="Login.me">Login</a></h1>
	<h1><a href="Join.me">Join</a></h1>
	<h1><a href="MyInfo.me">MyInfo</a></h1>	
	<h1><a href="MyOrderList.me">MyOrderList</a></h1>
	<h1><a href="MyRsvList.rsv">MyRsvList</a></h1>
	<h1><a href="MyProfile.me">MyProfile</a></h1>
	<h1><a href="TablesMain.tb">Tables</a></h1>
	<h1><a href="ProductMGM.po">ProductMGM</a></h1>
	<h1><a href="ProfitAnalysis.pa">ProfitAnalysis</a></h1>
	
	
</body>
</html>