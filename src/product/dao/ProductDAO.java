package product.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import vo.ProductDTO;



public class ProductDAO {

	private static ProductDAO instance;
	
	private ProductDAO() {}
	
	public static final ProductDAO getInstance() {
		//기존 boardDAO 인스턴스가 없을 때만 생성하고 있을떄 생성하지않음
		if(instance == null) {
			instance = new ProductDAO();
		}
		
		return instance;
	}
	//---------------------------------------------------------------------------------
	//Service클래스로부터 jdbcUtil에서 제공받은 Connection객체를 전달받기
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//여기서 부터 필요한 메서드를 적으면됩니다.
	
	
	// ----------------------------상품 등록 메서드--------------------------------------
	public int insertProduct(ProductDTO pdt) {
		System.out.println("ProductDAO - insertProduct");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int item_num = 0;
		
		try {
			String sql = "select max(item_num) from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				item_num = rs.getInt(1) + 1;				
			}
			
			sql = "insert into product values(?,?,?,?,?,?,?,?,?,1)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.setString(2, pdt.getItem_name());
			pstmt.setInt(3, pdt.getItem_price());
			pstmt.setString(4, pdt.getOrigin());
			pstmt.setInt(5, pdt.getCalorie());
			pstmt.setString(6, pdt.getItem_info());
			pstmt.setInt(7, pdt.getItem_category());
			pstmt.setString(8, pdt.getItem_allergie());
			pstmt.setString(9, pdt.getItem_img());
			
			insertCount  =  pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	// ----------------------------상품 리스트 가져오는 메서드--------------------------------------
	public ArrayList<ProductDTO> getProductList() {

		System.out.println("ProductDAO - getProductList");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
		
		try {
			String sql = "select * from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO pdt = new ProductDTO();

				pdt.setItem_num(rs.getInt("item_num"));
				pdt.setItem_name(rs.getString("item_name"));
				pdt.setItem_price(rs.getInt("item_price"));
				pdt.setOrigin(rs.getString("item_origin"));
				pdt.setCalorie(rs.getInt("item_calorie"));
				pdt.setItem_info(rs.getString("item_info"));
				pdt.setItem_category(rs.getInt("item_category"));
				pdt.setItem_allergie(rs.getString("item_allergie"));
				pdt.setItem_img(rs.getString("item_img"));
				pdt.setItem_show(rs.getInt("item_show"));
				
				productList.add(pdt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productList;
	}

	// ----------------------------상품 리뷰 메서드(미완성)--------------------------------------
	public int insertComment(int menu_num, int menu_grade, String menu_content) {
		System.out.println("ProductDAO - insertComment");
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		try {
			String sql = "insert into test_menu_comment values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menu_num);
			pstmt.setString(2, menu_content);
			pstmt.setInt(3, menu_grade);
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return insertCount;
	}

	// ----------------------------상품 정보 가져오기 메서드--------------------------------------
	public ProductDTO getProduct(int item_num) {
		System.out.println("ProductDAO - getProduct");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO bb = new ProductDTO();
		
		try {
			String sql = "select * from product where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				bb.setItem_num(rs.getInt("item_num"));
				bb.setItem_name(rs.getString("item_name"));
				bb.setItem_price(rs.getInt("item_price"));
				bb.setOrigin(rs.getString("item_origin"));
				bb.setCalorie(rs.getInt("item_calorie"));
				bb.setItem_info(rs.getString("item_info"));
				bb.setItem_category(rs.getInt("item_category"));
				bb.setItem_allergie(rs.getString("item_allergie"));
				bb.setItem_img(rs.getString("item_img"));
				bb.setItem_show(rs.getInt("item_show"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return bb;
	}

	// ----------------------------구매내역 리스트 가져오기 메서드(미완성)--------------------------------------
	public ArrayList<ProductDTO> getPurchaseList(int order_num) {
		
		System.out.println("ProductDAO - getPurchaseList");
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2= null;
		ProductDTO pd = null;
		ArrayList<ProductDTO> purchaseList = new ArrayList<ProductDTO>();
		
		try {
			String sql = "select item_num from order_test where order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs1 = pstmt.executeQuery();

			while(rs1.next()) {
				
				int item_num = rs1.getInt(1);
				
				sql = "select * from product where item_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, item_num);
				rs2 = pstmt.executeQuery();
				
				if(rs2.next()){
					pd = new ProductDTO();
					
					pd.setItem_num(rs2.getInt("item_num"));
					pd.setItem_name(rs2.getString("item_name"));
					pd.setItem_price(rs2.getInt("item_price"));
					pd.setOrigin(rs2.getString("item_origin"));
					pd.setCalorie(rs2.getInt("item_calorie"));
					pd.setItem_info(rs2.getString("item_info"));
					pd.setItem_category(rs2.getInt("item_category"));
					pd.setItem_allergie(rs2.getString("item_allergie"));
					pd.setItem_img(rs2.getString("item_img"));
					pd.setItem_show(rs2.getInt("item_show"));
					
					purchaseList.add(pd);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return purchaseList;
	}
}
