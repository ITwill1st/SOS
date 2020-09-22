<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#joinFormArea {
		margin: auto;
		width: 400px;
		height: 560px;
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
		width: 350px;
		margin: auto;
	}
	tr{
		width: 200px;
	}
</style>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script><script type="text/javascript">

jQuery(document).ready(function () {
	$('#btn').click(function() {
		var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/;
		var mem_id=$('#mem_id').val();
		if(regex.exec(mem_id)) { 
			//중복 체크 실행
			$.ajax('dupCheck.me',{
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
				element.innerHTML = "적합한 패스워드";
			} else { // 유효성 검사를 통과하지 못했을 경우
// 			alert('유효성 검사 탈락');
				element.innerHTML = "적합하지 않은 패스워드";
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
	<section id="joinFormArea">
		<h1>회원가입</h1>
		<form action="MemberJoinPro.me" id="join" method="post" name="joinForm">
			<fieldset>
				<table>
				<tr>
					<td></td>
				</tr>
					<tr>
						<td>이름</td>
						<td>
							<input type="text" name="mem_name" id="mem_name">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
					<tr>
						<td>닉네임</td>
						<td>
							<input type="text" name="mem_nickname" id="mem_nickname">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
						<td>아이디</td>
						<td>
							<input type="text" name="mem_id" id="mem_id"
								placeholder="4~12자리 영문,숫자 조합">
						</td>
						<td><input type="button" value="중복체크" id="btn">	</td>
					</tr>
					<tr height="35px">
						<td colspan="2"><span id="checkIdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
						<td>패스워드</td>
						<td>	
							<input type="password" name="mem_passwd" id="mem_passwd"
								placeholder="8~16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)">
						</td>
					</tr>
					<tr height="35px">
						<td colspan="2"><span id="checkPasswdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
						<td></td>
					</tr>
					<tr>
						<td>패스워드 재확인</td>
						<td>
							<input type="password" name="mem_passwd2"  
								placeholder="비번 재확인">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>E-Mail</td>
						<td><input type="email" name="mem_email" id="mem_email"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<input type="radio" name="mem_gender" value="true" checked="checked">남자
							<input type="radio" name="mem_gender" value="false">여자
						</td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="mem_phone" id="mem_phone" onKeyup="inputPhoneNumber(this)"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="text" name="mem_birth" id="mem_birth"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="가입" />
							<input type="button" value="취소" onclick="history.back()" />
						</td>
					</tr>
					
				</table>
			</fieldset>
		</form>
	</section>
</body>
</html>