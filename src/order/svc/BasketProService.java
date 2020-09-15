package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;


public class BasketProService {

	
	// 장바구니에 담긴 수량 조회 //
	public int getBasketCount(BasketBean basket) {
		
		int basketCount = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// basket에는 mem_num과 table_num이 담겨 있음
		basketCount = orderDAO.selectBasketCount(basket);
		
		close(con);
		
		return basketCount;
	}

	
	// 장바구니 담기 //
	public int insertBasket(BasketBean basket) {

		int insertSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		insertSuccess = orderDAO.insertBasket(basket);
	
		
		if (insertSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return insertSuccess;
	}
	
	// mem_num과 table_num에 해당하는  basketList가져올 메서드  //
	public ArrayList<BasketBean> getBasketList(int mem_num, int table_num) {
		
		ArrayList<BasketBean> basketList = null;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 담겨져있는 basketList 담아오기 
		basketList = orderDAO.selectBasketList(mem_num,table_num);

		close(con);
		
		return basketList;
	}

	
	// 장바구니 수량 +1 하기 위한 메서드  //
	public int insertQtyPlus(BasketBean basket) {
		
		int insertResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		insertResult = orderDAO.insertQtyPlus(basket);
		
		if (insertResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return insertResult;
		
	
	}
	
	// 장바구니 수량 -1 하기 위한 메서드 //
	public int insertQtyMinus(BasketBean basket) {
		
		int insertResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		insertResult = orderDAO.insertQtyMinus(basket);
		
		if (insertResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return insertResult;
	}


	// 장바구니 항목을 preorder에 넘긴 후 preorder_tossed = 1로 바꿔주는 메서드  //
	public int updateBasket(int mem_num, int table_num) {
	
		int updateResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		updateResult = orderDAO.updateBasket(mem_num,table_num);
		
		if (updateResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateResult;

	}

	
	// basket 삭제 위한 메서드 //
	public int deleteBasket(BasketBean basket) {
		
		int deleteResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		deleteResult = orderDAO.deleteBasket(basket);
		
		if (deleteResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return deleteResult;

	}






	
}
