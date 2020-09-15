package order.svc;


import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;
import vo.PreOrderBean;
import vo.ProductBean;
import vo.ProductInfoBean;

public class OrderService {


	// 주문에 성공했기 때문에 preorder의 order_tossed 값을 1로 바꿔줌 //
	public int updatePreOrder(int mem_num, int table_num) {
		
		int updateSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 주문에 성공했기 때문에 preorder의 order_tossed 값을 1로 바꿔줌 
		updateSuccess = orderDAO.updatePreOrder(mem_num,table_num);
		
		if (updateSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateSuccess;
	}

	
	// preorder에 담긴 항목들 가져오기 위한 메서드 //
	public ArrayList<BasketBean> getPreOrder(BasketBean basket) {
		
		ArrayList<BasketBean> preorder = null;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		preorder = orderDAO.selectPreOrder(basket);
		
		close(con);
		
		return preorder;

	}

	
	// preorder 정보를 order 테이블에 담기 위한 메서드 //
	public int insertOrder(ArrayList<BasketBean> preorderList) {

		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// order 메서드 
		orderSuccess = orderDAO.insertOrder(preorderList);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
		
	}




}
