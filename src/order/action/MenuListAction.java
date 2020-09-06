package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketCreateService;
import order.svc.BasketProService;
import order.svc.MenuListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductBean;

public class MenuListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// mem_num
		int mem_num=1;
		// 임시 table_num 전달 
		int table_num =1;

		ActionForward forward = null;
		
		// 장바구니 조회 및 생성 
		// mem_num에 따른 장바구니 조회하기 위한 basketProService 서비스 호출 
		BasketProService bps = new BasketProService();
		
		// id를 넘겨주고 basket에 담긴 item 수량 받아오기 
		int basketCount = bps.getBasketCount(mem_num);
		
		
		if(basketCount == 0) { 
			// basket에 아무것도 안 담겨있을경우 장바구니 생성 
			// 장바구니 생성을 위한 BasketCreateService 서비스 호출
			BasketCreateService bcs = new BasketCreateService();
			
			// 새로운 장바구니에 담길 변수 
			BasketBean basket= new BasketBean();
			basket.setMem_num(mem_num);
			basket.setBasket_info("");
			basket.setTable_num(table_num);
			
			// 장바구니 생성을 위한 메서드 호출
			// 성공 여부 확인을 위한 변수  return 값으로 받아옴 
			int createResult = bcs.createBasket(basket);
			
			if (createResult>0) {
				System.out.println("장바구니 생성 완료!");
			} else {
				System.out.println("장바구니 생성 실패!");
			}
			
		}
		
		
		// 전체 메뉴 조회 
		// 전체 메뉴 조회를 위한 MenuListService 서비스 호출 
		MenuListService menuListService = new MenuListService();
		
		// MenuListService의 getMenuList(); 메서드 호출
		// ArrayList<MenuBean> menuList에 담기 
		ArrayList<ProductBean> menuList = menuListService.getMenuList();
		
		// 초기화 
		forward = new ActionForward();
		
		// main.jsp로 이동 
		forward.setPath("/order/main.jsp");
		
		// menuList(전체메뉴)와  basketCount(장바구니 수량) 전달 
		request.setAttribute("menuList", menuList);
		request.setAttribute("basketCount", basketCount);
		request.setAttribute("table_num", table_num);
		
		return forward;
	}

}
