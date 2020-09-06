package order.svc;

import java.util.ArrayList;

import vo.ProductInfoBean;

public class BasketQtyChangeService {

	public ArrayList<ProductInfoBean> BasketQtyMinus(int item_num, ArrayList<ProductInfoBean> basketList) {
		
		
		for(int i = 0; i<basketList.size(); i++) {		
			
			//해당제품이 있다면 수량을 -1로 저장 
			if(basketList.get(i).getItem_num() == item_num) {
		
				
				ProductInfoBean p2 = new ProductInfoBean();			
				p2.setItem_num(item_num);
				p2.setItem_qty(basketList.get(i).getItem_qty()-1);		
				p2.setReview_ck(basketList.get(i).getReview_ck());
				basketList.set(i, p2);	
			}
			
		}
		
		return basketList;
	}

	public ArrayList<ProductInfoBean> BasketQtyPlus(int item_num, ArrayList<ProductInfoBean> basketList) {
	
		for(int i = 0; i<basketList.size(); i++) {		
			
			//해당제품이 있다면 수량을 +1로 저장 
			if(basketList.get(i).getItem_num() == item_num) {
		
				ProductInfoBean p2 = new ProductInfoBean();			
				p2.setItem_num(item_num);
				p2.setItem_qty(basketList.get(i).getItem_qty()+1);		
				p2.setReview_ck(basketList.get(i).getReview_ck());
				
				basketList.set(i, p2);	
			}
			
		}
		
		
		return basketList;
	}



}
