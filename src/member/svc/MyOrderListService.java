package member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import review.dao.ReviewDAO;
import vo.OrderDTO;

public class MyOrderListService {
	
	public ArrayList<OrderDTO> getOrderList(int mem_num) {

		Connection con = getConnection();

		MemberDAO mda = MemberDAO.getInstance();

		mda.setConnection(con);

		ArrayList<OrderDTO> orderList = mda.getOrderList(mem_num);
		

		for(int i = 0 ; i < orderList.size() - 1 ; i++) {
			for(int j = i+1 ; j < orderList.size() ; j++) {
				if(orderList.get(i).getOrder_num() == orderList.get(j).getOrder_num()) {
					orderList.get(i).setItem_qty(orderList.get(i).getItem_qty() + orderList.get(j).getItem_qty());
					orderList.get(i).setTotal_price(orderList.get(i).getTotal_price() + orderList.get(j).getTotal_price());
				}
			}
			ArrayList<OrderDTO> myOrderList = orderList;
		}
		
		
		close(con);

		return orderList;
	}

}
