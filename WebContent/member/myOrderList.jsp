<%@page import="vo.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/myOrderList.jsp</title>
<%
// 가져와야 될것들
// 주문 번호, 이용 날짜, 메뉴이름 외 ?개 
ArrayList<OrderDTO> orderList = (ArrayList<OrderDTO>)request.getAttribute("orderList");
int menuCount = 0;
int totalPrice = 0;
int order_num = orderList.get(0).getOrder_num(); // 1
%>
</head>
<body>
<h1>member/myOrderList.jsp</h1>
<h1>마이주문 리스트</h1>
<%
for(int i = 0 ; i < orderList.size()  ; i++){
	%>
	<%if(order_num == orderList.get(i).getOrder_num()){		
		menuCount += orderList.get(i).getItem_qty();
		totalPrice += orderList.get(i).getTotal_price();
	} else {%>	
				<div style="border: 1px solid; width: 25%;">
				주문 번호 : <%=orderList.get(i).getOrder_num()%> 이용날짜 : <%=orderList.get(i).getOrder_datetime()%><br>
				<b>SOS</b><br>
				<%=orderList.get(i).getItem_num()%>외 <%=menuCount%>개 가격 <%=totalPrice %><br><br>
				<input type="button" value = "리뷰쓰기"><input type="button" value = "가게보기">
				</div><br>
<%		order_num = orderList.get(i).getOrder_num();
		menuCount = orderList.get(i).getItem_qty();
		totalPrice = orderList.get(i).getTotal_price();
	}
}
%>
</body>
</html>