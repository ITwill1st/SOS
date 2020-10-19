package rsv.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.svc.RsvInsertProService;
import vo.ActionForward;
import vo.RsvDTO;

public class RsvInsertProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		RsvDTO rsvDTO = new RsvDTO();
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		int pax = Integer.parseInt(request.getParameter("pax"));
		String mem_email = request.getParameter("mem_email");
		rsvDTO.setMem_email(mem_email);
		rsvDTO.setRsv_date(date);
		rsvDTO.setRsv_time(time);
		rsvDTO.setRsv_pax(pax);
		
		RsvInsertProService rsvInsertProService = new RsvInsertProService();
		rsvInsertProService.RsvInsert(rsvDTO);
		
		
		forward = new ActionForward();
		forward.setPath("Main.do");
		return forward;
	}

}
