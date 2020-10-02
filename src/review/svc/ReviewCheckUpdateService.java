package review.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import order.svc.ArrayListToStringService;
import order.svc.StringToArrayListService;
import review.dao.ReviewDAO;
import vo.OrderDTO;
import vo.ProductBean;
import vo.ProductInfoBean;

public class ReviewCheckUpdateService {

	public void reviewCheckUpdate(int mem_num) {
		
		int a = 0;
		OrderListService orderListService = new OrderListService();
		ArrayList<OrderDTO> orderList = orderListService.getOrderList(mem_num);
		
		Connection con = getConnection();
		ReviewDAO rda = ReviewDAO.getInstance();
		rda.setConnection(con);
		
		for (int i = 0; i < orderList.size(); i++) {
			
			OrderDTO orderDTO = orderList.get(i);
			a = rda.reviewCheckerUpdate(orderDTO.getOrder_num());
			
		}
		
		if(a > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	}
	
	public void store_reviewCheckUpdate(int order_num) {
		
		Connection con = getConnection();
		
		ReviewDAO rda = ReviewDAO.getInstance();
		
		rda.setConnection(con);
		
		int isSuccess = rda.store_reviewCheckerUpdate(order_num);
		
		if(isSuccess > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
	}
	
}
