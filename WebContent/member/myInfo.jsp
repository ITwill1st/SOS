<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/myInfo.jsp</title>
</head>
<body>
<%
String id=(String)session.getAttribute("id");
MemberBean mb = (MemberBean)session.getAttribute("memberInfo");
%>
<fieldset>
<legend>회원정보</legend>
<h3>닉네임 : <%=mb.getMem_nickname() %><br></h3>
<h3>아이디 : <%=mb.getMem_id() %><br></h3>
<h3>비밀번호 :<%=mb.getMem_passwd() %><br></h3>
<h3>이름 :<%=mb.getMem_name() %><br></h3>
<h3>가입날짜:<%=mb.getMem_regdate() %><br></h3>
<h3>이메일:<%=mb.getMem_email() %><br></h3>
<h3>성별:<%=mb.getMem_gender() %><br></h3>	
<h3>전화번호:<%=mb.getMem_phone()%><br></h3>
<h3>생일:<%=mb.getMem_birth() %><br></h3>
</fieldset>
<br>
<br>
<br>
<br><br><br><br>
<p>

</p>
</body>
</html>
