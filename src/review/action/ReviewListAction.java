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
import vo.ProductBean;
import vo.ProductInfoBean;

public class ReviewListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 리뷰 해야할 리스트를 가져오는 액션 포워드
		System.out.println("ReviewListAction");
		ActionForward forward = null;
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		ArrayList<ProductInfoBean> order_info = null;
		ArrayList<ProductBean> product_list = new ArrayList<ProductBean>();
		//1. mem_num 을 이용해서 OrderList 가져오기		
		ReviewListService service = new ReviewListService();
		ArrayList<OrderDTO> orderList = service.getOrderList(mem_num);
		
		//2. OrderList 안의 order_info를 이용해 StringtoArrayList
		
		for(int i = 0 ; i < orderList.size() ; i++) {
			OrderDTO orderDTO = orderList.get(i);
			StringToArrayListService service_stringToArrayList = new StringToArrayListService();
			order_info =  service_stringToArrayList.getOrderInfoArray(orderDTO);
			
			for(int x = 0 ; x < order_info.size() ; x++) {
				ProductInfoBean pib = order_info.get(x);
				int item_num = pib.getItem_num();
				ProductBean productBean = service.getProduct(item_num);
				
				product_list.add(productBean);				
			}
		}	
		
		request.setAttribute("product_list", product_list);
		
		forward = new ActionForward();
		forward.setPath("/review/reviewOrderList.jsp?mem_num="+mem_num);
		
		return forward;
	}

}
