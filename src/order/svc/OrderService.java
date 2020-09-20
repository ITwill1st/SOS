package order.svc;


import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;
import vo.ProductInfoBean;

public class OrderService {


	
	// preorder에 저장된 정보를 order테이블에 저장하기 위한 메서드 
	public int insertOrder(BasketBean basket) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// preorder 테이블에 있는 정보를  order 테이블에 저장 
		orderSuccess = orderDAO.insertOrder(basket);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
	}


	// mem_num, table_num에 해당하는 preorder가 있는지 먼저 확인! 
	public int getPreorderCount(BasketBean basket) {
		
		
		int preorderCount = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// preorder 테이블에 있는 정보를  order 테이블에 저장 
		preorderCount = orderDAO.selectPreorderCount(basket);
				
		close(con);
		
		return preorderCount;
	}


	// 결제버튼 눌렀을 시 preorder-> order로 넘어가고 
	// 기존의 preorder 삭제됨 
	public int deletePreOrder(int mem_num, int table_num) {
		
		int deleteSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// preorder 테이블에 있는 정보를  order 테이블에 저장 
		deleteSuccess = orderDAO.deletePreOrder(mem_num, table_num);
		
		if (deleteSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return deleteSuccess;
		

	}


}
