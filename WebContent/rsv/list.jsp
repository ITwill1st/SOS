<%@page import="java.util.ArrayList"%>
<%@page import="vo.RsvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
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
	<form action="RsvDelete.rsv">
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
			<td>예약 번호</td><td><input type="text" name="rsv_num" value="${rsv_list.rsv_num }"></td>
		</tr>
	</table>
	<input type="submit" value="예약취소">
	</c:if>
	</form>
	
</body>
</html>