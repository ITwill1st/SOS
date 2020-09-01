<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="rsv.dao.RsvDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="db.JdbcUtil"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	
JSONArray dateList=(JSONArray)request.getAttribute("date");

%>

	<%=dateList%>

</body>
</html>