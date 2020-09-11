package member.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;

import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberJoinProService {

	public boolean joinMember(MemberBean mb) {
		boolean isJoinSuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int insertCount = memberDAO.insertMember(mb);
		if (insertCount>0) {
			commit(con);
			isJoinSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isJoinSuccess;
	}

	// 회원 중복 여부 확인 요청 작업을 수행하기 위한 dupCheckMember() 메서드 정의
	public int dupCheckMember(String mem_id) {
		int check = 0;  // 중복 체크 변수 ( 0: 아이디 중복, -1: 이메일 중복, 1: 중복 없음)
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		check = memberDAO.dupCheckMember(mem_id);
		
		return check;
	}

	

}
