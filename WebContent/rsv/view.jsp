<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rsv/view.jsp</title>
</head>
<body>
<h1>rsv/view.jsp</h1>
<h1>main페이지에서 예약 후 예약한 정보를 뿌려주는 페이지</h1>
<c:if test="${empty sessionScope.id }">
<form action="RsvSubmitProAction.rsv" method="post">
예약날짜 : <input type="date" name="rsv_date" value="${param.date }"><br>
예약인원 : <input type="text" name="pax" value="${param.pax }"><br>
예약시간 : <input type="time" name="rsv_time" value="${param.time }"><br>
phone : <input type="tel" name="phone"><br>
email : <input type="email" name="email"><br>
<input type="submit" value="예약접수">
</form>
</c:if>
<c:if test="${not empty sessionScope.id }">
<form action="RsvSubmitProAction.rsv" method="post">
예약날짜 : <input type="date" name="rsv_date" value="${param.date }>"><br>
예약인원 : <input type="text" name="pax" value="${param.pax }"><br>
예약시간 : <input type="time" name="rsv_time" value="${param.time }"><br>
<input type="submit" value="예약접수">
</form>
</c:if>
</body>
</html>