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
//cal.jsp에서 넘겨준 year와 month 파라미터를 받아온다.
String year=request.getParameter("year");
String month=request.getParameter("month");

//JSON 작업을 위해 DB를 connection 시킨다.
Connection con=JdbcUtil.getConnection();
RsvDAO dao=RsvDAO.getInstance();
dao.setConnection(con);

PreparedStatement pstmt=null;
ResultSet rs=null;

//받아온 파라미터 year,month를 sql 구문에 대입하여 해당하는 년도와 월의 데이터를 뽑아온다.
String sql="select rsv_date from reservation where rsv_date like '?'";
pstmt=con.prepareStatement(sql);
//year와 month를 결합하여 sql 구문에 적합한 형태로 넣어준다.
pstmt.setString(1, year+"-"+month+"-%");
rs=pstmt.executeQuery();

//값을 저장해서 넘겨주기위한 배열을 선언한다.
JSONArray dateList=new JSONArray();
while(rs.next()){
	JSONObject jo=new JSONObject();
	//조회된 데이터를 Object로 저장한다.
	jo.put("date", rs.getString("rsv_date"));
	
	//저정한 Object 를 배열에 저장한다.
	dateList.add(jo);
}

%>

<%=dateList%>

</body>
</html>