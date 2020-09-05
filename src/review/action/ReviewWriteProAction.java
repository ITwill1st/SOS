package review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.ReviewWriteProService;
import vo.ActionForward;

public class ReviewWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ReviewWriteProAction - execute");
		ActionForward forward = null;
		
		int review_count = Integer.parseInt(request.getParameter("review_count"));
		
		for(int i = 0 ; i < review_count ; i++) {
			
			int item_num = Integer.parseInt(request.getParameter("item_num_"+i));
			int review_rating = Integer.parseInt(request.getParameter("review_rating_"+item_num));
			String review_comment = request.getParameter("review_comment_"+item_num);
			
			System.out.println(item_num + review_rating + review_comment);
			
			ReviewWriteProService service = new ReviewWriteProService();
			
			boolean isReviewSucess = service.insertReview(item_num, review_rating, review_comment);
			
			if(!isReviewSucess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('리뷰 등록 실패 !')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('리뷰 등록 성공 !')");
				out.println("</script>");
			}
		}
		
		forward = new ActionForward();
		forward.setPath("index.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
