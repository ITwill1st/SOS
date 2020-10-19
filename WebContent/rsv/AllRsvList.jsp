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
<link rel="stylesheet" href="product/style/list.css">
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
<div class="top">           
	
	                    <div class="logo_img">                  
	                    <img alt="" src="inc/SOS logo_v2.png" height="70px">
	                    </div>
	    
	                    <div class="logo">                  
	                        <p class="logo_title">Silent Order</p>
	                        <p class="logo_subtitle">restaurant</p>
	                    </div>
	                    
	                    <div class="logo_pos">
	                        <p class="pos_title">Resvation LIST</p>
	                    </div>

</div>
<div class="content-main">	

<div id = "left-list">
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
</div>
</div>
	<section>
		
		            <div class="right-bar">
		                <div class="right-bar-List">
		                    <button onclick="location.href='TablesMain.tb'">주문 내역</button>
		                </div>
		                <div class="right-bar-List">
		                    <button onclick="location.href='MyRsvList.rsv'">예약 내역</button>
		                </div>
		                <div class="right-bar-List">
		                    <button onclick="location.href='ProductList.po'">상품 관리</button>
		                </div>
		                <div class="right-bar-List">
		                    <button>매출 관리</button>
		                </div>
		                <div class="right-bar-List">
		                    <button onclick="location.href='TablesEditForm.tb'">테이블 관리</button>
		                </div>
		                <div class="right-bar-List">
		                    <button>설정</button>
		                </div>
		            </div>
		
		</section> 
</div>

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
