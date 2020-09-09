<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 800px;
		border: 1px solid darkgray;
	}
	
	a {
		text-decoration: none;
	}

	#tr_top {
		background: orange;
		width: 800px; 
		text-align: center;
	}
	
	#writeButton {
		margin: auto;
		width: 800px;
		text-align: right;
	}
	
	#pageList {
		margin: auto;
		width: 800px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 800px;
		text-align: center;
	}
</style>
<%
	ArrayList<MemberBean> list = (ArrayList<MemberBean>)request.getAttribute("memberList");
	
%>

</head>
<body>
	<!-- 회원 목록 -->
	<section id="listForm">
		<h2>회원 목록</h2>
		<table>
			<tr id="tr_top">
					<td width="150">
					<a href="MemberList.me?orderTarget=member_idx&orderType=asc">▲</a>
					번호
					<a href="MemberList.me?orderTarget=member_idx&orderType=desc">▼</a>
				</td>
				<td width="150">
					<a href="MemberList.me?orderTarget=member_name&orderType=asc">▲</a>
					이름
					<a href="MemberList.me?orderTarget=member_name&orderType=desc">▼</a>
				<td width="150">아이디</td>
				<td width="150">패스워드</td>
				<td width="250">이메일</td>
				<td width="150">성별</td>
				<td width="250">전화번호</td>
				<td width="150">가입일</td>
			</tr>
			<!-- ArrayList<MemberBean> 객체가 null 이 아닐 때 반복문으로 회원 목록 출력 -->
			<%
			if(list != null) {
				for(MemberBean member : list) {
			%>
				<tr>
					<td><%=member.getMember_num() %></td>
					<td><%=member.getMember_name() %></td>
					<td><%=member.getMember_id() %></td>
					<td><%=member.getMember_passwd() %></td>
					<td><%=member.getMember_email() %></td>
					<td><%=member.getMember_gender() %></td>
					<td><%=member.getMember_phone() %></td>
					<td><%=member.getMember_regDate() %></td>
				</tr>		
			<%	
			}
			%>
			
			<%} else { %>
				<tr><td colspan="6">회원 목록이 없습니다.</td></tr>
			<%} %>	
		</table>		
	</section>
</body>
</html>


















