package order.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static db.JdbcUtil.*;

import vo.BasketBean;
import vo.PreOrderBean;
import vo.ProductBean;


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
	
	
	// 장바구니 수량 조회하는 메서드 //
	public int selectBasketCount(BasketBean basket) {

		int count = 0;

		try {

			// 장바구니에 담긴 메뉴 (항목의)수량 조회 
			String sql = "SELECT COUNT(item_num) FROM basket1 where mem_num=? and table_num=? and preorder_tossed=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, 0); // preorder로 넘어간 적 없는 장바구니 리스트 
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 조회한 항목의 수량 담아주기
				count = rs.getInt("count(item_num)");
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
	
	
	// 메인 페이지에 뿌려줄 전체메뉴 조회 //
	public ArrayList<ProductBean> selectMenuList() {
		
		// ArrayList 객체 생성 
		ArrayList<ProductBean> menuList = new ArrayList<ProductBean>();
		
		try {

			// 전체 메뉴 조회 
			String sql = "select * from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				// 전체 메뉴에 대한 정보를 담아갈  ProductBean 객체 생성 
				ProductBean p = new ProductBean();
				p.setItem_num(rs.getInt("item_num"));
				p.setItem_name(rs.getString("item_name"));
				p.setItem_img(rs.getString("item_img"));
				p.setItem_price(rs.getInt("item_price"));
				p.setItem_origin(rs.getString("item_origin"));
				p.setItem_calorie(rs.getInt("item_calorie"));
				p.setItem_category(rs.getString("item_category"));
				p.setItem_allergie(rs.getString("item_allergie"));
				p.setItem_info(rs.getString("item_info"));
				
				//ArrayList에 담기 
				menuList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectCountBasket() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return menuList;
	}


	// 전체 category 조회해오는 메서드 
	public ArrayList<ProductBean> selectCategory() {
		
		ArrayList<ProductBean> category = new ArrayList<ProductBean>();

		try {

			// 전체 category 조회 
			String sql = "select distinct item_category from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				// 카테고리 담아갈 ProductBean 객체 생성 
				ProductBean p = new ProductBean();
				p.setItem_category(rs.getString("item_category"));
				//ArrayList에 담기 
				category.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectCountBasket() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return category;
		
	
	}

	
	// 단일메뉴의 상세정보 조회하는 메서드 //
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
	
	
	// 장바구니 담기 //
	public int insertBasket(BasketBean basket) {

		// insert 성공여부 확인을 위한 변수 초기값 0 지정
		int insertResult = 0;

	
		try {
			
			String sql = "INSERT INTO basket1(mem_num,item_num,item_qty,table_num) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getItem_num());
			pstmt.setInt(3, basket.getItem_qty());
			pstmt.setInt(4, basket.getTable_num());
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertBasket() 메서드 " + e.getMessage());
		} finally {
				close(pstmt);
		}

			return insertResult;
		}

	
	// 장바구니 조회할 메서드 //
	public ArrayList<BasketBean> selectBasketList(int mem_num,int table_num) {

		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();

		try {

			String sql = "SELECT * FROM basket1 WHERE mem_num=? and table_num=? and preorder_tossed =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, table_num);
			pstmt.setInt(3, 0); // preorder로 넘어가지 않은 리스트만 보여야 함 
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				BasketBean basket= new BasketBean();
				basket.setItem_num(rs.getInt("item_num"));
				basket.setItem_qty(rs.getInt("item_qty"));				
				basket.setMem_num(rs.getInt("mem_num"));
				basket.setTable_num(rs.getInt("table_num"));
				
				basketList.add(basket);
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
	
	
	// 장바구니 항목 > PreOrder 테이블에 insert 
	public int insertPreOrder(BasketBean basket, ArrayList<BasketBean> basketList, JSONArray preorder_json) {
			
		int insertResult = 0;
		
			try {
				String sql = "INSERT INTO preorder2(json_basket,mem_num,table_num,time) VALUES(?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				pstmt.setObject(1, basketList);
				pstmt.setInt(2, basket.getMem_num());
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
	
	
	// 장바구니 수량 +1 //
	public int insertQtyPlus(BasketBean basket) {
		
		int insertResult = 0;
		
		try {
			
			String sql ="select * from basket1 where mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, basket.getItem_num());
			ResultSet rs = pstmt.executeQuery();
			
			int item_qty = 0;
			
			if(rs.next()) {
				
				// 기존에 저장되어있던 수량 조회 
				item_qty = rs.getInt("item_qty");
				
				// +1
				item_qty++;

			}
			
			// +1 된 수량으로 update
			sql = "update basket1 set item_qty = ? where mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_qty);
			pstmt.setInt(2, basket.getMem_num());
			pstmt.setInt(3, basket.getTable_num());
			pstmt.setInt(4, basket.getItem_num());
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return insertResult;
	}

	
	// 장바구니 수량 -1 //
	public int insertQtyMinus(BasketBean basket) {
		
		int insertResult = 0;
		
		try {
			
			String sql ="select * from basket1 where mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, basket.getItem_num());
			ResultSet rs = pstmt.executeQuery();
			
			int item_qty = 0;
			
			if(rs.next()) {
				
				// 기존에 있는 수량 조회 
				item_qty = rs.getInt("item_qty");
				
				// 수량 -1
				item_qty--;
				
			}
			
			
			sql = "update basket1 set item_qty = ? where mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_qty);
			pstmt.setInt(2, basket.getMem_num());
			pstmt.setInt(3, basket.getTable_num());
			pstmt.setInt(4, basket.getItem_num());
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return insertResult;
	}
	
	// preorder로 넘어갔으므로 preorder_tossed를 1로 바꿔주는 메서드 // 
	public int updateBasket(int mem_num, int table_num) {
		
		int updateResult = 0;
		
		try {
			
			String sql = "update basket1 set preorder_tossed = ? where mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1); // preorder로 넘어갔으므로 preorder_tossed를 1로 바꿔줌 
			pstmt.setInt(2, mem_num);
			pstmt.setInt(3, table_num);
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return updateResult;
	}

	// 장바구니 항목에서 삭제 
	public int deleteBasket(BasketBean basket) {
		
		int deleteResult = 0;
		
		try {
			
			String sql = "DELETE FROM basket1 where mem_num=? and table_num=? and item_num=? and preorder_tossed=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num()); 
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, basket.getItem_num());
			pstmt.setInt(4, 0); // preorder로 이미 넘어갔었던 항목은 삭제 하면 안되므로 0 조건 걸어줘야함 
			
			deleteResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return deleteResult;
	}



	// preorder->order 성공했으므로 order_tossed를 1로 바꿔주기 위한 메서드 //
	public int updatePreOrder(int mem_num, int table_num) {

		int updateResult = 0;
		
		try {
			
			String sql = "update preorder set order_tossed = ? where mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1); // order로 넘어갔으므로  order_tossed를 1로 바꿔줌 
			pstmt.setInt(2, mem_num);
			pstmt.setInt(3, table_num);
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateResult;

	}

	
	// basket의 정보를 preorder테이블에 저장하기 위한 메서드 //
	public int insertPreOrder(ArrayList<BasketBean> basketList) {
		
		int insertResult = 0;
		
		// basketList에 저장된 항목을 모두 전달하기위해 확장 for문 사용 
		for (BasketBean b : basketList) {
			
			try {
				String sql = "INSERT INTO preorder(mem_num,table_num,item_num,item_qty,time) VALUES(?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				
				// idx는 auto_increment 이므로 입력X
				// pre_confirm, order_tossed는 default 값이 0으로 따로 입력 X
				pstmt.setInt(1, b.getMem_num());
				pstmt.setInt(2, b.getTable_num());
				pstmt.setInt(3, b.getItem_num());
				pstmt.setInt(4, b.getItem_qty());

				
				insertResult = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
		}
		
		return insertResult;
	}
	

	
	// preorder에 담긴 항목 담아오기 위한 메서드 //
	public ArrayList<BasketBean> selectPreOrder(BasketBean basket) {
		
		ArrayList<BasketBean> preorderList = new ArrayList<BasketBean>();
		
		try {

			String sql = "SELECT * FROM preorder WHERE mem_num=? and table_num=? and order_tossed =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, 0); // order로 넘어가지 않은 리스트만 보여야 함 
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				BasketBean preorder= new BasketBean();
				preorder.setItem_num(rs.getInt("item_num"));
				preorder.setItem_qty(rs.getInt("item_qty"));				
				preorder.setMem_num(rs.getInt("mem_num"));
				preorder.setTable_num(rs.getInt("table_num"));
				
				preorderList.add(basket);
			}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			return preorderList;
	}



	// preorder에 담긴 정보 order테이블에 담기 위한 메서드 //
	public int insertOrder(ArrayList<BasketBean> preorderList) {
		
		int insertResult = 0;
		
		try {
	
			for(BasketBean b : preorderList) {

				String sql = "INSERT INTO order(mem_num,table_num,item_num,item_qty,order_time,total_price) VALUES(?,?,?,?,now(),?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, b.getMem_num());
				pstmt.setInt(2, b.getTable_num());
				pstmt.setInt(3, b.getItem_num());
				pstmt.setInt(4, b.getItem_qty());
				pstmt.setInt(5, 0);
				
				insertResult = pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return insertResult;
	}


	
	
	
}
	//-----------------------------------------------------------------


