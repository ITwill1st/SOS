package vo;


public class RsvDTO {
	
	private int rsv_num;
	private String rsv_date;
	private String rsv_time;
	private int rsv_pax;
	private int rsv_check;
	private String mem_email;
	
	public String getRsv_time() {
		return rsv_time;
	}
	public void setRsv_time(String rsv_time) {
		this.rsv_time = rsv_time;
	}
	public int getRsv_num() {
		return rsv_num;
	}
	public void setRsv_num(int rsv_num) {
		this.rsv_num = rsv_num;
	}
	public String getRsv_date() {
		return rsv_date;
	}
	public void setRsv_date(String rsv_date) {
		this.rsv_date = rsv_date;
	}
	public int getRsv_pax() {
		return rsv_pax;
	}
	public void setRsv_pax(int rsv_pax) {
		this.rsv_pax = rsv_pax;
	}
	public int getRsv_check() {
		return rsv_check;
	}
	public void setRsv_check(int rsv_check) {
		this.rsv_check = rsv_check;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
}
