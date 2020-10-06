package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import tb.dao.TbDAO;

public class PreOrderAcceptService {

	public int acceptPreOrder(int table_num) {
		
		int updateResult = 0;
		
		Connection con = getConnection();
		
		TbDAO tbDAO = TbDAO.getInstance();
		tbDAO.setConnection(con);
		
		updateResult = tbDAO.acceptPreOrder(table_num);
		
		if (updateResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateResult;
	}

}
