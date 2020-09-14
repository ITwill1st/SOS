package rsv.svc;

import vo.MemberBean;
import vo.RsvDTO;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import rsv.dao.RsvDAO;

public class RsvListCheckProService {

	//id를 이용해서 멤버 정보 얻기 위한 메서드 정의
	public MemberBean getMemberInfo(String id) {
		//리턴받기 위한 객체 생성
		MemberBean mb=null;
		//Conncetion 연결을 위한 JDBC연결
		Connection con=getConnection();
		//연결을 위한 dao 생성
		MemberDAO dao=MemberDAO.getInstance();
		//Connection 연결
		dao.setConnection(con);
		//리턴받기
		mb=dao.getUserInfo(id);
		if(mb!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return mb;
	}

	//멤버 정보를 이용해서 예약리스트 확인하기 위한 메서드 정의
	public RsvDTO getRsvList(String mem_email) {
		//조회된 정보를 담기위한 객체 생성
		RsvDTO dto=null;
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		dto=dao.selectRsvList(mem_email);
		if(dto==null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return dto;
	}

	//전체예약정보를 검색해서 저장하기 위한 메서드 정의
	public ArrayList<RsvDTO> getAllList() {
		//저장한 정보를 담기 위한 변수
		ArrayList<RsvDTO> allList=null;
		
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		allList=dao.allSelectRsv();
		if(allList!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		//작업 후 Connection 닫아주기
		close(con);
		
		return allList;
	}
	
	
	
}
