<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.JList"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#loginFormArea {
	margin: auto;
	width: 350px;
	height: 400px;
	border: 2px double purple;
	border-radius: 10px;
	text-align: center;
}

fieldset {
	text-align: center;
	border: none;
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
}

.td_right {
	width: 200px;
}
</style>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
	charset="utf-8"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
	<section id="loginFormArea">
		<h1>로그인</h1>
		<form action="MemberLoginPro.me" method="post" name="loginForm">
			<fieldset>
				<table>
					<tr>
						<td class="td_left"><label for="member_id">아이디</label></td>
						<td class="td_right"><input type="text" name="member_id"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="member_passwd">패스워드</label></td>
						<td class="td_right"><input type="password" name="member_passwd">
						</td>
					</tr>
				</table>
				<br> <input type="submit" value="로그인"> 
				<input type="button" value="회원가입" onclick="location.href='MemberJoinForm.me'">
				<input type="button" value="취소" onclick="history.back()"><br>
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
    						var name = res.properties.nickname;	// 유저의 닉네임
   							var passwd = null;
   							location.href='SnsLogin.me?member_id='+id+"&member_email="+email+"&member_birth="+birth+"&member_gender="+gender+"&member_name="+name+"&member_passwd="+passwd;
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
</body>
</html>