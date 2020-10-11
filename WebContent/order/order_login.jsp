<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.JList"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
// 임시 table_num
int table_num = (int)session.getAttribute("table_num");
%>
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
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="/SOS/plugins/OwlCarousel2-2.2.1/animate.css">
<link href="/SOS/plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link href="/SOS/plugins/jquery-datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/SOS/plugins/jquery-timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/responsive.css">
<style type="text/css">
#loginFormArea {
	top:+300px;
	margin: 0 auto;
	width: 350px;
	height: 400px;
	border: 2px double purple;
	border-radius: 10px;
	text-align: left;
	background-color: white;
}

fieldset {
	text-align: center;
	border: none;
	background-color: white;
}

#selectButton {
	margin-top: 10px;
}


table {
	width: 300px;
	margin: auto;
}

.td_left {
	width: 180px;
	margin-left: auto;
}

.td_right {
	width: 200px;
}
input[type="text"],input[type="password"]{
	width: 200px;
	}
</style>
<link rel="stylesheet" href="/SOS/table/style/table.css">
<script src="js/jquery.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
	charset="utf-8"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
<jsp:include page="../inc/top.jsp"/>
<!-- /header -->
<div class="home">
<!-- 		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"></div> -->
<!-- 		<div style="top:0;width:70%;height:70%;margin:0px auto;" data-parallax="scroll" data-image-src="images/home.jpg" data-speed="0.8"> -->
<div style="top:0;height:120%;margin:0px auto;" data-parallax="scroll" data-image-src="/SOS/images/home.jpg" data-speed="0.8">
	<section id="loginFormArea">
		<form action="/SOS/MemberOrderLoginPro.or" method="post" name="loginForm" id="loginForm">
			<fieldset id="f_id">
				<table id="login">
				<br>
				<h2>로그인</h2>
				<tr>
					<td height="30px"></td>
				</tr>
					<tr>
						<td class="td_left"><label for="mem_id">아이디</label></td>
						<td class="td_right"><input type="text" name="mem_id"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="mem_passwd">패스워드</label></td>
						<td class="td_right"><input type="password" name="mem_passwd">
						</td>
					</tr>
					<tr>
					<td height="10px"></td>
					</tr>
				</table>
				<input type="hidden" name="table_num" value="<%=table_num %>">
				<br> <input type="submit" value="로그인"> 
				<input type="button" value="회원가입" onclick="location.href='/SOS/MemberOrderJoinForm.or'">
				<input type="button" value="취소" onclick="history.back()"><br>
				<a href="/SOS/NonMemberOrder.or?table_num=<%=table_num %>">비회원</a>
				<br> <a id="kakao-login-btn"></a>
				
				<script type="text/javascript">
				
				//  카카오 로그인 화면 
 				// 사용할 앱의 JavaScript 키
				Kakao.init('f9f70cbe72f6c12e2fd5fd82d39ffd70');
 				
				
 				// 카카오 로그인 버튼을 생성
 				Kakao.Auth.createLoginButton({
  					container: '#kakao-login-btn',
 					success: function(authObj) {
   					
  						// 로그인 성공시, API를 호출
  						Kakao.API.request({
   							url: '/v2/user/me',
   							success: function(res) {  
     					    alert(JSON.stringify(res)); //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력
     						
     					    var id = res.id;    				// 유저의 카카오톡 고유 id
    						var email = res.kakao_account.email;   	// 유저의 이메일
    						var birth = res.kakao_account.birthday;  // 유저의 생년 월일
    						var gender = res.kakao_account.gender;	// 유저의 성별
    						var nickname = res.properties.nickname;	// 유저의 닉네임
    						var name = res.properties.name;
   							location.href='SnsLogin.me?mem_id='+id+"&mem_email="+email+"&mem_birth="+birth+"&mem_gender="+gender+"&mem_name="+name+"&mem_nickname="+nickname;

//    						$.ajax('SnsLogin.me',{
//    					         data:{
//    					                     mem_id:id,
//    					                     mem_email:email,
//    					                     mem_birth:birth,
//    					                     mem_gender:gender,
//    					                     mem_name:name,
//    					                     mem_nickname:nickname,
//    					                  },
//    					         success:function(rdata){
//    					            location.href ='index.jsp';
//    					         }
//    					   });
   							
   							},
   							
	
    					fail: function(error) {
    						alert(JSON.stringify(error));
                        }
   							
  							});
  							
  						console.log(authObj);
  						var token = authObj.access_token;
  						
					},
  						fail: function(err) {
  							alert(JSON.stringify(err));
 						}
 				});
						
				</script>
				<br>

				<div id="naverIdLogin"></div>
				<script type="text/javascript">//네이버 로그인
  					 var naverLogin = new naver.LoginWithNaverId(
      					{
        					clientId: "E3pvtJLoB309rBX1NK7h",
         					callbackUrl: "http://localhost:8080/SOS/callback.jsp",
         					isPopup: false,
         					loginButton: {color: "green", type: 3, height: 47}
     							 }
  				 		);
				
  			 naverLogin.init();
  			 
				</script>
			</fieldset>
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