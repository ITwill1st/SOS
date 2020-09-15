package rsv.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import org.json.simple.JSONArray;

import rsv.dao.RsvDAO;

public class RsvDateCheckProService {
	
	//예약내역을 뽑아오는 DAO작업을 하는 메서드 정의
	public JSONArray dateCheck(String year, String month) {
		
		JSONArray dateList=null;
		
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		dateList=dao.selectDate(year,month);
		if(dateList!=null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		return dateList;
	}

}
