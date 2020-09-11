<%@page import="vo.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 전체 메뉴 담아옴 
ProductBean menu = (ProductBean)request.getAttribute("menu");
// mem_num, table_num
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
int table_num = Integer.parseInt(request.getParameter("table_num"));
ArrayList<ReviewDTO> reviewList = (ArrayList<ReviewDTO>)request.getAttribute("reviewList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order/detail.jsp</title>
</head>
<body>
<h1>order/detail.jsp</h1>
<h1>메뉴디테일 정보</h1>

<form action="BasketPro.or" method="post" id="basket_form" name="basket_form">
<table border="1">
<tr>
	<td>메뉴 번호</td>
	<td><input type="text" value="<%=menu.getItem_num() %>" name="item_num" readonly="readonly"></td>
</tr>
<tr>
	<td>id</td>
	<td><input type="text" value="<%=mem_num %>" name="mem_num" readonly="readonly"></td>
</tr>
<tr>
	<td>메뉴 이름</td>
	<td><%=menu.getItem_name()%></td> 
</tr>
<tr>
	<td>메뉴 이미지</td>
	<td><img width="100" height="100" src="product/productUpload/<%=menu.getItem_img() %>"></td>
</tr>
<tr>
	<td>메뉴 정보</td>
	<td> <%=menu.getItem_info() %></td>
</tr>
<tr>
	<td>메뉴 가격</td>
	<td><%=menu.getItem_price() %> </td>
</tr>
<tr>
	<td>테이블 번호</td>
	<td><input type="text" value="<%=table_num %>" name="table_num" readonly="readonly"></td>
</tr>
</table>
수량 : <input type="number" min="1" id="item_qty" name="item_qty"><br>
<input type="submit" value="장바구니에 담기">
</form>
<br><br><br>
<hr>
<h2>댓글</h2>

<%
for(int i = 0 ; i < reviewList.size() ; i++){
	ReviewDTO rdt = reviewList.get(i);%>
<table border="1" style="width: 366px; height: 70px; text-align: left;">
	<tr><td><%=rdt.getMem_num()%></td><td>★ </td></tr>
	<tr><td colspan="2"><%=rdt.getReview_comment()%></td></tr>
</table>
<br>
<%
}
%>

</body>
</html>