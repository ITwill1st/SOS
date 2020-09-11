package order.svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.ProductBean;
import vo.ProductInfoBean;

public class BasketDetailService {

	public ArrayList<ProductBean> setItemnum(ArrayList<ProductInfoBean> basketList) {
		
		ArrayList<ProductBean> BasketDetail = new ArrayList<ProductBean>();
	
		for(ProductInfoBean pib : basketList) {
			
			ProductBean p = new ProductBean();
			p.setItem_num(pib.getItem_num());
			
			BasketDetail.add(p);
		}
		
		return BasketDetail;
	}

	public ArrayList<ProductBean> getItemInfo(ArrayList<ProductBean> itemNumList) {
		
		ArrayList<ProductBean> itemInfo = null;
		
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		itemInfo = orderDAO.selectItemInfo(itemNumList);
		
//		for(ProductBean p : itemInfo) {
//			System.out.println(p.getItem_num());
//			System.out.println(p.getItem_name());
//		}
		
		return itemInfo;
	}

}
