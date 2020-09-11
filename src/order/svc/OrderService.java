package order.svc;


import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;
import vo.ProductInfoBean;

public class OrderService {

	public int insertOrder(BasketBean basket) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		
		// order 메서드 
		orderSuccess = orderDAO.insertOrder(basket);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
		
	}

	public int deleteBasket(int mem_num) {
		
		int deleteSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// order 메서드 
		deleteSuccess = orderDAO.deleteOrder(mem_num);
				
		if (deleteSuccess>0) {
				commit(con);
		} else {
				rollback(con);
		}
				
		close(con);
				
		return deleteSuccess;
	}

	public String PreOrderToOrder(ArrayList<ProductInfoBean> preorderarray) {
		
		
		for (int i = 0; i < preorderarray.size() - 1; i++) {

			for(int j = i + 1; j<preorderarray.size(); j++) {
				
				if (preorderarray.get(i).getItem_num() == preorderarray.get(j).getItem_num()) {
					
//					System.out.println("원본: " + list.get(i).getItem_no() + ", " + list.get(i).getItem_qty());
//					System.out.println("대상: " + list.get(j).getItem_no() + ", " + list.get(j).getItem_qty());
					
					preorderarray.get(i).setItem_qty(preorderarray.get(i).getItem_qty() + preorderarray.get(j).getItem_qty());
					preorderarray.get(j).setItem_qty(0);
									
				}
			}
		}
		

			
		String listToString = "";
		
		for(ProductInfoBean pib : preorderarray) {
			
			listToString += (pib.getItem_num() + "," + pib.getItem_qty() + "/");
			
		}

		
		System.out.println("0 지우기전: " + listToString);
		
		
		
		
		ArrayList<ProductInfoBean> listCopy = new ArrayList<ProductInfoBean>();
		
		listCopy = preorderarray;
		
		for(int i = 0; i<preorderarray.size(); i++){
			
			for(int x = 0; x < listCopy.size(); x++){
				if(preorderarray.get(x).getItem_qty()==0) {
					listCopy.remove(x);
				}				
			}
						
		}
		
		
		
		String listToString2 = "";
		
		for(ProductInfoBean pib : listCopy) {
			
			listToString2 += (pib.getItem_num() + "," + pib.getItem_qty() + "," + pib.getReview_ck()+ "/");
			
		}

		System.out.println("0 지운 후: " + listToString2);	
		
		
		return listToString2;
	}

}
