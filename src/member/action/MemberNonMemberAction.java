package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberLoginProService;
import vo.ActionForward;

public class MemberNonMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		System.out.println("NonLoginProAction");
		int mem_num = -1;
//		int NonLoginSuccess = memberLoginProService.NonLogin();
		mem_num = memberLoginProService.NonLogin();
		
		if(mem_num != -1) {
			HttpSession session= request.getSession(); 
			session.setAttribute("mem_num", mem_num);
			
			forward=new ActionForward();
			forward.setPath("Main.do");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
