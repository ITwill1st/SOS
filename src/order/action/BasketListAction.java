package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import order.svc.StringToArrayListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward= null;

		// id, table_num 받아오기 
		String id = request.getParameter("id");
		String table_num = request.getParameter("table_num");
		
		
		// 현재 장바구니 조회하기위해 BasketProService 호출 
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(id);
		
		
		forward = new ActionForward();
		forward.setPath("/order/basket.jsp");
		
		request.setAttribute("id", id);
		request.setAttribute("table_num", table_num);
		request.setAttribute("basketList", basketList);
		
		return forward;
	}

}
