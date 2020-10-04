package rsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
			pstmt.setString(3, dto.getRsv_time());
			pstmt.setInt(4, dto.getRsv_pax());
			pstmt.setInt(5, dto.getRsv_check());
			pstmt.setString(6, dto.getMem_email());
			
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
	
	//year와 month 정보를 받아와서 해당하는 월의 예약내역을 뽑아가는 메서드 정의
	public JSONArray selectDate(String year, String month) {
		JSONArray dateList=new JSONArray();
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="select rsv_date from reservation where rsv_date like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, year+"-"+month+"-%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject jo=new JSONObject();
				jo.put("date",rs.getString("rsv_date"));
				dateList.add(jo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - selectDate() 오류");
		}
		
		
		return dateList;
	}
	
	//이메일을 이용해서 본인의 예약 리스트 조회하기 위한 메서드 정의
	public RsvDTO selectRsvList(String mem_email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		RsvDTO dto=new RsvDTO();
		
		try {
			String sql="select * from reservation where mem_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setRsv_num(rs.getInt("rsv_num"));
				dto.setRsv_date(rs.getString("rsv_date"));
				dto.setRsv_time(rs.getString("rsv_time"));
				dto.setRsv_pax(rs.getInt("rsv_pax"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - selectRsvList() 오류");
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return dto;
	}

	//전체 예약 정보 조회 후 저장하기 위한 메서드
	public ArrayList<RsvDTO> allSelectRsv() {
		
		ArrayList<RsvDTO> allList=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="select * from reservation";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				RsvDTO dto=new RsvDTO();
				dto.setMem_email(rs.getString("mem_email"));
				dto.setRsv_date(rs.getString("rsv_date"));
				dto.setRsv_num(rs.getInt("rsv_num"));
				dto.setRsv_pax(rs.getInt("rsv_pax"));
				dto.setRsv_time(rs.getString("rsv_time"));
				dto.setRsv_check(rs.getInt("rsv_check"));
				allList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - allSelectRsv() 오류");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return allList;
	}

	//비회원 예약 조회를 위한 메서드 정의
	public RsvDTO selectNonMember(String mem_email) {
		RsvDTO dto=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="select * from reservation where mem_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setMem_email(mem_email);
				dto.setRsv_check(rs.getInt("rsv_check"));
				dto.setRsv_date(rs.getString("rsv_date"));
				dto.setRsv_num(rs.getInt("rsv_num"));
				dto.setRsv_pax(rs.getInt("rsv_pax"));
				dto.setRsv_time(rs.getString("rsv_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - selectNonMember() 오류");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return dto;
	}

	//예약을 취소하기 위한 메서드
	public int deleteRsv(int rsv_num) {
		int resultCount=0;
		PreparedStatement pstmt=null;
		
		try {
			String sql="delete from reservation where rsv_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rsv_num);
			resultCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RsvDAO - deleteRsv() 오류");
		}finally {
			close(pstmt);
		}
		
		return resultCount;
	}
	
	
}
