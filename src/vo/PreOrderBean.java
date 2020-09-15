package vo;

import java.sql.Timestamp;

public class PreOrderBean {

	private String json_basket;
	private int pre_mem_num;
	private int pre_table_num;
	private Timestamp pre_time;
	private int order_tossed;
	private int total_price;
	
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getJson_basket() {
		return json_basket;
	}
	public void setJson_basket(String json_basket) {
		this.json_basket = json_basket;
	}
	public int getPre_mem_num() {
		return pre_mem_num;
	}
	public void setPre_mem_num(int pre_mem_num) {
		this.pre_mem_num = pre_mem_num;
	}
	public int getPre_table_num() {
		return pre_table_num;
	}
	public void setPre_table_num(int pre_table_num) {
		this.pre_table_num = pre_table_num;
	}
	public Timestamp getPre_time() {
		return pre_time;
	}
	public void setPre_time(Timestamp pre_time) {
		this.pre_time = pre_time;
	}
	public int getOrder_tossed() {
		return order_tossed;
	}
	public void setOrder_tossed(int order_tossed) {
		this.order_tossed = order_tossed;
	}
	
	
	
}
