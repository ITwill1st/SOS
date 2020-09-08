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

		ActionForward forward = null;
		
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		
		ReviewListService service = new ReviewListService();
		
		ArrayList<ProductBean> product_list = service.getProduct_list(mem_num);
		
		request.setAttribute("product_list", product_list);
		
		forward = new ActionForward();
		forward.setPath("/review/reviewOrderList.jsp?mem_num="+mem_num);
		
		return forward;
	}

}
