package review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import review.svc.ReviewCheckUpdateService;
import review.svc.ReviewWriteProService;
import vo.ActionForward;

public class ReviewWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int review_count = Integer.parseInt(request.getParameter("review_count"));
		
		ReviewWriteProService service = new ReviewWriteProService();
		
		for(int i = 0 ; i < review_count ; i++) {
			
			// form 태그에서 인덱스 번호를 부여하여 파라미터로 넘겨줌
			// 인덱스를 통해 구분 해놓은 요소들 가져오기
			String review_comment = request.getParameter("review_comment_"+i);
			int item_num = Integer.parseInt(request.getParameter("item_num_"+i));
			int review_rating = 0;
			
			if(request.getParameter("review_rating_"+i) != null) {
				review_rating = Integer.parseInt(request.getParameter("review_rating_"+i));
			}
					
			if(review_rating != 0) {								
				ReviewCheckUpdateService checkUpdateService = new ReviewCheckUpdateService();
				boolean isReviewSucess = service.insertReview(mem_num, item_num, review_rating, review_comment);
				if(isReviewSucess) {
					checkUpdateService.reviewCheckUpdate(mem_num);					
				}
				
			}
			
		}
		
		forward = new ActionForward();	
		forward.setPath("index.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
