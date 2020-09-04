$( document ).ready(function() {
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
		var fontSize = (pst[0][2] / 5);	
		ctx.font = fontSize + "px '맑은 고딕'";
		ctx.textAlign = "center";
		ctx.textBaseline = "top";
		ctx.fillText((i+1)+"번",pst[i][0]+(pst[i][2]/2),pst[i][1]);

 	}	
	
}


//경로생성 및 색 채우기
function rect(x,y,w,h) {
	

 ctx.beginPath();
 ctx.strokeStyle = "#000000";
 ctx.strokeRect(x, y, w, h);
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
		 
		  location.href = "TableDetail.tb?tableNo=" + (i+1);
		 
		  //테이블번호를 object에 저장
		  object = i;
		 }		
		
	}

});

draw();

});