package rsv.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.svc.RsvAllListProService;
import vo.ActionForward;
import vo.RsvDTO;

public class RsvAllListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		RsvAllListProService rsvAllListProService = new RsvAllListProService();
		
		ArrayList<RsvDTO> rsvList = rsvAllListProService.AllRsvList();
		
		request.setAttribute("rsvList", rsvList);
		
		forward = new ActionForward();
		forward.setPath("/rsv/AllRsvList.jsp");
		return forward;
		
	}

}
