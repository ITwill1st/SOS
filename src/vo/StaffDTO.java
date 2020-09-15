package vo;

import java.sql.Timestamp;

public class StaffDTO {
	private int emp_idx;
	private String emp_name;
	private String emp_contact;
	private String emp_id;
	private String emp_passwd;
	private Timestamp emp_hire_date;
	private String emp_birth;
	
	
	public int getEmp_idx() {
		return emp_idx;
	}
	public void setEmp_idx(int emp_idx) {
		this.emp_idx = emp_idx;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_contact() {
		return emp_contact;
	}
	public void setEmp_contact(String emp_contact) {
		this.emp_contact = emp_contact;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_passwd() {
		return emp_passwd;
	}
	public void setEmp_passwd(String emp_passwd) {
		this.emp_passwd = emp_passwd;
	}
	public Timestamp getEmp_hire_date() {
		return emp_hire_date;
	}
	public void setEmp_hire_date(Timestamp emp_hire_date) {
		this.emp_hire_date = emp_hire_date;
	}
	public String getEmp_birth() {
		return emp_birth;
	}
	public void setEmp_birth(String emp_birth) {
		this.emp_birth = emp_birth;
	}
	
	
}
