package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.MemberLogoutAction;
import member.action.MemberMyInfoAction;
import action.Action;
import member.action.MemberJoinProAction;
import member.action.MemberListAction;
import member.action.MemberLoginProAction;
import member.action.MemberSnsLoginProAction;
import member.action.MyOrderListAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
       
    public MemberFrontController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	String command = request.getServletPath();
    	System.out.println("command : " +command);
    	
    	if(command.equals("/MemberJoinForm.me")) {
    		forward = new ActionForward();
    		forward.setPath("/member/member_join.jsp");
    	}else if(command.equals("/MemberJoinPro.me")){
			action = new MemberJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/MemberLoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/member_login.jsp");
			
		}else if (command.equals("/MemberLoginPro.me")) {
			action = new MemberLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/Main.me")) {
			forward = new ActionForward();
			forward.setPath("/index.jsp");
		}else if (command.equals("/MemberList.me")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/MemberLogout.me")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/SnsLogin.me")) {
			action = new MemberSnsLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (command.equals("/MyInfo.me")) {
			action = new MemberMyInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/MyOrderList.me")) {
			action = new MyOrderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
