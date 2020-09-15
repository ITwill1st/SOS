package order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.MenuDetailService;
import review.svc.MenuDetailReviewListService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReviewDTO;

public class MenuDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;

		// mem_num, table_num
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		// 클릭한 item_num 
		int item_num = Integer.parseInt(request.getParameter("item_num"));

		
		// 선택한 Menu에 대한 상세정보를 가져오기 위한  MenuDetailService 호출
		MenuDetailService mds = new MenuDetailService();
		MenuDetailReviewListService reviewService = new MenuDetailReviewListService();
		
		// item_num을 전달하여 해당 item에 대한 정보를 ProductBean에 담아오기
		ProductBean menu  = mds.getDetail(item_num);
		ArrayList<ReviewDTO> reviewList = reviewService.getReview(item_num);

		forward = new ActionForward();
		forward.setPath("/order/detail.jsp");
		
		// 조회해온 menu 상세정보 담아가기 
		request.setAttribute("menu", menu);
		request.setAttribute("mem_num",mem_num);
		request.setAttribute("table_num", table_num);
		request.setAttribute("reviewList", reviewList);
		
		return forward;
	}

}
