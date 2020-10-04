<%@page import="review.svc.Re_ReviewListService"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Re_ReviewListService re_ReviewListService = new Re_ReviewListService();
JSONArray reviewList = re_ReviewListService.getJSONRe_reviewList();
%>
<%=reviewList %>
