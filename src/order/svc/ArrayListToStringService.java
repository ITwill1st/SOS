package order.svc;

import java.util.ArrayList;

import vo.ProductInfoBean;

public class ArrayListToStringService {

	public String toString(ArrayList<ProductInfoBean> basketList) {
		
		String basket_info = "";
		
		
		for(int i=0; i<basketList.size(); i++) {
			basket_info += (basketList.get(i).getItem_num() + "," + basketList.get(i).getItem_qty() + "/");
		}
		
		return basket_info;
	}

}
