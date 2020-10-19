package rsv.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import rsv.svc.RsvListProService;
import vo.ActionForward;
import vo.RsvDTO;

public class RsvListProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session= request.getSession(); 
		String mem_email = (String) session.getAttribute("mem_email");
		
		RsvListProService rsvListProService = new RsvListProService();
		ArrayList<RsvDTO> rsvList = rsvListProService.RsvList(mem_email);
		
		request.setAttribute("rsvList", rsvList);
		
		forward = new ActionForward();
		forward.setPath("/member/myRsvList.jsp");
		return forward;
	}

}
