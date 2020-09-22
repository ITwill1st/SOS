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

String sql = "select * from product";
PreparedStatement pstmt = con.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery();

JSONArray productList = new JSONArray();

while (rs.next()){
	
	
	JSONObject pb = new JSONObject();
	
	pb.put("name", rs.getString("item_name"));
	pb.put("num", rs.getString("item_num"));
	pb.put("price", rs.getString("item_price"));
	pb.put("info", rs.getString("item_info"));
	pb.put("img", rs.getString("item_img"));
	
	
	productList.add(pb);
}

%>

<%=productList %>