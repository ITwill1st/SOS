<%@page import="vo.MemberBean"%>
<%@page import="member.svc.MemberJoinProService"%>
<%@page import="member.action.MemberLoginProAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String mem_id=request.getParameter("mem_id");
request.setCharacterEncoding("UTF-8");
int check=0;
MemberJoinProService memberJoinProService=new MemberJoinProService();
check= memberJoinProService.dupCheckMember(mem_id);
if(check==1){
	out.print("아이디 사용불가");
}else if(check==0){
	out.print("아이디 사용가능");
}
%>