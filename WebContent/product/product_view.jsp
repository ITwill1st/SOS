<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 전달받은 request 객체에서 데이터 가져오기
	ProductBean productBean = (ProductBean)request.getAttribute("product");
	String nowPage = (String)request.getAttribute("page");
	String mem_id = (String) session.getAttribute("mem_id");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View</title>
<link rel="stylesheet" href="product/style/list.css">

</head>
<body>

    <div class="top">           

         <div class="logo_img">                  
         <img alt="" src="inc/SOS logo_v2.png" height="70px">
         </div>

         <div class="logo">                  
             <p class="logo_title">Silent Order</p>
             <p class="logo_subtitle">restaurant</p>
         </div>
         
         <div class="logo_pos">
             <p class="pos_title">PRODUCT DETAIL</p>
         </div>

    </div>


<!--------- 상품 디테일 --------->
    <div class="content">  
    
	    <div class = "view-left">
	            <%if(productBean.getItem_img() != null) { %>
	                <img src = "product/productUpload/<%=productBean.getItem_img() %>" width="700px" >
	            <%}%>
	    </div>
	    
	    <div class = "view-left">
	        <table class = "detail-talbe">
		        <tr><td class ="td-th">   상품이름 </td><td> <%=productBean.getItem_name() %></td></tr>
		        <tr><td class ="td-th">    상품가격 </td><td> <%=productBean.getItem_price() %></td></tr>
		        <tr><td class ="td-th">    카테고리 </td><td> <%=productBean.getItem_category() %></td></tr>
		        <tr><td class ="td-th">    상품정보 </td><td> <%=productBean.getItem_info() %></td></tr>
		        <tr><td class ="td-th">    칼로리 </td><td> <%=productBean.getItem_calorie() %></td></tr>
		        <tr><td class ="td-th">    원산지 </td><td> <%=productBean.getItem_origin() %></td></tr>
	            
	           <tr><td colspan="2" style="border-bottom: none; text-align: right;" >
		<%     if(mem_id.equals("admin")){ %>
				<a href="ProductModifyForm.po?item_num=<%=productBean.getItem_num()%>&page=<%=nowPage %>">
				<input type="button" value="수정" ></a>
				<input type = "button"  value = "삭제" onclick="button_event();">
							<script type="text/javascript">
								function button_event(){
									if(confirm("삭제하겠습니까?")== true){
										 location.href = "ProductDeletePro.po?item_num=<%=productBean.getItem_num()%>"
										alert("삭제되었습니다");
										location.href = "ProductList.po"
									}else{
										return;
									}
								}
							 </script>
			<%} %>
			     <a href="ProductList.po?page=<%=nowPage %>"><input type="button"  value="목록" ></a>
			     </td></tr>
			
	        </table>
	  </div>
	  
<!--   --------사이드메뉴------------ -->
	        <section>
	        
	                    <div class="right-bar">
	                        <div class="right-bar-List">
	                            <button>주문 내역</button>
	                        </div>
	                        <div class="right-bar-List">
	                            <button onclick="location.href='MyRsvList.rsv">예약 내역</button>
	                        </div>
	                        <div class="right-bar-List">
	                            <button onclick="location.href='ProductList.po'">상품 관리</button>
	                        </div>
	                        <div class="right-bar-List">
	                            <button>매출 관리</button>
	                        </div>
	                        <div class="right-bar-List">
	                            <button onclick="location.href='TablesEditForm.tb'">테이블 관리</button>
	                        </div>
	                        <div class="right-bar-List">
	                            <button>설정</button>
	                        </div>
	                    </div>
	        
	        </section>      
</div>
</body>
</html>
