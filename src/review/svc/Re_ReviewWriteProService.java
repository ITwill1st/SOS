package review.svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import review.dao.ReviewDAO;

import static db.JdbcUtil.*;

public class Re_ReviewWriteProService {

	public void updateRe_review(int review_comment_num, String review_re_comment) {
		
		System.out.println("updateRe_review");
		
		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);
		
		int isSucess = rda.updateRe_Review(review_comment_num, review_re_comment);
		
		if(isSucess > 0 ) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	}

}
