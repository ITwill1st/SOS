<%@page import="vo.OrderDTO"%>
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
<link rel="stylesheet" type="text/css" href="member/style/myInfo.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>member/myInfo.jsp</title>
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
<%
ArrayList<OrderDTO> orderList = (ArrayList<OrderDTO>)request.getAttribute("orderList");
String mem_id = (String)session.getAttribute("mem_id");
String mem_email = (String)session.getAttribute("mem_email");
String mem_name = (String)session.getAttribute("mem_name");
String mem_phone = (String)session.getAttribute("mem_phone");
MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
%>
<div id="main">
<h2>주문내역</h2>
<div id="myinfo">
<%
for(int i = 0 ; i < orderList.size() ; i++){%>
	<div style="border: 1px solid; width: 25%;">
	주문 번호 : <%=orderList.get(i).getOrder_num()%> 이용날짜 : <%=orderList.get(i).getOrder_datetime()%><br>
	<b>SOS</b><br>
	<%if(orderList.get(i).getItem_qty() == 1){%>
		<%=orderList.get(i).getItem_name()%>  <%=orderList.get(i).getItem_qty()%>개 가격 <%=orderList.get(i).getTotal_price()%><br><br>
		<%
	} else {%>
	<%=orderList.get(i).getItem_name()%> 외 <%=orderList.get(i).getItem_qty()%>개 가격 <%=orderList.get(i).getTotal_price()%><br><br>
	<%
	}
%>
<%-- 	<%if(orderList.get(i).getStore_review_chk() == 0){%> --%>
<%-- 	<input type="button"value = "리뷰쓰기" onclick="location.href='ReviewWriteForm.re?order_num=<%=orderList.get(i).getOrder_num()%>'"> --%>
<%-- 	<%} else {%> --%>
<%-- 	<input type="button" disabled="disabled" value = "리뷰쓰기" onclick="location.href='ReviewWriteForm.re?order_num=<%=orderList.get(i).getOrder_num()%>'"> --%>
<%-- 	<%}%> --%>
	<input type="button" value ="상세보기" onclick="location.href='MyOrderListDetail.me?order_num=<%=orderList.get(i).getOrder_num()%>'"><input type="button" value ="뒤로가기" onclick="history.back()">
	</div><br><%
}
%>
</div>
</div>
<br>
<br>
<br>
<br><br><br><br>
	</div>
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