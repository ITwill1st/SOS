<%@page import="vo.ProductInfoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//id, table_num가져오기
int mem_num = Integer.parseInt(request.getParameter("mem_num"));
int table_num = Integer.parseInt(request.getParameter("table_num"));
ArrayList<ProductInfoBean> baksetList = (ArrayList<ProductInfoBean>)request.getAttribute("basketList");

// 메뉴에 대한 정보 가져오기 위해 
// ArrayList<MenuBean> menuList = (ArrayList<MenuBean>)request.getAttribute("menuList");
// if를 써야하남? 
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
     		for(ProductInfoBean pib : baksetList){	
   		%>
         <tr>
         	<td>메뉴번호 </td>
            <td colspan="3"><%=pib.getItem_num() %></td> 
 		 </tr>
 		 <tr>
 		 	<td>메뉴 이름</td>
 		 	<td colspan="3">
 		 	어떻게 가져와야 한담? 답변)  <br>
 		 	가지고있는 item_num들을 가지고 ArrayList<productBean>에 저장합니다. <br>
 		 	이 페이지(order/main.jsp) 로딩시 action코드안에 아이템 정보를 가져오는 새로운 service를 생성하고안에<br>
 		 	dao.getItemInfo(파라미터는 ArrayList<productBean>) 메서드를 실행합니다<br>
 		 	dao의 리턴값은 ArrayList(productBean에 저장), 하나씩 꺼내서 출력합니다. 쉽죠잉?<br>
 		 	</td>
 		 </tr>
 		 <tr>
 		 	<td>메뉴 가격</td>
 		 	<td colspan="3">어떻게 가져와야 한담? <br>
 		 	답변) 위와 동일, 화이팅~ 모르는거 있으면 전화주세요. <br>
 		 	내일 점심때 일어날 예정(참고로 진짜 잘만들어놨네요. 누나 수고했어요.)<br>
 		 	</td>
 		 </tr>
 		  <tr>
 		 	<td>수량</td>
 		 	<td>
 		 	<input type="button" value="-" onclick="location.href='MinusBasketQty.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
 		 	<input type="number" min="1" value="<%=pib.getItem_qty() %>" name="item_qty" size="10">
 		 	<input type="button" value="+" onclick="location.href='PlusBasketQty.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'"> 	
 			<input type="button" value="삭제" onclick="location.href='DeleteBasket.or?item_num=<%=pib.getItem_num() %>&mem_num=<%=mem_num %>&table_num=<%=table_num %>'"> 	
 			</td>
 		 </tr>
         <%} %>
      </table>
	  <input type="button" value="주문하기" onclick="location.href='OrderBasket.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">     
</form>





</body>
</html>