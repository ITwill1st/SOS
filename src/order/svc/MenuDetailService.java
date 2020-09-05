package order.svc;

import vo.MenuBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import order.dao.OrderDAO;

public class MenuDetailService {

	// 단일메뉴 하나에 대한 상세정보 조회를 위한 메서드 
	public MenuBean selectDetail(int item_num) {
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		
		MenuBean menuBean = orderDAO.selectDetail(item_num);
		
		close(con);
		
		return menuBean;
	}

}
