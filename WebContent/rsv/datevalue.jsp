<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<% 
JSONArray dateList=(JSONArray)request.getAttribute("date");
%> 
<%=dateList%> 