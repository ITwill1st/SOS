package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.ArrayListToStringService;
import order.svc.BasketProService;
import order.svc.OrderBasketService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;

public class OrderBasketAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// id 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));

		// id에 해당하는 장바구니 가져오기 
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(mem_num);
		
		// ArrayList를 String으로 바꿔줄 서비스 호출 
		ArrayListToStringService service2 = new ArrayListToStringService();
		String basket_info = service2.toString(basketList);
		
		// 가져온 정보 전달하기 위해 BasketBean에 저장 
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setBasket_info(basket_info); // 가져온 장바구니(String)
		basket.setTable_num(table_num);
		
		// 주문테이블에 저장하기 위한 서비스 호출
		
		OrderBasketService service3 = new OrderBasketService();
		int insertResult = service3.insertOrder(basket);
		
		if (insertResult>0) {
			System.out.println("주문 성공!");
		} else {
			System.out.println("주문 실패!");
		}
		
		
		forward = new ActionForward();
		forward.setPath("/OrderMain.or");
		
		return forward;
	}

}
