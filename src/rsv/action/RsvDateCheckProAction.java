package rsv.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import rsv.svc.RsvDateCheckProService;
import vo.ActionForward;

public class RsvDateCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("RsvDateCheckProAction");
		
		ActionForward forward=null;
		
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		RsvDateCheckProService checkSvc=new RsvDateCheckProService();
		
		JSONArray dateList=null;
		
		dateList=checkSvc.dateCheck(year,month);
		
		if(dateList==null) {
			//자바스크립트로 실패 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('예약일정 확인이 실패했습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {
			forward=new ActionForward();
			request.setAttribute("date", dateList);
			forward.setPath("/rsv/datevalue.jsp");
		}
		
		
		return forward;
	}

}
