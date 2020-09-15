package mgr.staff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JdbcUtil.*;
import vo.StaffDTO;

public class ManagerStaffDAO {
	private ManagerStaffDAO(){}
	
	private static ManagerStaffDAO instance;
	
	public static ManagerStaffDAO getInstance() {
		if (instance == null) {
			instance = new ManagerStaffDAO();
		}
		return instance;
	}
	
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int staffDupCheck(String emp_id) {
		int check = 0;
		
		try {
			sql = "select emp_id from employees where emp_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emp_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) check = 0; // 0 : 중복,  1 : 가입 가능 
			else check = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return check;
	}
	
	public int insertStaff(StaffDTO staffInfo) {
		int result = 0;
		int idx = 0;
		
		sql = "select max(emp_idx) from employees";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idx = rs.getInt(1) + 1;
			} else {
				idx = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		
		sql = "insert into employees(emp_idx, emp_name, emp_contact, emp_id, emp_passwd, emp_hire_date, emp_birth) values(?, ?, ?, ?, ?, now(), ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, staffInfo.getEmp_name());
			pstmt.setString(3, staffInfo.getEmp_contact());
			pstmt.setString(4, staffInfo.getEmp_id());
			pstmt.setString(5, staffInfo.getEmp_passwd());
			pstmt.setString(6, staffInfo.getEmp_birth());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
}
