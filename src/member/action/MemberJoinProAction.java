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
//		String name = request.getParameter("name");
		String member_id = request.getParameter("member_id");
//		String passwd = request.getParameter("passwd");
//		String email = request.getParameter("email");
		
		MemberBean memberBean = new MemberBean();
//		memberBean.setName(request.getParameter("name"));
		memberBean.setMember_name(request.getParameter("member_name"));
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_passwd(request.getParameter("member_passwd"));
		memberBean.setMember_email(request.getParameter("member_email"));
		memberBean.setMember_gender(request.getParameter("member_gender"));
		memberBean.setMember_phone(request.getParameter("member_phone"));
		memberBean.setMember_birth(request.getParameter("member_birth"));
//		memberBean.setAllergy(request.getParameterValues("allergy"));
//		System.out.println(name+", "+id+", "+passwd+", "+email);
		
		// MemberJoinProService 클래스의 DupCheckmember() 메서드를 호출하여
		// 회원 가입 전 중복 여부 확인 요청 작업 수행
		// => 파라미터 : MemberBean, 리턴타입 : int
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		int check = memberJoinProService.dupCheckMember(member_id);
		
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
			boolean isJoinSuccess = memberJoinProService.joinMember(memberBean);
			if(!isJoinSuccess) {
				response.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out = response.getWriter();	
				out.println("<script>");	
				out.println("alert('회원가입 실패!')"); 
				out.println("history.back()");	
				out.println("</script>");
			}else {
				HttpSession session= request.getSession(); 
				session.setAttribute("member_email", request.getParameter("member_email"));
				session.setAttribute("member_name", request.getParameter("member_name"));
				session.setAttribute("member_id", request.getParameter("member_id"));
				session.setAttribute("member_num", request.getParameter("member_num"));
				session.setAttribute("member_passwd", request.getParameter("member_passwd"));
				session.setAttribute("member_phone", request.getParameter("member_phone"));
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("Main.me");
			}
		}
		
		return forward;
	}

}
