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

</script>

<h1>table/main.jsp</h1>


<div class="content">

<section>
<canvas id="canvas" width="1000" height="720">
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>

			<div class="right-bar">
				<div class="right-bar-List">
					<button>주문 내역</button>
				</div>
				<div class="right-bar-List">
					<button>예약 내역</button>
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