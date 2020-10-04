package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.PreOrderBean;

public class TablePreOrderService {

	public ArrayList<PreOrderBean> getArticle(String tableNo) {

		int no = Integer.parseInt(tableNo);
		
		ArrayList<PreOrderBean> list = new ArrayList<PreOrderBean>();
		
		Connection con = getConnection();
		
		TbDAO tbDAO = TbDAO.getInstance();	
			
		tbDAO.setConnection(con);	
		
		list = tbDAO.getThisTablePreOrder(no);
		
		close(con);
		
		return list;
	}

}
