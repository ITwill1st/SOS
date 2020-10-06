<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/myInfo.jsp</title>
</head>
<body>
<%
String id=(String)session.getAttribute("id");
MemberBean mb = (MemberBean)session.getAttribute("memberInfo");
%>
<fieldset>
<legend>회원정보</legend>
<h3>닉네임 : <%=mb.getMem_nickname() %><br></h3>
<h3>아이디 : <%=mb.getMem_id() %><br></h3>
<h3>비밀번호 :<%=mb.getMem_passwd() %><br></h3>
<h3>이름 :<%=mb.getMem_name() %><br></h3>
<h3>가입날짜:<%=mb.getMem_regdate() %><br></h3>
<h3>이메일:<%=mb.getMem_email() %><br></h3>
<h3>성별:<%=mb.getMem_gender() %><br></h3>	
<h3>전화번호:<%=mb.getMem_phone()%><br></h3>
<h3>생일:<%=mb.getMem_birth() %><br></h3>
</fieldset>
<br>
<br>
<br>
<br><br><br><br>
<c:if test="${sessionScope.id eq 'admin' }">
		<c:forEach var="dto" items="${rsv_All}">
			<table border="1">
				<tr>
					<td>예약 날짜</td><td>${dto.rsv_date }</td>
				</tr>
				<tr>
					<td>예약 시간</td><td>${dto.rsv_time }</td>
				</tr>
				<tr>
					<td>예약 인원</td><td>${dto.rsv_pax }</td>
				</tr>
				<tr>
					<td>예약 번호</td><td>${dto.rsv_num }</td>
				</tr>
				<tr>
					<td>예약 메일</td><td>${dto.mem_email }</td>
				</tr>
				<tr>
					<td><c:if test="${dto.rsv_check eq 1 }">
	${예약 완료 }
	</c:if> <c:if test="${dto.rsv_check eq 0 }">
	${ 예약 대기}</c:if> <c:if test="${dto.rsv_check eq -1 }">
	${ 예약 취소}</c:if></td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	<c:if test="${sessionScope.id != 'admin' }">
	<table border="1">
		<tr>
			<td>예약 날짜</td><td>${rsv_list.rsv_date }</td>
		</tr>
		<tr>
			<td>예약 시간</td><td>${rsv_list.rsv_time }</td>
		</tr>
		<tr>
			<td>예약 인원</td><td>${rsv_list.rsv_pax }</td>
		</tr>
		<tr>
			<td>예약 번호</td><td>${rsv_list.rsv_num }</td>
		</tr>
	</table>
	</c:if>
</body>
</html>
