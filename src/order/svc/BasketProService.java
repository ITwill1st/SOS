package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;


public class BasketProService {

	
	// 장바구니에 담긴 수량 조회 ///
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

	
	// 처음 담는 항목 장바구니 담기 ///
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
	
	// mem_num과 table_num에 해당하는  basketList가져올 메서드  ///
	public ArrayList<BasketBean> getBasketList(int mem_num, int table_num) {
		
		ArrayList<BasketBean> basketList = null;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 장바구니 담아오기 
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
	
	// 장바구니리스트의 수량 -1 하기 위한 메서드 //
	public int insertQtyMinus(BasketBean basket) {
		
		// 성공 여부 확인을 위한 변수 지정 
		int updateResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 수량 +1 하기위한 메서드 호출 
		updateResult = orderDAO.updateQtyMinus(basket);
		
		if (updateResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateResult;
	}



	
	// 장바구니List에서 항목 하나에 대한 삭제 
	public int deleteBasket(BasketBean basket) {
		
		int deleteResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		deleteResult = orderDAO.deleteBasketList(basket);
		
		if (deleteResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return deleteResult;

	}


	// preorder로 보내준 후 장바구니에서 완전히 삭제 
	public int deleteBasketTable(int mem_num, int table_num) {

		int deleteResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		deleteResult = orderDAO.deleteBasketTable(mem_num,table_num);
		
		if (deleteResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return deleteResult;

	}


	// 장바구니에 이미 담겨있는 항목인지 확인하기 위한 메서드 
	public boolean basketInsertCheck(BasketBean basket) {
		
		boolean isAlreadyInsert = false;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		isAlreadyInsert = orderDAO.selectBasketCheck(basket);
		
		return isAlreadyInsert;
	}


	// 장바구니에 이미 담겨있는 항목을 또 추가로 담을 경우 수량 업데이트 해주는 메서드 
	public int updateBasket(BasketBean basket) {
		
		int updateResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		updateResult = orderDAO.updateBasket(basket);
		
		if (updateResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateResult;
		
		
	}

	// mem_num, table_num 에 해당하는 preorder를 String타입으로 가져오기
	public String getPreOrder(int mem_num, int table_num) {
		
		String preorderInfo = null;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		preorderInfo = orderDAO.selectPreorderInfo(mem_num,table_num);
		
		close(con);
		
		System.out.println(preorderInfo);
		
		return preorderInfo;
		

	}




	
}
