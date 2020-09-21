<!DOCTYPE html>
<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
<link rel="stylesheet" href="product/style/list.css">
<script src="js/jquery.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="product/style/product.css"> -->

</head>
<body>

	<%
       ArrayList<ProductBean> productList = (ArrayList<ProductBean>)request.getAttribute("productList");
	String category = (String) request.getAttribute("category");   
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int listCount=pageInfo.getListCount();
    int nowPage=pageInfo.getPage();
    int maxPage=pageInfo.getMaxPage();
    int startPage=pageInfo.getStartPage();
    int endPage=pageInfo.getEndPage();
    %>
    
	<script type="text/javascript">
	//카테고리

 $(document).ready(function() {
    
     
       var category = <%=category%>;
        var category2 = "";
        for(var i=0; i < category.length; i++){
            $('.cate').append("<option id=" + category[i] + " value="+category[i]+">"+category[i]+"</option>");
        }
        
        
        $("#selectByCategory").on("click", function() {
        	$('#cate22 > tbody').empty();	
        	  
        	  
//          alert($(".cate option:selected").val());
         var selectedCategory = $(".cate option:selected").val();

		     $.ajax({
		         url: 'ProductListCategory.po', // 호출할 url정보
		         method: 'get',
		         dataType: "json",
		         data: {
		        	  category:selectedCategory       
		         },
		         success: function(data) {
// 		          alert(JSON.stringify(data));
		        	    
		            $.each(data, function(index,item){
		        	 var img = "<img src='product/productUpload/"+item.item_img+"' width =150/>";
		        			        	    
		                $('#cate22 > tbody').append("<tr><td>"+img+"</td><td>"+item.item_num+"</td><td>"+item.item_name+"</td><td>"+item.item_price+"</td><td>"+item.item_category+
                          "</td><td>"+item.item_info+"</td><td>"+item.item_calorie
                         +"</td><td>"+item.item_origin+"</td></tr>");
		                
		          
		          });
		            		            
		         }
		     });
        });
         
 });

	
	
	</script>

	
	<button onclick='location.href="ProductAddForm.po"'>상품추가</button>
	
	<select class="cate">
	

	</select>

    <button id="selectByCategory">조회</button>

	
<script type="text/javascript">
</script>
<!-- 	<div id="category11"> -->
		<table border="1" id = "cate22">
		<thead>
			<tr>
				<th>상품이미지</th>
				<th>상품번호</th>
				<th>상품이름</th>
				<th>상품가격</th>
				<th>카테고리</th>
				<th>상품정보</th>
				<th>칼로리</th>
				<th>원산지</th>
				<th>수정/삭제</th>
			</tr>
		</thead>
		<tbody>
		 
               <%
                    for (int i = 0; i < productList.size(); i++) { 
               %> 
               <tr>
               <td style="table-layout: fixed;">
                   상품 이미지 <a
                   href="ProductDetail.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage%>">
                       <img width="200px" height="100px"
                       src="product/productUpload/<%=productList.get(i).getItem_img()%>">
               </a>
               </td>
               
               <td><%=productList.get(i).getItem_num()%></td>
               <td><%=productList.get(i).getItem_name()%></td>
               <td><%=productList.get(i).getItem_price()%></td>
               <td><%=productList.get(i).getItem_category()%></td>
               <td><%=productList.get(i).getItem_info()%></td>
               <td><%=productList.get(i).getItem_calorie()%></td>
               <td><%=productList.get(i).getItem_origin()%></td>
               <td><a
                   href="ProductModifyForm.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage%>">
                       <input type="button" value="수정">
               </a> <a
                   href="ProductDeleteForm.po?item_num=<%=productList.get(i).getItem_num()%>&page=<%=nowPage%>">
                       <input type="button" value="삭제">
               </a></td>
            </tr>
            
            <%
             }
            %>

		</tbody>
			

		</table>
<!-- 	</div> -->
</body>
</html>