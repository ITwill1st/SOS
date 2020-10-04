package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.ReviewCheckUpdateService;
import review.svc.ReviewWriteProService;
import vo.ActionForward;

public class StoreReviewProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int review_rating = Integer.parseInt(request.getParameter("review_rating"));
		String review_comment = request.getParameter("review_comment");
		ReviewCheckUpdateService checkUpdateService = new ReviewCheckUpdateService();
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		boolean isSuccess = reviewWriteProService.insertReview(mem_num, 0, review_rating, review_comment);
		if(isSuccess) {
			checkUpdateService.store_reviewCheckUpdate(order_num);					
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("MyOrderList.me");	
		
		return forward;
	}

}
