package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.ProductBean;

public class TableDetailViewService {
	
	public ArrayList<ProductBean> getArticle(){
		
		ArrayList<ProductBean> list = null;
		
		Connection con = getConnection();
		
		TbDAO tbdao = TbDAO.getInstance();
		
		tbdao.setConnection(con);
		
		list = tbdao.getMenuList();
		
		close(con);
			
		return list;
	}
	

}
