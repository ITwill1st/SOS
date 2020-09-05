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
					<th>카테고리</th>
				</tr>
			</table>
		</div>


	</div>
</body>
</html>