package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	public int insertReview(int mem_num, int item_num, int review_rating, String review_comment) {

		int isReviewCount = 0;
		int review_comment_num = 0;

		try {
			sql = "select max(review_comment_num) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				review_comment_num = rs.getInt(1) + 1;
			}

			sql = "insert into review values(?,?,?,?,now(),null,0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.setInt(2, review_comment_num);
			pstmt.setString(3, review_comment);
			pstmt.setInt(4, review_rating);
			pstmt.setInt(5, mem_num);

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

		ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();

		try {
			String sql = "select * from review where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDTO rdt = new ReviewDTO();

				rdt.setItem_num(rs.getInt("item_num"));
				rdt.setReview_comment_num(rs.getInt("review_comment_num"));
				rdt.setReview_comment(rs.getString("review_comment"));
				rdt.setReview_rating(rs.getInt("review_rating"));
				rdt.setReview_datetime(rs.getTimestamp("review_datetime"));
				rdt.setReview_re_comment(rs.getString("review_re_comment"));
				rdt.setReview_re_checker(rs.getInt("review_re_checker"));
				rdt.setMem_num(rs.getInt("member_num"));

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
		// 구매내역 가져오는 메서드
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();

		try {
			// 가져올때 리뷰가 안된것들만 가져옴
			String sql = "select * from orders where mem_num=? and review_chk=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO orderDTO = new OrderDTO();

				orderDTO.setOrder_num(rs.getInt("order_num"));
				orderDTO.setMem_num(rs.getInt("mem_num"));
				orderDTO.setItem_num(rs.getInt("item_num"));
				orderDTO.setReview_chk(rs.getInt("review_chk"));

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

	public ArrayList<OrderDTO> getOrderList_orderNum(int order_num) {
		// 구매내역 가져오는 메서드
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();

		try {
			// 가져올때 리뷰가 안된것들만 가져옴
			String sql = "select * from orders o JOIN product p ON (o.item_num = p.item_num) where order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO orderDTO = new OrderDTO();

				orderDTO.setOrder_num(rs.getInt("order_num"));
				orderDTO.setMem_num(rs.getInt("mem_num"));
				orderDTO.setTable_num(rs.getInt("table_num"));
				orderDTO.setItem_name(rs.getString("item_name"));
				orderDTO.setItem_num(rs.getInt("item_num"));
				orderDTO.setItem_qty(rs.getInt("item_qty"));
				orderDTO.setItem_price(rs.getInt("item_price"));
				orderDTO.setTotal_price(rs.getInt("total_price"));
				orderDTO.setOrder_datetime(rs.getTimestamp("order_datetime"));
				orderDTO.setReview_chk(rs.getInt("review_chk"));

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

		ProductBean productBean = new ProductBean();
		System.out.println(item_num);

		try {
			String sql = "select * from product where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				productBean.setItem_num(rs.getInt("item_num"));
				productBean.setItem_name(rs.getString("item_name"));
				productBean.setItem_price(rs.getInt("item_price"));
				productBean.setItem_origin(rs.getString("item_origin"));
				productBean.setItem_calorie(rs.getInt("item_calorie"));
				productBean.setItem_info(rs.getString("item_info"));
				productBean.setItem_category(rs.getString("item_category"));
				productBean.setItem_allergies(rs.getString("item_allergies"));
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

	public int reviewCheckerUpdate(int order_num) {

		int a = 0;
		try {

			String sql = "UPDATE orders SET review_chk=1 WHERE order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			a = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return a;

	}

	public int store_reviewCheckerUpdate(int order_num) {


		int a = 0;
		try {

			String sql = "UPDATE orders SET store_review_chk=1 WHERE order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			a = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return a;

	}

	public ArrayList<ReviewDTO> getRe_reviewList() {
		
		ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		
		try {
			sql = "select * from review where review_re_checker=0";
			pstmt = con.prepareStatement(sql);
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
				rdt.setMem_num(rs.getInt("member_num"));

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

	public int updateRe_Review(int review_comment_num, String review_re_comment) {
		
		int isSuccess = 0;
		
		
		try {
			sql = "update review set review_re_comment=?,review_re_checker=1 where review_comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review_re_comment);
			pstmt.setInt(2, review_comment_num);
			isSuccess = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return isSuccess;
	}

	public JSONArray getJSONRe_reviewList() {
		
		JSONArray reviewList = new JSONArray();
		
		try {
			sql = "select * from review where review_re_checker=0";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("item_num", rs.getInt("item_num"));
				jsonObject.put("review_comment_num", rs.getInt("review_comment_num"));
				jsonObject.put("review_comment", rs.getString("review_comment"));
				jsonObject.put("review_rating", rs.getInt("review_rating"));
				jsonObject.put("review_datetime", rs.getTimestamp("review_datetime"));
				jsonObject.put("review_re_comment", rs.getString("review_re_comment"));
				jsonObject.put("review_re_checker", rs.getInt("review_re_checker"));
				jsonObject.put("member_num", rs.getInt("member_num"));

				reviewList.add(jsonObject);				
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
