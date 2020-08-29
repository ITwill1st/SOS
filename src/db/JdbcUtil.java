package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//jdbc 관련 작업 처리 기능을 제공하는 클래스
public class JdbcUtil {
	
	//JDBC연결을 위한 Connection객체를 리턴하는 getConnection() 메서드 정의
	
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
			//톰캣 컨텍스트 객체(context.xml) 가져오기
			Context initCtx = new InitialContext();
			
			
//			//Context xml 내의 <Resource> 태그 부분 가져오기
//			Context envCtx = (Context)initCtx.lookup("java:comp/env");
//			
//			//Context xml 내의 name 속성항목을 찾아 DataSource 객체(커넥션풀) 반환
//			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQL");
			
			//위의 lookup() 메서드 호출을 하나로 결합할 경우
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/MySQL");
			
			con = ds.getConnection(); //Context.xml 내에 username, password 입력시
//			con = ds.getConnection("root", "1234")// Context.xml 내에 user,pass 미입력시
			
			
			con.setAutoCommit(false);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public static void close(Connection con) {
		if(con != null) try {con.close();} catch(Exception e){}
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) try {pstmt.close();} catch(Exception e){}
	}
	public static void close(ResultSet rs) {
		if(rs != null) try {rs.close();} catch(Exception e){}
	}
	
	//DB 조작 완료 후 commit() 메서드 호출을 위한 commit() 메서드 정의
	//트랜잭션 처리를 위해 Auto Comot 기능을 해제했을때 필요
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//DB 조작 실패 후 rollback() 메서드 호출을 위한 rollback() 메서드 정의
	//
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}