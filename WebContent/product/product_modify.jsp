

<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// BoardBean 객체(article) 가져오기
	ProductBean product = (ProductBean)request.getAttribute("product");
	//page 파라미터 값 가져오기(page 식별자 지정 불가) => page 디렉티브 때문에 JSP의 예약어로 취급됨
	String nowPage = request.getParameter("page"); 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="product/style/list.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head> -->
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
                        <p class="pos_title">PRODUCT MODIFY</p>
                    </div>

    </div>

	<!-- 상품 수정 -->
<div class="content">  
	
		<form action="ProductModifyPro.po" method="post" name="boardForm">
			<input type="hidden" name="item_num" value="<%=product.getItem_num() %>" />
			<input type="hidden" name="page" value="<%=nowPage%>" />
			
		<div class = view-left>
			<img src="product/productUpload/<%=product.getItem_img()%>"> <br>
            <input name="item_img" type="file" value = "<%=product.getItem_img() %>">
			
		</div>		
		<div class = "view-left">
			<table class="detail-talbe">
					
				<tr>
					<td>상품이름:
						<input type="text" name="item_name"  value="<%=product.getItem_name() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td>상품가격:
						<input type="text" name="item_price"  value="<%=product.getItem_price() %>" required="required" />
					</td>
				</tr>
				<tr>
                    <td>카테고리:
                        <input type="text" name="item_category"value="<%=product.getItem_category() %>" required="required" />
                    </td>
                </tr>
                <tr>
                    <td>상품정보:
                        <input type="text" name="item_info" value="<%=product.getItem_info() %>" required="required" />
                    </td>
                </tr>
                <tr>
                    <td>칼로리:
                        <input type="text" name="item_calorie" value="<%=product.getItem_calorie() %>" required="required" />
                    </td>
                </tr>
				<tr>
					<td>원산지:
						<input type="text" name="item_origin" value="<%=product.getItem_origin()%>" required="required" />
					</td>
				</tr>
				
				
				
				
				<tr><td>
				<input type="submit"  value="수정" />&nbsp;&nbsp;
				<input type="button"  value="뒤로" onclick="history.back()" /> 
				</td></tr>
				
			
			</table>
		
			
	       	</form>	
	    </div>
	
	<!--   --------사이드메뉴------------ -->
	   
        
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
        
	
</body>
</html>















