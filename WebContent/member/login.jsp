<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<%
    String clientId = "E3pvtJLoB309rBX1NK7h";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/SOS/member/callback.jsp", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
 
</head>
<body>
<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "E3pvtJLoB309rBX1NK7h",
			callbackUrl:"http://localhost:8080/SOS/callback.jsp",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
			
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
// 	naverLogin.getLoginStatus(function (status) {
// 		if (status) {
// 			var email = naverLogin.user.getEmail();
// 			var name = naverLogin.user.getNickName();
// 			var profileImage = naverLogin.user.getProfileImage();
// 			var birthday = naverLogin.user.getBirthday();			
// 			var uniqId = naverLogin.user.getId();
// 			var age = naverLogin.user.getAge();
// 		} else {
// 			console.log("AccessToken이 올바르지 않습니다.");
// 		}
// 	});
</script>
<!-- // 네이버아이디로로그인 초기화 Script -->
</body>
</html>

