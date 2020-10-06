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

	public int NonLogin() {
		System.out.println("NonLoginService");
		int insertSuccess = -1;
		int mem_num = -1;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();

		memberDAO.setConnection(con);
		
		mem_num = memberDAO.selectNotMember();
		mem_num += 1;
		insertSuccess = memberDAO.NonLogin(mem_num);
		
		if(insertSuccess!=-1) {
			mem_num = memberDAO.selectNotMember();
			if(mem_num!=-1) {
				commit(con);
				System.out.println("commit 성공!");
				System.out.println(mem_num);
			}
		}else {
			rollback(con);
			System.out.println("실패!");
		}
		close(con);
		
		
		return mem_num;
	}
	
	
}
