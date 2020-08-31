<%@page import="vo.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	ProductDTO bb = (ProductDTO)request.getAttribute("bb");
%>
</head>
<body>
<div style="width: 366px; height: 200px;">
<table border="1" >
	<tr><td rowspan="3"><img alt="content" src="menuUpload/<%=bb.getItem_img()%>" width="150" height="100"></td><td style="width: 200px; height: 30px; text-align: center;"><%=bb.getItem_name()%></td></tr>
	<tr><td style="width: 200px; height: 30px; text-align: center;"><%=bb.getItem_price()%> 냥</td></tr>
	<tr><td style="width: 200px; height: 30px; text-align: center;"><%=bb.getItem_info()%></td></tr>
</table>
	<a href="#" style="float: right;">영양정보 및 원산지 확인</a>
	<div style="clear: both;"></div>
</div>

<div style="width: 366px; height: 200px;">
<h2>댓글</h2>
<hr>
<table>
<tr><td>권동현</td><td>★★★★★</td></tr>
<tr><td colspan="2"><input type="text" style="width: 358px; height: 40px;"></td></tr>
</table>
</div>
</body>
</html>