<%@page import="java.util.ArrayList"%>
<%@page import="vo.RsvDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String id=(String)session.getAttribute("mem_id");
if(id.equals("admin")){//id 가 관리자 일 경우 전체 리스트
	ArrayList<RsvDTO> allList=(ArrayList)request.getAttribute("rsv_All");
for(RsvDTO dto : allList){%>
	<table>
	<tr><td><%=dto.getRsv_date() %></td></tr>
	<tr><td><%=dto.getRsv_time() %></td></tr>
	<tr><td><%=dto.getRsv_pax() %></td></tr>
	<tr><td><%=dto.getRsv_num() %></td></tr>
	<tr><td><%=dto.getMem_email() %></td></tr>
	<tr><td><%if(dto.getRsv_check()==1){// 1=예약완료
		%><%="예약완료"%><%
	}else if(dto.getRsv_check()==0){//0=예약대기
		%><%="예약대기"%><%
	}else{//-1=예약취소
		%><%="예약취소"%><%
	}
	%>
	</td></tr>
	</table>
<%	
}

}else if(!id.equals("admin")){//id가 관리자가 아닐 경우 본인의 예약정보만 출력

	RsvDTO dto=(RsvDTO)request.getAttribute("rsv_list"); 
%>
<table>
<tr><td><%=dto.getRsv_date() %></td></tr>
<tr><td><%=dto.getRsv_time() %></td></tr>
<tr><td><%=dto.getRsv_pax() %></td></tr>
<tr><td><%=dto.getRsv_num() %></td></tr>
</table>
<%}%>
</body>
</html>