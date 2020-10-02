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
ArrayList<OrderDTO> orderList = (ArrayList<OrderDTO>)request.getAttribute("orderList");
%>
</head>
<body>
<h1>member/myOrderList.jsp</h1>
<h1>마이주문 리스트</h1>
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
</body>
</html>


