package tb.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.io.Closeable;
import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.ProductBean;

public class TableProductListService {

	public ArrayList<ProductBean> getArticle() {

		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		
		Connection con = getConnection();
		
		TbDAO tbDAO = TbDAO.getInstance();	
			
		tbDAO.setConnection(con);	
		
		list = tbDAO.getProductList();
		
		close(con);
		
		return list;
	}

}
