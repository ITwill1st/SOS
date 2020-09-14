package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberBean;

import static db.JdbcUtil.*;
public class MemberDAO {

	private MemberDAO(){}
	private static MemberDAO instance;
	
	public static MemberDAO getInstance() {
		
		if(instance==null) {
			instance = new MemberDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// 회원가입 처리를 위한 insertMember() 메서드
	public int insertMember(MemberBean mb) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT into member values(null,?,?,?,?,?,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMem_nickname());
			pstmt.setString(2, mb.getMem_name());
			pstmt.setString(3, mb.getMem_id());
			pstmt.setString(4, mb.getMem_passwd());
			pstmt.setString(5, mb.getMem_email());
			pstmt.setBoolean(6, mb.isMem_gender());
			pstmt.setString(7, mb.getMem_phone());
			pstmt.setString(8, mb.getMem_birth());
			pstmt.setString(9, mb.getMembercol());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	public int snsLogin(MemberBean mb) {
		int SnsLogincount=0;
		PreparedStatement pstmt = null;
		ResultSet rs=null;	
		try {
			String sql = "select * from member where mem_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb.getMem_id());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				SnsLogincount=-1;
			}else {
				sql = "INSERT into member values(null,?,?,?,?,?,?,?,?,now(),?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getMem_nickname());
				pstmt.setString(2, mb.getMem_name());
				pstmt.setString(3, mb.getMem_id());
				pstmt.setString(4, mb.getMem_passwd());
				pstmt.setString(5, mb.getMem_email());
				pstmt.setBoolean(6, mb.isMem_gender());
				pstmt.setString(7, mb.getMem_phone());
				pstmt.setString(8, mb.getMem_birth());
				pstmt.setString(9, mb.getMembercol());
				SnsLogincount = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("snsLogin오류"+e);
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return SnsLogincount;
	}


	public int dupCheckMember(String mem_id) {
		int check = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select mem_id from member where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 아이디 중복 체크 결과가 맞으면 0, 
				if(mem_id.equals(rs.getString("mem_id"))) {
					check = 1;
				}else {
					check = 0;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return check;
	}


	public int loginMember(String mem_id, String mem_passwd) {
		int idCheck = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("mem_passwd").equals(mem_passwd)){
					idCheck = 1;
				}else{
					idCheck = -1;
				}
					
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MemberDAO - loginMember() 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return idCheck;
	}


	public ArrayList<MemberBean> selectMemberList(String orderTarget, String orderType) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			// ORDER BY 절의 항목을 문자열 결합으로 생성
			String sql = "select * from member order by " + orderTarget + " " + orderType;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMem_num(rs.getInt("mem_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
				mb.setMem_nickname(rs.getString("mem_nickname"));
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_name(rs.getString("mem_name"));
				mb.setMem_passwd(rs.getString("mem_passwd"));
				mb.setMem_email(rs.getString("mem_email"));
				mb.setMem_gender(rs.getBoolean("mem_gender"));
				mb.setMem_phone(rs.getString("mem_phone"));
				mb.setMem_birth(rs.getString("mem_birth"));
				mb.setMem_regdate(rs.getDate("mem_regdate"));
				mb.setMembercol(rs.getString("membercol"));
				// MemberBean 객체에 저장한 것을 list
				list.add(mb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return list;
	}
	
	public ArrayList<MemberBean> selectMemberList() {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			ArrayList<MemberBean> list = new ArrayList<MemberBean>();
			
			try {
				String sql = "select * from member";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberBean mb = new MemberBean();
					mb.setMem_num(rs.getInt("mem_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
					mb.setMem_nickname(rs.getString("mem_nickname"));
					mb.setMem_id(rs.getString("mem_id"));
					mb.setMem_name(rs.getString("mem_name"));
					mb.setMem_passwd(rs.getString("mem_passwd"));
					mb.setMem_email(rs.getString("mem_email"));
					mb.setMem_gender(rs.getBoolean("mem_gender"));
					mb.setMem_phone(rs.getString("mem_phone"));
					mb.setMem_birth(rs.getString("mem_birth"));
					mb.setMem_regdate(rs.getDate("mem_regdate"));
					mb.setMembercol(rs.getString("membercol"));
					
					// MemberBean 객체에 저장한 것을 list
					list.add(mb);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			
			return list;
		}


	public MemberBean getUserInfo(String mem_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		
		try {
			 //  sql    select  id에 해당하는 회원정보 가져오기
			 // 3단계 연결정보를 이용해서 sql구문 만들고 실행할 객체생성 => PreparedStatement
			 String sql="select * from member where mem_id=?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, mem_id);
			 // 4단계 실행 결과 => ResultSet rs  
			 rs=pstmt.executeQuery();
			 //  if rs에 처음위치에서 다음행으로 이동  데이터가 있으면  True
			 //  출력 아이디 : 비밀번호 : 이름 : 가입날짜:
			 if(rs.next()){
				mb.setMem_num(rs.getInt("mem_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
				mb.setMem_nickname(rs.getString("mem_nickname"));
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_name(rs.getString("mem_name"));
				mb.setMem_passwd(rs.getString("mem_passwd"));
				mb.setMem_email(rs.getString("mem_email"));
				mb.setMem_gender(rs.getBoolean("mem_gender"));;
				mb.setMem_phone(rs.getString("mem_phone"));
				mb.setMem_birth(rs.getString("mem_birth"));
				mb.setMem_regdate(rs.getDate("mem_regdate"));
				mb.setMembercol(rs.getString("membercol"));
			 }
//			 	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO - getUserInfo 오류");
		}finally {
			close(rs);
			close(pstmt);
		}
		return mb;
	}




//	public int snsLogin(String id) {
//		int SnsLogincount=0;
//		PreparedStatement pstmt = null;
//		ResultSet rs=null;	
//		try {
//			String sql = "select * from member3 where member_id=?";
//			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				SnsLogincount=-1;
//			}else {
//				sql="INSERT INTO member3(idx,member_id) VALUES(null,?)";
//				pstmt=con.prepareStatement(sql);
//				pstmt.setString(1, id);
//				SnsLogincount=pstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//			System.out.println("snsLogin오류"+e);
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		
//		return SnsLogincount;
//	}


	
}
