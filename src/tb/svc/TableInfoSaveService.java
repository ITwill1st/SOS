package tb.svc;

import java.util.ArrayList;

import java.sql.Connection;
import tb.dao.TbDAO;
import vo.TableDTO;
import static db.JdbcUtil.*;

public class TableInfoSaveService {

	public boolean getArticle(String tableParam) {	
							
		boolean isSuccess = false;	
		
		String[] stableInfo = tableParam.split("/");
	
		ArrayList<TableDTO> list = new ArrayList<TableDTO>();
		
		for(int i = 0; i<stableInfo.length; i++) {
			
			TableDTO tDTO = new TableDTO();
			
			String[] tableInfo = stableInfo[i].split(",");
			
			tDTO.setTable_no(i+1);
			tDTO.setTable_x(Integer.parseInt(tableInfo[0]));
			tDTO.setTable_y(Integer.parseInt(tableInfo[1]));
			tDTO.setTable_w(Integer.parseInt(tableInfo[2]));
			tDTO.setTable_h(Integer.parseInt(tableInfo[3]));				
			list.add(tDTO);
					
		}
			
		Connection con = getConnection();
			
		TbDAO tbDAO = TbDAO.getInstance();	
			
		tbDAO.setConnection(con);							
			
		isSuccess = tbDAO.tableInfoSave(list);	
			
		if(isSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}

}
