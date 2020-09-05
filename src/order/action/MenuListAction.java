package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketCreateService;
import order.svc.BasketProService;
import order.svc.MenuListService;
import sun.swing.BakedArrayList;
import vo.ActionForward;
import vo.BasketBean;
import vo.MenuBean;

public class MenuListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 임시 id값 
		String id = "jo";
		int table_num = 1;
		
		ActionForward forward = null;
		
		
		// 장바구니 조회 및 생성 
		// id에 따른 장바구니 조회하기 위한 basketProService 서비스 호출 
		BasketProService bps = new BasketProService();
		
		// id를 넘겨주고 basket에 담긴 item 수량 받아오기 
		int basketCount = bps.getBasketCount(id);
		
		
		if(basketCount==0) { 
			// basket에 아무것도 안 담겨있을경우 장바구니 생성 
			// 장바구니 생성을 위한 BasketCreateService 서비스 호출
			BasketCreateService bcs = new BasketCreateService();
			
			// 장바구니 생성을 위한 메서드 호출
			// 성공 여부 확인을 위한 변수  return 값으로 받아옴 
			int createResult = bcs.createBasket(id,table_num);
			
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
		ArrayList<MenuBean> menuList = menuListService.getMenuList();
		
		// 초기화 
		forward = new ActionForward();
		
		// main.jsp로 이동 
		forward.setPath("/order/main.jsp");
		
		// menuList(전체메뉴)와  basketCount(장바구니 수량) 전달 
		request.setAttribute("menuList", menuList);
		request.setAttribute("basketCount", basketCount);
		
		return forward;
	}

}
