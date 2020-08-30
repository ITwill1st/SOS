<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rsv/view.jsp</title>
</head>
<body>
<h1>rsv/view.jsp</h1>
<h1>main페이지에서 예약 후 예약한 정보를 뿌려주는 페이지</h1>
<%
String rsv_date=request.getParameter("date");
int pax=Integer.parseInt(request.getParameter("pax"));
String rsv_time=request.getParameter("time");
String id=(String)session.getAttribute("id");
String ip=request.getRemoteAddr();

if(id==null){

%>
<form action="RsvSubmitProAction.rsv" method="post">
예약날짜 : <input type="date" name="rsv_date" value="<%=rsv_date %>"><br>
예약인원 : <input type="text" name="pax" value="<%=pax%>"><br>
예약시간 : <input type="time" name="rsv_time" value="<%=rsv_time %>"><br>
phone : <input type="tel" name="phone"><br>
email : <input type="email" name="email"><br>
알러지 : <input type="text" name="allergy"><br>
<input type="submit" value="예약접수">
</form>
<%}
else{%>
<form action="RsvSubmitProAction.rsv" method="post">
예약날짜 : <input type="date" name="rsv_date" value="<%=rsv_date %>"><br>
예약인원 : <input type="text" name="pax" value="<%=pax%>"><br>
예약시간 : <input type="time" name="rsv_time" value="<%=rsv_time %>"><br>
<input type="submit" value="예약접수">
</form>
<%}%>
</body>
</html>