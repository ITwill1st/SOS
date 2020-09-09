package review.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import review.dao.ReviewDAO;
import vo.ReviewDTO;

public class MenuDetailReviewListService {

	public 	ArrayList<ReviewDTO> getReview(int item_num) {
		
		System.out.println("ProductDetailService - getReview");
		
		ReviewDAO rda = ReviewDAO.getInstance();
		
		Connection con = getConnection();
		
		rda.setConnection(con);
		
		ArrayList<ReviewDTO> reviewList = rda.getReview(item_num);
		
		close(con);
		
		return reviewList;
	}

}
