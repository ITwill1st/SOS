<%@page import="vo.ProductBean"%>
<%@page import="vo.PreOrderBean"%>
<%@page import="vo.TableDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<title>table/main.jsp</title>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script>
<script src="table/js/main.js"></script>
</head>
<body>

<script>


var pst = [
<%
ArrayList<TableDTO> list = (ArrayList<TableDTO>)request.getAttribute("tableInfo");

for(int i=0;i<list.size();i++){
	%>
	[<%=list.get(i).getTable_x()%>,<%=list.get(i).getTable_y()%>,<%=list.get(i).getTable_w()%>,<%=list.get(i).getTable_h()%>]	
	<%if(i != (list.size() -1) ){%>,<%}
}
%>
];

var preorderInfo = [
<%
ArrayList<PreOrderBean> list2 = (ArrayList<PreOrderBean>)request.getAttribute("preorderInfo");
for(int i=0;i<list2.size();i++){
	%>
	[<%=list2.get(i).getTable_num()%>,<%=list2.get(i).getMem_num()%>,"<%=list2.get(i).getItem_name()%>",<%=list2.get(i).getItem_qty()%>]	
	<%if(i != (list2.size() -1) ){%>,<%}
}
%>
];
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

<section>
<canvas id="canvas" width="1400" height="720">
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>

			<div class="right-bar">
				<div class="right-bar-List">
					<button>주문 내역</button>
				</div>
				<div class="right-bar-List">
					<button onclick="location.href='MyRsvList.rsv">예약 내역</button>
				</div>
				<div class="right-bar-List">
					<button onclick="location.href='ProductList.po'">상품 관리</button>
				</div>
				<div class="right-bar-List">
					<button>매출 관리</button>
				</div>
				<div class="right-bar-List">
					<button onclick="location.href='TablesEditForm.tb'">테이블 관리</button>
				</div>
				<div class="right-bar-List">
					<button>설정</button>
				</div>
			</div>

</section>		


</div>

</body>
</html>