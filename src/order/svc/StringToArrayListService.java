package order.svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import order.dao.OrderDAO;
import vo.BasketBean;
import vo.ProductInfoBean;

public class StringToArrayListService {

	public ArrayList<ProductInfoBean> getBasketInfoArray(BasketBean basket) {
		
		ArrayList<ProductInfoBean> dbBasketInfo= new ArrayList<ProductInfoBean>();
		
		String[] basketInfo = basket.getBasket_info().split("/");
		
		if(basketInfo.length>=1) {
			

			for (String info : basketInfo) {
				
				ProductInfoBean pib = new ProductInfoBean();
				
				String[] basketInfo2 = info.split(",");
				
				pib.setItem_num(Integer.parseInt(basketInfo2[0]));
				pib.setItem_qty(Integer.parseInt(basketInfo2[1]));
				pib.setReview_ck(Integer.parseInt(basketInfo2[2]));
				
				dbBasketInfo.add(pib);
				
			}
			
			
		}
		
		return dbBasketInfo;
	}


	
	
	
	
	
}
