<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String year = request.getParameter("year");
String month = request.getParameter("month");
String date = request.getParameter("date");

String id = (String) session.getAttribute("id");

if (year == null) {
	year = "0";
}
if (month == null) {
	month = "0";
}
if (date == null) {
	date = "0";
}
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>Calendar</title>

<style>
body {
	font-family: Tahoma;
}

header {
	text-align: center;
}

#calendar {
	width: 100%;
}

#calendar a {
	color: #8e352e;
	text-decoration: none;
}

#calendar ul {
	list-style: none;
	padding: 0;
	margin: 0;
	width: 100%;
}

#calendar li {
	display: block;
	float: left;
	width: 14.342%;
	padding: 5px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	margin-right: -1px;
	margin-bottom: -1px;
}

#calendar ul.weekdays {
	height: 40px;
	background: #8e352e;
}

#calendar ul.weekdays li {
	text-align: center;
	text-transform: uppercase;
	line-height: 20px;
	border: none !important;
	padding: 10px 6px;
	color: #fff;
	font-size: 13px;
}

#calendar .days li {
	height: 180px;
}

#calendar .days li:hover {
	background: #d3d3d3;
}

#calendar .date {
	text-align: center;
	margin-bottom: 5px;
	padding: 4px;
	background: #333;
	color: #fff;
	width: 20px;
	border-radius: 50%;
	float: right;
}

#calendar .event {
	clear: both;
	display: block;
	font-size: 13px;
	border-radius: 4px;
	padding: 5px;
	margin-top: 40px;
	margin-bottom: 5px;
	line-height: 14px;
	background: #e4f2f2;
	border: 1px solid #b5dbdc;
	color: #009aaf;
	text-decoration: none;
}

#calendar .event-desc {
	color: #666;
	margin: 3px 0 7px 0;
	text-decoration: none;
}

#calendar .other-month {
	background: #f5f5f5;
	color: #666;
}
/* ============================
                Mobile Responsiveness
   ============================*/
@media ( max-width : 768px) {
	#calendar .weekdays, #calendar .other-month {
		display: none;
	}
	#calendar li {
		height: auto !important;
		border: 1px solid #ededed;
		width: 100%;
		padding: 10px;
		margin-bottom: -1px;
	}
	#calendar .date {
		float: none;
	}
}
</style>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
 
    //변수 초기화
	var year = "<%=year%>";
	
	year = Number(year);
 
	var month = "<%=month%>";
	
	month = Number(month);
	
	var date = "<%=date%>";
	
	date = Number(date);
	
	var thisMonthDate = 0;
	
	var lastMonthDate = 0;
	
	var nextMonthDate = 0;
	
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
	
	var firstDayOfWeek = 0;
	
	var lastMonthlastDayOfWeek = 0;
	
	var nextMonthFirtDayOfWeek = 0;
 	
	
	
	//이번달이 계산되어있는경우
	
	if(year == 0){
	//오늘날짜구함
	getToday();
	}
	
	//오늘날짜기준으로 해당월의 일수를 계산
	getMonthDate();
	
	//해당월의 요일을 계산
	getDayOfTheWeek();
	
	
	
	
	
 //오늘날짜 구하기
 function getToday(){
	    var now = new Date();
	    year = now.getFullYear();
	    month = now.getMonth() + 1;    //1월이 0으로 되기때문에 +1을 함.
	    date = now.getDate();

	    if((month + "").length < 2){        //2자리가 아니면 0을 붙여줌.
	        month = "0" + month;
	    }
	     // ""을 빼면 year + month (숫자+숫자) 됨.. ex) 2018 + 12 = 2030이 리턴됨.
	   var today = ""+year + month + date; 
// 	     alert(today);
	}

 
 //해당월의 날수 구하기
 function getMonthDate(){ 
	 thisMonthDate = new Date(year,month,0).getDate();
	 
	 lastMonthDate = new Date(year,month-1,0).getDate();
		
	 nextMonthDate = new Date(year,month+1,0).getDate();
	 
// 	 alert(thisMonthDate);
 }
 
 
 //해당월의 요일 구하기
 function getDayOfTheWeek(){
	 
	 //현재 월의 1일의 요일을 구한다 //0이면 일요일
	 firstDayOfWeek = new Date(year + '-' + month + '-1').getDay();
	 
	 lastMonthlastDayOfWeek = new Date(year + '-' + (month-1) + '-' + lastMonthDate).getDay();
	 
	 nextMonthFirtDayOfWeek = new Date(year + '-' + (month+1) + '-' +  '-1').getDay();
	 
 }
 
 
 
 $(document).ready(function(){

 //상단날짜 삽입////////////////////////////////////////
 $("h1").text(year+"년 "+month+"월");
 ///////////////////////////////////////////////////////

 
 //JSON 으로 예약가능한 날짜 가져오기
$.ajax('RsvDate.rsv',{
	dataType:'json',
	data:{year:year,month:month},
	success:function(data){
		alert(data.date);
	}
});
		

 
 
 //현재월의날짜기입////////////////////////////////////
 
  var startDay = 1;
 //칸별로 숫자대입
 for(var i=1; i<=thisMonthDate; i++){
	 
 //칸 별로 숫자를 카운트시켜줌
	 $(".date:eq("+firstDayOfWeek+")").text(startDay);
	 
	 firstDayOfWeek += 1;
	 startDay += 1;
	 
 }
 //////////////////////////////////////////////////////
 

 
 
 //전달날짜 삽입//////////////////////////////////////
 
 for(var i=lastMonthlastDayOfWeek; i>=0; i--){
	 
	 $(".date:eq("+i+")").text(lastMonthDate);
	 $(".date:eq("+i+")").css("background-color", "#ccccd7");
	 
	 lastMonthDate -= 1;
	 
	 $(".day:eq("+i+")").css("background-color", "#c2c2d6");
 }	
	 
 //////////////////////////////////////////////////////
 
 
 //다음달 날짜 삽입////////////////////////////////////
 
 var nextMonthDay = 1;
 
//  alert(thisMonthDate);
 
 for(var i=thisMonthDate+lastMonthlastDayOfWeek+1; i<=42; i++){
	 
	 $(".date:eq("+i+")").text(nextMonthDay);
	 $(".date:eq("+i+")").css("background-color", "#ccccd7");
	 
	 nextMonthDay += 1;
	 
	 $(".day:eq("+i+")").css("background-color", "#c2c2d6");
 }
 
 
 month = Number(month);
 
  var nextMonth = month+1;
  
 var previousMonth = month-1;
 
 $( '#next' ).click( function() {
	 location.href= "http://localhost:8080/OrderSystem/ReserveMain.re?year=" + year + "&&month=" + nextMonth + "&&day=" + date;
	} );

 $( '#previous' ).click( function() {
	 location.href= "http://localhost:8080/OrderSystem/ReserveMain.re?year=" + year + "&&month=" + previousMonth + "&&day=" + date;
	} );

 
 });

 
 

 </script>




<body>









	<header>

		<button id="previous">전달</button>
		<h1></h1>
		<button id="next">다음달</button>

	</header>
	<div id="calendar-wrap">

		<div id="calendar">
			<ul class="weekdays">
				<li>Sunday</li>
				<li>Monday</li>
				<li>Tuesday</li>
				<li>Wednesday</li>
				<li>Thursday</li>
				<li>Friday</li>
				<li>Saturday</li>
			</ul>



			<!-- Days from previous month -->
			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<a href="#">
					<li class="day">
						<div class="date"></div>
						<div class="event">
							<div class="event-desc">HTML 5 lecture with Brad Traversy
								from Eduonix</div>
							<div class="event-time">1:00pm to 3:00pm</div>
						</div>
				</li>
				</a>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>



			<!-- Row #2 -->
			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
					<div class="event">
						<div class="event-desc">HTML 5 lecture with Brad Traversy
							from Eduonix</div>
						<div class="event-time">1:00pm to 3:00pm</div>
					</div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>
			<!-- Row #4 -->
			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
					<div class="event">
						<div class="event-desc">HTML 5 lecture with Brad Traversy
							from Eduonix</div>
						<div class="event-time">1:00pm to 3:00pm</div>
					</div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>







			<!-- Row #3 -->

			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
					<div class="event">
						<div class="event-desc">HTML 5 lecture with Brad Traversy
							from Eduonix</div>
						<div class="event-time">1:00pm to 3:00pm</div>
					</div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>


			<!-- Row #4 -->
			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
					<div class="event">
						<div class="event-desc">HTML 5 lecture with Brad Traversy
							from Eduonix</div>
						<div class="event-time">1:00pm to 3:00pm</div>
					</div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>


			<!-- Row #5 -->
			<ul class="days">
				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
				</li>

				<li class="day">
					<div class="date"></div>
					<div class="event">
						<div class="event-desc">HTML 5 lecture with Brad Traversy
							from Eduonix</div>
						<div class="event-time">1:00pm to 3:00pm</div>
					</div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<li class="day">
					<div class="date"></div>
				</li>
				<!-- Days in current month -->
				<li class="day">
					<div class="date"></div>
				</li>
			</ul>
		</div>
		<!-- /. calendar -->
	</div>
	<!-- /. wrap -->



</body>
</html>
