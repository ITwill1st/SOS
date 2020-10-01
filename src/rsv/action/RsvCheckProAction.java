package rsv.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.svc.RsvCheckProService;
import vo.ActionForward;
import vo.RsvDTO;

public class RsvCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//예약확인을 위해 이동이 잘 되었는지 확인
		System.out.println("RsvCheckProAction");
		
		//포워딩을 위한 객체 생성
		ActionForward forward=null;
		
		//예약확인을 위해 넘겨 받은 파라미터
		String mem_email=request.getParameter("mail");
		
		//DB작업을 위한 Service 객체 생성
		RsvCheckProService checkSvc=new RsvCheckProService();
		
		//예약 내역을 확인 후 저장한 객체를 리턴 받기위한 객체 생성
		RsvDTO dto=null;
		
		//전달받은 이메일 파라미터로 DB작업을 실행
		dto=checkSvc.selectRsv(mem_email);
		
		if(dto==null) {//조회 작업이 실패하여 받아온 객체가 없을 경우
			
			//자바스크립트로 실패 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('예약일정 확인이 실패했습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {//조회 작업이 성공하여 객체를 넘겨주기 위한 작업 후 포워딩 작업
			request.setAttribute("RsvCheck", dto);
			forward=new ActionForward();
			forward.setPath("");
		}
		
		
		// 포워딩 객체 반환하여 지정한 곳으로 이동
		return forward;
	}

}
