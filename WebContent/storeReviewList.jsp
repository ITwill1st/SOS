<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Class.forName("com.mysql.jdbc.Driver");
String dbUrl ="jdbc:mysql://localhost:3306/sos";
String dbId = "root";
String dbPass = "1234";

Connection con = DriverManager.getConnection(dbUrl,dbId,dbPass);

String sql = "select * from review where item_num = 0 limit 0,3";
PreparedStatement pstmt = con.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery();

JSONArray storeReviewList = new JSONArray();

while (rs.next()){
	
	JSONObject srl = new JSONObject();
	
	srl.put("rating", rs.getInt("review_rating"));
	srl.put("comment", rs.getString("review_comment"));
	srl.put("datetime", rs.getTimestamp("review_datetime"));		
	
	storeReviewList.add(srl);
}
%>

<%=storeReviewList%>


