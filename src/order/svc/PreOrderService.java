package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


import order.dao.OrderDAO;
import vo.BasketBean;


public class PreOrderService {

	// basket의 정보를 preorder테이블에 저장하기 위한 메서드//
	public int basketToPreOrder(ArrayList<BasketBean> basketList) {
		
		int insertResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		insertResult = orderDAO.insertPreOrder(basketList);
		
		if (insertResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return insertResult;
	}





}
