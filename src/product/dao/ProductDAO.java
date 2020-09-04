package product.dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vo.ProductBean;

public class ProductDAO {
	private ProductDAO () {}
	private static ProductDAO instance;
	public static ProductDAO getInstance() {
		if(instance==null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
//----------------------------------------------------------------
	
	public int insertProduct(ProductBean pb) {
		int insertProduct = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(item_num) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 1;
			
			if(rs.next()) {
				num = rs.getInt(1)+1;
						
			}
			
			sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			pstmt.setString(2,pb.getItem_name());
			pstmt.setInt(3,pb.getItem_price());
			pstmt.setString(4,pb.getItem_origin());
			pstmt.setInt(5,pb.getItem_calorie());
			pstmt.setString(6,pb.getItem_info());
			pstmt.setString(7,pb.getItem_category());
			pstmt.setString(8,pb.getItem_allergie());
			pstmt.setString(9, pb.getItem_img());
			pstmt.setInt(10,pb.getItem_show());
			
			insertProduct = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProductDAO - insertProduct() 에러! : " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return insertProduct;
	}

	public int selectListCount() {
		int listCount = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT COUNT(item_num) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO - selectListCount() 에러!");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return listCount;
	}

	public ArrayList<ProductBean> selectProductList(int page, int limit) {
		ArrayList<ProductBean> productList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int startRow = (page - 1) * 10;
			
			String sql = "SELECT * FROM product LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,limit);
			rs =pstmt.executeQuery();
			
			productList = new ArrayList<ProductBean>();
			
			while(rs.next()) {
				ProductBean product = new ProductBean();
				
				product.setItem_num(rs.getInt("item_num"));
				product.setItem_name(rs.getString("item_name"));
				product.setItem_price(rs.getInt("item_price"));
				product.setItem_origin(rs.getString("item_origin"));
				product.setItem_calorie(rs.getInt("item_calorie"));
				product.setItem_info(rs.getString("item_info"));
				product.setItem_category(rs.getString("item_category"));
				product.setItem_allergie(rs.getString("item_allergie"));
				product.setItem_img(rs.getString("item_img"));
				
				productList.add(product);
				}
		} catch (SQLException e) {
			System.out.println("ProductDAO - selectProductList() 에러!");
			e.printStackTrace();
		}
		return productList;
	}

	public int deleteProduct(int item_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM product WHERE item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,item_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProductDAO - deleteproduct() 오류!");
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return deleteCount;
	}

	public ProductBean selectProduct(int item_num) {
		ProductBean product = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT * FROM product WHERE item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,item_num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				product = new ProductBean();
				
				product.setItem_num(rs.getInt("item_num"));
				product.setItem_name(rs.getString("item_name"));
				product.setItem_price(rs.getInt("item_price"));
				product.setItem_origin(rs.getString("item_origin"));
				product.setItem_calorie(rs.getInt("item_calorie"));
				product.setItem_info(rs.getString("item_info"));
				product.setItem_category(rs.getString("item_category"));
				product.setItem_allergie(rs.getString("item_allergie"));
				product.setItem_img(rs.getString("item_img"));
				
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO - selectProduct() 실패!");
			e.printStackTrace();
		}
		return product;
		
		
	}

	public int updateProduct(ProductBean productBean) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE product SET item_name=?, item_price=?,"
					+ "item_origin=?, item_calorie=?, item_info=?, item_category=?"
					+ " WHERE item_num=?";
			pstmt=con.prepareStatement(sql);
			
		
			pstmt.setString(1,productBean.getItem_name());
			pstmt.setInt(2,productBean.getItem_price());
			pstmt.setString(3,productBean.getItem_origin());
			pstmt.setInt(4,productBean.getItem_calorie());
			pstmt.setString(5,productBean.getItem_info());
			pstmt.setString(6,productBean.getItem_category());
//			pstmt.setString(8, productBean.getItem_img());
			pstmt.setInt(7,productBean.getItem_num());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProductDAO - updateProduct() 오류!");
			e.printStackTrace();
		}
		
		return updateCount;
	}

	
	
	
	
}
