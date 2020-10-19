package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberJoinProService;
import member.svc.MemberSnsLoginProService;
import rsv.svc.RsvListCheckProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberSnsLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberSnsLoginProAction");
		ActionForward forward=null;
		boolean isSnsLoginsuccess=false;
		
		String mem_id=request.getParameter("mem_id");
		int mem_gender = 0;
		if(request.getParameter("mem_gender").equals("male") ||request.getParameter("mem_gender").equals("M")) {
			mem_gender = 1;
		}else {
			mem_gender = 0;
		}
		
		MemberBean mb = new MemberBean();
		
		MemberJoinProService memberJoinProService = new MemberJoinProService();
	    int check = memberJoinProService.dupCheckMember(mem_id);
	    
	    // SNS로그인 하였을 때 아이디 중복확인
	    if(check == 1 ) {
	    	HttpSession session= request.getSession(); 
			session.setAttribute("mem_id", request.getParameter("mem_id"));
			session.setAttribute("mem_email", request.getParameter("mem_email"));
	    	mb = new MemberBean();
	    	RsvListCheckProService listCheck = new RsvListCheckProService();
			mb = listCheck.getMemberInfo(mem_id);
			forward=new ActionForward();
			forward.setPath("/index.jsp");
			forward.setRedirect(false);
			
		// SNS로그인 시 DB에 ID가 없을 때 memberBean 객체에 저장
	    }else {         
	    	mb.setMem_name(request.getParameter("mem_name"));
	    	mb.setMem_id(request.getParameter("mem_id"));
	    	mb.setMem_email(request.getParameter("mem_email"));
	    	mb.setMem_birth(request.getParameter("mem_birth"));
	    	mb.setMem_gender(mem_gender);
	    	mb.setMem_nickname(request.getParameter("mem_nickname"));
	    	MemberSnsLoginProService SnsProService=new MemberSnsLoginProService();
	    	isSnsLoginsuccess=SnsProService.snsLogin(mb);
	    	System.out.println("isSnsLogin 성공!"+isSnsLoginsuccess);
	    	
	    	// mb 객체에 넣은 값들을 받아 오는 것
	    	if(isSnsLoginsuccess) {
				HttpSession session= request.getSession(); 
				session.setAttribute("mem_id", request.getParameter("mem_id"));
				session.setAttribute("mem_email", request.getParameter("mem_email"));
			    RsvListCheckProService listCheck = new RsvListCheckProService();
			    mb = listCheck.getMemberInfo(mem_id);
			    response.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); 
				out.println("alert('로그인 성공!')"); 
				out.println("</script>");
				
			    // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
			    session.setAttribute("memberInfo", mb);
				
				forward=new ActionForward();
				forward.setPath("/index.jsp");
				forward.setRedirect(false);
			}else {
				response.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); 
				out.println("alert('로그인 실패!')"); 
				out.println("history.back()");
				out.println("</script>");
			}
	    }
		
		
		
		return forward;
	}

}
