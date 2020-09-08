package rsv.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MyRsvListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Controller에서 작업을 위한 곳으로 이동이 되었는지 확인하기 위한 콘솔출력
		System.out.println("MyRsvListProAction");
		
		//작업한 결과물을 저장하고 포워딩하기 위한 변수 정의
		ActionForward forward=null;
		
		String id=request.getParameter("id");
		
		
		
		
		return forward;
	}

}
