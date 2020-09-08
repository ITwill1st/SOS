package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.OrderDTO;
import vo.ProductBean;
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
	public int insertReview(int mem_num, int item_num, int review_rating, String review_comment) {
		
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
			
			sql = "insert into review values(?,?,?,?,?,now(),null,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, item_num);
			pstmt.setInt(3, review_comment_num);
			pstmt.setString(4, review_comment);
			pstmt.setInt(5, review_rating);
			
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
	public ArrayList<OrderDTO> getOrderList(int mem_num) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		try {
			String sql = "select * from orders where mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				
				orderDTO.setOrder_num(rs.getInt("order_num"));
				orderDTO.setMem_num(rs.getInt("mem_num"));
				orderDTO.setOrder_info(rs.getString("order_info"));
				orderDTO.setTable_num(rs.getInt("table_num"));
				orderDTO.setOrder_datetime(rs.getTimestamp("order_datetime"));
				orderDTO.setOrder_confirm(rs.getInt("order_confirm"));
				
				orderList.add(orderDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return orderList;
	}
	public ProductBean getProduct(int item_num) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBean productBean = new ProductBean();
		System.out.println(item_num);
		
		try {
			String sql = "select * from product where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				productBean.setItem_num(rs.getInt("item_num"));
				productBean.setItem_name(rs.getString("item_name"));
				productBean.setItem_price(rs.getInt("item_price"));
				productBean.setItem_origin(rs.getString("item_origin"));
				productBean.setItem_calorie(rs.getInt("item_calorie"));
				productBean.setItem_info(rs.getString("item_info"));
				productBean.setItem_category(rs.getString("item_category"));
				productBean.setItem_allergie(rs.getString("item_allergie"));
				productBean.setItem_img(rs.getString("item_img"));
				
				System.out.println(rs.getString("item_img"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return productBean;
	}
	
	public int reviewCheckerUpdate(int order_num, String order_info_toString) {
		
		PreparedStatement pstmt = null;
		System.out.println("reviewCheckerUpdate");
		System.out.println(order_num);
		System.out.println(order_info_toString);
		int a = 0;
		try {
			
			String sql = "UPDATE orders SET order_info=? WHERE order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_info_toString);
			pstmt.setInt(2, order_num);
			a = pstmt.executeUpdate();
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return a;
		
	}
	
}
