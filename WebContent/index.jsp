<%@page import="vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Silent Order Main</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="The Venue template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link href="plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link href="plugins/jquery-datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="plugins/jquery-timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">

<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/a372e2fae4.js" crossorigin="a372e2fae4"></script>
<%
	request.setCharacterEncoding("UTF-8");

	String mem_id = null; 
	String mem_email = null;
	String mem_name = null;
	String mem_phone = null;
	MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
// 	int mem_num = -1;
	if(session.getAttribute("mem_id") != null) mem_id = (String)session.getAttribute("mem_id");
	if(session.getAttribute("mem_email") != null) mem_email = (String)session.getAttribute("mem_email");
	if(session.getAttribute("mem_email") != null) mem_name = (String)session.getAttribute("mem_name");
	if(session.getAttribute("mem_phone") != null) mem_phone = (String)session.getAttribute("mem_phone");
// 	if(mb.getMem_num() != -1) mem_num = mb.getMem_num();
	if(session.getAttribute("mem_email") != null) session.setAttribute("mem_email", mem_email);
	// 전체 메뉴
	ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)session.getAttribute("menuList");
	ArrayList<ProductBean> category = (ArrayList<ProductBean>)session.getAttribute("category");
%>
</head>
<body>
<!-- <script type="text/javascript"> -->
<div class="super_container">
<!-- header -->
<jsp:include page="inc/top.jsp"/>
<!-- /header -->
	<!-- Home -->

	<div class="home">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_subtitle page_subtitle">Silent Order</div>
							<div class="home_title"><h1>특별한 경험을 선물합니다</h1></div>
							<div class="home_text ml-auto mr-auto">
								<p>2020년 10월 21일, 비대면 주문 시스템 도입 매장 Silent Order가 오픈합니다.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="scroll_icon"></div>
	</div>

	<!-- Intro -->

	<div class="intro">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="intro_content">
						<div class="intro_subtitle page_subtitle">Something new</div>
						<div class="intro_title"><h2>An Extraordinery Experience</h2></div>
						<div class="intro_text">
							<p>동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-4 col-md-6 intro_col">
							<div class="intro_image"><img src="images/intro_1.jpg" alt="https://unsplash.com/@quanle2819"></div>
						</div>
						<div class="col-xl-4 col-md-6 intro_col">
							<div class="intro_image"><img src="images/intro_2.jpg" alt="https://unsplash.com/@fabmag"></div>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<!-- Reservations -->

	<div class="reservations text-center" id="rsv_form">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/reservations.jpg" data-speed="0.8"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="reservations_content d-flex flex-column align-items-center justify-content-center">
						<div class="res_stars page_subtitle">5 Stars</div>
						<div class="res_title">Make a Reservation</div>
						<div class="res_form_container">
							<form action="RsvInsert.rsv" id="res_form" class="res_form" method="post">
								<div class="d-flex flex-sm-row flex-column align-items-center justify-content-start">
									<input type="hidden" name="mem_email" value="<%=mem_email%>">
									<input type="text" id="datepicker" class="res_input" name="date" required="required">
									<input type="text" class="res_input timepicker" name="time" required="required">
									<select class="res_select" name="pax">
										<option selected="selected">2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
									</select>
									<%if(mem_email == null){ %>
									<button type="submit" disabled="disabled" class="res_button">Make a Reservation</button>
									<%} else{ %>
								<button type="submit" class="res_button">Make a Reservation</button>
								<%} %>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</div>


	<!-- Signature Dish -->

	<div class="sig">
		<div class="sig_content_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-7">
						<div class="sig_content">
							<div class="sig_subtitle page_subtitle">Something new</div>
							<div class="sig_title"><h1>Our Signature Dish</h1></div>
							<div class="rating_r sig_rating rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
							<div class="sig_name_container d-flex flex-row align-items-start justify-content-start">
								<div class="sig_name">Pork Tenderloin marinated in Green Pepper</div>
								<div class="sig_price ml-auto">$20</div>
							</div>
							<div class="sig_content_list">
								<ul class="d-flex flex-row align-items-center justify-content-start">
									<li>Pork</li>
									<li>Tenderloin</li>
									<li>Green Pepper</li>
									<li>Veggies</li>
								</ul>
							</div>
							<div class="button sig_button trans_200"><a href="#">Order Now</a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="sig_image_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-7 offset-lg-5">
						<div class="sig_image">
							<div class="background_image" style="background-image:url(images/sig.jpg)"></div>
							<img src="images/sig.jpg" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- /////////////////////////////jQuery로 메뉴판 구현 필요/////////////////////////////////////////////////// -->
	<!-- The Menu -->

	<div class="themenu" id="pro_list">
		<div class="container">
			<div class="row">
				<div class="col">				
					<div class="themenu_title_bar_container">					
						<div class="themenu_stars text-center page_subtitle">5 Stars</div>					
						<div class="themenu_rating text-center">
							<div class="rating_r rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
						</div>						
						<div class="themenu_title_bar d-flex flex-column align-items-center justify-content-center">
							<div class="themenu_title">The Menu</div>
						</div>	
					</div>
				</div>
			</div>

<div class="row themenu_row">

<%for(ProductBean c : category) { %>  
	<div class="col-lg-4 themenu_column" style="float: left;">
		<div class="themenu_col">
			<div class="themenu_col_title">
			<input type="hidden"  id="category_<%=c.getItem_category() %>" value="<%=c.getItem_category() %>">
				<%=c.getItem_category() %>
			</div>
			<div class="dish_list">
			<!--메뉴정보 출력  -->
			<%for(ProductBean menu : menuList) {%>
			<%if (c.getItem_category().equals(menu.getItem_category()))  {%>
				<div class="dish">
					<div class="dish_title_container d-flex flex-xl-row flex-column align-items-start justify-content-start">
						<div class="dish_title">
							<%=menu.getItem_name() %>
						</div>
						<div class="dish_price">
							<%=menu.getItem_price() %>
						</div>
					</div>
					<div class="dish_contents">
						<ul class="d-flex flex-row align-items-start justify-content-start flex-wrap">
							<li>Pork</li>
						</ul>
					</div>
					<div class="dish_order">
						<a href="#item_<%=menu.getItem_name() %>">order Now</a>
					</div>
				</div>
			<%} }%>	
			</div>
		</div>
	</div>	
	
<%}%>    



			</div>
		</div>		
	</div>
</div>
<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

<!-- footer -->
<div id="map" style="width: auto; height: 350px; margin-left: 150px;margin-right: 150px"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0120bc990a1c1a8a6aa398f57dbda76f"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(35.1584648, 129.0619782),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		// 마커 위치
		var markerPosition  = new kakao.maps.LatLng(35.1584648, 129.0619782); 
		// 마커 생성
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 지도 상 마커 표시
		marker.setMap(map);
	</script>
<jsp:include page="inc/bottom.jsp"/>
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