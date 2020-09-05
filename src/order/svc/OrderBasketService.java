package order.svc;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import order.dao.OrderDAO;
import vo.BasketBean;

public class OrderBasketService {

	public int insertOrder(BasketBean basket) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		orderSuccess = orderDAO.insertOrder(basket);
	
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		
		return orderSuccess;
		
	}

}
