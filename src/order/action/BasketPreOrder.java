package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.ArrayListToStringService;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;

public class BasketPreOrder implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// mem_num & table_num 가져오기
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		
		// mem_num에 해당하는 장바구니에 담긴 내용 가져오기  
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(mem_num);
		
		// ArrayList를 String으로 바꿔줄 서비스 호출 
		ArrayListToStringService service2 = new ArrayListToStringService();
		String basket_info = service2.toString(basketList);
		
		// mem_num, basket_info, table_num을 전달하기 위해 
		// BasketBean에 저장 
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setBasket_info(basket_info); // 가져온 장바구니(String)
		basket.setTable_num(table_num);
		
		// preorder 테이블에 저장(insert)하기 위한 서비스 호출
		BasketProService service3 = new BasketProService();
		int insertResult = service3.basketPreOrder(basket);
		
		if (insertResult>0) {
			System.out.println("Preorder 성공!");
			
			// 장바구니 항목이 preorder 테이블에 insert 성공했을시 기존 장바구니 초기화 
			// 가져온 정보 전달하기 위해 BasketBean에 저장 
			BasketBean basket2 = new BasketBean();
			basket.setMem_num(mem_num);
			basket.setBasket_info(""); // 초기화 
			basket.setTable_num(table_num);
		
			// 가져온 정보 Basket table에 저장하기 위해 BasketProService 서비스 호출
			BasketProService service4 = new BasketProService();
			
			// 장바구니 담기 위한 메서드 호출
			// 성공 여부 확인을 위한 변수  return 값으로 받아옴 
			int updateCheck = service4.updateBasket(basket);
			
			if (updateCheck>0) {
				System.out.println("장바구니 비우기 성공!");
			} else {
				System.out.println("장바구니 비우기 실패!");
			}
			
			
		} else {
			System.out.println("Preorder 실패!");
		}
		
		
		forward = new ActionForward();
		forward.setPath("/OrderMain.or");
		
		return forward;
	}

}
