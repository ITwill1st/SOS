package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketDetailService;
import order.svc.BasketProService;
import order.svc.StringToArrayListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductBean;
import vo.ProductInfoBean;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward= null;

		// id, table_num 받아오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		String table_num = request.getParameter("table_num");
		
		
		// 현재 장바구니 조회하기위해 BasketProService 호출 
		BasketProService service = new BasketProService();
		ArrayList<ProductInfoBean> basketList = service.getBasketList(mem_num);
		
		
		// 장바구니의 메뉴 detail(이름, 가격 등) 가져오기 
//		어떻게 가져와야 한담? 답변)  <br>
//		 	가지고있는 item_num들을 가지고 ArrayList<productBean>에 저장합니다. <br>
//		 	이 페이지(order/main.jsp) 로딩시 action코드안에 아이템 정보를 가져오는 새로운 service를 생성하고안에<br>
//		 	dao.getItemInfo(파라미터는 ArrayList<productBean>) 메서드를 실행합니다<br>
//		 	dao의 리턴값은 ArrayList(productBean에 저장), 하나씩 꺼내서 출력합니다. 쉽죠잉?<br>
		
		// 장바구니에 있는 item ArrayList에 저장하기 위한 서비스 호출
		BasketDetailService service2 = new BasketDetailService();
		ArrayList<ProductBean> itemNumList = service2.setItemnum(basketList);
		
		BasketDetailService service3 = new BasketDetailService();
		ArrayList<ProductBean> basketInfo = service3.getItemInfo(itemNumList);
		
		forward = new ActionForward();
		forward.setPath("/order/basket.jsp");
		
		request.setAttribute("mem_num",mem_num);
		request.setAttribute("table_num", table_num);
		request.setAttribute("basketList", basketList);
		request.setAttribute("basketInfo", basketInfo);
		
		return forward;
	}

}
