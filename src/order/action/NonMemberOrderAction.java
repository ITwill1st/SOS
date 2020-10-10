package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberLoginProService;
import vo.ActionForward;

public class NonMemberOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("NonMemberOrderAction");
		
		// test.jps에서 table_num 받아오기
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		ActionForward forward = null;
		
		int mem_num = -1;
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		mem_num = memberLoginProService.NonLogin();
		
		if(mem_num != -1) {
			HttpSession session= request.getSession(); 
			session.setAttribute("mem_num", mem_num);
			session.setAttribute("table_num", table_num);
			
			forward=new ActionForward();
			forward.setPath("OrderMain.or");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}

}
