package rsv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import rsv.action.RsvCheckProAction;
import rsv.action.RsvDateCheckProAction;
import rsv.action.RsvDeleteProAction;
import rsv.action.RsvInsertProAction;
import rsv.action.RsvListCheckProAction;
import rsv.action.RsvSubmitProAction;
import vo.ActionForward;

@WebServlet("*.rsv")
public class RSVFrontController extends HttpServlet{

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("RSVFrontController");
		String command = request.getServletPath();
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/RsvView.rsv")) {
			
			forward = new ActionForward();
			forward.setPath("/rsv/view.jsp");
			
		}else if(command.equals("/RsvSubmitProAction.rsv")) {
			action=new RsvSubmitProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/RsvResult.rsv")) {
			forward=new ActionForward();
			forward.setPath("/rsv/cal.jsp");
		}
		else if(command.equals("/RsvDate.rsv")) {
			action=new RsvDateCheckProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MyRsvList.rsv")) {
			action=new RsvListCheckProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/RsvCheck.rsv")) {
			action=new RsvCheckProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/RsvDelete.rsv")) {
			action=new RsvDeleteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/RsvInsert.rsv")) {
			action = new RsvInsertProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
			//---------------------------------------------------------------------------
		
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
