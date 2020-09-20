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
		ReviewListService service = new ReviewListService();
		ArrayList<OrderDTO> orderList = service.getOrderList(mem_num);
		
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
	
}
