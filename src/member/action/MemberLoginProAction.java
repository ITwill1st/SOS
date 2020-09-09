package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.dao.MemberDAO;
import member.svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");
		System.out.println(request.getParameter("member_id"));
		System.out.println(request.getParameter("member_passwd"));
		
		int isLoginSuccess = memberLoginProService.loginMember(member_id, member_passwd); 
		
//		if(isLoginSuccess == 0 || isLoginSuccess == -1) {
//			String resultStr = "";
//			if(isLoginSuccess == 0) {
//				resultStr = "패스워드 틀림";
//			}else if (isLoginSuccess == -1) {
//				resultStr = "아이디 틀림";
//			}
//			
//			response.setContentType("text/html;charset=UTF-8"); 
//			PrintWriter out = response.getWriter();	
//			out.println("<script>");	
//			out.println("alert('"+resultStr+"')"); 
//			out.println("history.back()");	
//			out.println("</script>");
//		}else {
//			HttpSession session= request.getSession(); 
////			session.setAttribute("id", request.getParameter("id"));
//			session.setAttribute("email", request.getParameter("email"));
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("Main.me");
////			forward.setPath("./");  // 현재 프로젝트의 최상위 위치로 이동(index.jsp)
//			}
	
		if(isLoginSuccess==1) {
			HttpSession session= request.getSession(); 
			session.setAttribute("member_id", request.getParameter("member_id"));
//			request.setAttribute("id", id);
			MemberDAO dao = MemberDAO.getInstance();
			MemberBean memberBean = new MemberBean();
		    memberBean = dao.getUserInfo(member_id);
		       
		    // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
		    session.setAttribute("memberInfo", memberBean);
		    String member_phone = memberBean.getMember_phone();
		    session.setAttribute("member_phone", memberBean.getMember_phone());
		    System.out.println(member_phone);
		    
			
			forward=new ActionForward();
			forward.setPath("Main.me");
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
