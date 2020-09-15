package order.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;

public class AJAXPlusProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		// mem_num, table_num에 해당하는 장바구니 수량을 바꿔줘야 하므로 조회 
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		BasketBean basket = new BasketBean();
		basket.setItem_num(item_num);
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		// 수량 +1 하기 위해 서비스 호출 
		BasketProService bps = new BasketProService();
		int insertResult = bps.insertQtyPlus(basket);
	
		
		return forward;
	}

}
