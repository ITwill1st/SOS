<%@page import="vo.OrderDTO"%>
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
ArrayList<OrderDTO> orderList = (ArrayList<OrderDTO>)request.getAttribute("orderList");
int order_num = orderList.get(0).getOrder_num();
int total_price = 0;
%>
</head>
<body>
<h1>주문내역</h1>
<div>
<pre>
<b>주문번호 : <%=order_num%></b>
<%for(int i = 0 ; i < orderList.size() ; i++){
	total_price += orderList.get(i).getTotal_price();
%>
<%=orderList.get(i).getItem_name()%>  <%=orderList.get(i).getItem_qty()%>개  <%=orderList.get(i).getItem_price() %>원
<%
	}
%>
</pre> 
<h4>Total : <%=total_price%>원</h4>
</div>
<h3>가게 리뷰 하기</h3>
<form action="StoreReviewWritePro.re">
<input type="hidden" value="<%=order_num %>" name="order_num" >
<input type="hidden" value ="<%=orderList.get(0).getMem_num()%>" name= "mem_num">
<table border="1" id="ptable" style="width: 590px;"> 
<tr>
<td colspan="3"><input type="radio" name="review_rating" value="1">★
				<input type="radio" name="review_rating" value="2">★★
				<input type="radio" name="review_rating" value="3">★★★
				<input type="radio" name="review_rating" value="4">★★★★
				<input type="radio" name="review_rating" value="5">★★★★★</td></tr>
<tr><td><textarea cols="80" rows="7" name="review_comment"></textarea></td></tr>
</table>
<input type="submit" value="리뷰쓰기">
</form>

</body>
</html>