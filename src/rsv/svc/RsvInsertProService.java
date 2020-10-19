package rsv.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import rsv.dao.RsvDAO;
import vo.RsvDTO;

public class RsvInsertProService {
	
	public void  RsvInsert(RsvDTO rsvDTO) {
		System.out.println("RsvInsertProService");
		
		Connection con = getConnection();
		
		RsvDAO rsvDAO = RsvDAO.getInstance();
		
		rsvDAO.setConnection(con);
		
		rsvDAO.InsertRsv(rsvDTO);
		
		commit(con);
		
		close(con);
		
	}

}
