<%@page import="vo.ProductBean"%>
<%@page import="vo.BasketBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<%
// 임시 mem_num
int mem_num= (int)session.getAttribute("mem_num");
// 임시 table_num
int table_num = (int)session.getAttribute("table_num");

// 전체 메뉴
ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)session.getAttribute("menuList");
// 장바구니 수량
int basketCount = (int)session.getAttribute("basketCount");
// 카테고리
ArrayList<ProductBean> category = (ArrayList<ProductBean>)session.getAttribute("category");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/SOS/styles/bootstrap-4.1.2/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="/SOS/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SOS/order/style/order.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/responsive.css">


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
			
<%-- <h1>테이블 No  : <%=table_num %></h1> --%>
<%-- <h1>멤버 No  : <%=mem_num %></h1> --%>
 
<!-- 고정된 장바구니/결제  -->
 
   <aside class="as-shopping-basket">
	    <div class="shopping-basket">
	     <div class="shopping-basket-inner">
	 		<div class="user-card clearfix">
	      		<div class="user-photo">
	       			<a href="/SOS/BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>"><i class="fas fa-shopping-basket fa-2x" style="color:#8c7c74;"></i></a>
	        		<span class="user-status" aria-label="Active"><%=basketCount %></span>
	     		</div>
	    	</div>
	    	<div class="user-card clearfix">
    			<div class="user-photo">
    			<!-- 결제 창  -->
        			<a href="/SOS/Order.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>"><i class="fab fa-cc-visa fa-2x" style="color:#8c7c74;" ></i></a>
     			</div>
    		</div>
    		</div>
		</div>
    </aside>
    
    <br>
    <br>
    <br>
    
    
<!-- 카테고리 및 아이템 출력  -->
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


<div>

</div>
<!-- 하단에 전체 메뉴 출력  -->

<%for(ProductBean item : menuList) {
%>
	<div class="sig" style="clear: both;" id="item_<%=item.getItem_name() %>">
		<div class="sig_content_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-7">
						<div class="sig_content">
							<div class="sig_subtitle page_subtitle"><%=item.getItem_category() %></div>
							<div class="sig_title"><h1><%=item.getItem_name() %></h1></div>
							<div class="rating_r sig_rating rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
							<div class="sig_name_container d-flex flex-row align-items-start justify-content-start">
								<div class="sig_name"><%=item.getItem_info() %></div>
								<div class="sig_price ml-auto"><%=item.getItem_price() %></div>
							</div>
							<div class="sig_content_list">
								<ul class="d-flex flex-row align-items-center justify-content-start">
								</ul>
							</div>
							<div style="display: inline;">
							<div class="button sig_button trans_200"><a href="/SOS/detail.or?item_num=<%=item.getItem_num()%>&mem_num=<%=mem_num %>&table_num=<%=table_num %>">Order Now</a></div>
							<div class="button sig_button trans_200"><a href="#">전체메뉴</a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="sig_image_container">
			<div class="container_test">
				<div class="row">
					<div class="col-lg-7 offset-lg-5">
						<div class="sig_image">
							<div class="background_image" style="background-image:url(/SOS/product/productUpload/<%=item.getItem_img() %>)"></div>
							<img src="/SOS/product/productUpload/<%=item.getItem_img() %>" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<%}  %>

<!-- footer -->
<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>