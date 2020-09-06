package order.svc;


import static db.JdbcUtil.*;
import java.sql.Connection;

import order.dao.OrderDAO;
import vo.BasketBean;

public class OrderBasketService {

	public int insertOrder(BasketBean basket) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		
		// order 메서드 
		orderSuccess = orderDAO.insertOrder(basket);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
		
	}

	public int deleteBasket(int mem_num) {
		
		int deleteSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// order 메서드 
		deleteSuccess = orderDAO.deleteOrder(mem_num);
				
		if (deleteSuccess>0) {
				commit(con);
		} else {
				rollback(con);
		}
				
		close(con);
				
		return deleteSuccess;
	}

}
