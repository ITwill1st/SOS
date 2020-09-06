package vo;

import java.sql.Timestamp;

public class OrderDTO {
	
	int order_num;
	int mem_num;
	String order_info;
	int table_num;
	Timestamp order_datetime;
	int order_confirm;
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getOrder_info() {
		return order_info;
	}
	public void setOrder_info(String order_info) {
		this.order_info = order_info;
	}
	public int getTable_num() {
		return table_num;
	}
	public void setTable_num(int table_num) {
		this.table_num = table_num;
	}
	public Timestamp getOrder_datetime() {
		return order_datetime;
	}
	public void setOrder_datetime(Timestamp order_datetime) {
		this.order_datetime = order_datetime;
	}
	public int getOrder_confirm() {
		return order_confirm;
	}
	public void setOrder_confirm(int order_confirm) {
		this.order_confirm = order_confirm;
	}
		
}
