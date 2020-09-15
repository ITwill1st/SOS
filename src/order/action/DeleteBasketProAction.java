package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;

public class DeleteBasketProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		// mem_num, table_num, item_num 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
	
		// BasketBean에 전달할 정보 저장하기 위해 BasketBean 객체 생성
		BasketBean basket = new BasketBean();
		basket.setItem_num(item_num);
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		// 담긴 정보에 해당하는 basket 항목 삭제하기 위한 서비스 호출
		BasketProService bps = new BasketProService();
		int deleteResult = bps.deleteBasket(basket);
		
		if(deleteResult>0) {
			System.out.println("장바구니 삭제 성공!");
		} else {
			System.out.println("장바구니 삭제  실패!");
		}
		
		forward = new ActionForward();
		forward.setPath("BasketList.or");
		
		return forward;
		
	}

}
