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
<script src="js/jquery.js"></script>
<script type="text/javascript">
</script>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<form action="BasketPro.or" method="post" id="basket_form" name="basket_form">
 테이블 번호 :<input type="text" name="table_num" value="<%=table_num %>" readonly="readonly">
  회원 번호 :<input type="text" name="mem_num" value="<%=mem_num %>" readonly="readonly">
<div class="max-w-sm rounded overflow-hidden shadow-lg">
  <img class="w-full" src="product/productUpload/<%=menu.getItem_img() %>" alt="Sunset in the mountains">
  <div class="px-6 py-4">
    <div class="font-bold text-xl mb-2"><%=menu.getItem_name()%></div>
    <p class="text-gray-700 text-base">
         아이템 넘버 :<input type="text" name="item_num" value="<%=menu.getItem_num() %>" readonly="readonly"> <br>
     <%=menu.getItem_name()%> | <%=menu.getItem_price() %>원 <br>
     <%=menu.getItem_info() %> <br>
          수량 : <input type="number" min="1" id="item_qty" name="item_qty">
     <input type="submit" value="장바구니에 담기" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" >
     
    </p>
  </div>
  <div class="px-6 pt-4 pb-2">
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
  </div>
</div>


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