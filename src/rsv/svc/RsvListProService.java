package rsv.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import rsv.dao.RsvDAO;
import vo.RsvDTO;

public class RsvListProService {
	
	public ArrayList<RsvDTO>  RsvList(String mem_email) {
		
		Connection con = getConnection();
		
		RsvDAO rsvDAO = RsvDAO.getInstance();
		
		rsvDAO.setConnection(con);
		
		ArrayList<RsvDTO> rsvList =  rsvDAO.RsvList(mem_email);
		
		close(con);
		
		return rsvList;
		
	}
}
