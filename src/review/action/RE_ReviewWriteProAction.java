package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.Re_ReviewWriteProService;
import vo.ActionForward;

public class RE_ReviewWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;	
		System.out.println("RE_ReviewWriteProAction - execute");
		
		String review_re_comment = request.getParameter("review_re_comment");
		int review_comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		
		System.out.println(review_re_comment);
		System.out.println(review_comment_num);

		Re_ReviewWriteProService re_ReviewWriteProService = new Re_ReviewWriteProService();
		re_ReviewWriteProService.updateRe_review(review_comment_num, review_re_comment);

		return forward;
	}

}
