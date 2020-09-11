package member.svc;


import java.sql.Connection;

import static db.JdbcUtil.*;

import member.dao.MemberDAO;
import vo.MemberBean;
public class MemberSnsLoginProService {
	public boolean snsLogin(MemberBean mb) {
		System.out.println("MemberSnsLoginProService");
		boolean isSnsLogsuccess=false;
		int SnsLogincount=0;
		
		Connection con =getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();		
		memberDAO.setConnection(con);
		
		SnsLogincount=memberDAO.snsLogin(mb);
		
		if(SnsLogincount==1 || SnsLogincount==-1) {
			isSnsLogsuccess=true;
			commit(con);
		}else {
			isSnsLogsuccess=false;
			rollback(con);
		}
		
		close(con);
		System.out.println(isSnsLogsuccess);
		return isSnsLogsuccess;
	}

}
