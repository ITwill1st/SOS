package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import rsv.svc.RsvListCheckProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.RsvDTO;

public class MemberMyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		HttpSession session= request.getSession(); 
		String mem_id = (String)session.getAttribute("mem_id");
		System.out.println(mem_id);
		MemberBean mb = null;
		RsvListCheckProService listCheck = new RsvListCheckProService();
		mb = listCheck.getMemberInfo(mem_id);
		
		// 이메일만 따로 빼서 본인의 예약 리스트 조회하기
		RsvDTO dto = listCheck.getRsvList(mb.getMem_email());
		
		if(mb!= null) {
			
			if(dto!=null) {
				session.setAttribute("mem_id", mem_id);
			    session.setAttribute("memberInfo", mb);
			    String mem_phone = mb.getMem_phone();
			    session.setAttribute("mem_phone", mb.getMem_phone());
				// UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
				
				request.setAttribute("rsv_list", dto);
				
				forward=new ActionForward();
				forward.setPath("/member/myInfo.jsp");
				forward.setRedirect(false);
			}else{// 예약리스트를 가져오지 못했을 경우
				// 자바스크립트로 실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");// 문서타입지정
				PrintWriter out = response.getWriter();// PrintWriter 객체 가져오기
				// println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('예약리스트 확인이 실패했습니다.')");// 메세지 출력
				out.println("history.back()");// 이전페이지 이동
				out.println("</script>");

			}
			
			
			
		}
		
		
		return forward;
	}

}
