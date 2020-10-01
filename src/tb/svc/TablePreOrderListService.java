package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.PreOrderBean;

public class TablePreOrderListService {

	public ArrayList<PreOrderBean> getArticle() {
		
		ArrayList<PreOrderBean> list = new ArrayList<PreOrderBean>();
		
		Connection con = getConnection();
		
		TbDAO tbDAO = TbDAO.getInstance();	
			
		tbDAO.setConnection(con);							
			
		list = tbDAO.getAllPreOrderList();	
			
		close(con);
		
		return list;
	}

}
