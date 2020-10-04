<%@page import="vo.PreOrderBean"%>
<%@page import="vo.MemberBean"%>
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
	
	System.out.print(menu.get(0).getItem_num());
	
	request.setCharacterEncoding("UTF-8");
	
	MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
	
	String table_num = (String)request.getParameter("tableNo");
	
	//해당테이블 preorderInfo 불러오기
	ArrayList<PreOrderBean> preorderInfo = (ArrayList<PreOrderBean>) request.getAttribute("preorderInfo");
	
	
	
	%>

	<script type="text/javascript">
	
	var addOrder;
	
	$( document ).ready(function() {

		var category = <%=category%>; 
		
		
		//preorderInfo 출력
		<%
		if(preorderInfo != null){
			for(PreOrderBean pob : preorderInfo){%>
				$('.tableOrderList').prepend("<tr class=thisLine><td><input type=text class=item_name value='<%=pob.getItem_name()%>' /></td>"
						 + "<td><input type=number class=item_price value='<%=pob.getItem_price()%>' ></td>"
						 + "<td><input type=number class=item_qty value='<%=pob.getItem_qty()%>'></td</tr>"
						 + "<input type=text class=item_num value='<%=pob.getItem_num()%>' hidden=true />");
			<%}
		}
		
		%>
		
		
		
		//전체메뉴 출력
		<%for(int i=0; i< menu.size(); i++){%>
		$('.tableProductList-MenuByCategory').append("<div class=items><button onclick='selectThis(this.value);' value='<%=menu.get(i).getItem_name() + ',' + menu.get(i).getItem_price() + ',' + menu.get(i).getItem_num()%>'><%=menu.get(i).getItem_name()%></button></div>");

			<%}%>
	
		//카테고리를 버튼화
		for(var i=0; i< category.length; i++){
	
			$('.tableProductList-Category').prepend("<button onclick='getMenu(this.value);' value="+category[i]+">" + category[i] + "</button>");
					
		}	

	});
	 
	//카테고리 클릭시 해당 카테고리에 일치하는 메뉴 출력
	function getMenu(thisCategory){
		
		$('.tableProductList-MenuByCategory').empty();
		
		<%for(int i=0; i<menu.size(); i++){%>
		if(thisCategory == '<%=menu.get(i).getItem_category()%>'){
				
				$('.tableProductList-MenuByCategory').append("<div class=items><button onclick='selectThis(this.value);' value='<%=menu.get(i).getItem_name() + ',' + menu.get(i).getItem_price()%>'><%=menu.get(i).getItem_name()%></button></div>");
				
			}
		<%}%>
	
	}
	
	//선택한 아이템 주문리스트에 넣기
	function selectThis(thisMenu){
		
		var array = thisMenu.split(",");
		
		var itemName = array[0];
		
		var itemPrice = array[1];
		
		var itemNum = array[2];
		
		console.log(itemNum);

			$('.tableOrderList').prepend("<tr class=thisLine><td><input type=text class=item_name value='"+ itemName + "'></td>"
					 + "<td><input type=number class=item_price value=" + itemPrice + "></td>"
					 + "<td><input type=number class=item_qty value=" + 1 + "></td</tr>"
					 + "<input type=text class=item_num value='" + itemNum + "' hidden=true>");


			for(var i = 0; i<$('.item_name').length - 1; i++){				
				for(var j = i+1; j<$('.item_name').length; j++){				
					if($('.item_name').eq(i).val() == $('.item_name').eq(j).val()){			
						var newQty = Number($('.item_qty').eq(i).val()) + Number($('.item_qty').eq(j).val());				
						
						$('.item_qty').eq(i).val(newQty);
						$('.item_qty').eq(j).val(0);
						
					}			
				}			
			}
			
			for(var i = 0; i<$('.item_name').length; i++){
				
				if($('.item_qty').eq(i).val() == "0"){
					
					$('.thisLine').eq(i).remove();
					
				}				
			}
			
			var totalOrder = "";
			
			for(var i = 0; i<$('.item_name').length; i++){
				
				totalOrder = totalOrder + $('.item_num').eq(i).val() + "," + $('.item_qty').eq(i).val() + "/";
				
			}
			
			console.log(totalOrder);
					
			//주문정보
			$('#totalOrder').val(totalOrder);
			
			//주문정보를 가지고 parameter 전송
			$('#btnPreorder').click(function(){
				
				preorder.submit();
				
			});
			
			
	}
</script>
	<form action="PreOrderPro.tb" method="post" id="preorder" name="preorder" >
  	 테이블 번호 :<input type="text" name="table_num" value="<%=table_num %>" readonly="readonly">
 	 회원 번호 :<input type="text" name="mem_num" value="1" readonly="readonly">
	 주문정보:<input type="text" id="totalOrder" name="totalOrder" value="" readonly="readonly">
	</form>
	
	
	<h1>table/detail.jsp</h1>
	<div class="content">
		<div class="left">
		<div style="width:600px; height:500px; overflow:auto">
			<table class="tableOrderList">
				<thead>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				


			</table>
			</div>
			<table class="tableOrderPrice">
				<tr>
					<th><input type="button" id="btnPreorder" value="주문"></th>
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
			<div class="tableProductList">
				<div class="tableProductList-Category"></div>
				
				<div class="tableProductList-MenuByCategory"></div>

			</div>
		</div>


	</div>
</body>
</html>