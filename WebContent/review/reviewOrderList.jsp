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
<link rel="stylesheet" href="review/style/review.css">
<%
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
ArrayList<ProductBean> product_list = (ArrayList<ProductBean>) request.getAttribute("product_list");
%>
</head>
<body>
<div>
<%
for(int i = 0 ; i < product_list.size() ; i++){
	ProductBean productBean = product_list.get(i);
%>
<form action="ReviewWritePro.re" method="post">
<input type="hidden" value="<%=mem_num%>" name="mem_num">
<input type="hidden" value="<%=productBean.getItem_num()%>" name="item_num_<%=i%>">
<input type="hidden" value="<%=product_list.size()%>" name="review_count">
<table border="1" id="ptable">
<tr><td rowspan="2" class="tdimg"><img src="product/productUpload/<%=productBean.getItem_img()%>" width="200px" height="100px"></td>
<td><input type="radio" name="review_rating_<%=i%>" value="1">★
		<input type="radio" name="review_rating_<%=i%>" value="2">★★
		<input type="radio" name="review_rating_<%=i%>" value="3">★★★
		<input type="radio" name="review_rating_<%=i%>" value="4">★★★★
		<input type="radio" name="review_rating_<%=i%>" value="5">★★★★★</td></tr>
<tr><td><textarea cols="80" rows="7" name="review_comment_<%=i%>"></textarea></td></tr>
</table>
<%
}%>
<input type="submit" value="리뷰등록">
</form>
</div>
</body>
</html>