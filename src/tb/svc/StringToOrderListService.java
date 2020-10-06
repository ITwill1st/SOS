package tb.svc;

import java.util.ArrayList;

import vo.BasketBean;

public class StringToOrderListService {

	public ArrayList<BasketBean> setBasketBean(int table_num, int mem_num, String totalOrder) {

		String[] str2 = totalOrder.split("/");
	
		ArrayList<BasketBean> list = new ArrayList<BasketBean>();
		
		for (int i = 0; i < str2.length; i++) {

			BasketBean bb = new BasketBean();
			
			String[] str3 = str2[i].split(",");
			
			bb.setMem_num(mem_num);
			bb.setTable_num(table_num);
			bb.setItem_num(Integer.parseInt(str3[0]));
			bb.setItem_qty(Integer.parseInt(str3[1]));

			System.out.println("회원번호: " + mem_num + ", 테이블번호: " + table_num + ", 아이템번호: " + str3[0] + ", 수량: " + str3[1]);
			
			
			list.add(bb);
			
		}
		
		
		return list;
	}

}
