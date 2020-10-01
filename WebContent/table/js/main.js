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

//메뉴리스트 내려쓰기 변수 -> 메뉴가 하나써질때마다 아래로 하나씩 내려간다.

var lineInterval = pst[0][2] / 6;

var nextLine = [lineInterval];



//그리는 함수
function draw() {
	
	var WIDTH = canvas.width;
	var HEIGHT = canvas.height;
	
	//도형 전체 지우기 	
// 	clear(0, 0, WIDTH, HEIGHT);
	
	ctx.fillStyle = "#f2f2f2";
	rect(0,0,WIDTH,HEIGHT);	
	

		
	for(var i=0; i<pst.length; i++){
			 
		 pst[i][0] = Math.floor(pst[i][0]);
		 pst[i][1] = Math.floor(pst[i][1]);
		 pst[i][2] = Math.floor(pst[i][2]);
		 pst[i][3] = Math.floor(pst[i][3]);
		 
		 ctx.fillStyle = "#232323";	 
		rect(pst[i][0]-5, pst[i][1]-5, (pst[i][2]+10), (pst[i][3]+10));
		 
		ctx.fillStyle = "#ffffff";
		rect(pst[i][0], pst[i][1], pst[i][2], pst[i][3]);
		
		ctx.fillStyle = "#00805e";
		rect(pst[i][0], pst[i][1], pst[i][2], (pst[i][3] / 7));
		
		
	
		ctx.fillStyle = "#000000";		
		var fontSize = (pst[0][2] / 8);	
		ctx.font = "italic " + fontSize + "px New Gulim";
		ctx.textAlign = "center";
		ctx.textBaseline = "top";
		ctx.fillText("No." + (i+1),pst[i][0]+(pst[i][2]/2),pst[i][1]);

		
		
		nextLine.push(lineInterval);
		
		for(var c = 0; c<preorderInfo.length; c++){
			
			var tableNum = i + 1;
			
			//테이블번호와 preorder에 저장되어있는 테이블번호를 비교한다
			if(tableNum == preorderInfo[c][0]){
				ctx.fillStyle = "#000000";	
				//메뉴종류가 많으면 메뉴를 함축한다.
				if(nextLine[i] < pst[0][3]){
					
					var fontSize2 = (pst[0][2] / 9);	
					//한줄에 출력할 메뉴이름 및 수량
					var thisItem = preorderInfo[c][2];
					
					var print = thisItem + "-" + preorderInfo[c][3];
					
					//메뉴이름이 테이블 폭 크기에 비해 길다면 메뉴이름을 함축시킨다.
					if(pst[i][2] < (fontSize2 * print.length)){
						print = thisItem.substring(0,2) + ".." + "-" + preorderInfo[c][3];
					}
					
					
					
					ctx.font = fontSize2 + "px New Gulim";
					ctx.textAlign = "start";				
					ctx.fillText(print, pst[i][0], pst[i][1] + nextLine[i]);
					
					//이용중 표시
					ctx.fillStyle = "#ffcccc";
					rect(pst[i][0], pst[i][1] + (pst[i][3] * (6/7)), pst[i][2], (pst[i][3] / 7));
					
					//다음줄이 오면 글자가 겹치지않게 띄워서 출력
					nextLine[i] = nextLine[i] + lineInterval;	
					
				}
		
			}
			
		}

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