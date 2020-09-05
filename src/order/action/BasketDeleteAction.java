package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.ArrayListToStringService;
import order.svc.BasketDeleteService;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		
		System.out.println("BasketDeleteAction");
		
		// id, item_num, table_num 가져오기
		String id = request.getParameter("id");
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		int item_num = Integer.parseInt(request.getParameter("item_num"));
				
		// 현재 id에 해당하는 장바구니 가져오기 
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(id);
		
		// 장바구니 항목 삭제를 위한 서비스 호출 
		BasketDeleteService service2 = new BasketDeleteService();
		// 선택한 항목 삭제하고 남은 장바구니 리스트  
		ArrayList<ProductInfoBean> basketList2 = service2.BasketDelete(item_num,basketList);
		
		// ArrayList를 String으로 바꿔줄 서비스 호출 
		ArrayListToStringService service3 = new ArrayListToStringService();
		String basket_info2 = service3.toString(basketList2);
		
		// 가져온 정보 전달하기 위해 BasketBean에 저장 
		BasketBean basket = new BasketBean();
		basket.setMember_id(id);
		basket.setBasket_info(basket_info2); // 항목이 삭제된 후 업데이트된 장바구니 정보 
		basket.setTable_num(table_num);
		
		
		// 가져온 정보 Basket table에 저장하기 위해 BasketProService 서비스 호출
		BasketProService service4 = new BasketProService();
		
		// 장바구니 업데이트하기 위한 메서드 호출 
		// 성공 여부 확인을 위한 변수  return 값으로 받아옴 
		int updateCheck = service4.updateBasket(basket);
		

		if (updateCheck>0) {
			System.out.println(item_num + "삭제 성공");
		} else {
			System.out.println(item_num + "삭제 실패!");
		}
		
		
		forward = new ActionForward();
		forward.setPath("/BasketList.or");
		
		return forward;
	}

}