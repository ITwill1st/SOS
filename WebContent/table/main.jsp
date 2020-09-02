<%@page import="vo.TableDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>table/main.jsp</title>

<style type="text/css">
body{
margin:0;
padding:0;
}
</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
</head>
<body>
<h1>테이블 위치 및 테이블 별 주문을 보여주는 사장님 페이지</h1>
<a href="TablesEditForm.tb">클릭시 테이블을 수정할수있는 페이지로넘어가는 버튼</a><br>

<a href="TableDetail.tb">해당 테이블 클릭시 해당테이블의 주문목록 및 주문추가가 가능한 페이지로 넘어가는버튼</a>
<section>
		<div>
			<canvas id="canvas" width="1200" height="800" style="border:1px solid #000000;">
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>
		</div>
		
		<div class="ButtonList">
		<button>예약내역확인</button>
		<button>주문내역확인</button>
		
		</div>
</section>		


<script type="text/javascript">
//전역변수 설정

// 캔버스 변수
var canvas = document.getElementById("canvas");

//테이블
var ctx;

//테이블번호 초기값은 설정-1(없는테이블정의)
var object = -1;

//테이블 중심점에서 상하좌우로까지의 거리
var pos = pst[0][0]/2;


//캔버스 변수 선언
canvas = document.getElementById("canvas");

//캔버스 타입 2D 이미지 타입
ctx = canvas.getContext("2d");

//그리는 함수
function draw() {
	
	var WIDTH = canvas.width;
	var HEIGHT = canvas.height;
	
	//도형 전체 지우기 	
// 	clear(0, 0, WIDTH, HEIGHT);
	
	ctx.fillStyle = "#ffffff";
	rect(0,0,WIDTH,HEIGHT);	
		
	for(var i=0; i<pst.length; i++){
			 
		 pst[i][0] = Math.floor(pst[i][0]);
		 pst[i][1] = Math.floor(pst[i][1]);
		 pst[i][2] = Math.floor(pst[i][2]);
		 pst[i][3] = Math.floor(pst[i][3]);
			 
		
		ctx.fillStyle = "#f2f2f2";
		rect(pst[i][0], pst[i][1], pst[i][2], pst[i][3]);
		
		ctx.fillStyle = "#000000";
		ctx.font = "20pt '맑은 고딕'";
		ctx.fillText((i+1)+"번 테이블",pst[i][0],pst[i][1]);

 	}	
	
}


//경로생성 및 색 채우기
function rect(x,y,w,h) {	
 ctx.beginPath();
 ctx.rect(x,y,w,h);
 ctx.closePath();
 ctx.fill();

}

//테이블 클릭시 해당테이블로 이동

$(document).click(function(e){

	for(var i=0; i<pst.length; i++){
		
		
		//마우스커서가 테이블안에 있고 & 테이블 배경도형안에 있을때만  도형을  움직일수 있음
		 
		if(
		e.pageX > (pst[i][0]) + canvas.offsetLeft && 
		e.pageX < (pst[i][0]+(pst[i][2])) + canvas.offsetLeft &&
		e.pageY > (pst[i][1])+ canvas.offsetTop &&
		e.pageY < (pst[i][1]+(pst[i][3]))+ canvas.offsetTop
		){	 

		
		 console.log((i+1) + "번째 박스 클릭"); 
		  
		  //테이블번호를 object에 저장
		  object = i;
		 }		
		
	}

});

draw();

</script>



</body>
</html>