<%@page import="java.util.ArrayList"%>
<%@page import="vo.TableDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit.jsp</title>
<style type="text/css">
body{
margin:0;
padding:0;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1>edit.jsp</h1>
	<button id="addTable">테이블추가</button>
	<button id="sizeUp">SizeUp</button>
	<button id="sizeDown">SizeDown</button>
	<button id="save">테이블 저장</button>
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

	<section>
		<div>
			<canvas id="canvas" width="1200" height="800" style="border:1px solid #000000;">
This text is displayed if your browser does not support HTML5 Canvas.
</canvas>
		</div>
		
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


//배경의 넓이와 높이(픽셀단위)

canvas = document.getElementById("canvas");

// var WIDTH = canvas.width;
// var HEIGHT = canvas.height;

//마우스로 테이블을 드래그중인지 판별하는 전역 변수
var dragok = false;


//경로생성 및 색 채우기
function rect(x,y,w,h) {	
 ctx.beginPath();
 ctx.rect(x,y,w,h);
 ctx.closePath();
 ctx.fill();
}


//지우기
function clear() {	
	var WIDTH = canvas.width;
	var HEIGHT = canvas.height;
	ctx.clearRect(0, 0, WIDTH, HEIGHT);	
}


//캔버스 호출
function init(e) {
	
 //캔버스 변수 선언
 canvas = document.getElementById("canvas");
 
 //캔버스 타입 2D 이미지 타입
 ctx = canvas.getContext("2d");
 
 
 //0.01초마다 draw 함수실행
 return setInterval(draw, 10);
 
}

function draw() {
	
	var WIDTH = canvas.width;
	var HEIGHT = canvas.height;
	
	//도형 전체 지우기 	
	clear(0, 0, WIDTH, HEIGHT);
	
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
		ctx.fillText((i+1)+"번",pst[i][0],pst[i][1]);

 	}
	
}


	
	function myMove(e){
		e.preventDefault();
			//dragok=true 면 실행
			 if (dragok){
			//도형에 대한 드래그가 끝난 후 도형의 x,y 좌표값을 가져옵니다.
	 		 pst[object][0] = e.pageX - (pst[object][2]/2) - canvas.offsetLeft;
	 		 pst[object][1] = e.pageY - (pst[object][3]/2) - canvas.offsetTop;
	 		 

	 		}
		
	}
	

	//마우스클릭을 하고있는 상태일때 테이블이 드래그가 가능함
	function myDown(e){
		
	
	for(var i=0; i<pst.length; i++){
		
		
		//마우스커서가 테이블안에 있고 & 테이블 배경도형안에 있을때만  도형을  움직일수 있음
		 
		if(
		e.pageX > (pst[i][0]) + canvas.offsetLeft && 
		e.pageX < (pst[i][0]+(pst[i][2])) + canvas.offsetLeft &&
		e.pageY > (pst[i][1])+ canvas.offsetTop &&
		e.pageY < (pst[i][1]+(pst[i][3]))+ canvas.offsetTop
		){	 
		  //테이블의 위치를 설정
		  pst[i][0] = e.pageX - (pst[i][2]/2) - canvas.offsetLeft;
		  pst[i][1] = e.pageY - (pst[i][3]/2) - canvas.offsetTop;
		
		 console.log((i+1) + "번째 박스" + "좌표X=" + e.pageX + " 좌표Y=" +e.pageY); 
		  
		  dragok = true;//myMove함수 실행
		  //마우스가 움직일때(단 마우스가 클릭되어있는 상태여야함) 계속해서 myMove함수를 재호출한다.
		  canvas.onmousemove = myMove;
		  
		  canvas.touchmove = myMove;
		  
		  //테이블번호를 object에 저장
		  object = i;
		 }else{
			 console.log("좌표X=" + e.pageX + " 좌표Y=" +e.pageY);
		 }		
		
	}

	}

	//마우스클릭을때면 더이상 테이블이 드래그 되지않음
	function myUp(e){
		
	 dragok = false;
	 //테이블 이동이 완료 되었을 경우 해당 테이블의 위치를 반환한다.(추후 데이터베이스에 테이블 위치를 저장할때 사용함)
	 canvas.onmousemove = null;
	 canvas.touchmove = null;
	}
	//init()호출
	init();

	//칸바스 객체안에 마우스클릭 하고있을때 myDown함수실행, 클릭을 땠을때 myUp함수실행
	
	canvas.onmousedown = myDown;
	canvas.onmouseup = myUp;
	
	canvas.touchstart = myDown;
	canvas.touchend = myUp;

	
	$( document ).ready(function() {
		
	
		
		
	$('#addTable').click(function(){
		
		//테이블을 추가하기 위해 현재 존재하는 테이블갯수 확인후 테이블 삽입
		//마지막 테이블 숫자 
		var length = pst.length-1;
		
		var newTable;
		
		newTable = [
			(canvas.width /2),
			(canvas.height /2),
			pst[0][2],
			pst[0][3]		
			]

		pst.push(newTable);
				
	});
	
	
	
	
// 배치도 크기 키우기 줄이기 
	$('#sizeUp').click(function(){
		
		for(i=0; i<pst.length; i++){
			
			pst[i][0] = (pst[i][0] * 0.9);
			pst[i][1] = (pst[i][1] * 0.9);
			pst[i][2] = (pst[i][2] * 0.9);
			pst[i][3] = (pst[i][3] * 0.9);
			
			if(pst[i][1] < 150 ){
				pst[i][1] = 150;
			}
			if(pst[i][0] < 150 ){
				pst[i][0] = 150;
			}
			
		}	
		
	});


	// 배치도 크기 키우기 줄이기 	
	$('#sizeDown').click(function(){
		

		for(i=0; i<pst.length; i++){
			
			pst[i][0] = (pst[i][0]);
			pst[i][1] = (pst[i][1]);
			pst[i][2] = (Math.floor(pst[i][2] * 1.1));
			pst[i][3] = (Math.floor(pst[i][3] * 1.1));

		}
			
				
	});
	
	
	//디비에 테이블 위치값 저장
	$('#save').click(function(){
	
		var tableParam = "";
		
		for(var i=0; i<pst.length; i++){
			
			tableParam = tableParam + pst[i][0] + "," + pst[i][1] + "," + pst[i][2] + "," + pst[i][3];
			
			if(i!=(pst.length-1)){
				tableParam = tableParam + "/";
			}
		}
		
		alert(tableParam);
		
		location.href = "SaveTables.tb?tableParam=" + tableParam;
		
		
	
	});
	
	
	});
	
	
</script>
	</section>

</body>
</html>