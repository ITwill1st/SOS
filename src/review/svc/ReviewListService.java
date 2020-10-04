package review.svc;

import java.sql.Connection;
import java.util.ArrayList;

import order.svc.StringToArrayListService;

import static db.JdbcUtil.*;
import review.dao.ReviewDAO;
import vo.OrderDTO;
import vo.ProductBean;
import vo.ProductInfoBean;

public class ReviewListService {
	// orders 테이블에서 리뷰 해야할 것들 가져오는 서비스 

	public ProductBean getProduct(int item_num) {

		ProductBean productBean = null;

		Connection con = getConnection();

		ReviewDAO rda = ReviewDAO.getInstance();

		rda.setConnection(con);

		// 오더리스트 안에있는 item_num을 통해 메뉴정보 가져오기
		productBean = rda.getProduct(item_num);

		close(con);

		return productBean;
	}

	public ArrayList<ProductBean> getProduct_list(int mem_num) {

		ArrayList<ProductBean> product_list = new ArrayList<ProductBean>();
		OrderListService orderListService = new OrderListService();
		ArrayList<OrderDTO> orderList = orderListService.getOrderList(mem_num);

		for(int i = 0 ; i < orderList.size() ; i++) {
			OrderDTO orderDTO = orderList.get(i);
			ProductBean productBean = getProduct(orderDTO.getItem_num());
			
			product_list.add(productBean);
		}

		return product_list;

	}

}
