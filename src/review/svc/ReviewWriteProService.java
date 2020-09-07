package review.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;
import review.dao.ReviewDAO;

public class ReviewWriteProService {

	public boolean insertReview(int mem_num, int item_num, int review_rating, String review_comment) {
		System.out.println("ReviewWriteProService - insertReview");
		
		boolean isReviewSuccess = false;
		ReviewDAO rda = ReviewDAO.getInstance();
		
		Connection con = getConnection();
		
		rda.setConnection(con);
		
		int isReviewCount = rda.insertReview(mem_num, item_num, review_rating, review_comment);
		
		if(isReviewCount > 0) {
			isReviewSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isReviewSuccess;
	}

}
