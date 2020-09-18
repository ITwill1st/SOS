<%@page import="vo.BasketBean"%>
<%@page import="vo.ProductBean"%>
<%@page import="vo.ProductInfoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
//id, table_num가져오기
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
int table_num = Integer.parseInt(request.getParameter("table_num"));

ArrayList<BasketBean> basketList = (ArrayList<BasketBean>)request.getAttribute("basketList");
ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)request.getAttribute("menuList");
%>
<!DOCTYPE html>
<html>
<script src="js/jquery.js"></script>
<head>
<meta charset="UTF-8">
<title>order/basket.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<h1>order/basket.jsp</h1>
<h1 class="text-6xl">장바구니 정보</h1>
<form method="get">
 <table border="1" class="table-auto">
 	<thead class="thead-dark">
         <tr>
            <td width="100" colspan="4">장바구니</td>
         </tr>
    </thead>
    <tbody>
         <tr>
         	<td>mem_num</td>
            <td width="100"><%=mem_num %></td>
         	<td>table_num</td>
            <td width="100"><%=table_num %></td>
         </tr>         
        <% 
     		for(BasketBean basket : basketList){		 
     			for(ProductBean product : menuList){
     				if(basket.getItem_num()==product.getItem_num()){
   		%>
         <tr>
         	<td>메뉴번호 </td>
            <td colspan="3"><%=basket.getItem_num() %></td> 
 		 </tr>
 		 <tr>
 		 	<td>메뉴 이름</td> 
 		 	<td colspan="3"><%=product.getItem_name() %></td> 
 		 </tr>
 		 <tr>
 		 	<td>메뉴 가격</td> 
 		 	<td colspan="3"><%=product.getItem_price() %></td> 
 		 </tr>
 		  <tr>
 		 	<td>수량</td>
 		 	<td>
 		 	<input type="button" value="-" id="minusQty_<%=basket.getItem_num() %>" class="button_minus" <%if(basket.getItem_qty()==1) {%> disabled="disabled" <% }%>>
 		 	<input type="text" min="1" value="<%=basket.getItem_qty() %>" name="item_qty" readonly="readonly" id="qty_<%=basket.getItem_num() %>">
 		 	<input type="button" value="+" id="plusQty_<%=basket.getItem_num() %>" class="button_plus" > <br>
 			<input type="button" class="btn btn-danger" value="삭제" 
 			       onclick="location.href='DeleteBasket.or?item_num=<%=basket.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'"> 	
 			</td>
 		 </tr>
	<script type="text/javascript">
	// (+)버튼 클릭시 수량 증가 
	$('#plusQty_<%=basket.getItem_num() %>').click(function() {
		
		var qty= $('#qty_<%=basket.getItem_num() %>').val();
		// 변수마다 item_num을 붙여준 이유는 for문으로 계속 돌기때문에 구분이 필요함 
		$.ajax({
			url: "/SOS/AJAX_Plus.or?item_num=<%=basket.getItem_num() %>&table_num=<%=table_num %>&mem_num=<%=mem_num %>",
			type:'GET',
			success: function(data) {
				console.log("성공");
				qty++;
				$('#qty_<%=basket.getItem_num() %>').val(qty); 
				// 수량 +1 된것을 표시 
				if(qty>1){
					$('#minusQty_<%=basket.getItem_num() %>').removeAttr("disabled");
				// 수량이 1일때만 (-)버튼에 disabled="disabled" 속성을 걸어줬으므로 2이상이 되면 (-)버튼 활성화 
				}
			},
			error: function(data) {
				console.log("에러!");
			}
			
		})
		
		console.log(qty);
	});
	
	
	// (-)버튼 클릭시 수량 감소 
	$('#minusQty_<%=basket.getItem_num() %>').click(function() {
		
		var qty= $('#qty_<%=basket.getItem_num() %>').val();
		// 변수마다 item_num을 붙여준 이유는 for문으로 계속 돌기때문에 구분이 필요함 
		$.ajax({
			url: "/SOS/AJAX_Minus.or?item_num=<%=basket.getItem_num() %>&table_num=<%=table_num %>&mem_num=<%=mem_num %>",
			type:'GET',
			success: function(data) {
				console.log("성공");
				qty--;
				$('#qty_<%=basket.getItem_num() %>').val(qty);
				// 수량 -1 된것을 표시 
				if(qty==1){
					// 수량이 1일때만 (-)버튼 비활성화
					$('#minusQty_<%=basket.getItem_num() %>').attr( "disabled", "disabled" );
				}
			},
			error: function(data) {
				console.log("에러!");
			}
			
		})
		console.log(qty);
	});
	</script>
         <%} 	}
 			} %>
 			</tbody>
      </table>
	  <input type="button" class=".bg-black" value="주문하기" onclick="location.href='PreOrder.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">     
</form>



</body>
</html>