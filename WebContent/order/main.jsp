<%@page import="vo.ProductBean"%>
<%@page import="vo.BasketBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<%
int mem_num= 1;
// 임시 table_num
int table_num = (int)request.getAttribute("table_num");

// 전체 메뉴
ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)request.getAttribute("menuList");
// 장바구니 수량
int basketCount = (int)request.getAttribute("basketCount");
// 카테고리
ArrayList<ProductBean> category = (ArrayList<ProductBean>)request.getAttribute("category");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="order/style/order.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="https://kit.fontawesome.com/a372e2fae4.js" crossorigin="a372e2fae4"></script>
</head>
<body>
<!--상단바 -->
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="themenu_title_bar_container">
						<div class="themenu_stars text-center page_subtitle">Silent Order</div>
						<div class="themenu_rating text-center">
							<div class="rating_r rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
						</div>
						<div class="themenu_title_bar d-flex flex-column align-items-center justify-content-center">
							<div class="themenu_title">The Menu</div>
						</div>
					</div>
				</div>
			
			</div>
 
    
<!-- 고정된 장바구니/결제  -->
 
   <aside class="as-shopping-basket">
	    <div class="shopping-basket">
	     <div class="shopping-basket-inner">
	 		<div class="user-card clearfix">
	      		<div class="user-photo">
	       			<a href="BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>"><i class="fas fa-shopping-basket fa-2x" style="color:#8c7c74;"></i></a>
	        		<span class="user-status" aria-label="Active"><%=basketCount %></span>
	     		</div>
	    	</div>
	    	<div class="user-card clearfix">
    			<div class="user-photo">
        			<a href="Order.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>"><i class="fab fa-cc-visa fa-2x" style="color:#8c7c74;" ></i></a>
     			</div>
    		</div>
    		</div>
		</div>
    </aside>
    
<!--전체 메뉴 출력  -->

<%for(ProductBean item : menuList) {
%>
<div class="sig">
		<div class="sig_content_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-7">
						<div class="sig_content">
							<div class="sig_subtitle page_subtitle"><%=item.getItem_category() %></div>
							<div class="sig_title"><h1><a href="detail.or?item_num=<%=item.getItem_num()%>&mem_num=<%=mem_num %>&table_num=<%=table_num %>"> 
  		 	    			<%=item.getItem_name() %></a></h1></div>
							<div class="rating_r sig_rating rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
							<div class="sig_name_container d-flex flex-row align-items-start justify-content-start">
								<div class="sig_name"><%=item.getItem_info() %> </div>
								<div class="sig_price ml-auto"><%=item.getItem_price() %>원</div>
							</div>
							<div class="sig_content_list">
								<ul class="d-flex flex-row align-items-center justify-content-start">
									<li>Pork</li>
									<li>Tenderloin</li>
									<li>Green Pepper</li>
									<li>Veggies</li>
								</ul>
							</div>
							<div class="button sig_button trans_200"><a href="detail.or?item_num=<%=item.getItem_num()%>&mem_num=<%=mem_num %>&table_num=<%=table_num %>">Order Now</a></div>
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
							<div class="background_image" style="background-image:url(product/productUpload/<%=item.getItem_img() %>)"></div>
							<img src="product/productUpload/<%=item.getItem_img() %>" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>

<%}  %>

<!-- footer -->
<jsp:include page="../inc/bottom.jsp"/>
</body>
</html>