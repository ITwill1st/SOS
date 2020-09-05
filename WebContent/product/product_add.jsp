<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="product/style/product.css">
</head>
<body>
	<!-- 상품 등록 -->

	<section id="writeForm">
		<h2>등록</h2>
		<form action="ProductAddPro.po" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="board_name">상품명</label></td>
					<td class="td_right"><input type="text" name="item_name"
						id="item_name" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">가격</label></td>
					<td class="td_right"><input type="number" name="item_price"
						id="item_price" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">원산지</label></td>
					<td class="td_right"><input type="text" name="item_origin"
						id="item_origin" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">칼로리</label></td>
					<td class="td_right"><input type="number" name="item_calorie"
						id="item_calorie" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">메뉴소개</label></td>
					<td class="td_right"><input type="text" name="item_info"
						id="item_info" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">카테고리</label></td>
					<td class="td_right"><input type="text" name="item_category"
						id="item_category" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_name">알레르기정보</label></td>
					<td class="td_right"><input type="text" name="item_allergie"
						id="item_allergie" required="required" /></td>
				</tr>
				
				
				<tr>
					<td class="td_left"><label for="board_file">  </label></td>
					<td class="td_right"><input name="item_img" type="file"
						id="item_img" required="required" 

						/></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" />
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>