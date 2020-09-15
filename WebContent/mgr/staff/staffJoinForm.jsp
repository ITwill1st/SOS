<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 가입 화면</title>
</head>
<body>
	<section id="joinFormArea">
		<h1>회원가입</h1>
		<form action="StaffJoinPro.stf" id="join" method="post" name="joinForm">
			<fieldset>
				<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="emp_name" id="emp_name"></td>
				</tr>
				<tr height="35px">
					<td></td>
				</tr>
				<tr height="35px">
					<td></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="emp_id" id="emp_id" placeholder="4~12자리 영문,숫자 조합"></td>
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
					<td><input type="password" name="emp_passwd" id="emp_passwd" placeholder="8~16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)">
					</td>
				</tr>
				<tr height="35px">
					<td colspan="2"><span id="checkPasswdResult"><!-- 자바스크립스에 의해 메세지 출력할 공간 --></span></td>
					<td></td>
				</tr>
				<tr>
					<td>패스워드 재확인</td>
					<td><input type="password" name="emp_passwd2" placeholder="비번 재확인"></td>
				</tr>
				<tr height="35px">
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>E-Mail</td> <!-- 필요한지 검토 필요 -->
					<td><input type="email" name="emp_email" id="emp_email"></td>
				</tr>
				<tr height="25px">
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="emp_contact" id="emp_contact" onKeyup="inputPhoneNumber(this)"></td>
				</tr>
				<tr height="25px">
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="emp_birth" id="emp_birth"></td>
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