<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="The Venue template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/SOS/styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="/SOS/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/animate.css">
<link href="/SOS/plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link href="/SOS/plugins/jquery-datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/SOS/plugins/jquery-timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/responsive.css">
<style type="text/css">
input[type="text"],input[type="password"],input[type="email"]{
	width: 250px;
	font-size:15px;
	}
#joinFormArea { 
		top:+150px;
		margin:0 auto;
		width: 800px; 
		height: 700px; 
		border: 2px double purple; 
		border-radius: 10px; 
 		text-align: center; 
 		background-color: white;
 	} 
	fieldset {
 	text-align: left;  
 	border: none; 
	} 
	
	#selectButton { 
		margin-top: 10px; 
 	} 
	
 	table {
 		width: 600px; 
 		margin: auto; 
 		margin-left: 300px;
   		margin-right: auto;
 		
 	}

 	td{width:150px; margin-right: 200px;
 		
 	 }
 	
</style>
<link rel="stylesheet" href="/SOS/table/style/table.css">
<script src="js/jquery.js"></script><script type="text/javascript">

jQuery(document).ready(function () {
	$('#btn').click(function() {
		var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/;
		var mem_id=$('#mem_id').val();
		if(regex.exec(mem_id)) { 
			//중복 체크 실행
			$.ajax('/SOS/dupCheck.me',{
				data:{mem_id:mem_id},
					success:function(rdata){
// 						alert(rdata)
						$('#checkIdResult').html(rdata);
					}});
			
		}else{
			alert('아이디 형식이적합하지 않음');
			
		}
			
	});
});
	
	function checkPasswd(mem_passwd) {
		// 8 ~ 16자리 패스워드 영문,숫자,특수문자 조합
		// 1. 정규표현식 지정
		// 1) 길이 체크 : 8 ~ 16자리. 영문 대문자&소문자&숫자&특수문자(!@#$%^_)
		var lengthRegex = /^[A-Za-z0-9!@#$%^_]{8,16}$/;
		// 2) 소문자 체크
		var lowerCaseRegex = /[a-z]/;
		// 3) 숫자 체크
		var digitRegex = /[0-9]/;
		// 4) 특수문자 체크
		var specCharRegex = /[!@#$%^_]/;
		
		// 2. 체크 후 메세지 표시할 공간의 태그 id 값 가져오기
		var element = document.getElementById('checkPasswdResult'); // checkPasswdResult 값을 ID 로 갖는 태그 찾기
		
 		// 3. 정규표현식을 통한 유효성 검사 수행(정규표현식 저장 변수명.exec() 를 사용)
 		// 함수 호출 시 전달받은 파라미터(id) 의 값을 정규표현식으로 검사
		// 길이, 대문자, 소문자, 숫자, 특수문자 체크를 모두 통과했을 경우
		if(mem_passwd.value!=""){
			if(lengthRegex.exec(mem_passwd.value) && lowerCaseRegex.exec(mem_passwd.value) && digitRegex.exec(mem_passwd.value) &&
					specCharRegex.exec(mem_passwd.value)) {
//  			alert('유효성 검사 통과');	
			// 지정된 태그 내에 메세지 표시
				element.innerHTML = "올바른 패스워드입니다.";
			} else { // 유효성 검사를 통과하지 못했을 경우
// 			alert('유효성 검사 탈락');
				element.innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			}
		}else{
				element.innerHTML = "";
		}		
	}
	
	function checkBirthday(mem_birth){
		var regEx = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;

		var element = document.getElementById('checkBirth'); // checkBirth 값을 ID 로 갖는 태그 찾기
		
		if(mem_birth.value!=""){
			if(regEx.exec(mem_birth.value)){
				element.innerHTML = "올바른 주민번호";
			}else{
				element.innerHTML = "주민번호를 올바르게 작성 하세요";
			}
		}else{
				element.innerHTML = "";
		}	
	}
	
	function checkEmail(mem_email){
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

		var element = document.getElementById('checkEmail'); // checkBirth 값을 ID 로 갖는 태그 찾기
		
		if(mem_email.value!=""){
			if(emailRule.exec(mem_email.value)){
				element.innerHTML = "올바른 이메일";
			}else{
				element.innerHTML = "이메일을 올바르게 작성 하세요";
			}
		}else{
				element.innerHTML = "";
		}	
	}
// 번호 입력할 때  -표시 하는 함수 	
function inputPhoneNumber(obj) {

	  var number = obj.value.replace(/[^0-9]/g, "");
	  var phone = "";

	  if(number.length < 4) {
	      return number;
	  } else if(number.length < 7) {
	      phone += number.substr(0, 3);
	      phone += "-";
	      phone += number.substr(3);
	  } else if(number.length < 11) {
	      phone += number.substr(0, 3);
	      phone += "-";
	      phone += number.substr(3, 3);
	      phone += "-";
	      phone += number.substr(6);
	  } else {
	      phone += number.substr(0, 3);
	      phone += "-";
	      phone += number.substr(3, 4);
	      phone += "-";
	      phone += number.substr(7);
	  }
	  obj.value = phone;
}
	
var birthJ = false;

 $(document).ready(function() {
		
		$('#join').submit(function() {
	 		if($('#mem_name').val()==""){
	 			alert("이름을 입력하세요");
	 			$('#mem_name').focus();
	 			return false;
	 		}
	 		if($('#mem_id').val()==""){
	 			alert("아이디를 입력하세요");
	 			$('#mem_id').focus();
	 			return false;
	 		}
			
			if($('#mem_passwd').val()==""){
				alert("비밀번호를 입력하세요");
				$('#mem_passwd').focus();
				return false;
	 		}
			if($('#mem_passwd2').val()==""){
				alert("비밀번호 재입력을 입력하세요");
				$('#mem_passwd').focus();
				return false;
	 		}
			if($('#mem_passwd').val()!=$('#mem_passwd2').val()){
				alert("비밀번호가 다릅니다");
				$('#mem_passwd').focus();
				return false;
	 		}
	 		
	 		if($('#mem_email').val()==""){
	 			alert("이메일을 입력하세요");
	 			$('#mem_email').focus();
	 			return false;
	 		}
	 		if($('#mem_phone').val()==""){
	 			alert("번호를 입력하세요");
	 			$('#mem_phone').focus();
	 			return false;
	 		}
	 		if($('#mem_birth').val()==""){
	 			alert("생년월일을 입력하세요");
	 			$('#mem_birth').focus();
	 			return false;
	 		}
		});
	});
 </script>
</head>
<body>
<!-- <div class="super_container"> -->
<!-- header -->

<jsp:include page="../inc/top.jsp"/>
<!-- /header -->
<div class="home">
<!-- 		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"></div> -->
<!-- 		<div style="top:0;width:70%;height:70%;margin:0px auto;" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"> -->
<div style="top:0;height:120%;margin:0px auto;" data-parallax="scroll" data-image-src="/SOS/images/home.jpg" data-speed="0.8">
		<section id="joinFormArea">
		<h1>회원가입</h1>
		<br>
		<form action="/SOS/MemberOrderJoinPro.or" id="join" method="post" name="joinForm">
			<fieldset>
				<table style="margin-left: auto; margin-right: auto;">
				<tr>
					<td></td>
				</tr>
					<tr>
						<td style="font-size: 18px;">이름</td>
						<td>
							<input type="text" name="mem_name" id="mem_name">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">닉네임</td>
						<td>
							<input type="text" name="mem_nickname" id="mem_nickname">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">아이디</td>
						<td>
							<input type="text" name="mem_id" id="mem_id"
								placeholder=" 4~12자리 영문,숫자 조합">
						</td>
						<td><input type="button" value="중복체크" id="btn">	</td>
					</tr>
					<tr height="35px">
						<td colspan="2"><span style="color:red" id="checkIdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
					<tr>
						<td style="font-size: 18px;">패스워드</td>
						<td>	
							<input type="password" name="mem_passwd" id="mem_passwd"
								placeholder=" 8~16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)">
						</td>
					</tr>
					<tr height="35px">
						<td colspan="2"><span style="color:red" id="checkPasswdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">패스워드 재확인</td>
						<td>
							<input type="password" name="mem_passwd2" id="mem_passwd2"
							placeholder=" 8~16자리 영문,숫자,특수문자 조합"	placeholder="비번 재확인">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">E-Mail</td>
						<td><input type="email" name="mem_email" id="mem_email" 
						placeholder=" ex)zheor@naver.com" onkeyup="checkEmail(this)"></td>
					</tr>
					<tr height="25px">
						<td colspan="2"><span style="color:red" id="checkEmail"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">성별</td>
						<td>
							<input type="radio" name="mem_gender" value="1" checked="checked">남자
							<input type="radio" name="mem_gender" value="0">여자
						</td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">전화번호</td>
						<td><input type="text" name="mem_phone" id="mem_phone"
						placeholder=" - 없이 입력" onKeyup="inputPhoneNumber(this)"maxlength="13"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="font-size: 18px;">생년월일</td>
						<td><input type="text" name="mem_birth" id="mem_birth"
						placeholder=" ex)19930316" onkeyup="checkBirthday(this)" maxlength="8"></td>
					</tr>
					<tr height="25px">
						<td colspan="2"><span style="color:red" id="checkBirth"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
						<td></td>
					</tr>
					
				</table>
			</fieldset>
					<input type="submit" value="가입" style="font-size: 15px;"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="취소" style="font-size: 15px;" onclick="history.back()" />
		</form>
	</section>
		
		
		
		</div>
<!-- 		<div class="home_container"> -->
<!-- 			<div class="container"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col"> -->
<!-- 						<div class="home_content text-center"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="scroll_icon"></div>
	</div>
	
	
	
<jsp:include page="../inc/bottom.jsp"/>
<!-- /footer -->


<script src="js/jquery-3.2.1.min.js"></script>
<script src="/SOS/styles/bootstrap-4.1.2/popper.js"></script>
<script src="/SOS/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="/SOS/plugins/greensock/TweenMax.min.js"></script>
<script src="/SOS/plugins/greensock/TimelineMax.min.js"></script>
<script src="/SOS/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/SOS/plugins/greensock/animation.gsap.min.js"></script>
<script src="/SOS/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/SOS/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/SOS/plugins/easing/easing.js"></script>
<script src="/SOS/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/SOS/plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="/SOS/plugins/jquery-datepicker/jquery-ui.js"></script>
<script src="/SOS/plugins/jquery-timepicker/jquery.timepicker.js"></script>
<script src="/SOS/js/custom.js"></script>
</body>
</html>