<%@page import="java.util.ArrayList"%>
<%@page import="vo.TableDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<title>edit.jsp</title>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script>
<script src="table/js/edit.js"></script>
</head>
<body>

<script>

var pst = [
<%
ArrayList<TableDTO> list = (ArrayList<TableDTO>)request.getAttribute("tableInfo");

for(int i=0;i<list.size();i++){
	%>
	[<%=list.get(i).getTable_x()%>,<%=list.get(i).getTable_y()%>,<%=list.get(i).getTable_w()%>,<%=list.get(i).getTable_h()%>]
	
	
	<%if(i != (list.size() -1) ){%>
		,
	<%}
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
					<button id="addTable">4인용</button>
				</div>
				<div class="right-bar-List">	
					<button id="addTable2-1">2인용(세로)</button>
				</div>	
				<div class="right-bar-List">	
					<button id="addTable2-2">2인용(가로)</button>
				</div>
				<div class="right-bar-List">
					<button id="sizeUp">SizeUp</button>
				</div>
				<div class="right-bar-List">
					<button id="sizeDown">SizeDown</button>
				</div>
				<div class="right-bar-List">
					<button id="save">테이블 저장</button>
				</div>
			</div>
			
</section>
	

</div>

</body>
</html>