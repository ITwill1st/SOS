package rsv.svc;

import vo.RsvDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import rsv.dao.RsvDAO;

public class RsvAllListProService {

	public ArrayList<RsvDTO>  AllRsvList() {
		
		Connection con = getConnection();
		
		RsvDAO rsvDAO = RsvDAO.getInstance();
		
		rsvDAO.setConnection(con);
		
		ArrayList<RsvDTO> rsvList =  rsvDAO.AllRsvList();
		
		close(con);
		
		return rsvList;
		
	}

}
