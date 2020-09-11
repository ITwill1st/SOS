package member.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberListService {

	public ArrayList<MemberBean> getMemberList() {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<MemberBean> list = memberDAO.selectMemberList();
		
		close(con);
		
		return list;
	}
	
	// 회원 목록 조회 요청을 위한 getMemberList() 메서드 정의(파라미터 있을 경우)
	public ArrayList<MemberBean> getMemberList(String orderTarget, String orderType) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// 파라미터 : 정령 대상(orderTarget)  , 정렬 방식(orderType)
		ArrayList<MemberBean> list = memberDAO.selectMemberList(orderTarget, orderType);
		
		close(con);
		
		return list;
	}
	
	
	
}
