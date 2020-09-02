package tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.TableDTO;

import static db.JdbcUtil.*;

public class TbDAO {

	private static TbDAO instance;
	
	private TbDAO() {}
	
	public static final TbDAO getInstance() {
		//기존 boardDAO 인스턴스가 없을 때만 생성하고 있을떄 생성하지않음
		if(instance == null) {
			instance = new TbDAO();
		}
		
		return instance;
	}
	//---------------------------------------------------------------------------------
	//Service클래스로부터 jdbcUtil에서 제공받은 Connection객체를 전달받기
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<TableDTO> tableInfoProView() {
		
		ArrayList<TableDTO> list = new ArrayList<TableDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "SELECT * FROM tableinfo";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				
				TableDTO tdto = new TableDTO();
				tdto.setTable_no(rs.getInt(1));
				tdto.setTable_x(rs.getInt(2));
				tdto.setTable_y(rs.getInt(3));
				tdto.setTable_w(rs.getInt(4));
				tdto.setTable_h(rs.getInt(5));
				
				list.add(tdto);
				
			}
			
			if(list.isEmpty()) {
				
				TableDTO tdto = new TableDTO();
				tdto.setTable_no(1);
				tdto.setTable_x(200);
				tdto.setTable_y(200);
				tdto.setTable_w(300);
				tdto.setTable_h(300);		
				
				list.add(tdto);
				
			}
			

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public boolean tableInfoSave(ArrayList<TableDTO> list) {

		
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		

			String sql;
			try {
				sql = "truncate table tableinfo";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		
		try {	
			
			for(int i = 0; i< list.size(); i++) {
				
				sql = "insert into tableinfo values (?,?,?,?,?)";		
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (list.get(i).getTable_no()));
				pstmt.setInt(2, (list.get(i).getTable_x()));
				pstmt.setInt(3, (list.get(i).getTable_y()));
				pstmt.setInt(4, (list.get(i).getTable_w()));
				pstmt.setInt(5, (list.get(i).getTable_h()));	
				pstmt.executeUpdate();	
				
			}
						
				isSuccess = true;
				
				System.out.println(list.get(0).getTable_no());
				System.out.println(list.get(0).getTable_x());
				System.out.println(list.get(0).getTable_y());
				System.out.println(list.get(0).getTable_w());
				System.out.println(list.get(0).getTable_h());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return isSuccess;
	}
	


	
	
	
	
}
