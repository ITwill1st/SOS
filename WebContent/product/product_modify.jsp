

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
<title>Modify</title>
<!-- <link rel="stylesheet" href="product/style/product.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head>
<body>
	<!-- 상품 수정 -->
	<section id="writeForm">
		<h2>상품 수정</h2>
		<form action="ProductModifyPro.po" method="post" name="boardForm">
			<input type="hidden" name="item_num" value="<%=product.getItem_num() %>" />
			<input type="hidden" name="page" value="<%=nowPage%>" />
			
			<table class="table table-striped">
				<tr>
					
					
				<tr>
					<td class="td_left"><label for="item_img">상품이미지</label></td>
					<td class="td_right">
					<img src="product/productUpload/<%=product.getItem_img()%>"> <br>
					<input name="item_img" type="file" value = "<%=product.getItem_img() %>"></td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="item_num">상품번호</label></td>
					<td class="td_right">
						<input type="text" name="item_num" id="item_num" value="<%=product.getItem_num()%>" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_name">상품이름</label></td>
					<td class="td_right">
						<input type="text" name="item_name" id="item_name" value="<%=product.getItem_name() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_price">상품가격</label></td>
					<td class="td_right">
						<input type="text" name="item_price" id="item_price" value="<%=product.getItem_price() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_origin">원산지</label></td>
					<td class="td_right">
						<input type="text" name="item_origin" id="item_origin" value="<%=product.getItem_origin()%>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_calorie">칼로리</label></td>
					<td class="td_right">
						<input type="text" name="item_calorie" id="item_calorie" value="<%=product.getItem_calorie() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_info">상품정보</label></td>
					<td class="td_right">
						<input type="text" name="item_info" id="item_info" value="<%=product.getItem_info() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="item_category">카테고리</label></td>
					<td class="td_right">
						<input type="text" name="item_category" id="item_category" value="<%=product.getItem_category() %>" required="required" />
					</td>
				</tr>
				
				
				
			</table>
			<section id="commandCell" style = "text-align: center;">
				<input type="submit" value="수정" />&nbsp;&nbsp;
				<input type="button" value="뒤로" onclick="history.back()" />
			</section>
		</form>	
	</section>
</body>
</html>















