package rsv.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import rsv.dao.RsvDAO;

public class RsvDeleteProService {
	//DB작업을 위한 메서드 정의
	public int deleteRsv(int rsv_num) {
		
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		int result=dao.deleteRsv(rsv_num);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return result;
	}

}
