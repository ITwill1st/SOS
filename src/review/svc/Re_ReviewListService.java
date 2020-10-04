package review.svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import static db.JdbcUtil.*;
import review.dao.ReviewDAO;
import vo.ReviewDTO;

public class Re_ReviewListService {

	public ArrayList<ReviewDTO> getRe_reviewList() {
		
		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);
		
		ArrayList<ReviewDTO> reviewList = rda.getRe_reviewList();
		
		close(con);
		
		return reviewList;
	}
	
	public JSONArray getJSONRe_reviewList() {
		
		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);
		
		JSONArray reviewList = rda.getJSONRe_reviewList();
		
		return reviewList;
		
	}

}
