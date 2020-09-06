package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.StringToArrayListService;
import review.svc.ReviewListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.OrderDTO;
import vo.ProductInfoBean;

public class ReviewListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 리뷰 해야할 리스트를 가져오는 액션 포워드
		ActionForward forward = null;
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		
		//1. mem_num 을 이용해서 OrderList 가져오기
		
//		ReviewListService service = new ReviewListService();
//		OrderDTO orderDTO = service.getOrderList(mem_num);
		
		//2. OrderList 안의 order_info를 이용해 StringtoArrayList
		
//		StringToArrayListService service2 = new StringToArrayListService();
//		ArrayList<ProductInfoBean> order_info =  service2.getBasketInfoArray(orderDTO);
		
		//3. ArrayList안의 item_num 을 모두 불러와서 리스트로 출력

//		int item_num = order_info.getItem_num();
		
		return forward;
	}

}
