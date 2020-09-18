package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import order.svc.MainPageProService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductBean;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 장바구니 리스트 확인 
		ActionForward forward= null;

		// mem_num, table_num 받아오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));

		// 현재 장바구니 조회하기위해 BasketProService 호출 
		BasketProService bps = new BasketProService();
		ArrayList<BasketBean> basketList = bps.getBasketList(mem_num,table_num);
		
		// (장바구니에 담긴)상품에 대한 정보를 가져오기  위한 MenuListService 서비스 호출 
		MainPageProService mls = new MainPageProService();
		ArrayList<ProductBean> menuList = mls.getMenuList();
		

		forward = new ActionForward();
		forward.setPath("/order/basket.jsp");
		
		request.setAttribute("mem_num",mem_num);
		request.setAttribute("table_num", table_num);
		request.setAttribute("basketList", basketList);
		request.setAttribute("menuList", menuList);

		return forward;
	}

}
