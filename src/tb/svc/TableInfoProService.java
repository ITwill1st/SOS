package tb.svc;


import java.sql.Connection;
import java.util.ArrayList;

import tb.dao.TbDAO;
import vo.TableDTO;

import static db.JdbcUtil.*;

public class TableInfoProService {

	public ArrayList<TableDTO> getArticle() {
		
		
		ArrayList<TableDTO> list = null;
		
		Connection con = getConnection();
		
		TbDAO tbdao = TbDAO.getInstance();
		
		tbdao.setConnection(con);
		
		list = tbdao.tableInfoProView();
		
		close(con);
		
		return list;
	}

}
