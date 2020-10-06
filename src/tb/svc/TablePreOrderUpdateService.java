package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.BasketBean;

public class TablePreOrderUpdateService {

	public int updatePreOrder(ArrayList<BasketBean> basketList) {
		int updateResult = 0;
		
		Connection con = getConnection();
		
		TbDAO tbDAO = TbDAO.getInstance();
		
		tbDAO.setConnection(con);
		
		updateResult = tbDAO.updatePreOrder(basketList);
		
		if (updateResult>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateResult;
	}

}
