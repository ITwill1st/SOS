<%@page import="vo.ProductBean"%>
<%@page import="vo.BasketBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<%
// 임시 mem_num
int mem_num=1;
// 임시 table_num
int table_num = (int)request.getAttribute("table_num");
// MenuListAction에서 담아온 전체 메뉴
ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)request.getAttribute("menuList");
// MenuListAction에서 담아온 장바구니 수량
int basketCount = (int)request.getAttribute("basketCount");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>order/main.jsp</h1>
<h1>메인 메뉴페이지</h1>
<p>
음식리스트 출력
음식클릭시 order/detail.jsp로 이동
</p>

<section>
<h2>메뉴 목록</h2>
    <table border="1">
         <tr>
            <td width="100" colspan="3">전체 메뉴</td>
         </tr>
        <%
     		for(ProductBean item : menuList) {
   		%>
         <tr>
         	<td>메뉴 번호</td>
            <td><%=item.getItem_num() %></td> 
            <td rowspan="4"><img width="200" height="200" src="product/productUpload/<%=item.getItem_img() %>"></td>
 		 </tr>
 		 <tr>
 		 	<td>메뉴 이름</td>
 		 	<td><a href="detail.or?item_num=<%=item.getItem_num()%>&mem_num=<%=mem_num %>&table_num=<%=table_num %>">
 		 	    <%=item.getItem_name() %></a></td>
 		 </tr>
 		 <tr>
 		 	<td>메뉴 설명</td>
 		 	<td><%=item.getItem_info() %></td>
         </tr>
         <tr>
        	 <td>메뉴 가격</td>
         	<td><%=item.getItem_price() %></td>
         </tr>
         <%} %>
      </table>
</section>

장바구니 수량 : <%=basketCount %> <br>
<input type="button" value="장바구니 리스트" onclick="location.href='BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
<input type="button" value="결제하기" onclick="location.href='Order.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
</body>
</html>