package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
//		if(session!=null) {
			session.invalidate();
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("Main.me");
//		}
		
		// 만약, 자바스크립트를 사용하여 메세지 출력 후 이동할 경우
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();	
		out.println("<script>");	
		out.println("alert('로그아웃 되었습니다.')"); 
		out.println("location.href='./'");	
		out.println("</script>");
		
		return forward;
	}

}
