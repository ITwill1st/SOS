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

@WebServlet("*.stf")
public class ManagerStaffFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	String command = request.getServletPath();
		
    	if(command.equals("/StaffJoinForm.stf")) {
    		forward = new ActionForward();
    		forward.setPath("/mgr/staff/staffJoinForm.jsp");
    		forward.setRedirect(false);
    	}
    	
    	if (command.equals("/StaffJoinPro.stf")) {
    		action = new ManagerStaffJoinAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		
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
