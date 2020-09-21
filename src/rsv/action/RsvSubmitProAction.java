package rsv.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import rsv.svc.RsvSubmitProService;
import vo.ActionForward;
import vo.RsvDTO;

public class RsvSubmitProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//경로가 잘 이동되었는지 확인하기 위한 콘솔출력
		System.out.println("RsvSubmitProAction"); 
		
		ActionForward forward=null;
		
		//id와 ip 값 유지 하기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String ip=request.getRemoteAddr();
		
		//입력된 예약 내역 받아오기.
		String rsv_date=request.getParameter("rsv_date");
		int rsv_pax=Integer.parseInt(request.getParameter("pax"));
		String rsv_time=request.getParameter("rsv_time");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String allergy=request.getParameter("allergy");
		
		//예약 내역 객체에 저장하기.
		RsvDTO dto=new RsvDTO();
		dto.setRsv_date(rsv_date);
		dto.setRsv_pax(rsv_pax);
		dto.setMem_email(email);
		dto.setRsv_time(rsv_time);
		
		
		//DB에 저장하기 위한 Service 불러오기.
		RsvSubmitProService Rsvc=new RsvSubmitProService();
		
		
		//예약이 실패했는지 성공했는지 Host로부터 값을 받아서 DB에 저장하기 위한 메서드 호출
		
		
		int rsvReturn=1;//아직 Host로부터 리턴값이 없기 때문에 1로 지정 
		
		if(rsvReturn==-1) {//Host가 예약을 취소했을 경우 DB에 rsv_check 에 -1값을 넣기위한 순서
			
			//Host가 예약을 받지 못할 경우 리턴값으로 -1을 받아서 DB에 입력해준다.
			dto.setRsv_check(-1);
			
			//Host로 값을 받고 DB에 rsv_check에 값을 바꿔주기 위한 메서드 호출
			boolean isModifyRsv=Rsvc.modifyRsv(dto);
			
			if(!isModifyRsv) {
				
				//자바스크립트로 예약실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");//문서타입지정
				PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
				//println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약이 취소되었습니다.')");//메세지 출력
				out.println("history.back()");//이전페이지 이동
				out.println("</script>");
				
			}
		}else if(rsvReturn==0) {
			//Host가 예약을 신청 받고 값을 입력하기 전의 상태(예약대기중...)
			dto.setRsv_check(0);
			
			//Host로 값을 받고 DB에 rsv_check에 값을 바꿔주기 위한 메서드 호출
			boolean isModifyRsv=Rsvc.modifyRsv(dto);
			if(!isModifyRsv) {
				
				//자바스크립트로 예약실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");//문서타입지정
				PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
				//println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약이 대기중입니다.')");//메세지 출력
				out.println("history.back()");//이전페이지 이동
				out.println("</script>");
				
			}
			
		}
		else {//예약이 성공 했을 경우 rsv_check에 1을 넣어 주기
			dto.setRsv_check(1);
			//DB에 저장하기 위한 작업의 성공,실패를 리턴받는 변수설정 및 저장하기위한 메서드 호출
			boolean isResultSubmit=Rsvc.submitRsv(dto);
			if(!isResultSubmit) {//예약이 실패했을 경우
				response.setContentType("text/html;charset=UTF-8");//문서타입지정
				PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
				//println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약이 실패되었습니다.')");//메세지 출력
				out.println("history.back()");//이전페이지 이동
				out.println("</script>");
				
			}else {//예약이 성공했을 경우
				forward=new ActionForward();
				forward.setPath("/RsvResult.rsv");
				forward.setRedirect(true);
			}
			
		}
		
		
		return forward;
	}

}
