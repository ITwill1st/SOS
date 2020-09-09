<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
   <script>
      var naverLogin = new naver.LoginWithNaverId(
         {
            clientId: "E3pvtJLoB309rBX1NK7h",
            callbackUrl: "http://localhost:8080/SOS/callback.jsp",
            isPopup: false,
            callbackHandle: true
         }
      );
      naverLogin.init();
      window.addEventListener('load', function () {
         naverLogin.getLoginStatus(function (status) {
            if (status) {
               var email = naverLogin.user.getEmail();
                if( email == undefined || email == null) {
                  alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
                  naverLogin.reprompt();
                  return;
               }
               //alert( email ); // 로그인 한 이메일 ***@naver.com 이 출력된다.
               //window.location.replace("http://127.0.0.1/test2.html");
//             	var nickName = naverLogin.user.getNickName();
				var email = naverLogin.user.getEmail();
				var name = naverLogin.user.getName();
				var birth = naverLogin.user.getBirthday();
				var id = naverLogin.user.getId();
            	var gender = naverLogin.user.getGender();
               	location.href='SnsLogin.me?member_id='+id+"&member_email="+email+"&member_birth="+birth+"&member_name="+name+"&member_gender="+gender;
               	
               	console(id);
               	alert(email);

            } else {
               console.log("callback 처리에 실패하였습니다.");
            }
         });
      });
   </script>
  </body>
</html>