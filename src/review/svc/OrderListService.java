package review.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import review.dao.ReviewDAO;
import vo.OrderDTO;

public class OrderListService {
	
	
	public ArrayList<OrderDTO> getOrderList(int mem_num) {

		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);

		// mem_num을 이용해 구매내역 조회후 orderList 가져오기(리뷰체커로 안된것만 가져옴)
		ArrayList<OrderDTO> orderList = rda.getOrderList(mem_num);

		close(con);

		return orderList;
	}
	
	public ArrayList<OrderDTO> getOrderList_orderNum(int order_num) {
		
		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);

		// mem_num을 이용해 구매내역 조회후 orderList 가져오기(리뷰체커로 안된것만 가져옴)
		ArrayList<OrderDTO> orderList = rda.getOrderList_orderNum(order_num);

		close(con);

		return orderList;
	}

}
