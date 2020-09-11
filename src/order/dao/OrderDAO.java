package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.BasketBean;
import vo.ProductBean;
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

	// 장바구니 생성 & 단일메뉴 담기
	public int insertBasket(BasketBean basket) {

		// insert 성공여부 확인을 위한 변수 초기값 0 지정
		int updateResult = 0;
		int insertResult = 0;

		try {
			
			String sql = "SELECT * FROM basket where mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				sql = "UPDATE basket set basket_info=? where mem_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, basket.getBasket_info());
				pstmt.setInt(2, basket.getMem_num());
				updateResult = pstmt.executeUpdate();
				
				//update구문이 실행되었으면 insert구문은 실행되지않고 
				//insertResult변수에 updateResult변수값을 저장한다.
				insertResult = updateResult;
				
			}else {
				sql = "INSERT INTO basket VALUES(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, basket.getMem_num()); // 가져온 id
				pstmt.setString(2, basket.getBasket_info());
				pstmt.setInt(3, basket.getTable_num());

				insertResult = pstmt.executeUpdate();
			}
			

			System.out.println("orderDAO의 insertResult : " + insertResult);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertBasket() 메서드 " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}

		return insertResult;
	}

	// 장바구니에 담긴 메뉴수량 조회하는 메서드
	public int selectCountBasket(int mem_num) {

		int count = 0;

		try {

			String sql = "SELECT basket_info from basket where mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				String basket_info = rs.getString("basket_info");
				
				if(!basket_info.equals("")) {
					count = basket_info.split("/").length;
				}
				
				if (count == -1) {
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
	public ArrayList<ProductBean> selectMenuList() {

		ArrayList<ProductBean> menuList = new ArrayList<ProductBean>();
		ProductBean menu = new ProductBean();

		try {
			String sql = "SELECT * FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				menu = new ProductBean();
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
	public ProductBean selectDetail(int item_num) {

		ProductBean menu = null;

		try {

			String sql = "SELECT * from product where item_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				menu = new ProductBean();

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
			close(rs);
			close(pstmt);

		}

		return menu;
	}

	// 장바구니 수량 변경
	public int updateBasket(BasketBean basket) {

		int updateBasket = 0;

		try {
			String sql = "UPDATE basket SET basket_info = ? where mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, basket.getBasket_info());
			pstmt.setInt(2, basket.getMem_num());

			updateBasket = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - updateBasket() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return updateBasket;
	}

	// 기존의 장바구니 조회할 메서드
	public ArrayList<ProductInfoBean> selectBasketList(int mem_num) {

		ArrayList<ProductInfoBean> basketList = new ArrayList<ProductInfoBean>();

		try {

			String sql = "SELECT * FROM basket WHERE mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (!rs.getString("basket_info").equals("")) {
					// String으로 묶인 모든 basket_info쪼개기
					String[] dbBasketArray = rs.getString("basket_info").split("/");

					if (dbBasketArray.length >= 1) {

						for (String s : dbBasketArray) {

							ProductInfoBean p = new ProductInfoBean();
							String[] dbOrderArray2 = s.split(",");

							p.setItem_num(Integer.parseInt(dbOrderArray2[0]));
							p.setItem_qty(Integer.parseInt(dbOrderArray2[1]));
							p.setReview_ck(Integer.parseInt(dbOrderArray2[2]));

							basketList.add(p);

						}

					}
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

	// 장바구니에 있는 상품 주문하기
	public int insertOrder(BasketBean basket) {

		int insertResult = 0;

		try {

			String sql = "SELECT MAX(order_num) from orders";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int num = 1; // 새 주문번호

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO ORDERS VALUES(?,?,?,?,now(),?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, basket.getMem_num());
			pstmt.setString(3, basket.getBasket_info());
			pstmt.setInt(4, basket.getTable_num());
			pstmt.setInt(5, 0);

			insertResult = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertOrder() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
			
		}

		return insertResult;
	}

	// 주문후 장바구니 초기화를 위한 메서드
	public int deleteOrder(int mem_num) {

		int deleteResult = 0;

		try {

			String sql = "DELETE FROM preorder WHERE mem_num=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			deleteResult = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - deleteOrder() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return deleteResult;

	}

	
	// 장바구니 항목 > PreOrder 테이블에 insert 
	public int insertPreOrder(BasketBean basket) {
		
		int insertResult = 0;
		
		try {
			
			String sql = "INSERT INTO preorder VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setString(2, basket.getBasket_info());
			pstmt.setInt(3, basket.getTable_num());
			
			insertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertPreOrder() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		
		return insertResult;
	}

	
	// 장바구니에 담긴 item_num이 담긴 itemNumList 전달하여 
	// product table에 있는 해당 아이템 정보 가져오기 
	public ArrayList<ProductBean> selectItemInfo(ArrayList<ProductBean> itemNumList) {
		
		ArrayList<ProductBean> itemInfo = new ArrayList<ProductBean>();
		
		// 장바구니에 있는 item_num이 담긴 itemNumList 
		for (ProductBean p : itemNumList) {
			
			try {
				
				String sql ="SELECT * FROM product WHERE item_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, p.getItem_num());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					ProductBean p2 = new ProductBean();
					
					p2.setItem_num(rs.getInt("item_num"));
					p2.setItem_name(rs.getString("item_name"));
					p2.setItem_price(rs.getInt("item_price"));
					p2.setItem_info(rs.getString("item_name"));
					p2.setItem_info(rs.getString("item_img"));
					
					itemInfo.add(p2);
					
				}
				
			
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("OrderDAO - selectItemInfo() 메서드 " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
		}
		
		return itemInfo;
	}

	public String selectPreorderInfo(int mem_num) {
		String preorderInfo = "";
		
		try {
			String sql = "SELECT * FROM preorder WHERE mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				preorderInfo += rs.getString("basket_info");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectPreorderInfo() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return preorderInfo;
	}

	

}
