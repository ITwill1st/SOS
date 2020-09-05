package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import product.dao.ProductDAO;
import vo.ReviewDTO;

public class ReviewDAO {

private ReviewDAO() {
		
	}
	
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}

	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int insertReview(int item_num, int review_rating, String review_comment) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int isReviewCount = 0;
		int review_comment_num = 0;
		
		try {
			String sql = "select max(review_comment_num) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review_comment_num = rs.getInt(1) + 1;				
			}
			
			sql = "insert into review values(?,?,?,?,now(),null,0,1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.setInt(2, review_comment_num);
			pstmt.setString(3, review_comment);
			pstmt.setInt(4, review_rating);
			
			isReviewCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isReviewCount;
	}
	
	public ArrayList<ReviewDTO> getReview(int item_num) {
		
		System.out.println("ReviewDAO - getReview");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		
		try {
			String sql = "select * from review where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO rdt = new ReviewDTO();
				
				rdt.setItem_num(rs.getInt("item_num"));
				rdt.setReview_comment_num(rs.getInt("review_comment_num"));
				rdt.setReview_comment(rs.getString("review_comment"));
				rdt.setReview_rating(rs.getInt("review_rating"));
				rdt.setReview_datetime(rs.getTimestamp("review_datetime"));
				rdt.setReview_re_comment(rs.getString("review_re_comment"));
				rdt.setReview_re_checker(rs.getInt("review_re_checker"));
				rdt.setMember_num(rs.getInt("member_num"));
				
				reviewList.add(rdt);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return reviewList;
	}
	
}
