package vo;


public class RsvDTO {
	
	int rsv_num;
	String rsv_date;
	int rsv_pax;
	int rsv_check;
	String mem_email;
	
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
