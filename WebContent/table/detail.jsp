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
<script src="#"></script>
</head>
<body>
<%
//메뉴불러오기
ArrayList<ProductBean> menu = (ArrayList<ProductBean>)request.getAttribute("menu"); 

%>
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
				<tr>
					<th>메뉴</th>
				</tr>
				<%for(ProductBean pb : menu){%>
					<tr><td><%=pb.getItem_category() %>
					<br><%=pb.getItem_name() %>
					<br><%=pb.getItem_price() %></td></tr>
				<%} %>
				
			</table>
		</div>


	</div>
</body>
</html>