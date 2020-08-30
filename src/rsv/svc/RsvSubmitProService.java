package rsv.svc;

import vo.RsvDTO;

import java.sql.Connection;

import rsv.dao.RsvDAO;

import static db.JdbcUtil.*;

public class RsvSubmitProService {

	//예약내용을 저장하고 리턴해주는 메서드 정의
	public boolean submitRsv(RsvDTO dto) {
		
		//작업이 성공여부를 리턴받기위한 변수설정
		boolean isResultInsert=false;
		
		//Connection 지정을 위한 작업
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		//DB에서 작업이 성공했는지 리턴 받기
		int insertResult=dao.InsertRsv(dto);
		if(insertResult==1) {
			//작업이 성공했다면 DB를 commit 시켜주고 true 값을 정의
			commit(con);
			isResultInsert=true;
		}else {
			//작업이 실패했다면 DB를 rollback 
			rollback(con);
		}
		
		// 작업이 종료된 후 connection 종료 후 리턴값 리턴해주기
		close(con);
		return isResultInsert;
	}
	
	//Host로 부터 예약 결과에 대한 값을 리턴 받은 후 예약 상태를 바꾸기 위한메서드
	public boolean modifyRsv(RsvDTO dto) {
		//작업의 성공여부를 리턴받고 외부로 리턴하기 위한 변수 설정
		boolean isModifyResult=false;
		
		//Connection 지정을 위한 작업
		Connection con=getConnection();
		RsvDAO dao=RsvDAO.getInstance();
		dao.setConnection(con);
		
		int resultModify=dao.updateRsv(dto);
		if(resultModify==1) {//작업이 성공했을 경우 리턴값을 true로 변경
			commit(con);
			isModifyResult=true;
		}else {//작업이 실패했을 경우
			rollback(con);
		}
		
		//연결을 끊기 위한 메서드
		close(con);
		
		return isModifyResult;
	}

}
