<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
    <%
    	ArrayList<ProductBean> productList = (ArrayList<ProductBean>)request.getAttribute("productList");
 	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
    
    %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick='location.href="ProductAddForm.po"'>상품추가</button>
<table border="1" >
<tr id="tr_top">
				<td>상품이미지</td>
				<td>상품번호</td>
				<td>상품이름</td>
				<td>상품가격</td>
				<td>카테고리</td>
				<td>상품정보</td>
				<td>칼로리</td>
				<td>원산지</td>
			</tr>
			<tr>
			<% 
			for(int i = 0; i<productList.size();i++){
			%>
			
	       	<td>
<%-- 	       	<img src = "<%=productList.get(i).getItem_img() %>"> --%>
	       <a href="ProductDetail.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage%>">
						<img src = "product/productUpload/<%=productList.get(i).getItem_img()%>">
				</a></td>
			<td><%=productList.get(i).getItem_num() %></td>
			<td><%=productList.get(i).getItem_name() %></td>
			<td><%=productList.get(i).getItem_price() %></td>
	       	<td><%=productList.get(i).getItem_category() %></td>
	       	<td><%=productList.get(i).getItem_info() %></td>
	       	<td><%=productList.get(i).getItem_calorie() %></td>
	       	<td><%=productList.get(i).getItem_origin() %></td>
			<td><a href= "ProductModifyForm.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage %>">
				<input type="button" value="수정" ></a>
				<a href="ProductDeleteForm.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage %>">
				<input type="button" value="삭제" ></a></td>
			</tr>
			<%} %>
			</table>
			
			<section id="pageList">
			<%if(nowPage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href = "productList.po?page<%=nowPage-1 %>">[이전]</a>&nbsp;
			<%} %>
			
			<%for (int a = startPage;a<=endPage;a++){
				if(a==nowPage){%>
			[<%=a%>]
			<%}else{ %>	
			<a href = "productList.po?page<%=a %>">[<%=a %>]
			</a>&nbsp;
            <%} %>
            <%} %>
            <%if(nowPage>maxPage){ %>
            [다음]
			<%}else{ %>
			<a href="productList.po?page<%=nowPage+1 %>">[다음]</a>
			<%} %>
			</section>
<%-- 			<% --%>
<%-- 			else{%> --%>
<!-- 			<section id = "emptyArea"> 등록된 글이 없습니다.</section> -->
<%-- 			<%}%> --%>
			
<!-- 			</section> -->
			
</body>
</html>