package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.ArrayListToStringService;
import order.svc.BasketProService;
import order.svc.OrderService;
import order.svc.StringToArrayListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;

public class OrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		// id, table_num 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));

		// mem_num에 해당하는 preorder가져오기 
		BasketProService service = new BasketProService();
		String PreOrderInfo = service.getPreOrder(mem_num);

		StringToArrayListService service2 = new StringToArrayListService();
		ArrayList<ProductInfoBean> preorderarray = service2.getArray(PreOrderInfo);
	
		// 합쳐주기 위한 서비스 
		OrderService service5 = new OrderService();
		String OrderInfo = service5.PreOrderToOrder(preorderarray);
		

		// mem_num에 해당하는 preorder ==> orders에 담기 위해 basketBean에 저장
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setBasket_info(OrderInfo);
		basket.setTable_num(table_num);
		
		// preorder>> orders에 담기 위한 서비스 호출
		OrderService service3 = new OrderService();
		int insertResult = service3.insertOrder(basket);
		
		if(insertResult>0) {
			System.out.println("preorder->orders insert 성공!");
			
			// insert에 성공했으므로 
			// 기존 preorder는 비워줘야함 
			
			OrderService service4 = new OrderService();
			int deleteResult = service4.deleteBasket(mem_num);
			
			if(deleteResult>0) {
				System.out.println("preorder delete성공!");
			} else {
				System.out.println("preorder delete실패!");
			}
			
			
		} else {
			System.out.println("preorder->orders insert 실패!");
		}
	
		
		forward = new ActionForward();
		forward.setPath("/OrderMain.or");
		
		request.setAttribute("mem_num", mem_num);
		request.setAttribute("table_num", table_num);
		
		
		return forward;
	}

}
