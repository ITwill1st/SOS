package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberBean;
import vo.OrderDTO;

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
		ResultSet rs = null;
		int mem_num=100000000;
		try{
		
			String sql="select max(mem_num) from member";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mem_num=rs.getInt("max(mem_num)")+1;
			}
			
			sql = "INSERT into member values(?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,mem_num);
			pstmt.setString(2, mb.getMem_nickname());
			pstmt.setString(3, mb.getMem_name());
			pstmt.setString(4, mb.getMem_id());
			pstmt.setString(5, mb.getMem_passwd());
			pstmt.setString(6, mb.getMem_email());
			pstmt.setInt(7,mb.getMem_gender());
			pstmt.setString(8, mb.getMem_phone());
			pstmt.setString(9, mb.getMem_birth());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return insertCount;
	}
	public int NonLogin(int mem_num) {
		int NonLogCount = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			String sql = "INSERT into member values(?,?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,mem_num);
			pstmt.setString(2, null);
			pstmt.setString(3, null);
			pstmt.setString(4, null);
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setInt(7, -1);
			pstmt.setString(8, null);
			pstmt.setString(9, null);
			NonLogCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("nondao - 오류");
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return NonLogCount;
	}

	public int selectNotMember() {
		int mem_num=-1;
		PreparedStatement pstmt = null;
		ResultSet rs=null;	
		try {
			
			String sql="select max(mem_num) from member";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mem_num=rs.getInt("max(mem_num)");
			}
			
		} catch (SQLException e) {
			System.out.println("snsLogin오류"+e);
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return mem_num;
	}
	
	
	public int snsLogin(MemberBean mb) {
		int SnsLogincount=0;
		PreparedStatement pstmt = null;
		ResultSet rs=null;	
		try {
			int mem_num=100000000;
			
			String sql="select max(mem_num) from member";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mem_num=rs.getInt("max(mem_num)")+1;
			}
			
			sql = "INSERT into member values(?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, mb.getMem_nickname());
			pstmt.setString(3, mb.getMem_name());
			pstmt.setString(4, mb.getMem_id());
			pstmt.setString(5, mb.getMem_passwd());
			pstmt.setString(6, mb.getMem_email());
			pstmt.setInt(7,mb.getMem_gender());
			pstmt.setString(8, mb.getMem_phone());
			pstmt.setString(9, mb.getMem_birth());
			SnsLogincount = pstmt.executeUpdate();
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
					check = 1;
					
				
			}else {
				check = 0;
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
				mb.setMem_gender(rs.getInt("mem_gender"));
				mb.setMem_phone(rs.getString("mem_phone"));
				mb.setMem_birth(rs.getString("mem_birth"));
				mb.setMem_regdate(rs.getDate("mem_regdate"));
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
					mb.setMem_gender(rs.getInt("mem_gender"));
					mb.setMem_phone(rs.getString("mem_phone"));
					mb.setMem_birth(rs.getString("mem_birth"));
					mb.setMem_regdate(rs.getDate("mem_regdate"));
					
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
				mb.setMem_gender(rs.getInt("mem_gender"));;
				mb.setMem_phone(rs.getString("mem_phone"));
				mb.setMem_birth(rs.getString("mem_birth"));
				mb.setMem_regdate(rs.getDate("mem_regdate"));
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


	public ArrayList<OrderDTO> getOrderList(int mem_num) {
		// 구매내역 가져오는 메서드
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		try {
			String sql = "select * from orders o JOIN product p ON (o.item_num = p.item_num) where mem_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				
				orderDTO.setOrder_num(rs.getInt("order_num"));
				orderDTO.setMem_num(rs.getInt("mem_num"));
				orderDTO.setTable_num(rs.getInt("table_num"));
				orderDTO.setItem_num(rs.getInt("item_num"));
				orderDTO.setItem_name(rs.getString("item_name"));
				orderDTO.setItem_qty(rs.getInt("item_qty"));
				orderDTO.setItem_price(rs.getInt("item_price"));
				orderDTO.setTotal_price(rs.getInt("total_price"));
				orderDTO.setOrder_datetime(rs.getTimestamp("order_datetime"));
				orderDTO.setReview_chk(rs.getInt("review_chk"));
				
				orderList.add(orderDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return orderList;
	}


	
	
}
