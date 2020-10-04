package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.OrderListService;
import vo.ActionForward;
import vo.OrderDTO;

public class ReviewWriteFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		
		OrderListService orderListService = new OrderListService();	
		ArrayList<OrderDTO> orderList = orderListService.getOrderList_orderNum(order_num);

		request.setAttribute("orderList", orderList);
		
		forward = new ActionForward();
		forward.setPath("/review/reviewWrite.jsp");
		return forward;
	}

}
