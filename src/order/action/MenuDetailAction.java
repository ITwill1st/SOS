package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.MenuDetailService;
import vo.ActionForward;
import vo.ProductBean;

public class MenuDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;

		// mem_num, table_num
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		// 클릭한 item_num 
		int item_num = Integer.parseInt(request.getParameter("item_num"));

		
		// 선택한 Menu에 대한 상세정보를 가져오기 위한  MenuDetailService 호출
		MenuDetailService service = new MenuDetailService();
		ProductBean menu  = service.selectDetail(item_num);

		forward = new ActionForward();
		forward.setPath("/order/detail.jsp");
		
		// 조회해온 menu 상세정보 담아가기 
		request.setAttribute("menu", menu);
		request.setAttribute("mem_num",mem_num);
		request.setAttribute("table_num", table_num);
		
		return forward;
	}

}
