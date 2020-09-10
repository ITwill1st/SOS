<%@page import="java.util.Iterator"%>
<%@page import="vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<title>table/detail.jsp</title>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script>
</head>
<body>
	<%
		//메뉴불러오기
	ArrayList<ProductBean> menu = (ArrayList<ProductBean>) request.getAttribute("menu");

	String category = (String) request.getAttribute("category");
	%>

	<script type="text/javascript">
	$( document ).ready(function() {
//카테고리 배열
var category = <%=category%>;

for(var i=0; i< category.length; i++){
	
	$('.tableProductListTR').prepend("<td><button>"+category[i]+"</button></td>");
	$('.tableProductList').append("<tr id="+category[i]+"></tr>")
	
	<%for(ProductBean pb : menu){%>
	if(category[i] == '<%=pb.getItem_category()%>'){
			$('#<%=pb.getItem_category()%>').append('<td><%=pb.getItem_name()%></td>');
		}
	<%}%>
	
	
}
	});
</script>

	<h1>table/detail.jsp</h1>
	<h1><%=request.getAttribute("tableNo")%></h1>
	<div class="content">
		<div class="left">
			<table class="tableOrderList">
				<thead>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="3"></td>
					</tr>
				</tbody>
			</table>
			<table class="tableOrderPrice">
				<tr>
					<th>1</th>
					<th>2</th>
				</tr>
				<tr>
					<th>3</th>
					<th>4</th>
				</tr>
				<tr>
					<th>5</th>
					<th>6</th>
				</tr>
			</table>
		</div>

		<div class="right">
			<table class="tableProductList">
				<tr class="tableProductListTR"></tr>
				

			</table>
		</div>


	</div>
</body>
</html>