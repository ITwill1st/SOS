<%@page import="vo.RsvDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="The Venue template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link href="plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link href="plugins/jquery-datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="plugins/jquery-timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="../styles/responsive.css">
<link rel="stylesheet" type="text/css" href="member/style/myOrderList.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>member/myInfo.jsp</title>
<%
ArrayList<RsvDTO> rsvList = (ArrayList<RsvDTO>)request.getAttribute("rsvList");
String mem_id = (String)session.getAttribute("mem_id");
String mem_email = (String)session.getAttribute("mem_email");
String mem_name = (String)session.getAttribute("mem_name");
String mem_phone = (String)session.getAttribute("mem_phone");
MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
%>
</head>
<body>
<!-- header -->
<jsp:include page="../inc/myInfo_top.jsp"/>
<!-- /header -->
<div class="super_container">
<div class="home" style="height: 600px;">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_subtitle page_subtitle">Silent Order</div>
							<div class="home_title"><h1>특별한 경험을 선물합니다</h1></div>
							<div class="home_text ml-auto mr-auto">
								<p>2020년 10월 21일, 비대면 주문 시스템 도입 매장 Silent Order가 오픈합니다</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="scroll_icon"></div>
	</div>
<div id="main">
<h2>예약내역</h2>
<%for(RsvDTO rsvDTO : rsvList){ %>
<div id="myinfo">
<h3>예약번호 : <%=rsvDTO.getRsv_num()%></h3><br>
<h3>예약날짜 : <%=rsvDTO.getRsv_date() %></h3><br>
<h3>예약시간 : <%=rsvDTO.getRsv_time() %></h3><br>
<h3>이메일   : <%=rsvDTO.getMem_email() %></h3><br>
</div>
<%} 
%>

<br>
<br>
<br>
<br><br><br><br>
</div>
<%-- <c:if test="${sessionScope.id eq 'admin'}"> --%>
<%-- 		<c:forEach var="dto" items="${rsv_All}"> --%>
<!-- 			<table border="1"> -->
<!-- 				<tr> -->
<%-- 					<td>예약 날짜</td><td>${dto.rsv_date }</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td>예약 시간</td><td>${dto.rsv_time }</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td>예약 인원</td><td>${dto.rsv_pax }</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td>예약 번호</td><td>${dto.rsv_num }</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td>예약 메일</td><td>${dto.mem_email }</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<%-- 					<td><c:if test="${dto.rsv_check eq 1 }"> --%>
<%-- 	${예약 완료 } --%>
<%-- 	</c:if> <c:if test="${dto.rsv_check eq 0 }"> --%>
<%-- 	${ 예약 대기}</c:if> <c:if test="${dto.rsv_check eq -1 }"> --%>
<%-- 	${ 예약 취소}</c:if></td> --%>
<!-- 				</tr> -->
<!-- 			</table> -->
<%-- 		</c:forEach> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${sessionScope.id != 'admin' }"> --%>
<!-- 	<table border="1"> -->
<!-- 		<tr> -->
<%-- 			<td>예약 날짜</td><td>${rsv_list.rsv_date }</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>예약 시간</td><td>${rsv_list.rsv_time }</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>예약 인원</td><td>${rsv_list.rsv_pax }</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>예약 번호</td><td>${rsv_list.rsv_num }</td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->
<%-- 	</c:if> --%>

<!-- /footer -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- /footer -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap-4.1.2/popper.js"></script>
<script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="plugins/jquery-datepicker/jquery-ui.js"></script>
<script src="plugins/jquery-timepicker/jquery.timepicker.js"></script>
<script src="js/custom.js"></script>
</body>
</html>
