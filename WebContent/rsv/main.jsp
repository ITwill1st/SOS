<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rsv/main.jsp</title>
</head>
<body>
<h1>rsv/main.jsp</h1>
<h1>예약메인페이지</h1>
<h2>예약날짜, 예약시간, 인원수 등을 표시하는 페이지</h2>
<form action="RsvView.rsv" method="post">
예약 날짜 : <input type="date" name="date"><br>
예약 인원 : <input type="text" name="pax" min="1" maxlength="6"><br>
예약 시간 : <input type="time" name="time"><br>
<input type="submit" value="예약접수">
</form>
</body>
</html>