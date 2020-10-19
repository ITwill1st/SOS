package order.svc;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;

import static db.JdbcUtil.*;
import vo.ProductBean;

public class MainPageProService {
	
	// product 테이블에 담긴 전체 메뉴리스트 가져오기 ///
	public ArrayList<ProductBean> getMenuList() {
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		// OrderDAO의 selectMenuList()메서드 호출하여 전체 메뉴 가져와서 ArrayList형식으로 담기 
		ArrayList<ProductBean> menuList = dao.selectMenuList();
		
		close(con);
		
		return menuList;
	}

	// category 가져오기 
	public ArrayList<ProductBean> getCategory() {
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		// OrderDAO의 selectCategory()메서드 호출하여 전체 카테고리 가져와서 ArrayList형식으로 담기 
		
		ArrayList<ProductBean> category = dao.selectCategory();
		
		close(con);
		
		return category;
		
	}
	
	
}
