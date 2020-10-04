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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"></head>

</head>
<body>
	<!-- 상품 디테일 -->
	<section id="articleForm">
		<h2>상세보기</h2>
		<section id="basicInfoArea">
		<table class="table table-striped">
		<tr>	상품이미지 : 
			<%if(productBean.getItem_img() != null) { %>
				<img src = "product/productUpload/<%=productBean.getItem_img() %>" width=200; height =200; >
			<%}%></td></tr>
			<tr><td>상품이름 : <%=productBean.getItem_name() %></td></tr>
		<tr><td>	상품가격 : <%=productBean.getItem_price() %></td></tr>
		<tr><td>	카테고리 : <%=productBean.getItem_category() %></td></tr>
		<tr><td>	상품정보 : <%=productBean.getItem_info() %></td></tr>
		<tr><td>	칼로리 : <%=productBean.getItem_calorie() %></td></tr>
		<tr><td>	원산지 : <%=productBean.getItem_origin() %></td></tr>
			
			</table>
		</section>
		
	</section>
	<section id="commandList">
<%-- 	<%     if(mem_id.equals("admin")){ %> --%>
		<a href="ProductModifyForm.po?item_num=<%=productBean.getItem_num()%>&page=<%=nowPage %>"><input type="button" value="수정" ></a>
		<input type = "button" value = "삭제" onclick="button_event();">
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
<%-- 		<%} %> --%>
		<a href="ProductList.po?page=<%=nowPage %>"><input type="button" value="목록" ></a>
	</section>
</body>
</html>


















