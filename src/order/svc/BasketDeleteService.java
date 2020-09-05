package order.svc;

import java.util.ArrayList;

import vo.ProductInfoBean;

public class BasketDeleteService {

	public ArrayList<ProductInfoBean> BasketDelete(int item_num, ArrayList<ProductInfoBean> basketList) {
		
		
		for(int i = 0; i<basketList.size(); i++) {		
			//해당제품이있다면
			if(basketList.get(i).getItem_num() == item_num) {
				basketList.remove(i);
			}
		}
		
		return basketList;

	}

}
