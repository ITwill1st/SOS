package review.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import review.dao.ReviewDAO;
import vo.OrderDTO;
import vo.ProductBean;

public class ReviewListService {

	public ArrayList<OrderDTO> getOrderList(int mem_num) {
			
		Connection con = getConnection();
		
		ReviewDAO rda = ReviewDAO.getInstance();
		
		rda.setConnection(con);
		
		ArrayList<OrderDTO> orderList = rda.getOrderList(mem_num);

		close(con);
		
		return orderList;
	}

	public ProductBean getProduct(int item_num) {
		
		ProductBean productBean = null;
		
		Connection con = getConnection();
		
		ReviewDAO rda = ReviewDAO.getInstance();
		
		rda.setConnection(con);
		
		productBean = rda.getProduct(item_num);
		
		close(con);
		
		return productBean;
	}

}
