package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import sun.swing.BakedArrayList;
import static db.JdbcUtil.*;

import vo.BasketBean;
import vo.MenuBean;
import vo.ProductInfoBean;

public class OrderDAO {

	private static OrderDAO instance;

	private OrderDAO() {
	}

	public static final OrderDAO getInstance() {
		// 기존 boardDAO 인스턴스가 없을 때만 생성하고 있을떄 생성하지않음
		if (instance == null) {
			instance = new OrderDAO();
		}

		return instance;
	}

	// ---------------------------------------------------------------------------------
	// Service클래스로부터 jdbcUtil에서 제공받은 Connection객체를 전달받기
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 여기서 부터 필요한 메서드를 적으면됩니다.
	
	// 빈 장바구니 생성하는 메서드 
	public int insertBasket(String id, int table_num) {
		
		// insert 성공여부 확인을 위한 변수 초기값 0 지정 
		int insertResult =0;
		
		try {
		
			String sql = "INSERT INTO basket2 VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 가져온 id 
			pstmt.setString(2, ""); // 빈장바구니를 생성하므로 table_info=""
			pstmt.setInt(3, table_num); // 가져온 table_num 
			
			insertResult= pstmt.executeUpdate();
			
			System.out.println("orderDAO의 insertResult : " + insertResult);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertBasket() 메서드 " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		
		return insertResult;
	}

	
	// 장바구니에 담긴 메뉴수량 조회하는 메서드 
	public int selectCountBasket(String id) {
		
		int count = 0;
		
		try {
			
			String sql = "SELECT basket_info from basket2 where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String basket_info = rs.getString("basket_info");
				count = basket_info.split("/").length;
				
				if(count == -1) {
					count = 0;
				}
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectCountBasket() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;

	}
	
	
	// 전체 메뉴 조회하는 메서드 
	public ArrayList<MenuBean> selectMenuList() {

		ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
		MenuBean menu = new MenuBean();

		try {
			String sql = "SELECT * FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				menu = new MenuBean();
				menu.setItem_num(rs.getInt("item_num"));
				menu.setItem_name(rs.getString("item_name"));
				menu.setItem_img(rs.getString("item_img"));
				menu.setItem_price(rs.getInt("item_price"));
				menu.setItem_info(rs.getString("item_Info"));

				menuList.add(menu);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectMenuList() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return menuList;
	}
	
	// 단일메뉴의 상세정보 조회하는 메서드  
	public MenuBean selectDetail(int item_num) {
		
		MenuBean menu = null;
		
		try {
			
			String sql ="SELECT * from product where item_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				menu = new MenuBean();
				
				menu.setItem_num(item_num);
				menu.setItem_name(rs.getString("item_name"));
				menu.setItem_img(rs.getString("item_img"));
				menu.setItem_info(rs.getString("item_info"));
				menu.setItem_price(rs.getInt("item_price"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectDetail() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}
		

		return menu;
	}


	// 단일메뉴 장바구니 담기 메서드 
	public int insertBasket(BasketBean basket) {
		
		// 성공여부 확인을 위한 변수 
		int basketSuccess = 0;
		
		try {
			
			String sql = "INSERT INTO basket2 values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, basket.getMember_id());
			pstmt.setString(2, basket.getBasket_info()+"/");
			pstmt.setInt(3, basket.getTable_num());

			basketSuccess = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertBasket() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return basketSuccess;
	}

	
	// 업데이트할것 
	public int updateBasket(BasketBean basket) {
		
		int updateBasket = 0;
		
		try {
			String sql = "UPDATE basket2 SET basket_info = ? where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, basket.getBasket_info());
			pstmt.setString(2, basket.getMember_id());
			
			updateBasket = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - updateBasket() 메서드 " + e.getMessage());
		}
		
		return updateBasket;
	}

	
	// 기존의 장바구니 조회할 메서드 
	public ArrayList<ProductInfoBean> selectBasketList(String id) {
		
		ArrayList<ProductInfoBean> basketList = new ArrayList<ProductInfoBean>();
		
	
		try {
			String sql = "SELECT * FROM basket2 WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

				
			if(rs.next()) {
					
				// String으로 묶인 모든 basket_info쪼개기
				String[] dbBasketArray = rs.getString("basket_info").split("/");
				
				
				for (int i=0; i < dbBasketArray.length ; i++) {
						
					ProductInfoBean p = new ProductInfoBean();
					String[] dbOrderArray2 = dbBasketArray[i].split(",");
						
					p.setItem_num(Integer.parseInt(dbOrderArray2[0]));
					p.setItem_qty(Integer.parseInt(dbOrderArray2[1]));
						
					basketList.add(p);
						
					}
				}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return basketList;
	}

	
	// 주문을 위한 메서드 
	public int insertOrder(BasketBean basket) {
		
		// 성공여부 확인을 위한 변수 
		int orderSuccess = 0;
				
		try {
					
			String sql = "INSERT INTO order2 values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, basket.getMember_id());
			pstmt.setString(2, basket.getBasket_info()+"/");
			pstmt.setInt(3, basket.getTable_num());

			orderSuccess = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertOrder() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}
				
		return orderSuccess;

	}

	
}
	

