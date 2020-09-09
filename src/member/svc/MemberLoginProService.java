package member.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;

import member.dao.MemberDAO;

public class MemberLoginProService {

	public int loginMember(String member_id, String member_passwd) {
		System.out.println("LoginProService");
		int isLoginSuccess = 0;
		

		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();

		memberDAO.setConnection(con);
		
		isLoginSuccess = memberDAO.loginMember(member_id, member_passwd);
		
		close(con);
		
		
		return isLoginSuccess;
	}


	
}
