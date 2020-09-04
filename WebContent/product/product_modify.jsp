

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
<title>MVC 게시판</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
		border: 1px solid darkgray;
	}
	
	textarea {
		resize: none;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
	header {
		text-align: right;
	}
</style>
</head>
<body>
	<!-- 게시판 글 수정 -->
	<section id="writeForm">
		<h2>수정</h2>
		<form action="ProductModifyPro.po" method="post" name="boardForm">
			<input type="hidden" name="item_num" value="<%=product.getItem_num() %>" />
			<input type="hidden" name="page" value="<%=nowPage%>" />
			<table>
				<tr>
					<!-- label 태그를 사용하여 해당 레이블 클릭 시 for 속성에 지정된 이름과 같은 id 속성을 갖는 텍스트필드로 커서 요청 -->
					
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
			<section id="commandCell">
				<input type="submit" value="수정" />&nbsp;&nbsp;
				<input type="button" value="뒤로" onclick="history.back()" />
			</section>
		</form>	
	</section>
</body>
</html>















