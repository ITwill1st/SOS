package vo;

import java.sql.Timestamp;

public class OrderDTO {
	
	int order_num;
	int pre_num;
	int mem_num;
	int basket_num;
	int table_num;
	int item_num;
	int item_qty;
	int item_price;
	int total_price;
	Timestamp order_datetime;
	int review_chk;
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getPre_num() {
		return pre_num;
	}
	public void setPre_num(int pre_num) {
		this.pre_num = pre_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getBasket_num() {
		return basket_num;
	}
	public void setBasket_num(int basket_num) {
		this.basket_num = basket_num;
	}
	public int getTable_num() {
		return table_num;
	}
	public void setTable_num(int table_num) {
		this.table_num = table_num;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getItem_qty() {
		return item_qty;
	}
	public void setItem_qty(int item_qty) {
		this.item_qty = item_qty;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public Timestamp getOrder_datetime() {
		return order_datetime;
	}
	public void setOrder_datetime(Timestamp order_datetime) {
		this.order_datetime = order_datetime;
	}
	public int getReview_chk() {
		return review_chk;
	}
	public void setReview_chk(int review_chk) {
		this.review_chk = review_chk;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	
	
		
}
