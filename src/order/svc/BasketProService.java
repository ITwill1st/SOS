package order.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import order.dao.OrderDAO;
import vo.BasketBean;
import vo.ProductInfoBean;

public class BasketProService {

	
	// 장바구니에 담긴 수량 조회 
	public int getBasketCount(int mem_num) {
		
		int basketCount = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		basketCount = orderDAO.selectCountBasket(mem_num);
		
		close(con);
		
		return basketCount;
	}

	
	// 장바구니 담기 
	public int insertBasket(BasketBean basket) {

		int basketSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		basketSuccess = orderDAO.insertBasket(basket);
	
		
		if (basketSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return basketSuccess;
	}

	
	// 장바구니 수정할 서비스!! - 추가 수정 필요 
	public int updateBasket(BasketBean basket) {
		int basketUpdateSuccess = 0;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		basketUpdateSuccess = orderDAO.updateBasket(basket);
		
		
		if (basketUpdateSuccess>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		
		close(con);
		
		return basketUpdateSuccess;
	}


	// id에 해당하는 담겨져있는 basketList가져올 메서드 
	public ArrayList<ProductInfoBean> getBasketList(int mem_num) {
		
		ArrayList<ProductInfoBean> basketList = null;
		
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		// 담겨져있는 basketList 담아오기 
		basketList = orderDAO.selectBasketList(mem_num);

		close(con);
		
		return basketList;
	}


	// 담을 리스트와 담긴 리스트 비교 및 basket_info 수정할 메서드 
	public ArrayList<ProductInfoBean> compareBasket(String basket_info, ArrayList<ProductInfoBean> basketList) {
		
		String[] item = basket_info.split(",");
		// 담을 메뉴의 item_num과 item_qty
		int item_num = Integer.parseInt(item[0]);
		int item_qty = Integer.parseInt(item[1]);
		int review_ch = Integer.parseInt(item[2]);
		
		// 담을 메뉴를 담을 ProductInfoBean
		ProductInfoBean p = new ProductInfoBean();
		p.setItem_num(item_num);
		p.setItem_qty(item_qty);
		p.setReview_ck(review_ch);
		
		boolean isChanged = false;
		
		for(int i = 0; i<basketList.size(); i++) {		
			
			//해당제품이있다면
			if(basketList.get(i).getItem_num() == item_num) {
		
				ProductInfoBean p2 = new ProductInfoBean();			
				p2.setItem_num(item_num);
				p2.setItem_qty(basketList.get(i).getItem_qty()+item_qty);
				p2.setReview_ck(review_ch);
				basketList.set(i, p2);	
				
				//basketList가 바꼈기에 아래 if문은 동작하지않는다.
				isChanged = true;
			}
			
		}
		
		//해당제품이 없다면
		if(!isChanged){
			//간단하다
			
			//넣어준객체를 list에 add한다
			basketList.add(p);
			
		}
		
		
		return basketList;
	}

	

	
}
