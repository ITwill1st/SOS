package order.svc;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;

import static db.JdbcUtil.*;

import vo.MenuBean;

public class MenuListService {

	public ArrayList<MenuBean> getMenuList() {
		
		
		// jdbc 연결 
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		
		// OrderDAO의 selectMenuList()메서드 호출하여 전체 메뉴 가져와서 ArrayList형식으로 담기 
		ArrayList<MenuBean> menuList = dao.selectMenuList();
		
		close(con);
		
		return menuList;
	}

	
	
}