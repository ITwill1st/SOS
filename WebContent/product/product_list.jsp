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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>

	<%
       ArrayList<ProductBean> productList = (ArrayList<ProductBean>)request.getAttribute("productList");
       ArrayList<ProductBean> category = (ArrayList<ProductBean>)request.getAttribute("category");

    %>
<!-- ----------카테고리---------- -->  
<%
// 현재 카테고리 뿌려주기 
for(ProductBean cate : category){
    %><ul><li id="<%=cate.getItem_category() %>"><%=cate.getItem_category() %></li>
    
    
    <script type="text/javascript">
    // category 클릭 시 해당 메뉴만 출력하기 
    $('#<%=cate.getItem_category() %>').click(function() {
        
        $('#table').empty();  
        var category = $('#<%=cate.getItem_category() %>').val();

        $.ajax({
            url: "CategoryShow.po?item_category=<%=cate.getItem_category() %>",
            type:'GET',
            dataType: "json",
            success: function(data) {
                console.log("성공");
                console.log(data);
            
                $.each(data, function(index,item){
                
                    var img = "<img src='product/productUpload/"+item.item_img+"' width =150/>";
               
                
                      $('#table ').append("<tr><td>"+img+"</td><td>"+item.item_num+"</td><td>"+item.item_name+"</td><td>"+item.item_price+"</td><td>"+item.item_category+
                              "</td><td>"+item.item_info+"</td><td>"+item.item_calorie
                             +"</td><td>"+item.item_origin+"</td><td style = 'text-align: center;'><a href='ProductModifyForm.po?item_num="
                                 +item.item_num+"&page='><input type='button' value='수정'>"
                                 +"</a><input type = 'button' value = '삭제' onclick='button_event();'>"
                                 +"<script type='text/javascript'>function button_event(){"
                                 +"if(confirm('삭제하겠습니까?')== true){location.href = 'ProductDeletePro.po?item_num="
                                 +item.item_num+"&page='alert('삭제되었습니다');"
                                 +"}else{return;}}</td></tr>");
                            
                      });
                
            
                
            },
            error: function(data) {
                console.log("에러!");
            }
            
        
            
        })
        
        
    });
    
    
    </script>
    
    </ul><%
}
%>

<!-- ----------상품 삭제---------- -->
		
		
<!-- ----------상품 리스트---------- -->
	
	<h2>상품 리스트</h2>
				
<!-- ----------카테고리---------- -->

	
	
	

	<button id = "btnAdd" onclick='location.href="ProductAddForm.po"'>상품추가</button>
<!-- 			<div id ="table">  -->
			<table border="1px" id = "table">
			
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



				<%
			    for(ProductBean item : productList) {
			%>

				  <tr>
				  <td><a  href="ProductDetail.po?item_num=<%=item.getItem_num()%>">
				  <img src="${pageContext.request.contextPath}/product/productUpload/<%=item.getItem_img()%>" width="200px" height="100px"></a></td>
				  <td> <%=item.getItem_num() %> </td>
				  <td><%=item.getItem_name() %></td>
				  <td> <%=item.getItem_price() %> </td>
				  <td> <%=item.getItem_category() %> </td>
				  <td><%=item.getItem_info() %> </td>
				  <td><%=item.getItem_calorie() %> </td>
				  <td><%=item.getItem_origin() %> </td>
				  <td style = "text-align: center;"><a
                      href="ProductModifyForm.po?item_num=<%=item.getItem_num()%>">
                          <input type="button" value="수정"></a> 
                  <input type = "button" value = "삭제" onclick="button_event();">
                  <script type="text/javascript">
                     function button_event(){
                        if(confirm("삭제하겠습니까?")== true){
                            location.href = "ProductDeletePro.po?item_num=<%=item.getItem_num()%>"
                           alert("삭제되었습니다");
                        }else{
                           return;
                        }
                     }
      </script>
                  </td>
				<%} %>
			</table>	
<!-- 			 </div>	   	 -->
		

</body>
</html>