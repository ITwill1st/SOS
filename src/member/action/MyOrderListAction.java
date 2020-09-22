package member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.MyOrderListService;
import vo.ActionForward;
import vo.OrderDTO;

public class MyOrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
//		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int mem_num = 1;
		
		MyOrderListService myOrderListService = new MyOrderListService();
		ArrayList<OrderDTO> orderList = myOrderListService.getOrderList(mem_num);
		
		request.setAttribute("orderList", orderList);
		
		forward = new ActionForward();
		forward.setPath("/member/myOrderList.jsp?mem_num=1");
		return forward;
	}

}
