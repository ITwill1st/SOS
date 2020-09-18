package order.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import static db.JdbcUtil.*;

import vo.BasketBean;
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
	
	
	// 장바구니 수량 조회하는 메서드 ///
	public int selectBasketCount(BasketBean basket) {

		int count = 0;

		try {

			// 장바구니에 담긴 메뉴 (항목의)수량 조회 
			String sql = "SELECT COUNT(item_num) FROM basket1 where mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num()); // mem_num
			pstmt.setInt(2, basket.getTable_num()); // basket_num
			
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
	
	
	// 메인 페이지에 뿌려줄 전체메뉴 조회 ///
	public ArrayList<ProductBean> selectMenuList() {
		
		// ArrayList 객체 생성 
		ArrayList<ProductBean> menuList = new ArrayList<ProductBean>();
		
		try {

			// product테이블에 담긴 전체 메뉴 조회 
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
	
	
	// 처음 담는 항목 장바구니 담기 ///
	public int insertBasket(BasketBean basket) {

		// insert 성공여부 확인을 위한 변수 초기값 0 지정
		int insertResult = 0;

	
		try {
			
			
			String sql = "SELECT MAX(basket_num) FROM basket1 WHERE mem_num=? AND table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			rs = pstmt.executeQuery();
			
			int num = 1; // 새 장바구니 번호 
			
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} 
			
			
			sql = "INSERT INTO basket1(basket_num,mem_num,item_num,item_qty,table_num) VALUES(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num); // basket_num
			pstmt.setInt(2, basket.getMem_num()); // mem_num
			pstmt.setInt(3, basket.getItem_num()); //item_num
			pstmt.setInt(4, basket.getItem_qty()); // item_qty
			pstmt.setInt(5, basket.getTable_num()); // table_num
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertBasket() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

			return insertResult;
		}

	
	// 장바구니 조회할 메서드 ///
	public ArrayList<BasketBean> selectBasketList(int mem_num,int table_num) {

		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();

		try {

			String sql = "SELECT * FROM basket1 WHERE mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, table_num);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				BasketBean basket= new BasketBean();
				basket.setItem_num(rs.getInt("item_num")); // item_num
				basket.setItem_qty(rs.getInt("item_qty")); // item_qty
				basket.setMem_num(rs.getInt("mem_num"));   // mem_num
				basket.setTable_num(rs.getInt("table_num")); // table_num
				
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
	public int updateQtyMinus(BasketBean basket) {
		
		int insertResult = 0;
		
		try {
			
			// 기존 아이템의 수량 조회하기 
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
	
	

	// 장바구니 List항목에서 삭제 
	public int deleteBasketList(BasketBean basket) {
		
		int deleteResult = 0;
		
		try {
			
			String sql = "DELETE FROM basket1 where mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num()); 
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, basket.getItem_num());
			
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
		
		try {
			
			
			// preorder_num 주기 위해 먼저 preorder_num 조회
			String sql ="SELECT MAX(preorder_num) FROM preorder WHERE mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			
			// 하나의 basketList에 담긴 mem_num과 table_num은 모두 동일한것만 담아온것이므로
			// 0번째 mem_num과 table_num을 줌 
			pstmt.setInt(1, basketList.get(0).getMem_num());  
			pstmt.setInt(2, basketList.get(0).getTable_num());
			
			rs = pstmt.executeQuery();
			
			int num = 1;
			
			if(rs.next()) {
				num = rs.getInt("MAX(preorder_num)") + 1 ;
			}
			
			// basketList에 저장된 항목을 모두 전달하기위해 확장 for문 사용 
			for (BasketBean b : basketList) {
			
					
				sql = "INSERT INTO preorder(preorder_num,mem_num,table_num,item_num,item_qty,time) "
						+ "VALUES(?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, b.getMem_num());
				pstmt.setInt(3, b.getTable_num());
				pstmt.setInt(4, b.getItem_num());
				pstmt.setInt(5, b.getItem_qty()); // preorder 테이블의 order_tossed 칼럼은 default=0이므로 따로 안줘도됨 

					
				insertResult = pstmt.executeUpdate();
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertPreOrder() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertResult;
	}
	



	// 장바구니 정보를 preorder로 넘겨준 후 삭제하는 메서드
	// * 장바구니 리스트에서 항목 하나를 삭제 하는 메서드와 헷갈리지 말기 
	public int deleteBasketTable(int mem_num, int table_num) {

		
		int deleteResult = 0;
		
		try {
			
			String sql = "DELETE FROM basket1 where mem_num=? and table_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num); 
			pstmt.setInt(2, table_num);
			
			deleteResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - deleteBasketTable() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}	
		
		return deleteResult;

	}

	
	// 장바구니에 이미 담겨있는 항목인지 확인하기 위한 메서드 
	public boolean selectBasketCheck(BasketBean basket) {
		
		boolean isAlreadyInsert = false;
		
		try {
			
			String sql = "SELECT item_num FROM basket1 WHERE mem_num =? and table_num=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				if(rs.getInt("item_num")== basket.getItem_num()) {
					isAlreadyInsert = true;
				}
				
			}
			
			System.out.println(isAlreadyInsert);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectBasketCheck() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		
		return isAlreadyInsert;
	}

	
	// 장바구니에 있는 항목을 또 장바구니에 추가로 담을 경우 수량 증가(update)시켜주는 메서드 
	public int updateBasket(BasketBean basket) {
		
		int updateResult =0;
		
		try {
			
			// 장바구니에 담겨있는 수량 가져오기 
			String sql ="SELECT item_qty FROM basket1 WHERE mem_num=? and table_num=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, basket.getItem_num());
			ResultSet rs = pstmt.executeQuery();
			
			int qty = 0;
			
			if(rs.next()) {
				
				int alreadyInQty = rs.getInt("item_qty"); // 테이블에 이미 담겨있던 수량 
				qty = alreadyInQty + basket.getItem_qty(); // 최종 수량 = 담겨져있던 수량 + 방금 담은 수량 		
				
				
				sql ="update basket1 set item_qty = ? where mem_num=? and table_num=? and item_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,qty);
				pstmt.setInt(2, basket.getMem_num());
				pstmt.setInt(3, basket.getTable_num());
				pstmt.setInt(4, basket.getItem_num());
				
				updateResult = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - updateBasket() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return updateResult;
	}

	
	// mem_num, table_num 에 해당하는 preorder를 String타입으로 가져오기
	public String selectPreorderInfo(int mem_num, int table_num) {

		
		String preorderInfo = "";
		
		try {
			String sql = "SELECT * FROM preorder WHERE mem_num=? and table_num=? and order_tossed = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, table_num);
			pstmt.setInt(3, 0);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				preorderInfo += (rs.getInt("item_num") + "," + rs.getInt("item_qty") + ","+0+"/");
				// 방금 주문된 항목이므로 reviewCk = 0 임 

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

	
	// preorder 테이블에 있는 정보를 reviewInfo 테이블에 넣기 위한 메서드
	public int insertOrder(BasketBean basket, String orderInfo) {
		
		int insertResult = 0;
		
		try {
			
			
			String sql = "INSERT INTO reviewinfo(mem_num,table_num,orderInfo,orderTime) VALUES(?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setString(3, orderInfo); // string형태로 만들어놓은 preorder정보(item_num,item_qty,reviewCk)
			
			insertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertPreOrder() 메서드 " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		
		return insertResult;
	
	}

	
	
	// preorder에 있는 항목을 order 테이블에 저장하는 메서드 
	public int insertOrder(BasketBean basket) {
		
		int insertSuccess =0;
		
		try {
			
			// order_num을 주기 위해 존재하는 order_num 구하기 
			String sql = "SELECT MAX(order_num) FROM order1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 0;

			if(rs.next()) {
				num = rs.getInt("MAX(order_num)") + 1 ;
			}
			
			// product 테이블에 있는 가격 정보와 preorder 테이블에 있는 정보를 가져오기 위해 join절 사용
			// item_qty는 최종수량을 구해야하므로 sum함수사용 
			sql = "select p.item_num, i.item_price, (sum(item_qty) * i.item_price) as 'total_price',sum(item_qty) as 'item_qty'" + 
					"from preorder p join product i on (p.item_num = i.item_num)" + 
					"where p.mem_num=? and p.table_num=? and order_tossed = ? group by p.item_num";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, 0); // 결제 여부 확인하기 위한 order_tossed
			
			rs = pstmt.executeQuery();
			

			// 가져온 값을 order 테이블에 바로 저장하는 insert구문 
			while(rs.next()) {
				
				sql = "insert into order1(order_num,mem_num,table_num,order_time,item_num,item_qty,item_price,total_price) "
						+ "values(?,?,?,now(),?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, basket.getMem_num());
				pstmt.setInt(3, basket.getTable_num());
				pstmt.setInt(4, rs.getInt("item_num"));
				pstmt.setInt(5, rs.getInt("item_qty"));
				pstmt.setInt(6, rs.getInt("item_price"));
				pstmt.setInt(7, rs.getInt("total_price"));
				

				insertSuccess = pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - insertOrder() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return insertSuccess;
	}

	
	// mem_num, table_num에 해당하는 preorder가 있는지 먼저 확인! 
	public int selectPreorderCount(BasketBean basket) {
		
		int preorderCount = 0;
		
		try {
			String sql = "SELECT COUNT(item_num) FROM preorder where mem_num=? and table_num=? and order_tossed=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, basket.getMem_num());
			pstmt.setInt(2, basket.getTable_num());
			pstmt.setInt(3, 0); // 결제완료 된 적 없는 행만 select 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				preorderCount = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO - selectPreorderCount() 메서드 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return preorderCount;
	}


	
	
	
}
	//-----------------------------------------------------------------


