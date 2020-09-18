package order.svc;


import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;
import vo.ProductInfoBean;

public class OrderService {


	// 주문에 성공했기 때문에 preorder의 order_tossed 값을 1로 바꿔줌 //
	public int updatePreOrder(int mem_num, int table_num) {
		
		int updateSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 주문에 성공했기 때문에 preorder의 order_tossed 값을 1로 바꿔줌 
		updateSuccess = orderDAO.updatePreOrder(mem_num,table_num);
		
		if (updateSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateSuccess;
	}



	// 중복되는 아이템의 경우 qty를 합쳐주는 메서드 
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


	// preorder 테이블에 있는 정보를 reviewInfo 테이블에 넣기 위한 메서드
	public int insertOrder(BasketBean basket, String orderInfo) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// order -> reviewInfo
		orderSuccess = orderDAO.insertOrder(basket,orderInfo);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
	
	}

	
	// preorder에 저장된 정보를 order테이블에 저장하기 위한 메서드 
	public int insertOrder(BasketBean basket) {
		
		int orderSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// preorder 테이블에 있는 정보를  order 테이블에 저장 
		orderSuccess = orderDAO.insertOrder(basket);
		
		if (orderSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return orderSuccess;
	}


	// mem_num, table_num에 해당하는 preorder가 있는지 먼저 확인! 
	public int getPreorderCount(BasketBean basket) {
		
		
		int preorderCount = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// preorder 테이블에 있는 정보를  order 테이블에 저장 
		preorderCount = orderDAO.selectPreorderCount(basket);
				
		close(con);
		
		return preorderCount;
	}


}
