package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("memberJoinPro.me");
		String mem_id = request.getParameter("mem_id");
		
		
		MemberBean mb = new MemberBean();
		mb.setMem_nickname(request.getParameter("mem_nickname"));
		mb.setMem_name(request.getParameter("mem_name"));
		mb.setMem_id(request.getParameter("mem_id"));
		mb.setMem_passwd(request.getParameter("mem_passwd"));
		mb.setMem_email(request.getParameter("mem_email"));
		mb.setMem_gender(Integer.parseInt(request.getParameter("mem_gender")));
		mb.setMem_phone(request.getParameter("mem_phone"));
		mb.setMem_birth(request.getParameter("mem_birth"));
		
		
		// MemberJoinProService 클래스의 DupCheckmember() 메서드를 호출하여
		// 회원 가입 전 중복 여부 확인 요청 작업 수행
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		int check = memberJoinProService.dupCheckMember(mem_id);
		
		// 중복 체크 결과가 0이면 '아이디 중복' , -1 이면 " 이메일 중복" 메세지를 
		// 문자열에(resultStr)에 저장 후 자바스크립트를 사용하여
		// 메세지 출력 후 이전페이지로 이동
		// 중복체크 결과가 1이면 회원 가입 처리 요청 작업 수행
		
		if(check == 1 ) {
			String resultStr = "";
			
			resultStr = "아이디 중복";
			
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();	
			out.println("<script>");	
			out.println("alert('"+resultStr+"')"); 
			out.println("history.back()");	
			out.println("</script>");
		}else {			
			boolean isJoinSuccess = memberJoinProService.joinMember(mb);
			if(!isJoinSuccess) {
				response.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out = response.getWriter();	
				out.println("<script>");	
				out.println("alert('회원가입 실패!')"); 
				out.println("history.back()");	
				out.println("</script>");
			}else {
				HttpSession session= request.getSession(); 
				session.setAttribute("mem_email", request.getParameter("mem_email"));
				session.setAttribute("mem_name", request.getParameter("mem_name"));
				session.setAttribute("mem_id", request.getParameter("mem_id"));
				session.setAttribute("mem_num", request.getParameter("mem_num"));
				session.setAttribute("mem_passwd", request.getParameter("mem_passwd"));
				session.setAttribute("mem_phone", request.getParameter("mem_phone"));
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("Main.me");
			}
		}
		
		return forward;
	}

}
