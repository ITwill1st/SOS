package rsv.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

@WebServlet("*.rsv")
public class RSVFrontController extends HttpServlet{

	

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/RsvMain.rsv")) {
			
			forward = new ActionForward();
			forward.setPath("/rsv/main.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/RsvView.rsv")) {
			
			forward = new ActionForward();
			forward.setPath("/rsv/view.jsp");
			forward.setRedirect(false);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		//Redirect 방식과 Dispatch방식에 대한 포워딩을 처리하기 위한 영역
		//1.ActionForward객체가 null이 아닐때만 포워딩 작업 수행
		if(forward != null) {
			
			if(forward.isRedirect()) {
				//response객체의 sendRedirect() 메서드를 호출하여 ActionForward 객체의 저장되어 있는 URL 정보 전달
				response.sendRedirect(forward.getPath());
			}else {
				//RequestDispatcher 객체를 리턴받기 위해
				//request 객체의 getRequestDispatcher()메서드를 호출 파라미터로 ActionForward 객체에 저장되어 있는 URL 정보 전달
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
			
		}
		
		
		
		
		
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}
	
}
