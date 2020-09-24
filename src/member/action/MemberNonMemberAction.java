package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.MemberLoginProService;
import vo.ActionForward;

public class MemberNonMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		System.out.println("NonLoginProAction");
		
		int NonLoginSuccess = memberLoginProService.NonLogin();
		
		if(NonLoginSuccess == 1) {
			forward=new ActionForward();
			forward.setPath("Main.me");
			forward.setRedirect(false);
		}
		
		
		
		
		return forward;
	}

}
