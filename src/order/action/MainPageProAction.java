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

public class MainPageProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 임시 mem_num
		int mem_num=1;
		// 임시 table_num 전달 
		int table_num =1;

		ActionForward forward = null;
				
		// 메인화면 진입과 동시에 장바구니 조회
		// mem_num, table_num에 따른 장바구니 조회하기 위한 basketProService 서비스 호출 
		BasketProService bps = new BasketProService();
				
		// 서비스에 전달할 mem_num과 table_num을 basketBean에 담기 위해 객체 생성
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		// mem_num과 table_num에 해당하는 장바구니 수량 받아오는 메서드 호출
		int basketCount = bps.getBasketCount(basket);
		
		
		
		// 메인에 출력해줄 전체 메뉴 조회 
		// 전체 메뉴 조회를 위한 MenuListService 서비스 호출 
		MainPageProService mls = new MainPageProService();
		ArrayList<ProductBean> menuList = mls.getMenuList();
		
		// 카테고리(Starter, Main, Dessert) 가져오기
		MainPageProService mls2 = new MainPageProService();
		ArrayList<ProductBean> category = mls2.getCategory();

		// 초기화 
		forward = new ActionForward();
				
		// main.jsp로 이동 
		forward.setPath("/order/main.jsp");
				
		// 메인페이지에 뿌려줄 menuList(전체메뉴)와  basketCount(장바구니 수량) 전달 
		request.setAttribute("menuList", menuList);
		request.setAttribute("basketCount", basketCount);
		
		request.setAttribute("table_num", table_num);
		request.setAttribute("mem_num", mem_num);
		
		// 카테고리 전달
		request.setAttribute("category", category);
		
				
		return forward;
		
	}

	

}
