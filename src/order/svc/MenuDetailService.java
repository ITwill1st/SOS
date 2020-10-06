package order.svc;


import vo.ProductBean;

import static db.JdbcUtil.*;

import java.sql.Connection;

import order.dao.OrderDAO;

public class MenuDetailService {

	// 단일메뉴 클릭시 상품에 대한 상세정보 조회를 위한 메서드 //
	public ProductBean getDetail(int item_num) {
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ProductBean productBean = orderDAO.selectDetail(item_num);
		
		close(con);
		
		return productBean;
	}

	// 단일메뉴 클릭시 상품의 조회수 +1 
	public int updateReadcount(int item_num) {
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		int updateSuccess = orderDAO.updateReadcount(item_num);
		
		if(updateSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	
		return updateSuccess;
	}

}
