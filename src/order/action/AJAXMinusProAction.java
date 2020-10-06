package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;

public class AJAXMinusProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 장바구니 List에서 수량 -1 했을 경우
		ActionForward forward = null;
		
		// 해당 item_num, mem_num, table_num 전달 받음 
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		// 한번에 전달하기 위해 BasketBean 객체 생성하여 저장
		BasketBean basket = new BasketBean();
		basket.setItem_num(item_num);
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		// 수량 -1 하기 위해 서비스 호출 
		BasketProService bps = new BasketProService();
		int insertResult = bps.insertQtyMinus(basket);
	
		// Consol창에 성공여부 출력 
		if(insertResult>0) {
			System.out.println("AJAXMinusProAction - 장바구니 수량 -1 성공");
		} else {
			System.out.println("AJAXMinusProAction - 장바구니 수량 -1 실패");
		}
		
		return forward;
	}

}
