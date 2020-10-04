<%@page import="vo.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
ArrayList<OrderDTO> orderList = (ArrayList<OrderDTO>)request.getAttribute("orderList");
int total_price = 0;
%>
</head>
<body>
<h1>주문 내역</h1>
<div>
<h2>SOS</h2>
<pre>
주문 번호 : <%=orderList.get(0).getOrder_num() %>
주문 일시 : <%=orderList.get(0).getOrder_datetime()%>
</pre>
<hr>
</div>
<div>
<pre>
<%
for(int i = 0 ; i < orderList.size() ; i++){
 total_price += orderList.get(i).getTotal_price();%>
	
<%=orderList.get(i).getItem_name() %>  <%=orderList.get(i).getItem_qty() %>개        <%=orderList.get(i).getTotal_price() %>원
<%
}%>
</pre>
<hr>
총 주문금액 : <%=total_price %>원
</div>
</body>
</html>