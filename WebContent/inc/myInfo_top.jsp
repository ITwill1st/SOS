<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">
<%
	request.setCharacterEncoding("UTF-8");
	String mem_id = (String)session.getAttribute("mem_id");
	String mem_email = (String)session.getAttribute("mem_email");
	String mem_name = (String)session.getAttribute("mem_name");
	String mem_phone = (String)session.getAttribute("mem_phone");
	MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
%>
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="header_content d-flex flex-row align-items-center justify-content-start">
						<div class="logo">
							<a href="#">
								<div>SilentOrder</div>
								<div>restaurant</div> 
							</a>
						</div>
						<nav class="main_nav">
							<ul class="d-flex flex-row align-items-center justify-content-start">
								<li><a href="Main.do">Main</a></li>
								<li><a href="MyOrderList.me">My OrderList</a></li>
								<li><a href="MyRsvList.me">My Reservation</a></li>
								<% if (mem_id != null) {
 				 					if (mem_id.equals("admin") || mem_id.equals("owner")) {%>
								<li><a href="#">테이블</a></li>
								<li><a href="#">상품관리</a></li>
								<li><a href="#">예약관리</a></li>
								<li><a href="#">회원관리</a></li>
								<li><a href="#">매출분석</a></li>
									<%}
								} %>
							</ul>
						</nav>
						<div class="reservations_phone ml-auto">
						 <% if (mem_id == null) {%>
						   <a href="MemberLoginForm.me">Login</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MemberJoinForm.me">Sign Up</a>
						 <%} else if (mem_phone == null) {%>
						   <a href="MemberLogout.me">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MyInfo.me" ><%=mem_email %>'s Info</a>
						 <%} else { %>
						   <a href="MemberLogout.me">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MyInfo.me"><%=mem_id %>'s Info</a>
						 <%} %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Hamburger -->
	
	<div class="hamburger_bar trans_400 d-flex flex-row align-items-center justify-content-start">
		<div class="hamburger">
			<div class="menu_toggle d-flex flex-row align-items-center justify-content-start">
				<span>menu</span>
				<div class="hamburger_container">
					<div class="menu_hamburger">
						<div class="line_1 hamburger_lines" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
						<div class="line_2 hamburger_lines" style="visibility: inherit; opacity: 1;"></div>
						<div class="line_3 hamburger_lines" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<!-- Menu -->

	<div class="menu trans_800">
		<div class="menu_content d-flex flex-column align-items-center justify-content-center text-center">
			<ul>
				<li><a href="Main.do">Main</a></li>
				<li><a href="MyOrderList.me">My OrderList</a></li>
				<li><a href="MyRsvList.me">My Reservation</a></li>
				<% if (mem_id != null) {
	 					if (mem_id.equals("admin") || mem_id.equals("owner")) {%>
				<li><a href="#">테이블</a></li>
				<li><a href="#">상품관리</a></li>
				<li><a href="#">예약관리</a></li>
				<li><a href="#">회원관리</a></li>
				<li><a href="#">매출분석</a></li>
					<%}
				} %>
			</ul>
		</div>
		<div class="menu_reservations_phone ml-auto">
		<% if (mem_id == null) {%>
		   <a href="MemberLoginForm.me">Login</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MemberJoinForm.me">Sign Up</a>
		 <%} else if (mem_phone == null) {%>
		   <a href="MemberLogout.me">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MyInfo.me" ><%=mem_email %>'s Info</a>
		 <%} else { %>
		   <a href="MemberLogout.me">Logout</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="MyInfo.me"><%=mem_id %>'s Info</a>
		 <%} %>
		</div>
	</div>