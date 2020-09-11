package profit.dao;

import java.sql.Connection;

public class ProfitDAO {

	private static ProfitDAO instance;
	
	private ProfitDAO() {}
	
	public static final ProfitDAO getInstance() {
		//기존 boardDAO 인스턴스가 없을 때만 생성하고 있을떄 생성하지않음
		if(instance == null) {
			instance = new ProfitDAO();
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
	
	
}