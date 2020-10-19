<%@page import="vo.ProductBean"%>
<%@page import="java.awt.print.Printable"%>
<%@page import="vo.ProductInfoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="review/style/review2.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/SOS/styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/responsive.css">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/a372e2fae4.js" crossorigin="a372e2fae4"></script>
<%
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
ArrayList<ProductBean> product_list = (ArrayList<ProductBean>) request.getAttribute("product_list");
%>
</head>
<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="themenu_title_bar_container">
						<div class="themenu_stars text-center page_subtitle">Silent Order</div>
						<div class="themenu_rating text-center">
							<div class="rating_r rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
						</div>
						<div class="themenu_title_bar d-flex flex-column align-items-center justify-content-center">
							<div class="themenu_title">Review</div>
						</div>
					</div>
				</div>
			
			</div>
		</div>
<div id="reviewMain">
<%
if (product_list.size() != 0){
for(int i = 0 ; i < product_list.size() ; i++){
	ProductBean productBean = product_list.get(i);

%>
<form action="ReviewWritePro.re" method="post"> 
<input type="hidden" value="<%=mem_num%>" name="mem_num">
<input type="hidden" value="<%=productBean.getItem_num()%>" name="item_num_<%=i%>">
<input type="hidden" value="<%=product_list.size()%>" name="review_count">
<div  id="formMain">
<img src="product/productUpload/<%=productBean.getItem_img()%>" id="productImg">
<table border="1" id="ptable">
<tr><td><input type="radio" name="review_rating_<%=i%>" value="1">★
		<input type="radio" name="review_rating_<%=i%>" value="2">★★
		<input type="radio" name="review_rating_<%=i%>" value="3">★★★
		<input type="radio" name="review_rating_<%=i%>" value="4">★★★★
		<input type="radio" name="review_rating_<%=i%>" value="5">★★★★★</td></tr>
<tr><td><textarea id="comment" cols="80" rows="7" name="review_comment_<%=i%>" ></textarea></td></tr>
</table>
</div>
<%}
} else {%>
		<h2>등록할 리뷰가 없습니다.</h2>
		<%
	}
%>
<div id="buttonGroup">
<input type="submit" value="리뷰등록" id="button"><input type="button" value="돌아가기" onclick="location.href='http://localhost:8080/SOS/StartOrder.or'" id="button">
</div>
</form>
</div>
<div style="clear: both;"></div>
<jsp:include page="../inc/bottom.jsp"/>
</body>
</html>