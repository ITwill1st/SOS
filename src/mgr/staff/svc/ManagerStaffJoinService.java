package mgr.staff.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;
import mgr.staff.dao.ManagerStaffDAO;
import vo.StaffDTO;

public class ManagerStaffJoinService {

	public int dupCheckStaff(String emp_id) {
		int check = 0; // 중복 체크 변수 ( 0: 아이디 중복, -1: 이메일 중복, 1: 중복 없음)
		Connection con = getConnection();
		ManagerStaffDAO msDAO = ManagerStaffDAO.getInstance();
		msDAO.setConnection(con);
		
		check = msDAO.staffDupCheck(emp_id);
		
		close(con);
		
		return check;
	}
	
	
	public int staffInsert(StaffDTO staffInfo) {
		int result = 0;
		
		Connection con = getConnection();
		ManagerStaffDAO msDAO = ManagerStaffDAO.getInstance();
		msDAO.setConnection(con);
		
		result = msDAO.insertStaff(staffInfo);
		
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}


}
