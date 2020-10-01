package rsv.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.svc.RsvDeleteProService;
import vo.ActionForward;

public class RsvDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//예약 취소를 위한 작업을 시작하기 위한곳으로 잘 도착했는지 확인
		System.out.println("RsvDeleteProAction");
		
		//포워딩을 위한 객체를 생성
		ActionForward forward=null;
		
		//파라미터로 넘겨준 rsv_num을 받아온다.
		int rsv_num=Integer.parseInt(request.getParameter("rsv_num"));
		
		//예약을 취소하기 위한 DB작업을 하기위해 Service 객체생성
		RsvDeleteProService deleteSvc=new RsvDeleteProService();
		
		//작업의 결과를 식별하기 위한 변수 선언
		int resultCount=deleteSvc.deleteRsv(rsv_num);
		
		if(resultCount!=1) {//예약취소가 실패했을 경우
			//자바스크립트로 실패 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('예약일정 취소가 실패했습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {
			forward=new ActionForward();
			forward.setPath("Main.me");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
