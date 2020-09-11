package member.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;

import member.dao.MemberDAO;

public class MemberLoginProService {

	public int loginMember(String mem_id, String mem_passwd) {
		System.out.println("LoginProService");
		int isLoginSuccess = 0;
		

		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();

		memberDAO.setConnection(con);
		
		isLoginSuccess = memberDAO.loginMember(mem_id, mem_passwd);
		
		close(con);
		
		
		return isLoginSuccess;
	}


	
}
