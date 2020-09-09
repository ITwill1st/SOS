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

	public ArrayList<ProductBean> getProduct_list(int mem_num) {

		ArrayList<ProductInfoBean> order_info = null;
		ArrayList<ProductBean> product_list = new ArrayList<ProductBean>();
		ArrayList<OrderDTO> orderList = getOrderList(mem_num);

		for (int i = 0; i < orderList.size(); i++) {
			OrderDTO orderDTO = orderList.get(i);
			StringToArrayListService service_stringToArrayList = new StringToArrayListService();
			order_info = service_stringToArrayList.getOrderInfoArray(orderDTO);

			for (int x = 0; x < order_info.size(); x++) {

				ProductInfoBean pib = order_info.get(x);
				int item_num = pib.getItem_num();
				int review_ck = pib.getReview_ck();
				if (review_ck == 0) {
					ProductBean productBean = getProduct(item_num);
					product_list.add(productBean);
				}

			}
		}

		return product_list;

	}

}
