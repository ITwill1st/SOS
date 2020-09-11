<%@page import="vo.ProductBean"%>
<%@page import="vo.ProductInfoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//id, table_num가져오기
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
int table_num = Integer.parseInt(request.getParameter("table_num"));
ArrayList<ProductInfoBean> basketList = (ArrayList<ProductInfoBean>)request.getAttribute("basketList");
ArrayList<ProductBean> itemInfo = (ArrayList<ProductBean>)request.getAttribute("basketInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order/basket.jsp</title>
</head>
<body>
<h1>order/basket.jsp</h1>
<h1>장바구니 정보</h1>

<form method="get">
 <table border="1">
         <tr>
            <td width="100" colspan="4">장바구니</td>
         </tr>
         <tr>
         	<td>mem_num</td>
            <td width="100"><%=mem_num %></td>
         	<td>table_num</td>
            <td width="100"><%=table_num %></td>
         </tr>         
        <%  
       
     		for(ProductInfoBean pib : basketList){		
     			for(ProductBean pb : itemInfo){
     				if(pib.getItem_num()==pb.getItem_num()){
   		%>
         <tr>
         	<td>메뉴번호 </td>
            <td colspan="3"><%=pib.getItem_num() %></td> 
 		 </tr>
 		 <tr>
 		 	<td>메뉴 이름</td> 
 		 	<td colspan="3"><%=pb.getItem_name() %></td> 
 		 </tr>
 		 <tr>
 		 	<td>메뉴 가격</td> 
 		 	<td colspan="3"><%=pb.getItem_price() %></td> 
 		 </tr>
 		  <tr>
 		 	<td>수량</td>
 		 	<td>
 		 	<input type="button" value="-" onclick="location.href='MinusBasketQty.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
 		 	<input type="number" min="1" value="<%=pib.getItem_qty() %>" name="item_qty" >
 		 	<input type="button" value="+" onclick="location.href='PlusBasketQty.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'"> 	
 			<input type="button" value="삭제" onclick="location.href='DeleteBasket.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'"> 	
 			</td>
 		 </tr>
         <%} 	}
 			} %>
      </table>
	  <input type="button" value="주문하기" onclick="location.href='PreOrder.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">     
</form>





</body>
</html>