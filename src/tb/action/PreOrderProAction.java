package tb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.PreOrderService;
import tb.svc.StringToOrderListService;
import tb.svc.TablePreOrderUpdateService;
import vo.ActionForward;
import vo.BasketBean;

public class PreOrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int table_num = Integer.parseInt(request.getParameter("tableNo").trim());
		
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		
		String totalOrder = request.getParameter("totalOrder");
		
		StringToOrderListService service = new StringToOrderListService();
		
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		
		basketList = service.setBasketBean(table_num, mem_num, totalOrder);
		
		
		// basket에 저장된 정보를 preorder 테이블에 저장하기 위해 서비스 호출 
		TablePreOrderUpdateService pos = new TablePreOrderUpdateService();
		int updateResult = pos.updatePreOrder(basketList);
		
		if (updateResult>0) {
			
			System.out.println("Preorder 성공!");
			
		} else {
			System.out.println("Preorder 실패!");
		}
		
		
		
		forward = new ActionForward();
		forward.setPath("/SOS/TablesMain.tb");
		forward.setRedirect(true);
				
		return forward;
	}

}
