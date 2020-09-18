<%@page import="vo.ProductBean"%>
<%@page import="vo.BasketBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<%
// 임시 mem_num
int mem_num=1;
// 임시 table_num
int table_num = (int)request.getAttribute("table_num");

// 전체 메뉴
ArrayList<ProductBean> menuList = (ArrayList<ProductBean>)request.getAttribute("menuList");
// 장바구니 수량
int basketCount = (int)request.getAttribute("basketCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>


<nav class="flex items-center justify-between flex-wrap bg-green-900 p-6">
  <div class="flex items-center flex-shrink-0 text-white mr-6">
    <svg class="fill-current h-8 w-8 mr-2" width="54" height="54" viewBox="0 0 54 54" xmlns="http://www.w3.org/2000/svg"><path d="M13.5 22.1c1.8-7.2 6.3-10.8 13.5-10.8 10.8 0 12.15 8.1 17.55 9.45 3.6.9 6.75-.45 9.45-4.05-1.8 7.2-6.3 10.8-13.5 10.8-10.8 0-12.15-8.1-17.55-9.45-3.6-.9-6.75.45-9.45 4.05zM0 38.3c1.8-7.2 6.3-10.8 13.5-10.8 10.8 0 12.15 8.1 17.55 9.45 3.6.9 6.75-.45 9.45-4.05-1.8 7.2-6.3 10.8-13.5 10.8-10.8 0-12.15-8.1-17.55-9.45-3.6-.9-6.75.45-9.45 4.05z"/></svg>
    <span class="font-semibold text-xl tracking-tight">SOS Order System</span>
  </div>
  <div class="block lg:hidden">
    <button class="flex items-center px-3 py-2 border rounded text-teal-200 border-teal-400 hover:text-white hover:border-white">
      <svg class="fill-current h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Menu</title><path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/></svg>
    </button>
  </div>
  <div class="w-full block flex-grow lg:flex lg:items-center lg:w-auto">
    <div class="text-sm lg:flex-grow">
      <a href="#responsive-header" class="block mt-4 lg:inline-block lg:mt-0 text-teal-200 hover:text-white mr-4">
        내정보 
      </a>
      <a href="#responsive-header" class="block mt-4 lg:inline-block lg:mt-0 text-teal-200 hover:text-white mr-4">
       몰라용 
      </a>
      <a href="BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>" class="block mt-4 lg:inline-block lg:mt-0 text-teal-200 hover:text-white">
       나도몰라  
      </a>
    </div>
     <div>
      <a href="BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>" class="inline-block text-sm px-4 py-2 leading-none border rounded text-white border-white hover:border-transparent hover:text-teal-500 hover:bg-white mt-4 lg:mt-0">장바구니 리스트 (<%=basketCount %>)</a>
    </div>
      <div>
      <a href="Order.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>" class="inline-block bg-yellow-800 text-sm px-4 py-2 leading-none border rounded text-white hover:border-transparent hover:text-teal-500 hover:bg-white mt-4 lg:mt-0">결제하기</a>
    </div>
  </div>
</nav>

<%
	for(ProductBean item : menuList) {
%>
<div class="max-w-sm rounded overflow-hidden shadow-lg float-left">
  <img class="w-full" src="product/productUpload/<%=item.getItem_img() %>" alt="Sunset in the mountains">
  <div class="px-6 py-4">
    <div class="font-bold text-xl mb-2"><a href="detail.or?item_num=<%=item.getItem_num()%>&mem_num=<%=mem_num %>&table_num=<%=table_num %>"> 
  		 	    <%=item.getItem_name() %></a></div>
    <p class="text-gray-700 text-base">
      <%=item.getItem_num() %> | <%=item.getItem_price() %>원 | <br>
      <%=item.getItem_info() %> 
    </p>
  </div>
  <div class="px-6 pt-4 pb-2">
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
  </div>
</div>
<%} %>
장바구니 수량 : <%=basketCount %> <br>
<input type="button" value="장바구니 리스트" onclick="location.href='BasketList.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
<input type="button" value="결제하기" onclick="location.href='Order.or?mem_num=<%=mem_num %>&table_num=<%=table_num %>'">
</body>
</html>