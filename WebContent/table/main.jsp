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


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <script src="//code.jquery.com/jquery-3.2.1.min.js"></script> -->
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>

	<script>

var dialogOn = false;

var pst = [
<%ArrayList<TableDTO> list = (ArrayList<TableDTO>) request.getAttribute("tableInfo");

for (int i = 0; i < list.size(); i++) {%>
	[<%=list.get(i).getTable_x()%>,<%=list.get(i).getTable_y()%>,<%=list.get(i).getTable_w()%>,<%=list.get(i).getTable_h()%>]	
	<%if (i != (list.size() - 1)) {%>,<%}
}%>
];

var preorderInfo = [
<%ArrayList<PreOrderBean> list2 = (ArrayList<PreOrderBean>) request.getAttribute("preorderInfo");
for (int i = 0; i < list2.size(); i++) {%>
	[<%=list2.get(i).getTable_num()%>,<%=list2.get(i).getMem_num()%>,"<%=list2.get(i).getItem_name()%>",<%=list2.get(i).getItem_qty()%>,<%=list2.get(i).getPre_confirm()%>]	
	<%if (i != (list2.size() - 1)) {%>,<%}
}%>
];

//다이얼로그에 주문 처리되지 않은 표현할 주문 목록
var preorderTable2 = [
	<%for (int i = 0; i < list2.size(); i++) {
	if (list2.get(i).getPre_confirm() == 0) {%>
		<%=list2.get(i).getTable_num()%>
		<%if (i != (list2.size() - 1)) {%>,<%}
}
}%>
	];

//번호순 정렬을 위한메서드
preorderTable2.sort(function (a,b){ return a-b; });

//중복제거를 한 값을 저장할 테이블
var preorderTable = [];


$( document ).ready(function() {
	
	//번호순 정렬 후 중복제거를 메서드
	$.each(preorderTable2,function(i,value){
	    if(preorderTable.indexOf(value) == -1 ) preorderTable.push(value);
	});

	
	//다이얼로그에 접수/거절을 누르는 버튼
	for(var i = 0; i < preorderTable.length; i++){		
		
		$('.dialog-content').append("<div class='dialog-preorder'>"
		+ "<div class='dialog-preorder-title' >"+ preorderTable[i] + "번 테이블</div>"		
		+ "<div id='table"+ preorderTable[i] +"'></div>"		
		+ "<button class='btn-accept' value='"+ preorderTable[i] +"'>접수하기</button><button class='btn-cancel'>거절</button>"
		+ "</div>");
		
// 		//다이얼로그에 메뉴리스트를 출력하는 메서드
		for(var j = 0; j < preorderInfo.length; j++){
			
			if(preorderTable[i] == preorderInfo[j][0]){
				
				$('#table'+preorderTable[i]).append("<div>" + preorderInfo[j][2] + " - " + preorderInfo[j][3] + "개 </div>");
				
			}		
		}
					
	}
	
	//다이얼로그가 열려야되는지 확인하는 메서드
	if(preorderTable.length > 0){
		dialogOn = true;
	}
	
	//다이얼로그에서 메뉴를 수락할 경우
	$(document).on("click",".btn-accept",function(){
		alert(this.value);
		
		location.href = "PreOrderAccept.tb?table_num=" + this.value;
	});
	

	//주문이 왔을때 다이얼로그를 연다
	if(dialogOn == true){			
		
// 		var audio = new Audio("table/style/alram.mp3");
// 		audio.play();
			
		$('#dialog-message').dialog({
				modal: true,
				width: '500px',
				buttons: {
				"닫기": function() { dialogOn = false;  $(this).dialog('close'); }

			}
		});	
	}
	

	var url = "ws://localhost:8080/SOS/websocket";
    var webSocket = null;
	
    webSocket = new WebSocket(url);
    
    webSocket.onopen = function(e) {
//         console.log(e);
    }

    
    webSocket.onmessage = function(e) {
    	location.reload(true);
    }
	
	
});



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
					<button onclick="location.href='TablesMain.tb">주문 내역</button>
				</div>
				<div class="right-bar-List">
					<button onclick="location.href='RsvList.rsv'">예약 내역</button>
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

	<div id="dialog-message" title="주문 요청리스트" style='display: none'>
		<div class="dialog-content"></div>
	</div>






</body>
</html>