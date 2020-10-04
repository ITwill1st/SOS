package rsv.svc;

import vo.RsvDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;

import rsv.dao.RsvDAO;

public class RsvCheckProService {

	//비회원 예약 조회를 위한 작업을 하기위한 메서드
	public RsvDTO selectRsv(String mem_email) {
		RsvDTO dto=null;
		
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		dto=dao.selectNonMember(mem_email);
		if(dto!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		
		return dto;
	}

}
