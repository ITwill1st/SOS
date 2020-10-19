package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberLoginProService;
import rsv.svc.RsvListCheckProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		String mem_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		System.out.println(request.getParameter("mem_id"));
		System.out.println(request.getParameter("mem_passwd"));
		
		int isLoginSuccess = memberLoginProService.loginMember(mem_id, mem_passwd); 
		
	
		if(isLoginSuccess==1) {
			HttpSession session= request.getSession(); 
			session.setAttribute("mem_id", request.getParameter("mem_id"));
			MemberBean mb = new MemberBean();
			RsvListCheckProService listCheck = new RsvListCheckProService();
			mb = listCheck.getMemberInfo(mem_id);
			
		       
		    // 회원정보 전달용 session에 MemberBean 넣어 보냄 
		    session.setAttribute("memberInfo", mb);
		    String mem_phone = mb.getMem_phone();
		    session.setAttribute("mem_phone", mb.getMem_phone());
		    
		    System.out.println(mem_phone);
			
			forward=new ActionForward();
			forward.setPath("Main.do");
			forward.setRedirect(false);
			
		}else if(isLoginSuccess==0) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('아이디 틀림!')"); 
			out.println("history.back()");
			out.println("</script>");
			
		}else if(isLoginSuccess==-1) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('비밀번호 틀림!')"); 
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		return forward;
	}

}
