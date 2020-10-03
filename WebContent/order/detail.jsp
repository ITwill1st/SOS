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

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="styles/responsive.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order/detail.jsp</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">

//수량 0이하 입력불가능하도록제어 
function checkNumber(event) {
	//수량 input폼에 onkeypress 이벤트가 발생하면 checkNumber 함수를 호출하여 결과를 리턴
	  if(event.key >= 1 && event.key <= 9) {
		  // 1~9까지의 숫자만 입력 가능하도록 제어 
	    return true;
	  }
	  return false;
	}
</script>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="shopping-detail">
<form action="BasketPro.or" method="post" id="basket_form" name="basket_form">
<input type="hidden" name="table_num" value="<%=table_num %>" readonly="readonly">
<input type="hidden" name="mem_num" value="<%=mem_num %>" readonly="readonly">
<div class="max-w-sm rounded overflow-hidden shadow-lg">
  <img class="w-full" src="product/productUpload/<%=menu.getItem_img() %>" alt="Sunset in the mountains">
  <div class="px-6 py-4">
    <div class="font-bold text-xl mb-2"><%=menu.getItem_name()%></div>
    <p class="text-gray-700 text-base">
    <input type="hidden" name="item_num" value="<%=menu.getItem_num() %>" readonly="readonly">
     <%=menu.getItem_info() %> <br>
     <%=menu.getItem_price() %>원 <br>
     <input type="number" min="1" id="item_qty" name="item_qty" onkeypress='return checkNumber(event)'>
     <input type="submit" value="담기" class="btn btn-warning btn-xs">
    </p>
  </div>
</div>


</form>
</div>
<br><br><br>
<hr>
<h2>댓글</h2>
<%
for(int i = 0 ; i < reviewList.size() ; i++){%>
<script type="text/javascript">
$(document).ready(function() {
	var star = "";
		for(var x = 0 ; x < <%=reviewList.get(i).getReview_rating()%> ; x++) {
			star += "★";
		}
	$("#starRating_<%=reviewList.get(i).getReview_comment_num()%>").html(star);
});
</script>

<%
	ReviewDTO rdt = reviewList.get(i);%>
<table border="1" style="width: 366px; height: 70px; text-align: left;">
	<tr><td><%=rdt.getMem_num()%></td><td><div id="starRating_<%=reviewList.get(i).getReview_comment_num()%>"></div></td></tr>
	<tr><td colspan="2"><%=rdt.getReview_comment()%></td></tr>
</table>
<br>
<%
}
%>

</body>
</html>