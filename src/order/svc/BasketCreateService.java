package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import order.dao.OrderDAO;
import vo.BasketBean;

public class BasketCreateService {

	// 장바구니 생성을 위한 메서드 
	public int createBasket(int mem_num) {
		
		// 초기값 0으로 지정 
		int insertResult = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		insertResult = orderDAO.insertBasket(mem_num);
		

		// 장바구니가 성공적으로 생성되었는지 확인 
		if(insertResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return insertResult;
	}

	

		
	
	
	
	
}
