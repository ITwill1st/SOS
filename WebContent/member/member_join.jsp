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
		height: 550px;
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
	
</style>
<link rel="stylesheet" href="table/style/table.css">
<script src="js/jquery.js"></script><script type="text/javascript">
jQuery(document).ready(function () {
	$('#btn').click(function() {
		var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/;
		var id=$('#member_id').val();
		if(regex.exec(id)) { 
			//중복 체크 실행
			$.ajax('member/idCheck.jsp',{
				data:{id:id},
							success:function(rdata){
								$('#checkIdResult').html(rdata);
							}});
			
		}else{
			alert('아이디 형식이적합하지 않음');
			
		}
			
	})
});
	
// 	function checkPasswd(member_passwd) {
// 		// 8 ~ 16자리 패스워드 영문,숫자,특수문자 조합
// 		// 1. 정규표현식 지정
// 		// 1) 길이 체크 : 8 ~ 16자리. 영문 대문자&소문자&숫자&특수문자(!@#$%^_)
// 		var lengthRegex = /^[A-Za-z0-9!@#$%^_]{8,16}$/;
// 		// 2) 소문자 체크
// 		var lowerCaseRegex = /[a-z]/;
// 		// 3) 숫자 체크
// 		var digitRegex = /[0-9]/;
// 		// 4) 특수문자 체크
// 		var specCharRegex = /[!@#$%^_]/;
		
// 		// 2. 체크 후 메세지 표시할 공간의 태그 id 값 가져오기
// 		var element = document.getElementById('checkPasswdResult'); // checkPasswdResult 값을 ID 로 갖는 태그 찾기
		
//  		// 3. 정규표현식을 통한 유효성 검사 수행(정규표현식 저장 변수명.exec() 를 사용)
//  		// 함수 호출 시 전달받은 파라미터(id) 의 값을 정규표현식으로 검사
// 		// 길이, 대문자, 소문자, 숫자, 특수문자 체크를 모두 통과했을 경우
// 		if(passwd.value!=""){
// 			if(lengthRegex.exec(passwd.value) && lowerCaseRegex.exec(passwd.value) && digitRegex.exec(passwd.value) &&
// 					specCharRegex.exec(passwd.value)) {
// //  			alert('유효성 검사 통과');	
// 			// 지정된 태그 내에 메세지 표시
// 				element.innerHTML = "적합한 패스워드";
// 			} else { // 유효성 검사를 통과하지 못했을 경우
// // 			alert('유효성 검사 탈락');
// 				element.innerHTML = "적합하지 않은 패스워드";
// 			}
// 		}else{
// 				element.innerHTML = "";
// 		}		
// 	}
	
	
 $(document).ready(function() {
		
		$('#join').submit(function() {
	 		if($('#member_name').val()==""){
	 			alert("이름을 입력하세요");
	 			$('#member_name').focus();
	 			return false;
	 		}
	 		if($('#member_id').val()==""){
	 			alert("아이디를 입력하세요");
	 			$('#member_id').focus();
	 			return false;
	 		}
			
			if($('#member_passwd').val()==""){
				alert("비밀번호를 입력하세요");
				$('#member_passwd').focus();
				return false;
	 		}
	 		
	 		if($('#member_email').val()==""){
	 			alert("이메일을 입력하세요");
	 			$('#member_email').focus();
	 			return false;
	 		}
	 		if($('#member_phone').val()==""){
	 			alert("번호를 입력하세요");
	 			$('#member_phone').focus();
	 			return false;
	 		}
	 		if($('#member_birth').val()==""){
	 			alert("생년월일을 입력하세요");
	 			$('#member_birth').focus();
	 			return false;
	 		}
		});
	});
 </script>
</head>
<body>
	<section id="joinFormArea">
		<h1>회원가입</h1>
		<form action="MemberJoinPro.me" id="join" method="get" name="joinForm">
			<fieldset>
				<table>
					<tr>
						<td>이름</td>
						<td>
							<input type="text" name="member_name" id="member_name">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
					</tr>
					<tr>
						<td>아이디</td>
						<td>
							<input type="text" name="member_id" id="member_id"
								placeholder="4~12자리 영문,숫자 조합">
						</td>
						<td><input type="button" value="중복체크" id="btn">	</td>
					</tr>
					<tr height="35px">
						<td><span id="checkIdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
					</tr>
					<tr>
						<td>패스워드</td>
						<td>	
							<input type="password" name="member_passwd" id="member_passwd"
								placeholder="8~16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)">
						</td>
					</tr>
					<tr height="35px">
						<td><span id="checkPasswdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
						<td></td>
					</tr>
					<tr>
						<td>패스워드 재확인</td>
						<td>
							<input type="password" name="member_passwd2"  
								placeholder="비번 재확인">
						</td>
					</tr>
					<tr height="35px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>E-Mail</td>
						<td><input type="email" name="member_email" id="member_email"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<input type="radio" name="member_gender" value="male">남자
							<input type="radio" name="member_gender" value="female">여자
						</td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="member_phone" id="member_phone"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="text" name="member_birth" id="member_birth"></td>
					</tr>
					<tr height="25px">
						<td></td>
						<td></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>알레르기 정보</td>											 -->
<!-- 						<td><input type="checkbox" name="allergy" value="달걀">달걀 -->
<!-- 						<input type="checkbox" name="allergy" value="우유">우유 -->
<!-- 						<input type="checkbox" name="allergy" value="밀">밀 -->
<!-- 						<input type="checkbox" name="allergy" value="견과류">견과류 -->
<!-- 						<input type="checkbox" name="allergy" value="갑각류">갑각류 -->
<!-- 						<input type="checkbox" name="allergy" value="오이">오이</td> -->
<!-- 					</tr> -->
<!-- 					<tr height="25px"> -->
<!-- 						<td></td> -->
<!-- 						<td></td> -->
<!-- 					</tr> -->
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