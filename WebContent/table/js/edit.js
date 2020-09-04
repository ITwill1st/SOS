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


//배경의 넓이와 높이(픽셀단위)

canvas = document.getElementById("canvas");

// var WIDTH = canvas.width;
// var HEIGHT = canvas.height;

//마우스로 테이블을 드래그중인지 판별하는 전역 변수
var dragok = false;


//경로생성 및 색 채우기
function rect(x,y,w,h) {	
 ctx.beginPath();
 ctx.strokeStyle = "#000000";
 ctx.strokeRect(x, y, w, h);
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
	
	if(dragok){
		 ctx.fillStyle = "#c9b7b7";	
		 ctx.font = "30px '맑은 고딕'";
		 ctx.textAlign = "center";
		 ctx.fillText("드롭하여 테이블 삭제",(canvas.width/2),650);
		 
		 
		 ctx.beginPath();
		 ctx.strokeStyle = "#c9b7b7";
		 ctx.strokeRect(0, 600, canvas.width, canvas.height-600);
		 ctx.closePath();
		 ctx.fill();
		 

	}
	
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


	
	function myMove(e){
			//dragok=true 면 실행
			 if (dragok){
			//도형에 대한 드래그가 끝난 후 도형의 x,y 좌표값을 가져옵니다.
	 		 pst[object][0] = e.pageX - (pst[object][2]/2) - canvas.offsetLeft;
	 		 pst[object][1] = e.pageY - (pst[object][3]/2) - canvas.offsetTop;
	 		 
	 		}else{
	 			
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
	 
	 
	//테이블 중 테이블이 드롭다운 삭제 위치로 안으로 들어갈경우
	 for(var i=0; i<pst.length; i++){	 
		 
		 if(pst[i][1] < canvas.height && pst[i][1] > 600 - (pst[i][3]/2)  ){
			 
			 //해당테이블을 삭제한다.
			 pst.splice(i,1);		 
		 }	 
	 }
	 
	}
	//init()호출
	init();

	//칸바스 객체안에 마우스클릭 하고있을때 myDown함수실행, 클릭을 땠을때 myUp함수실행
	
	canvas.onmousedown = myDown;
	canvas.onmouseup = myUp;
	
	canvas.touchstart = myDown;
	canvas.touchend = myUp;

	

		
	
		
	//테이블 추가 함수	
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
	
	//테이블 추가 함수	
	$('#addTable2-1').click(function(){
		
		//테이블을 추가하기 위해 현재 존재하는 테이블갯수 확인후 테이블 삽입
		//마지막 테이블 숫자 
		var length = pst.length-1;
		
		var newTable;
		
		newTable = [
			(canvas.width /2),
			(canvas.height /2),
			pst[0][2]/2,
			pst[0][3]		
			]

		pst.push(newTable);
				
	});
	
	//테이블 추가 함수	
	$('#addTable2-2').click(function(){
		
		//테이블을 추가하기 위해 현재 존재하는 테이블갯수 확인후 테이블 삽입
		//마지막 테이블 숫자 
		var length = pst.length-1;
		
		var newTable;
		
		newTable = [
			(canvas.width /2),
			(canvas.height /2),
			pst[0][2],
			pst[0][3]/2		
			]

		pst.push(newTable);
				
	});
	
	

// 배치도 크기 키우기 줄이기 
	$('#sizeUp').click(function(){
		
		var dif1 = 0;
		var dif2 = 0;	
		
		for(i=0; i<pst.length; i++){
			
			dif1 = pst[i][0] - (pst[i][0] * 0.9);
			dif2 = pst[i][1] - (pst[i][1] * 0.9);
			
			pst[i][0] = (pst[i][0] + dif1);
			pst[i][1] = (pst[i][1] + dif2);
			pst[i][2] = (pst[i][2] * 0.9);
			pst[i][3] = (pst[i][3] * 0.9);
	
		}	
		
	});


	// 배치도 크기 키우기 줄이기 	
	$('#sizeDown').click(function(){

		var dif1 = 0;
		var dif2 = 0;

		for(i=0; i<pst.length; i++){
			
			dif1 = pst[i][0] - (pst[i][0] * 1.1);
			dif2 = pst[i][1] - (pst[i][1] * 1.1);
			
			pst[i][0] = (pst[i][0] + dif1);
			pst[i][1] = (pst[i][1] + dif2);
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