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

		System.out.println("ReviewCheckUpdateService - reviewCheckUpdate");
		int a = 0;
		ReviewListService service = new ReviewListService();
		ArrayListToStringService arrayListToStringService = new ArrayListToStringService();
		ArrayList<OrderDTO> orderList = service.getOrderList(mem_num);
		ArrayList<ProductInfoBean> order_info = null;
		
		Connection con = getConnection();
		ReviewDAO rda = ReviewDAO.getInstance();
		rda.setConnection(con);
		
		for (int i = 0; i < orderList.size(); i++) {
			
			OrderDTO orderDTO = orderList.get(i);
			int order_num = orderDTO.getOrder_num();
			StringToArrayListService service_stringToArrayList = new StringToArrayListService();
			 order_info = service_stringToArrayList.getOrderInfoArray(orderDTO);
			 
			 for(int x = 0 ; x < order_info.size() ; x++) {
					
					ProductInfoBean pib = order_info.get(x);
					pib.setReview_ck(1);
									
			}
			 
			 String order_info_toString = arrayListToStringService.toString(order_info);
			 a = rda.reviewCheckerUpdate(order_num, order_info_toString);			
		}
		
		if(a > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	}
	
}
