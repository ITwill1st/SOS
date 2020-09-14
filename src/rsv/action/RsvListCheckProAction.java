package rsv.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.svc.RsvListCheckProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.RsvDTO;

public class RsvListCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 본인의 예약리스트 가져오기 위해 잘 도착했는지 확인용
		System.out.println("RsvListCheckProAction");

		// 포워딩을 위한 객체 생성
		ActionForward forward = null;

		// id 값 받아오기
		String id = (String) request.getAttribute("mem_id");


		if(id.equals("admin")) {//넘겨 받은 id가 관리자일 경우
			
			// 예약전체정보를 얻기 위한 Service 불러오기
			RsvListCheckProService listCheck = new RsvListCheckProService();
			
			//전체예약정보를 저장한 ArrayList 를 저장하기위한 변수선언
			ArrayList<RsvDTO> allList=null;
			
			//DB작업을 거친 전체예약정보를 선언한 변수로 저장
			allList=listCheck.getAllList();
			
			if(allList==null) {//전체예약정보를 불러오지 못했을 경우
				// 자바스크립트로 실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");// 문서타입지정
				PrintWriter out = response.getWriter();// PrintWriter 객체 가져오기
				// println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약리스트 확인이 실패했습니다.')");// 메세지 출력
				out.println("history.back()");// 이전페이지 이동
				out.println("</script>");
			}else {//작업이 성공했을 경우
				//전체리스트를 넘겨주기 위해 새로 객체를 만든다.
				request.setAttribute("rsv_All", allList);
				forward=new ActionForward();
				forward.setPath("/rsv/list.jsp");
			}
			
		}else if(!id.equals("admin")){//id가 관리자가 아닐 경우
			
			// 멤버정보 얻기 위한 Service 불러오기
			RsvListCheckProService listCheck = new RsvListCheckProService();
			
			// 멤버 정보를 리턴 받기 위한 객체 생성
			MemberBean mb = null;
			// 멤버 정보 리턴 받아서 객체에 저장
			mb = listCheck.getMemberInfo(id);
			
			// 이메일만 따로 빼서 본인의 예약 리스트 조회하기
			RsvDTO dto = listCheck.getRsvList(mb.getMem_email());
			
			if (dto == null) {//예약리스트를 가져오지 못했을 경우
				// 자바스크립트로 실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");// 문서타입지정
				PrintWriter out = response.getWriter();// PrintWriter 객체 가져오기
				// println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약리스트 확인이 실패했습니다.')");// 메세지 출력
				out.println("history.back()");// 이전페이지 이동
				out.println("</script>");
				
			} else {//본인의 예약 리스트를 가져오는게 성공했을 경우
				request.setAttribute("rsv_list", dto);
				forward = new ActionForward();
				forward.setPath("/rsv/list.jsp");
			}
		}
		
		

		return forward;
	}

}
