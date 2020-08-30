package rsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.RsvDTO;
import static db.JdbcUtil.*;

public class RsvDAO {
	
	private static RsvDAO instance;
	
	private RsvDAO() {}
	
	public static final RsvDAO getInstance() {
		//기존 boardDAO 인스턴스가 없을 때만 생성하고 있을 때 생성하지않음
		if(instance == null) {
			instance = new RsvDAO();
		}
		
		return instance;
	}
	//---------------------------------------------------------------------------------
	//Service클래스로부터 jdbcUtil에서 제공받은 Connection객체를 전달받기
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//여기서 부터 필요한 메서드를 적으면됩니다.
	//----------------------------------------------------------------------------------
	
	//예약입력된 내용을 DB에 저장하기 위한 메서드
	public int InsertRsv(RsvDTO dto) {
		int insertResult=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//예약번호를 지정하기 위해서 예약 번호가 있는지 조회 후 +1 추가를 위한 sql
			String sql="select max(rsv_num) from reservation";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int num=1;//예약번호가 없다면 초기값 1을 지정
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}
			
			sql="insert into reservation values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,dto.getRsv_date());
			pstmt.setInt(3, dto.getRsv_pax());
			pstmt.setInt(4, dto.getRsv_check());
			pstmt.setString(5, dto.getMem_email());
			
			//예약 내용을 DB에 저장완료 후 리턴값 지정
			insertResult=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - InsertRsv() 오류");
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return insertResult;
	}
	
	//Host로 리턴값을 받고 예약상태를 변경하기 위한 메서드 정의
	public int updateRsv(RsvDTO dto) {
		int resultUpdate=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		try {
			//예약번호를 지정하기 위해서 예약 번호가 있는지 조회 후 +1 추가를 위한 sql
			String sql="select max(rsv_num) from reservation";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int num=1;//예약번호가 없다면 초기값 1을 지정
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}
			
			sql="insert into reservation values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,dto.getRsv_date());
			pstmt.setInt(3, dto.getRsv_pax());
			pstmt.setInt(4, dto.getRsv_check());
			pstmt.setString(5, dto.getMem_email());
			
			//예약 내용을 DB에 저장완료 후 리턴값 지정
			resultUpdate=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - updateRsv() 오류");
		}
		
		return resultUpdate;
	}
	
}
