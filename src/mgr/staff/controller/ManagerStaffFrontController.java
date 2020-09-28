package mgr.staff.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mgr.staff.action.ManagerStaffJoinAction;
import vo.ActionForward;

@WebServlet("*.do")
public class ManagerStaffFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	String command = request.getServletPath();
		
    	if(command.equals("/Main.do")) {
    		forward = new ActionForward();
    		forward.setPath("/index.jsp");
    		forward.setRedirect(false);
    	}
    	
    	
		
    	
    	if(forward!=null) {
    		
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
			}
    	}
		
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
