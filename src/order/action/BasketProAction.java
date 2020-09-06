package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.ArrayListToStringService;
import order.svc.BasketProService;
import order.svc.StringToArrayListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;



public class BasketProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		

		// 장바구니에 담을 정보 가져오기 
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		int item_qty = Integer.parseInt(request.getParameter("item_qty"));
		int reviewCheck = 0; 
		
		// basket_info(String type)에 item_num과 item_qty, reviewch 저장
		String basket_info = item_num+","+item_qty+","+reviewCheck;
		
		// mem_num, table_num 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
	

		
		// 기존 DB에 있는 id에 해당하는 basketList 가져오기 위해 BasketProService 호출
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(mem_num);
		
		// 담을 리스트와 담긴 리스트 비교 및 update 하기 위해 BasketProService 호출 
		BasketProService service2 = new BasketProService();
		// 비교하여 최종적으로 담겨질  ArrayList2
		ArrayList<ProductInfoBean> basketList2 = service2.compareBasket(basket_info,basketList);
		
		// ArrayList를 String으로 바꿔줄 서비스 호출 
		ArrayListToStringService service3 = new ArrayListToStringService();
		String basket_info2 = service3.toString(basketList2);

		
		// 가져온 정보 전달하기 위해 BasketBean에 저장 
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setBasket_info(basket_info2); // 기존장바구니와 비교하여 업데이트된 장바구니 정보 
		basket.setTable_num(table_num);
		
		// 가져온 정보 Basket table에 저장하기 위해 BasketProService 서비스 호출
		BasketProService service4 = new BasketProService();
		
		// 장바구니 담기 위한 메서드 호출
		// 성공 여부 확인을 위한 변수  return 값으로 받아옴 
		int updateCheck = service4.updateBasket(basket);
		

		if (updateCheck>0) {
			System.out.println("장바구니 담기 성공!");
		} else {
			System.out.println("장바구니 담기 실패!");
		}
		
		
		forward = new ActionForward();
		forward.setPath("OrderMain.or");
		
		return forward;
	}

}
