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

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	
</head>
<body>

	<%
	
	request.setCharacterEncoding("UTF-8");
		//메뉴불러오기
	ArrayList<ProductBean> menu = (ArrayList<ProductBean>) request.getAttribute("menu");

	String category = (String) request.getAttribute("category");
	

// 	MemberBean mb=(MemberBean)session.getAttribute("memberInfo");
	
	String tableNo = (String) request.getParameter("tableNo");

	
	//해당테이블 preorderInfo 불러오기
	ArrayList<PreOrderBean> preorderInfo = (ArrayList<PreOrderBean>) request.getAttribute("preorderInfo");
	
	
	
	%>

	<script type="text/javascript">
		
	var addOrder;
	
	var tableNo = <%=tableNo%>;
	
	$( document ).ready(function() {
			
		var category = <%=category%>; 
		
		var totalOrder = "";
		//preorderInfo 출력
		<%
		if(preorderInfo != null){
			for(PreOrderBean pob : preorderInfo){%>
				$('.tableOrderList').prepend("<tr class=thisLine><td><input type=text class=item_name value='<%=pob.getItem_name()%>' /></td>"
						 + "<td><input type=number class=item_price value='<%=pob.getItem_price()%>' ></td>"
						 + "<td><input type=number class=item_qty value='<%=pob.getItem_qty()%>'></td</tr>"
						 + "<input type=text class=item_num value='<%=pob.getItem_num()%>' hidden=true />");
				
						totalOrder = totalOrder + '<%=pob.getItem_num()%>' + "," + '<%=pob.getItem_qty()%>' + '/';
			<%}
		}
		
		
		
		%>
		
		$('#totalOrder').val(totalOrder);
		
		//전체메뉴 출력
		<%for(int i=0; i< menu.size(); i++){%>
		$('.tableProductList-MenuByCategory').append("<div class=items><button onclick='selectThis(this.value);' value='<%=menu.get(i).getItem_name() + ',' + menu.get(i).getItem_price() + ',' + menu.get(i).getItem_num()%>'><%=menu.get(i).getItem_name()%><img alt='' src='product/productUpload/<%=menu.get(i).getItem_img()%>'><%=menu.get(i).getItem_price()%></button></div>");

			<%}%>
	
		//카테고리를 버튼화
		for(var i=0; i< category.length; i++){
	
			$('.tableProductList-Category tr').prepend("<td><button onclick='getMenu(this.value);' value="+category[i]+">" + category[i] + "</button></td>");
					
		}
		
		
 		//카테고리가 많을수록 자동적으로 넓이 조정을 해줌
		var btnCategoryWidth = (100 / category.length) + "%";
		
		$('.tableProductList-Category td').width(btnCategoryWidth);
		

	});
	 
	//카테고리 클릭시 해당 카테고리에 일치하는 메뉴 출력
	function getMenu(thisCategory){
		
		$('.tableProductList-MenuByCategory').empty();
		
		<%for(int i=0; i<menu.size(); i++){%>
		if(thisCategory == '<%=menu.get(i).getItem_category()%>'){
				
			$('.tableProductList-MenuByCategory').append("<div class=items><button onclick='selectThis(this.value);' value='<%=menu.get(i).getItem_name() + ',' + menu.get(i).getItem_price() + ',' + menu.get(i).getItem_num()%>'><%=menu.get(i).getItem_name()%><img alt='' src='product/productUpload/<%=menu.get(i).getItem_img()%>'><%=menu.get(i).getItem_price()%></button></div>");
			}else if(thisCategory == '전체메뉴'){
				$('.tableProductList-MenuByCategory').append("<div class=items><button onclick='selectThis(this.value);' value='<%=menu.get(i).getItem_name() + ',' + menu.get(i).getItem_price() + ',' + menu.get(i).getItem_num()%>'><%=menu.get(i).getItem_name()%><img alt='' src='product/productUpload/<%=menu.get(i).getItem_img()%>'><%=menu.get(i).getItem_price()%></button></div>");
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
			
			totalOrder = "";
			
			for(var i = 0; i<$('.item_name').length; i++){
				
				totalOrder = totalOrder + $('.item_num').eq(i).val() + "," + $('.item_qty').eq(i).val() + "/";
				
			}
			
			//주문정보를 가지고 parameter 전송

			$(document).on('click', '#btnPreorder', function(){
//					preorder.submit();
				console.log("실행");
				location.href="PreOrderPro.tb?tableNo=" + tableNo + "&&mem_num=1&&totalOrder=" + totalOrder;
				
			});	
	}
	

	
	
</script>

<div class="top">			

					<div class="logo_img">					
					<img alt="" src="inc/SOS logo_v2.png" height="70px">
					</div>
	
					<div class="logo">					
						<p class="logo_title">Silent Order</p>
						<p class="logo_subtitle">restaurant</p>
					</div>
					
					<div class="logo_pos">
						<p class="pos_title">POS Manager</p>
					</div>

</div>

	
	<div class="content">
		<div class="left">
		<div class="table_background">		
			<table class="tableOrder_title">
					<tr>
						<th>상 품 명</th>
						<th>가 격</th>
						<th>수 량</th>
					</tr>
			</table>					
			<table class="tableOrderList">
			</table>							
			</div>
			
			<table class="tableOrder_title">
					<tr>
						<th>합 계</th>
						<th>0</th>
						<th>0</th>
					</tr>

			</table>
			
			<table class="tableOrderBtn">
				<tr>
					<th><input type="button" id="btnCancelAll" value="전체취소"></th>
					<th><input type="button" id="btnCancelThis" value="선택취소"></th>
					<th><input type="button" id="btnCancelThis" value="선택취소"></th>
					<th><input type="button" id="btnChangeCount" value="수량변경"></th>
				</tr>					
					
				<tr>
					<th colspan="2"><input type="button" id="btnPreorder" value="주문"></th>
					<th colspan="2"><input type="button" id="btnOrder" value="결제"></th>
				</tr>

			</table>
			
		</div>

		<div class="right">
			<div class="tableProductList">
				<div class="tableProductList-Category">
				<table><tr></tr></table>
				</div>
				
				<div class="tableProductList-MenuByCategory"></div>

			</div>
		</div>


	</div>
	
	
</body>
</html>