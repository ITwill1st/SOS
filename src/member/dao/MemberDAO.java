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
	public int insertMember(MemberBean memberBean) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT into member2 values(null,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_name());
			pstmt.setString(2, memberBean.getMember_id());
			pstmt.setString(3, memberBean.getMember_passwd());
			pstmt.setString(4, memberBean.getMember_email());
			pstmt.setString(5, memberBean.getMember_gender());
			pstmt.setString(6, memberBean.getMember_phone());
			pstmt.setString(7, memberBean.getMember_birth());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	public int snsLogin(MemberBean memberBean) {
		int SnsLogincount=0;
		PreparedStatement pstmt = null;
		ResultSet rs=null;	
		try {
			String sql = "select * from member2 where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_id());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				SnsLogincount=-1;
			}else {
				sql = "INSERT into member2 values(null,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, memberBean.getMember_name());
				pstmt.setString(2, memberBean.getMember_id());
				pstmt.setString(3, memberBean.getMember_passwd());
				pstmt.setString(4, memberBean.getMember_email());
				pstmt.setString(5, memberBean.getMember_gender());
				pstmt.setString(6, memberBean.getMember_phone());
				pstmt.setString(7, memberBean.getMember_birth());
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


	public int dupCheckMember(String member_id) {
		int check = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select id from member2 where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 아이디 중복 체크 결과가 맞으면 0, 
				if(member_id.equals(rs.getString("member_id"))) {
					check = 1;
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


	public int loginMember(String member_id, String member_passwd) {
		int idCheck = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member2 WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("member_passwd").equals(member_passwd)){
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
			String sql = "select * from member2 order by " + orderTarget + " " + orderType;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMember_num(rs.getInt("member_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_passwd(rs.getString("member_passwd"));
				mb.setMember_email(rs.getString("member_email"));
				mb.setMember_gender(rs.getString("member_gender"));
				mb.setMember_phone(rs.getString("member_phone"));
				mb.setMember_birth(rs.getString("member_birth"));
				mb.setMember_regDate(rs.getDate("member_regDate"));
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
				String sql = "select * from member2";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberBean mb = new MemberBean();
					mb.setMember_num(rs.getInt("member_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
					mb.setMember_id(rs.getString("member_id"));
					mb.setMember_name(rs.getString("member_name"));
					mb.setMember_passwd(rs.getString("member_passwd"));
					mb.setMember_email(rs.getString("member_email"));
					mb.setMember_gender(rs.getString("member_gender"));
					mb.setMember_phone(rs.getString("member_phone"));
					mb.setMember_birth(rs.getString("member_birth"));
					mb.setMember_regDate(rs.getDate("member_regDate"));
					
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


	public MemberBean getUserInfo(String member_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		
		try {
			//1단계 드라이버 불러오기	// 2단계 디비연결
			con=getConnection();
			 //  sql    select  id에 해당하는 회원정보 가져오기
			 // 3단계 연결정보를 이용해서 sql구문 만들고 실행할 객체생성 => PreparedStatement
			 String sql="select * from member2 where member_id=?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, member_id);
			 // 4단계 실행 결과 => ResultSet rs  
			 rs=pstmt.executeQuery();
			 //  if rs에 처음위치에서 다음행으로 이동  데이터가 있으면  True
			 //  출력 아이디 : 비밀번호 : 이름 : 가입날짜:
			 if(rs.next()){
				mb.setMember_num(rs.getInt("member_num"));	// rs에서 가져온 값을 memberBean 객체에 저장
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_passwd(rs.getString("member_passwd"));
				mb.setMember_gender(rs.getString("member_gender"));
				mb.setMember_phone(rs.getString("member_phone"));
				mb.setMember_birth(rs.getString("member_birth"));
				mb.setMember_regDate(rs.getDate("member_regDate"));
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
