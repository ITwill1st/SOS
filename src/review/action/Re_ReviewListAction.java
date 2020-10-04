package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.Re_ReviewListService;
import vo.ActionForward;
import vo.ReviewDTO;

public class Re_ReviewListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		Re_ReviewListService re_ReviewListService = new Re_ReviewListService();
		ArrayList<ReviewDTO> reviewList = re_ReviewListService.getRe_reviewList();
		
		request.setAttribute("reviewList", reviewList);
		
		forward = new ActionForward();
		forward.setPath("/review/re_review_List.jsp");
		return forward;
	}

}
